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
        if (data.length > 0) {
            
            var tableHtml = '<table class="table"><thead><tr><th>ID</th><th>Date of Joining</th><th>Years of Experience</th><th>Relevant Certifications</th><th>Role</th><th>Location</th><th>Salary</th><th>Is Full Time</th><th>Actions</th></tr></thead><tbody>';
            data.forEach(function(item) {
            tableHtml += '<tr><td>' + item.eid + '</td><td>' + item.dateOfJoining + '</td><td>' + item.yearsOfExperience + '</td><td>' + item.relevantCertifications + '</td><td>' + item.role + '</td><td>' + item.location + '</td><td>' + item.salary + '</td><td>' + item.isFullTime + '</td><td><button class="btn btn-primary update-btn" data-id="' + item.eid + '">Update</button></td></tr>';
            });

            tableHtml += '</tbody></table>';
            dataContainer.html(tableHtml);

            $('.update-btn').on('click', function() {

                var itemId = $(this).data('id');
                window.location.href = "updateemployeeform.html?id=" + itemId;

            });
        } else {
            dataContainer.html('<p>No data available.</p>');
        }
    }

    fetchData();
});
document.getElementById('UpdateEmployeeDetails').addEventListener('click', function() {

    window.location.href = 'update.html';
});