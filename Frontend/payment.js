

$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();


    $('.nav-pills a').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href");
        if (target === "#credit-card") {

        } else if (target === "#paypal") {

        } else if (target === "#net-banking") {

        }
    });
});

