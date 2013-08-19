//Self contained alert box for display server status and error messages
//Replacement for the browser 'alert' box which can be plain and out of the
//Website's look and feel.

$.extend({ alert: function (title, message) {
  $("<div></div>").dialog( {
    buttons: { "Ok": function () { $(this).dialog("close"); } },
    close: function (event, ui) { $(this).remove(); },
    resizable: false,
    title: title,
    modal: true
  }).text(message);
}
});

$.extend({alertdownload: function(title, message)
{
   $('<div id="Download_Modal"></div>').dialog(
       {
            //buttons: { "Ok": function () { $(this).dialog("close"); } },
            close: function (event, ui) { $(this).remove(); },
            resizable: false,
            title: title,
            modal: true
       }).text(message);
}
});

$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
  });


$(document).ajaxStart(function()
{
    $.alertdownload('Downloading', 'Downloading data from server');

});

$(document).ajaxComplete(function()
{
    $('#Download_Modal').dialog("close");
    
});