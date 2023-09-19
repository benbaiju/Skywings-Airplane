document.addEventListener('DOMContentLoaded', function () {
  const form = document.querySelector('form');

  form.addEventListener('submit', function (event) {
    event.preventDefault();

    // Create a formObject to store all form field values
    const formObject = {
      role: form.role.value,
      location: form.location.value,
      dateOfJoining: form.dateofjoining.value, // Corrected field name
      yearsOfExperience: form.yearsofexperience.value, // Corrected field name
      salary: form.salary.value,
      isFullTime: form.isFullTime.value,
      relevantCertifications: form.relevantcertifications.value, // Corrected field name
    };

    // Define the API URL
    const apiUrl = 'http://localhost:8080/saveemployee';
    console.log('Sending data to:', apiUrl);
    console.log(formObject);
    // Send a POST request with the formObject as JSON
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
        // Redirect or perform other actions after successful submission
        // window.location.href = "payment.html";
      })
      .catch(error => {
        console.log('Error:', error);
      });
  });
});
