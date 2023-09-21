$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const selectedItemId = urlParams.get('id');
    console.log(selectedItemId);
  
    $("#updateemployeeform").submit(function (event) {
        event.preventDefault();
       
  
        //Create a formObject to store updated employee information
        // const formObject = {
        //     role: $("#role").val(),
        //     location: $("#location").val(),
        //     dateOfJoining: $("#dateOfjoining").val(),
        //     yearsOfExperience: $("#yearsOfexperience").val(),
        //     salary: parseFloat($("#salary").val()),
        //     isFullTime: $("#isFullTime").val(),
        //     relevantCertifications: $("#relevantCertifications").val(),
        // };
        
        const form = document.querySelector('form');
      const formData = new FormData(form);
      const formObject = {};
      formData.forEach((value, key) => {
        formObject[key] = value;
      })
        console.log(formObject);
  
        // Define the API URL for updating employee information
        const apiUrl = 'http://localhost:8080/updateEmployee'+'/' + selectedItemId;
  
        $.ajax({
            url: apiUrl,
            method: "PUT",
            contentType: 'application/json',
            data: JSON.stringify(formObject),
            success: function () {
                alert("Employee data updated successfully.");
                // Redirect or perform other actions after successful update
                // window.location.href = 'redirect-page.html';
            },
            error: function () {
                alert("Error updating employee data.");
            },
        });
    });
});
