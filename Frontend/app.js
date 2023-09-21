document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
  
    form.addEventListener('submit', function (event) {
      event.preventDefault();
  
      const formObject = {
        suffix: form.suffix.value,
        firstname: form.firstname.value,
        middlename: form.middlename.value,
        lastname: form.lastname.value,
        gender: form.gender.value,
        dob: form.dob.value,
        number: form.number.value,
        email: form.email.value,
        address: form.address.value,
        city: form.city.value,
        state: form.state.value,
        zip: form.zip.value,
        comments: form.comments.value,
        role: form.role.value,     
        location: form.location.value 
      };
  
      const apiUrl = 'http://localhost:8080/save';
      console.log('Sending data to:', apiUrl);
      
      fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formObject),
      })
        .then(response => response.json())
        .then(data => {
          console.log(data);
          console.log(formObject);
          window.location.href = "employeedetails.html";
        })
        .catch(error => {
          console.log('Error:', error);
        });
    });
  });
  
  
  
    