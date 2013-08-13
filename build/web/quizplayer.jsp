<!doctype html>
<html>
    <head>    
        <!-- Stylesheets -->
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css"/>
        <link rel="stylesheet" href="css/quizdelivery.css" type="text/css"/>
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
        
        <script type="text/javascript" src="js/QuizDelivery/quizmanager.js"></script>
        <script type="text/javascript" src="js/QuizDelivery/quizplayermodel.js"></script>
        <script type="text/javascript" src="js/QuizDelivery/quizplayerview.js"></script>
        <script type="text/javascript" src="js/QuizDelivery/quizresult.js"></script>
      
        <!-- jQuery and JQuery UI Imports -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
        <script type="text/javascript"  src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script type="text/javascript" src="js/handlebars.js"/></script>
        
        <script id="question_template" type="text/x-handlebars-template">           
        <div class="display_question" id="{{QuestionId}}">
            <h1>Question {{CurrentQuestion}}</h1>

            <p>{{QuestionText}}</p>
            <p>{{Mark}} points</p>

            <ul id="options">
                {{#each Options}}
                <li><input id="{{this.OptionId}}" type="checkbox"/>{{this.OptionText}}</li>
                {{/each}}
            </ul>
        </div>         
        </script>
        
        <script id="start_quiz_template" type="text/x-handlebars-template">
            <h1>{{Title}}</h1>
            <p>{{Content}}</p>
            <p>Pass Criteria: {{PassCriteria}}</p>
        </script>
            
        <script id="end_quiz_template" type="text/x-handlebars-template">
            <h1>{{Title}}</h1>
            <p>{{Content}}</p>
            <p>Please close this window when you are finished.</p>
        </script>
        
        
        <script type="text/javascript">               
            $(document).ready(function()
            {
                var QuizManager = new QuizPlayerManager();
              
                //Test data
                // Replace with JSP property to insert Quiz Details
                var QuizIntro = '{"Title":"Test Quiz", "Content":"Welcome to the test quiz", "PassCriteria":"60%"}';
                var QuizData = '{"QuizEventId":30, "UserId":1,"QuizId":1,"QuizTitle":"Core Skills ICT","QuizDuration":45,"Questions":[{"QuestionId":1,"QuestionText":"What is a mouse?","Options":[{"OptionId":5,"OptionText":"A hardware device","OptionCorrect":true},{"OptionId":2,"OptionText":"Graphics software","OptionCorrect":false},{"OptionId":3,"OptionText":"Database software","OptionCorrect":false},{"OptionId":4,"OptionText":"Spreadsheet software","OptionCorrect":false}]},{"QuestionId":2,"QuestionText":"What is a keyboard?","Options":[{"OptionId":1,"OptionText":"A hardware device","OptionCorrect":true},{"OptionId":6,"OptionText":"Graphics software","OptionCorrect":false},{"OptionId":7,"OptionText":"Database software","OptionCorrect":false},{"OptionId":8,"OptionText":"Spreadsheet software","OptionCorrect":false}]}]}';
                var QuizEnd = '{"Title":"Quiz Complete", "Content":"Congratulations, you have now completed this quiz."}';
                //
    
                QuizManager.init(QuizData, QuizIntro, QuizEnd);
                QuizManager.displayInstructions();
                
                $('#submit_button').on('click', function()
                {
                    QuizManager.endQuiz();
                    QuizManager.submitToServer();
                });
                
                // Find user's response
                // Assign response to Response object
                // Render next question
                $('#next_button').on('click', function()
                {
                    QuizManager.nextQuestionEvent();
                }); 
                
                // Find user's response
                // Assign response to response object
                // Return to previous question (from cache)
                $('#previous_button').on('click', function()
                {
                    QuizManager.previousQuestionEvent();
                });
                
                $('input[type="button"]').button();
            });          
        </script>
        
    </head>
    <body>
     
        <div id="question_wrapper">
            <div id="quiz_title">

            </div>

            <div id="timer">
                Initialising Timer
            </div>

            <div id="question">


            </div>
            <div id="progress">

            </div>
            <div id="prev_next_buttons">    
                <input type="button" id="previous_button" value="Previous Question"/>
                <input type="button" id="next_button" value="Next Question"/>
            </div>

            <div id="submit">
                 <input type="button" id="submit_button" value="Submit Answers"/>          
            </div>     
        </div>
        
        <!-- Information Dialogue - displayed at start and end of quiz -->
        <div id="info_dialogue_wrapper" style="display: none">            
            <div id="info_dialogue">

            </div>
            <div id ="info_dialogue_button">
                <input type="button" id="dialogue_continue" value="Begin"/>
            </div>
        </div>
         <div id="result_dialogue" style="display: none">
                <h1>Result</h1>
         </div>
    </body>
</html>
