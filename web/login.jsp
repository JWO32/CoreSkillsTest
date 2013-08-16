<jsp:include page="inc/header-inc.jsp" flush="false" />

<script id="quiz_events_template" type="text/x-handlebars-template">
    <div id="qe_"{{QuizEventId}}>
        <h3>Quiz: {{QuizName}}</h3>
        <p>This quiz has <strong>{{NumberOfQuestions}}</strong> questions.</p>
        <p>You <strong>{{Feedback}}</strong> receive feedback when you have completed this quiz.</p>
        <p><a class="doQuizLink" target="_blank" href="Dispatcher/doquiz/{{UserId}}/{{QuizEventId}}">Click here to attempt this quiz.</p>
    </div>

</script>
    

<script type="text/javascript">
    $(document).ready(function()
    {
       var Controller = new LoginController();

       $('#submit_button').on('click', function (){
          Controller.loginEvent(); 
       });
       
       $()

       $('input[type="button"]').button();
    });
</script>


<section id="login_details">
    <h1>Quiz Login</h1>
    <p>Please enter your e-mail address into the text box below and click the 'Find Quiz Sessions' button to see if you have any quizzes
        available to sit.</p>
    <form id="login_form">
        <fieldset id="details">
            <legend>Enter your E-mail Address</legend>
            <label><img src="images/icons/email.jpg"/> E-mail Address:</label>
            <input id="email" type="text" />
            <input type="button" id="submit_button" value="Find Quiz Sessions"/>
        </fieldset>
    </form>  
</section>

<section id="quiz_event_list">
    
    
</section>