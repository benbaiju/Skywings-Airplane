$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const selectedItemId = urlParams.get('id');
    console.log(selectedItemId);
  
    $("#updateemployeeform").submit(function (event) {
        event.preventDefault();
       
  
        
        const form = document.querySelector('form');
      const formData = new FormData(form);
      const formObject = {};
      formData.forEach((value, key) => {
        formObject[key] = value;
      })
        console.log(formObject);
  
        const apiUrl = 'http://localhost:8080/updateEmployee'+'/' + selectedItemId;
  
        $.ajax({
            url: apiUrl,
            method: "PUT",
            contentType: 'application/json',
            data: JSON.stringify(formObject),
            success: function () {
                alert("Employee data updated successfully.");
                
            },
            error: function () {
                alert("Error updating employee data.");
            },
        });
    });
});
