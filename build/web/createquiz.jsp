<!doctype html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<title>Create a Quiz</title>

<!-- Stylesheets -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css"/>
<link rel="stylesheet" href="css/quizcreate.css" type="text/css"/>
<link rel="stylesheet" href="css/main.css" type="text/css"/>

<!-- JavaScript Library Imports -->

<!-- jQuery and JQuery UI Imports -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript"  src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<!-- Custom Imports: TODO: Reduce for final version! -->
<script type="text/javascript" src="js/QuizManager/Customlib.js"></script>
<script type="text/javascript" src="js/QuizManager/QuizController.js"></script>
<script type="text/javascript" src="js/QuizManager/QuizModel.js"></script>
<script type="text/javascript" src="js/QuizManager/QuizView.js"></script>

<!-- Controller Class -->
<script type="text/javascript">
$(document).ready(function()
{	
	var QuestionListElement = $('#QuestionList');

	CurrentQuiz =  new Quiz(); //Declare the model
	QLM = new QuestionListManager(QuestionListElement); //Declare View Manager

	Controller = new QuizController(CurrentQuiz, QLM); // Declare the App Controller
	
	Controller.init();
	
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
	
});
</script>
</head>
<body>

<div id="mainbody">

<header>
<h1>Create a New Quiz</h1>
</header>
<section id="QuizConfiguration">
<form id="quiz_details">
<fieldset>
<legend> Quiz Details:</legend>
<div id="quiz_details_leftcolumn">
<ol>
	<li>
		<label>Title:</label>
		<input type="text" size="50" name="quiz_title"  class="text ui-widget-content ui-corner-all"></input>
	</li>
	<li>
		<label>Level:</label>
		<!-- Download these values from the server -->
		<select name="quiz_level" class="text ui-widget-content ui-corner-all">
			<option>No Level</option>
			<option>SCQF Level 3</option>
			<option>SCQF Level 4</option>
			<option>SCQF Level 5</option>
			<option>SCQF Level 6</option>
		</select>
	</li>
</ol>
</div>
<div id="quiz_details_rightcolumn">
<ol>
	<li>
		<label>Subject:</label>
		<input type="text" size="50" name="quiz_subject" class="text ui-widget-content ui-corner-all"></input>
	</li>
	<li>
		<label>Duration:</label>
		<input type="number" name="quiz_hours" value="0" class="text ui-widget-content ui-corner-all"></input> hour
		<input type="number" name="quiz_minutes" value="45" class="text ui-widget-content ui-corner-all"></input> minute
	</li>
</ol>

</div>
</fieldset>
<div id="quiz_buttons">
<input type="button" id="quiz_add_question" value="Add Question"></input>
<input type="button" id="quiz_edit_question" value="Edit Question"></input>
<input type="button" id="quiz_delete_selected_question" value="Delete Question/s"></input>
<input type="button" id="quiz_save" value="Save Quiz" value="Save Quiz"></input>
</div>
</form>
</section>

<section>
<ul id="QuestionList">


</ul>
</section>


<div id="QuestionDesigner" style="display:none">

</div>

<footer>
Copyright &copy James Oliver and Dundee University 2013
</footer>

</div>

</body>
</html>