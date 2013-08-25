<% pageContext.getSession().setAttribute("PageTitle", "Manage Quizzes"); %>
<% pageContext.getSession().setAttribute("PageHeading", "Manage Quizzes");%>

<jsp:include page="inc/header-inc.jsp" flush="false" />
<jsp:include page="inc/pageheader-inc.jsp" flush="false"/>
<jsp:include page="inc/navigation-inc.jsp" flush="false" />

<script id="quiz_template" type="text/x-handlebars-template">             
<div class="quiz_event ui-widget"> 
    <div class="select_box">          
            <input type="checkbox" id="q_{{QuizId}}"/>          
    </div>                
    <h3 class="ui-widget-header ui-corner-all">Quiz: {{QuizTitle}}</h3>
        <div class="event_details ui-widget-content">  
        <h4>Quiz Details</h4>
            <ul>
                <li>Number of Questions: {{NumberOfQuestions}}</li>
                <li>Total Marks: {{NumberOfMarks}}</li>
                <li>Result Rules Present: {{NumberOfResultRules}}</li>
            </ul> 
            <p><a href="Quiz/edit/{{QuizId}}">Click here to edit</a></p>
        </div>
</div>  
</script>

<script type="text/javascript">
$(document).ready(function ()
{
    QuizManagerController.init();
    
    $('#delete_quiz').on('click', function ()
    {
        QuizManagerController.deleteQuizEvent();
    });
    
    $('#edit_quiz').on('click', function ()
    {
        QuizManagerController.editQuizEvent();
    });
    
    $('input[type="button"]').button();
    
});
</script>

<input type="button" id="delete_quiz" value="Delete Quiz" />
<input type="button" id="edit_quiz" value="Edit Quiz" />

<div id="quiz_wrapper">
    
    
</div>

<jsp:include page="inc/footer-inc.jsp" flush="false" />