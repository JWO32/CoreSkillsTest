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
            <div class="quiz_event ui-widget"> 
                <div class="select_box">          
                        <input type="checkbox" id="qe_{{QuizEventId}}"/>          
                </div>                
                <h3 class="ui-widget-header ui-corner-all">Quiz Event: {{QuizName}}</h3>
                    <div class="event_details ui-widget-content">  
                    Open From {{OpenDate}} to {{CloseDate}}
                    <h4>Quiz Options:</h4>
                        <ul>
                            <li><strong>Group: {{GroupName}}</strong></li>
                            <li>Number of Questions: {{NumberOfQuestions}}</li>
                            <li>Randomize Questions: {{RandomQuestions}}</li>
                            <li>Feedback to User: {{Feedback}}</li>
                        </ul> 
                    </div>
            </div>
        </script>
        
        <script type="text/javascript">
        $(document).ready(function()
        {
            var controller = QuizSetupController();

            controller.downloadQuizList();
            controller.initAllDetails();
          
            $('#add_quiz_button').on('click', function()
            {
                 controller.addQuizEvent();
            });

            $('#delete_quiz_button').on('click', function()
            {
                 controller.deleteQuizEvent();
            });

            $('#edit_quiz_button').on('click', function()
            {
                 controller.editQuizEvent();
            });

            $('clear_quiz_button').on('click', function()
            {
                controller.clearForm();
            });

            $('#number_questions').on('change', function()
            {
                controller.checkNumberOfQuestionsEvent();
            });

            $('#quiz_list').on('change', function()
            {
                controller.changeQuizEvent();
            });

            //Configure datepicker and date representation options
            $('input[type="datetime"]').datepicker();
            $('input[type="datetime"]').datepicker('setDate', new Date());
            $('input[type="datetime"]').datepicker('option', 'dateFormat', 'dd/mm/yy');
            $('input [type="text"]').addClass('ui-widget ui-widget-content ui-widget-header ui-corner-all');
            $('input [type="checkbox"]').button();

            $('input[type="button"]').button();
        });  
        </script>
        
        <title>Quiz Management: Set up a Quiz</title>
    </head>
    <body>
         <div id="MainContent">
            <section>        
                <!-- Include page header here --> 
                <header id="MainHeading">
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
                            <div id="quiz_group_select">
                                <legend>Quiz and Group:</legend>
                                <ol>
                                    <li>
                                        <label>Select a Quiz</label>
                                    </li>
                                    <li>
                                        <select id="quiz_list">
                                            <option value="0">Downloading Quizzes</option>
                                        </select>
                                    </li>
                               </ol>
                                <ol>
                                    <li>
                                        <label>Group Details</label>
                                    </li>
                                    <li>
                                        <select id="group_list">
                                            <option value="0">Downloading Groups</option>
                                        </select>
                                    </li> 
                                </ol>
                            </div>
                            <div id="quiz_dates">
                                <legend>Select Dates:</legend>
                                <ol>
                                    <li>
                                        <label>Select an Opening Date</label>
                                    </li>
                                    <li>
                                        <input type="datetime" id="opening_date"/>
                                    </li>
                                    <li>
                                        <label>Select a Closing Date</label>
                                    </li>
                                    <li>
                                        <input type="datetime" id="closing_date"/>
                                    </li>
                                </ol>
                            </div>
                            <div id="quiz_options">
                                <legend>Event Options: </legend>
                                <ol>                                 
                                    <li>
                                        <label>Randomise Questions:</label>
                                        <input type="checkbox" id="randomise_questions"/>
                                    </li>
                                    <li>
                                        <label>Give feedback to user:</label>
                                        <input type="checkbox" id="feedback"/>
                                    </li>
                                    <li>
                                        <label>Number of questions to include</label>
                                        <input type="number" value="0" id="number_questions"/>
                                    </li>                              
                                </ol>
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