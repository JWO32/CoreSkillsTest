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
      
        <!-- jQuery and JQuery UI Imports -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script type="text/javascript" src="js/handlebars.js"/></script>

        <script id="quiz_event_template" type="text/x-handlebars-template">
            <div id="{{QuizEventId}}">
                <ul class="quiz_event">
                    <li>Quiz Title: {{QuizTitle}}</li>
                    <li>Set for Group: {{GroupName}}</li>
                    <li>Number of Questions: {{NumberOfQuestions}}</li>
                    <li>Randomize Questions: {{Randomise}}</li>
                    <li>Quiz Open Date: {{OpenDate}}</li>
                    <li>Quiz Close Date: {{CloseDate}}</li>
                    <li>Feedback to User: {{Feedback}}</li>
                </ul>      
            </div>
        </script>
        
        <script type="text/javascript">
        $(document).ready(function()
        {
            var quizSetupController = QuizSetupController();
            
            quizSetupController.downloadQuizList();
            quizSetupController.initAllDetails();
           
            
           $('#add_quiz_button').on('click', function()
           {
                quizSetupController.addQuizEvent();
           });
           
           $('input[type="datetime"]').datepicker();
           $('input[type="datetime"]').datepicker('setDate', new Date());
           $('input[type="button"]').button();
        });  
        </script>
        
        <title>Quiz Management: Set up a Quiz</title>
    </head>
    <body>
         <div id="MainContent">
            <section id="MainHeading">        
                <!-- Include page header here --> 
                <header>
                    <h1>Set Quiz Events</h1>
                </header>
            </section>
            <section id="Navigation">
                <!-- Include page navigation here -->
                <nav id="TabbedLinks">
                    <ul>
                        <li><a href="#">Home Page</a></li>
                        <li><a href="#">Create a New Quiz</a></li>
                        <li><a href="#">Manage Quizzes</a></li>
                        <li><a href="#">Manage Users</a></li>
                        <li><a href="#">Create a Quiz Event</a></li>
                    </ul>  
                </nav>
            </section>
             <section id="event_form">
                 <form id="new_event_form">
                     <fieldset>
                         <legend>Quiz Details</legend>
                         <div id="quiz_select">
                            <label>Select a Quiz</label>
                            <select id="quiz_list">
                                <option value="0">No Quiz Selected</option>
                            </select>
                         </div>
                         <div id="group_select">
                             <label>Group Details</label>
                             <select id="group_list">
                                 <option value="0">No Group Selected</option>
                             </select>
                         </div>
                         <div id="quiz_dates">
                            <label>Select an Opening Date</label>
                            <input type="datetime" id="opening_date"/>
                            <label>Select a Closing Date</label>
                            <input type="datetime" id="closing_date"/>
                         </div>
                         <div id="quiz_options">
                            <label>Randomise Questions</label>
                            <input type="checkbox" id="randomise_questions"/>
                            <label>Number of questions to include</label>
                            <input type="number" value="0" id="number_questions"/>
                            <label>Give feedback to user</label>
                            <input type="checkbox" id="feedback"/>
                         </div>
                     </fieldset>
                     <fieldset>
                         <legend>Operations</legend>
                         <input type="button" id="add_quiz_button" value="Add Quiz Event"/>
                         <input type="button" id="delete_quiz_button" value="Delete Quiz Event"/>
                         <input type="button" id="edit_quiz_button" value="Edit Quiz Event"/>
                         <input type="button" id="clear_quiz_button" value="Clear Form"/>
                     </fieldset>
                 </form>                 
             </section>
             <section id="quiz_events">
                 
             </section>
         </div>
        
        <!-- Probably redundant for this part of the app -->
        <div id="add_quiz_event_dialogue" style="display: none">
            
        </div>
    </body>

</html>