<html>
    <head>    
        <!-- Stylesheets -->
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css"/>
        <link rel="stylesheet" href="css/quizcreate.css" type="text/css"/>
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
        
        <script type="text/javascript" src="js/QuizDelivery/quizmanager.js"></script>
        <script type="text/javascript" src="js/QuizDelivery/quizplayermodel.js"></script>
        <script type="text/javascript" src="js/QuizDelivery/quizplayerview.js"></script>
        <script type="text/javascript" src="js/QuizDelivery/quizresult.js"></script>
      
        <!-- jQuery and JQuery UI Imports -->
        <!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>-->
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
            <p>{{PassCriteria}}</p>
        </script>
            
        <script id="end_quiz_template" type="text/x-handlebars-template">
            <h1>{{Title}}</h1>
            <p>{{Content}}</p>
        </script>
        
        
        <script type="text/javascript">               
            $(document).ready(function()
            {
                var QuizManager = new QuizPlayerManager();
                
                //Test data
                //
                var QuizIntro = '{"Title":"Test Quiz", "Content":"Welcome to the test quiz", "PassCriteria":"60%"}';
                var QuizData = '{"QuizId":0,"QuizTitle":"Core Skills ICT","QuizDuration":45,"Questions":[{"QuestionId":4,"QuestionText":"What is a mouse?","Options":[{"OptionId":7,"OptionText":"A hardware device","OptionCorrect":true},{"OptionId":8,"OptionText":"Graphics software","OptionCorrect":false},{"OptionId":9,"OptionText":"Database software","OptionCorrect":false},{"OptionId":10,"OptionText":"Spreadsheet software","OptionCorrect":false}]},{"QuestionId":5,"QuestionText":"What is a keyboard?","Options":[{"OptionId":11,"OptionText":"A hardware device","OptionCorrect":true},{"OptionId":12,"OptionText":"Graphics software","OptionCorrect":false},{"OptionId":13,"OptionText":"Database software","OptionCorrect":false},{"OptionId":14,"OptionText":"Spreadsheet software","OptionCorrect":false}]}]}';
                var QuizEnd = '{"Title":"Quiz Complete", "Content":"You have now completed this quiz. Once you have seen your result, please close this window"}';
                //
    
                QuizManager.init(QuizData, QuizIntro, QuizEnd);
                QuizManager.displayInstructions();
                
                $('#submit_button').on('click', function()
                {
                    QuizManager.endQuiz();
                    QuizManager.compileResponses();
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
        <div id="QuizHeader"> 
            
        </div>
        
        <div id="question_wrapper">
            <div id="quiz_title">

            </div>

            <div id="timer">


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
        
        <!-- Modal Start Dialogue -->
        <div id="info_dialogue" style="display: none">
 
        </div>
        
        <div id="QuizFooter">
            
        </div>
    </body>
</html>
