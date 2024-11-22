async function fetchStreets() {
    const streetname = document.getElementById('streetname').value;
    const plz = document.getElementById('plz').value;
    const locality = document.getElementById('locality').value;

    let queryParams = new URLSearchParams();
    if (streetname) queryParams.append('name', streetname);
    if (plz) queryParams.append('plz', plz);
    if (locality) queryParams.append('locality', locality);

        try {
            const response = await fetch(`/api/OpenPLZ/streets?${queryParams.toString()}`);
            const data = await response.json();

            const dropdown = document.getElementById('streetDropdown');
            dropdown.innerHTML = '<option value="">Select a street...</option>'; // Clear the dropdown

            if (data && Array.isArray(data)) {
                data.forEach(street => {
                    const option = document.createElement('option');
                    option.value = street.outputStreet;
                    option.textContent = `${street.outputStreet} (${street.outputLocality})`;
                    dropdown.appendChild(option);
                });
            }
        } catch (error) {
            console.error('Error fetching streets:', error);
        }
    } else {
        const dropdown = document.getElementById('streetDropdown');
        dropdown.innerHTML = '<option value="">Select a street...</option>';
}

setInterval(fetchStreets, 2000);
