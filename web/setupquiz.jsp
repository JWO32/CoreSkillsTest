<% pageContext.getSession().setAttribute("PageTitle", "Manage Quiz Events"); %>
<% pageContext.getSession().setAttribute("PageHeading", "Manage Quiz Events");%>

<jsp:include page="inc/header-inc.jsp" flush="false" />



<jsp:include page="inc/pageheader-inc.jsp" flush="false"/>
<jsp:include page="inc/navigation-inc.jsp" flush="false" />

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
    $('input [type="text"]').addClass('text ui-widget ui-widget-content ui-corner-all');
    $('input [type="checkbox"]').button();

    $('input[type="button"]').button();
});  
</script>
        
    <section id="event_form">
        <form id="new_event_form">
            <fieldset>
                <legend>Quiz Details</legend>
                   <div id="quiz_group_select">
                       <legend>Quiz and Group:</legend>
                       <ol>
                           <li>
                               <label>Select a Quiz:</label>
                           </li>
                           <li>
                               <select id="quiz_list">
                                   <option value="0">Downloading Quizzes</option>
                               </select>
                           </li>
                      </ol>
                       <ol>
                           <li>
                               <label>Select a Group:</label>
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
                               <input type="number" value="1" id="number_questions"/>
                           </li>                              
                       </ol>
                   </div>
               </fieldset>
        </form>
            <div id="buttons">   
                <input type="button" id="add_quiz_button" value="Add Quiz Event"/>
                <input type="button" id="delete_quiz_button" value="Delete Quiz Event"/>
                <input type="button" id="edit_quiz_button" value="Edit Quiz Event"/>
                <input type="button" id="clear_quiz_button" value="Clear Form"/>
            </div>
        </section>

        <section id="quiz_events">

        </section>
        
<jsp:include page="inc/footer-inc.jsp" flush="false" />