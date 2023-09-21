
$(document).ready(function() {
    var apiUrl = 'http://localhost:8080/viewData';

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
            var tableHtml = '<table class="table"><thead><tr>';
            
            // Define an array with the field names
            var fields = [
                'id', 'suffix', 'firstname', 'middlename', 'lastname', 'gender',
                // 'dob', 'number', 'email', 'address', 'city', 'state', 'zip',
                //'role', 'location', 
                //'comments', 
                'dateOfJoining', 'yearsOfExperience',
                'relevantCertifications', 'employeeRole', 'employeeLocation', 'salary', 'isFullTime'
            ];
        
            // Dynamically generate table headers based on field names
            fields.forEach(function(field) {
                tableHtml += '<th>' + field + '</th>';
            });
            
            tableHtml += '</tr></thead><tbody>';
            
            data.forEach(function(item) {
                tableHtml += '<tr>';
                
                // Dynamically generate table data based on field names
                fields.forEach(function(field) {
                    tableHtml += '<td>' + item[field] + '</td>';
                });
                
                tableHtml += '</tr>';
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


            // Create a data URI for the CSV content
            var encodedUri = encodeURI(csvContent);

            // Create a hidden anchor element to trigger the download
            var link = document.createElement('a');
            link.setAttribute('href', encodedUri);
            link.setAttribute('download', 'data.csv');
            document.body.appendChild(link);

            // Trigger the click event to initiate the download
            link.click();

            // Remove the anchor element
            document.body.removeChild(link);
        }
    }
});
