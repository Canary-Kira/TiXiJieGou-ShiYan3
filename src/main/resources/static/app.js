document.getElementById('contactForm').addEventListener('submit', function(e) {
    e.preventDefault();
    addContact();
});
function addContact() {
    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;
    const phone = document.getElementById('phone').value;

    fetch('/api/contacts/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, address, phone }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                loadContacts();
            } else {
                console.error('添加联系人失败');
            }
        })
        .catch(error => console.error('Error:', error));
}

function loadContacts() {
    fetch('/api/contacts/contacts')
        .then(response => response.json())
        .then(data => {
            const table = document.getElementById('contactsTable').getElementsByTagName('tbody')[0];
            table.innerHTML = '';
            data.forEach(contact => {
                const row = table.insertRow();
                row.insertCell(0).textContent = contact.name;
                row.insertCell(1).textContent = contact.address;
                row.insertCell(2).textContent = contact.phone;
                const deleteCell = row.insertCell(3);
                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Delete';
                deleteButton.onclick = function() { deleteContact(contact.id); };
                deleteCell.appendChild(deleteButton);
            });
        })
        .catch(error => console.error('Error:', error));
}

function deleteContact(id) {
    fetch('/api/contacts/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id }),
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                loadContacts();
            } else {
                // Handle error if the contact was not deleted successfully
                console.error('Failed to delete contact');
            }
        })
        .catch(error => console.error('Error:', error));
}

loadContacts();