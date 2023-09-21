$(document).ready(function() {
    var API_URL = 'http://localhost:8080/employeeusers'; 


    function fetchData() {
        $.ajax({
            url: API_URL,
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                displayData(data);
            },
            error: function(error) {
                console.error('Error fetching data:', error);
            }
        });
    }

    
    function displayData(data) {
        var dataContainer = $('#dataContainer');
        // if (data.length > 0) {
        //     var tableHtml = '<table class="table"><thead><tr><th>ID</th><th>Suffix</th><th>First Name</th><th>Middle Name</th><th>Last Name</th><th>Gender</th><th>Date of Birth</th><th>Phone Number</th><th>Email</th><th>Address</th><th>City</th><th>State</th><th>Zip</th><th>role</th><th>location</th><th>Comments</th><th>Actions</th></tr></thead><tbody>';
        //     data.forEach(function(item) {
        //         tableHtml += '<tr><td>' + item.id + '</td><td>' + item.suffix + '</td><td>' + item.firstname + '</td><td>' + item.middlename + '</td><td>' + item.lastname + '</td><td>' + item.gender + '</td><td>' + item.dob + '</td><td>' + item.number + '</td><td>' + item.email + '</td><td>' + item.address + '</td><td>' + item.city + '</td><td>' + item.state + '</td><td>' + item.zip + '</td><td>' + item.role + '</td><td>' + item.location + '</td><td>' + item.comments + '</td><td><button class="btn btn-danger delete-btn" data-id="' + item.id + '">Delete</button></td></tr>';
        //     });
        if (data.length > 0) {
            var tableHtml = '<table class="table"><thead><tr><th>ID</th><th>Date of Joining</th><th>Years of Experience</th><th>Relevant Certifications</th><th>Role</th><th>Location</th><th>Salary</th><th>Is Full Time</th><th>Actions</th></tr></thead><tbody>';
            data.forEach(function(item) {
                tableHtml += '<tr><td>' + item.eid + '</td><td>' + item.dateOfJoining + '</td><td>' + item.yearsOfExperience + '</td><td>' + item.relevantCertifications + '</td><td>' + item.role + '</td><td>' + item.location + '</td><td>' + item.salary + '</td><td>' + item.isFullTime + '</td><td><button class="btn btn-danger delete-btn" data-id="' + item.eid + '">Delete</button></td></tr>';
            });
        
        
            tableHtml += '</tbody></table>';
            dataContainer.html(tableHtml);

            $('.delete-btn').on('click', function() {
                var itemId = $(this).data('id');
                deleteItem(itemId);
            });
        } else {
            dataContainer.html('<p>No data available.</p>');
        }
    }


    function deleteItem(itemId) {
        var deleteUrl = 'http://localhost:8080/delete' + '/' + itemId;
        $.ajax({
            url: deleteUrl,
            method: 'DELETE',
            success: function(response) {
                console.log('Item deleted:', response);
  
                fetchData();
            },
            error: function(error) {
                console.error('Error deleting item:', error);
            }
        });
    }

 
    fetchData();
});

document.getElementById('EmployeePersonalDetails').addEventListener('click', function() {

    window.location.href = 'delete.html';
});
