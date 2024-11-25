const streetInput = document.getElementById("streetname");
const plzInput = document.getElementById("plz");
const localityInput = document.getElementById("locality");
const dropdown = document.getElementById("streetDropdown");

class Script {
    constructor() {
        this.data = [];
        this.isDatalistSelection = false;
    }

    async fetchAndUpdateDataList() {
        try {
            const name = streetInput.value.trim();
            const plz = plzInput.value.trim();
            const locality = localityInput.value.trim();

            if (!name && !plz && !locality) {
                dropdown.innerHTML = '<option value="">Enter values to search...</option>';
                return;
            }

            const url = `/api/OpenPLZ/streets?name=${encodeURIComponent(name)}&plz=${encodeURIComponent(plz)}&locality=${encodeURIComponent(locality)}`;

            const response = await fetch(url);
            const datalist = document.getElementById('suggestions');

            if (response.ok) {
                try {
                    datalist.innerHTML = '';
                    this.data = await response.json();

                    this.data.forEach((item) => {
                        const option = document.createElement('option');
                        option.value = item.outputStreet;
                        option.textContent = `${item.outputStreet} (${item.outputPostalCode} ${item.outputLocality})`;
                        datalist.appendChild(option);
                    });
                    console.log(this.data); // Debug
                } catch (jsonError) {
                    console.error("Failed to parse the response JSON:", jsonError);
                    dropdown.innerHTML = '<option value="">Invalid data received. Please try again.</option>';
                }
            } else {
                console.error(`Failed to fetch data: ${response.status} - ${response.statusText}`);
                dropdown.innerHTML = `<option value="">Error: ${response.status} - ${response.statusText}</option>`;
            }
        } catch (networkError) {
            console.error("Network error occurred while fetching data:", networkError);
            dropdown.innerHTML = '<option value="">Failed to fetch data. Please check your connection.</option>';
        }
    }

    addOtherInformation(selectedValue) {
        const streetField = document.getElementById('streetname');
        const localityField = document.getElementById('locality');
        const plzField = document.getElementById('plz');

        if (this.data.length > 0 && this.isDatalistSelection) {
            const firstItem = this.data[0];

            if (firstItem) {
                const {outputStreet, outputLocality, outputPostalCode} = firstItem;

                if (streetField.value !== outputStreet) {
                    streetField.value = `${outputStreet}`;
                }
                if (localityField.value !== outputLocality) {
                    localityField.value = `${outputLocality}`;
                }
                if (plzField.value !== outputPostalCode) {
                    plzField.value = `${outputPostalCode}`;
                }
                this.isDatalistSelection = false;
            } else {
                console.warn("The first item in this.data is undefined or empty.");
            }
        } else {
            console.warn("No valid data found for the selected street.");
        }
    }

    monitorUserInput(){
        const streetField = document.getElementById('streetname');

        streetField.addEventListener('input', () => {
            this.isDatalistSelection = false;
        });

        streetField.addEventListener('change', () => {
            this.isDatalistSelection = true;
            const selectedValue = streetField.value;
            this.addLocality(selectedValue);
        });
    }
}

const scriptInstance = new Script();

function fetchAndUpdateDataList() {
    scriptInstance.fetchAndUpdateDataList();
}

function addOtherInformation() {
    scriptInstance.addOtherInformation();
}

scriptInstance.monitorUserInput();
