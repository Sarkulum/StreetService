const streetInput = document.getElementById("streetname");
const plzInput = document.getElementById("plz");
const localityInput = document.getElementById("locality");
const dropdown = document.getElementById("streetDropdown");

class Script {
    constructor() {
        this.data = [];
    }

    async fetchAndUpdateDropdown() {
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
                        option.textContent = `${item.outputStreet} (${item.outputLocality})`;
                        datalist.appendChild(option);
                    });
                    console.log(this.data); //Debug
                } catch (jsonError) {
                    dropdown.innerHTML = '<option value="">Invalid data received</option>';
                }
            } else {
                dropdown.innerHTML = `<option value="">Error: ${response.status} - ${response.statusText}</option>`;
            }
        } catch (networkError) {
            dropdown.innerHTML = '<option value="">Failed to fetch data</option>';
        }
    }

    addLocality() {
        const streetField = document.getElementById('streetname')
        const localityField = document.getElementById('locality');
        const plzField = document.getElementById('plz');

        if (this.data.length > 0) {
            const { outputStreet, outputLocality, outputPostalCode } = this.data[0];
            localityField.value = `${outputLocality}`;
            plzField.value = `${outputPostalCode}`
        } else {
            localityField.value = '';
            plzField.value = '';
        }
    }
}

const scriptInstance = new Script();

function fetchAndUpdateDropdown() {
    scriptInstance.fetchAndUpdateDropdown();
}

function addLocality() {
    scriptInstance.addLocality();
}
