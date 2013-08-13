LoginController = function()
{
    var View = new LoginView();
    
    return{
        loginEvent: function ()
        {
          var emailAddress = View.getEmailAddress();
          
          if(emailAddress !== null)
              this.ajaxSendLogin(emailAddress);
        },
        ajaxSendLogin: function (emailAddress)
        {   
            $.ajax({
                url:'Dispatcher/getevents',
                method:'GET',
                dataType: 'json',
                data: 'email='+emailAddress,
                success: function ()
                {
                    
                },
                error: function ()
                {
                    
                    
                }
            });
        }
        
    };
};

LoginView = function ()
{
    return{
      getEmailAddress: function()
      {
         return $('#email').val(); 
      }
    };
};