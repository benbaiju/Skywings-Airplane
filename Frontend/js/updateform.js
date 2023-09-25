$(document).ready(function () {

    const urlParams = new URLSearchParams(window.location.search);
    const selectedItemId = urlParams.get('id');
    console.log(selectedItemId);
    
  
    
    $("#updateform").submit(function (event) {
      event.preventDefault();
  
      const form = document.querySelector('form');
      const formData = new FormData(form);
      const formObject = {};
      formData.forEach((value, key) => {
        formObject[key] = value;
      });
      
  
      $.ajax({
        url: 'http://localhost:8080/update' + '/' + selectedItemId,
        method: "PUT",
        contentType: 'application/json', 
        data: JSON.stringify(formObject),
        success: function () {
          alert("User data updated successfully.");
        },
        error: function () {
          alert("Error updating user data.");
        },
      });
    });
  });
  