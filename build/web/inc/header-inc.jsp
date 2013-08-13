<!DOCTYPE html>
<html>
    <head>    
        <!-- Stylesheets -->
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css"/>
        <link rel="stylesheet" href="css/setupquiz.css" type="text/css"/>
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
        
        <script type="text/javascript" src="js/SetupQuiz/setupquizcontroller.js"></script>
        <script type="text/javascript" src="js/SetupQuiz/setupquizmodel.js"></script>
        <script type="text/javascript" src="js/SetupQuiz/setupquizview.js"></script>
      
        <script type="text/javascript" src="js/Login/logincontroller.js"></script>
        <script type="text/javascript" src="js/Login/loginview.js"></script>
        
        
        <!-- jQuery and JQuery UI Imports -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script type="text/javascript" src="js/handlebars.js"/></script>
    
    <script type="text/javascript">
            $(document).ready(function()
            {

               var Controller = new LoginController();

               $('#submit_button').on('click', function (){
                  Controller.loginEvent(); 
               });

               $('input[type="button"]').button();
            });
        </script>
        <title>Quiz Management: Set up a Quiz</title>
    </head>