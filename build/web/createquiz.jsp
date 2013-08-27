<% pageContext.getSession().setAttribute("PageTitle", "Quiz Creator"); %>
<% pageContext.getSession().setAttribute("PageHeading", "Create/Edit Quiz");%>


<%
    String quizJSON = (String) pageContext.getSession().getAttribute("Quiz");
    //Reset the session
    if(quizJSON != null)
        pageContext.getSession().setAttribute("Quiz", null);
%>
<jsp:include page="inc/header-inc.jsp" flush="false" />
<jsp:include page="inc/pageheader-inc.jsp" flush="false"/>
<jsp:include page="inc/navigation-inc.jsp" flush="false" />

<!-- Controller -->
<script type="text/javascript">
$(document).ready(function()
{
        var QLM = new QuestionListManager(QuestionListElement); //Declare View Manager
        
    <%
        if(quizJSON != null)
        {
            %>
        var editQuiz = <%=quizJSON%>;
        <%
        }
        else
        {%>
        var editQuiz = null;
        <%
        }
        %>
    var QuestionListElement = $('#QuestionList');
    
    if(editQuiz !== null)
    {
        CurrentQuiz = new Quiz(editQuiz.QuizTitle, editQuiz.Duration, editQuiz.QuizSubject, editQuiz.QuizLevel);
       
        for(var i = 0; i < editQuiz.Questions.length; i++)
        {
            CurrentQuiz.addQuestion(editQuiz.Questions[i]);
        }
        
        // This is important -- if the quizId is set must make sure the edit
        // action is called.
        CurrentQuiz.QuizId = editQuiz.QuizId;
        
        CurrentQuiz.setEditing(true);
    
    }else
    {
        CurrentQuiz =  new Quiz(); //Declare the model
    }


    var Controller = new QuizController(CurrentQuiz, QLM); // Declare the App Controller


    /*
    * Bind events to controller functions
    */
    $('#quiz_add_question').on('click', function()
    {
            Controller.addQuestionEvent(Controller.triggerAddQuestionCallback);
    });

    $('#quiz_delete_selected_question').on('click', function()
    {
            Controller.deleteQuestionEvent();
    });

    $('#quiz_edit_question').on('click', function()
    {
            Controller.editQuestionEvent(Controller.triggerEditQuestionCallback);
    });

    $('#quiz_save').on('click', function()
    {
        Controller.saveQuizEvent();
    });

    $('#add_quiz_rule').on('click', function()
    {
       Controller.addRuleEvent(); 

    });

    $('#QuestionList').sortable({
            update: function(event, ui)
            {
                    var listArrangement = $(this).sortable('toArray');
                    Controller.updateOrderEvent(listArrangement);
            },
            handle: ".handle"
    }).selectable().find("li.q");


    // Set buttons and other widgets to have appropriate jQuery UI Styling
    $('input[type="button"]').button();
    
    Controller.init();
	
});
</script>
<section id="QuizConfiguration">
<form id="quiz_details">
<fieldset>
<legend> Quiz Details:</legend>
<div id="quiz_details_leftcolumn">
<ul>
	<li>
		<label>Title:</label>
		<input type="text" size="50" name="quiz_title"  id="quiz_title" class="text ui-widget-content ui-corner-all"></input>
	</li>
        <li>
		<label>Quiz Category:</label>
		<!-- Download these values from the server -->
		<select name="quiz_level" id="quiz_level" class="text ui-widget-content ui-corner-all">
			<option>No Level</option>
			<option>SCQF Level 3</option>
			<option>SCQF Level 4</option>
			<option>SCQF Level 5</option>
			<option>SCQF Level 6</option>
		</select>
	</li>
</ul>
</div>
<div id="quiz_details_rightcolumn">
<ul>
	<li>
		<label>Subject:</label>
        </li>
        <li>
		<input type="text" size="50" name="quiz_subject" id="quiz_subject" class="text ui-widget-content ui-corner-all"></input>
	</li>
	<li>
		<label>Duration:</label>
        </li>
        <li>
		<input type="number" id="quiz_hours" name="quiz_hours" value="0" class="text ui-widget-content ui-corner-all"></input> hour
		<input type="number" id="quiz_minutes" name="quiz_minutes" value="45" class="text ui-widget-content ui-corner-all"></input> minute/s
	</li>
</ul>
    
<input type="button" id="add_quiz_rule" value="Add New Rule"></input>

</div>
<div id="quiz_buttons">
<input type="button" id="quiz_add_question" value="Add Question"></input>
<input type="button" id="quiz_edit_question" value="Edit Question"></input>
<input type="button" id="quiz_delete_selected_question" value="Delete Question/s"></input>
<input type="button" id="quiz_save" value="Save Quiz" value="Save Quiz"></input>
</div>

</fieldset>

</form>
</section>

<section id="QuestionListWrapper">
<ul id="QuestionList">


</ul>
</section>


<div id="QuestionDesigner" style="display:none">

</div>

<div id="ResultRuleDialog" style="display:none">
    
    
</div>

<jsp:include page="inc/footer-inc.jsp" flush="false" />