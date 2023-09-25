document.addEventListener('DOMContentLoaded', function () {
  const form = document.querySelector('form');

  form.addEventListener('submit', function (event) {
    event.preventDefault();

    const formObject = {
      role: form.role.value,
      location: form.location.value,
      dateOfJoining: form.dateofjoining.value, 
      yearsOfExperience: form.yearsofexperience.value, 
      salary: form.salary.value,
      isFullTime: form.isFullTime.value,
      relevantCertifications: form.relevantcertifications.value, 
    };
    alert('Details entered and send to Database');

  
    const apiUrl = 'http://localhost:8080/saveemployee';
    console.log('Sending data to:', apiUrl);
    console.log(formObject);

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
      
      })
      .catch(error => {
        console.log('Error:', error);
      });
  });
});
