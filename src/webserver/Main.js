$(document).ready(function(event)
{
    $('#Login').submit(function(event)
    {
        data = $('#Login').serializeArray();
        event.preventDefault();
        var tempUser = {
            username : data[0]['value'],
            password : data[1]['value']
        };
       BrugerGodkendelse(tempUser);

        return false;
    });
});

var formData = JSON.stringify($("#myLoginForm").serializeArray());

function BrugerGodkendelse(tempUser)
{
    $.ajax({
        type: 'GET',
        url: 'Galgeleg/Klient/Index',
        dataType: 'json',
        async: false,
        converters: {
            'text json': true
        },
        success: function(response)
        {
            // Her skal den sende tempUser json objektet til galgelogik.brugerGodkendelse()

        },
        error: function (jqXHR, textStatus, errorThrown)
        {
            alert("Could not receive the role: " + textStatus);
        }
    });
}