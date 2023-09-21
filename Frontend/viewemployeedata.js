
$(document).ready(function() {
    var apiUrl = 'http://localhost:8080/employeeusers';

    $.ajax({
        url: apiUrl,
        method: 'GET',
        dataType: 'json',
        success: function(data) {
            displayData(data);
        },
        error: function(error) {
            console.error('Error fetching data:', error);
        }
    });

    function displayData(data) {
        var dataContainer = $('#dataContainer');

if (data.length > 0) {
    var tableHtml = '<table class="table"><thead><tr><th>ID</th><th>Date of Joining</th><th>Years of Experience</th><th>Relevant Certifications</th><th>Role</th><th>Location</th><th>Salary</th><th>Is Full Time</th></tr></thead><tbody>';
    data.forEach(function (item) {
        tableHtml += '<tr><td>' + item.eid + '</td><td>' + item.dateOfJoining + '</td><td>' + item.yearsOfExperience + '</td><td>' + item.relevantCertifications + '</td><td>' + item.role + '</td><td>' + item.location + '</td><td>' + item.salary + '</td><td>' + item.isFullTime + '</td></tr>';
    });
    tableHtml += '</tbody></table>';


            dataContainer.html(tableHtml);
        } else {
            dataContainer.html('<p>No data available.</p>');
        }

        $('#downloadCsv').click(function() {
            downloadCsv(data);
        });
    }


    function downloadCsv(data) {
        if (data.length > 0) {
            var csvContent = 'data:text/csv;charset=utf-8,';
           
            csvContent += 'ID,Suffix,First Name,Middle Name,Last Name,Gender,Date of Birth,Phone Number,Email,Address,City,State,Zip,role,location,Comments\n';
data.forEach(function(item) {
    csvContent += item.id + ',' + item.suffix + ',' + item.firstname + ',' + item.middlename + ',' + item.lastname + ',' + item.gender + ',' + item.dob + ',' + item.number + ',' + item.email + ',' + item.address + ',' + item.city + ',' + item.state + ',' + item.zip + ',' + item.role + ',' + item.location + ',' + item.comments + '\n';
});


            
            var encodedUri = encodeURI(csvContent);

            
            var link = document.createElement('a');
            link.setAttribute('href', encodedUri);
            link.setAttribute('download', 'data.csv');
            document.body.appendChild(link);

            link.click();

          
            document.body.removeChild(link);
        }
    }
    document.getElementById('ViewPersonalDetails').addEventListener('click', function() {

        window.location.href = 'view.html';
    });
});
