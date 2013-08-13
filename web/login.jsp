<jsp:include page="inc/header-inc.jsp" flush="false" />

<script id="quiz_events_template" type="text/x-handlebars-template">
    <div id="qe_"{{QuizEventId}}>
        <3>Quiz: {{QuizName}}</h3>
        <p>This quiz has <strong>{{NumberOfQuestions}}</strong> questions.</p>
        <p><a class="doQuizLink" href="{{QuizId}}/{{QuizEventId}}">Click here to attempt this quiz</p>
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
    <form id="login_form">
        <fieldset id="details">
            <legend>Log in to take Quiz</legend>
            <label><img src="images/icons/email.jpg"/> E-mail Address</label>
            <input id="email" type="text" />
            <input type="button" id="submit_button" value="Login to Quizzes"/>
        </fieldset>
    </form>  
</section>

<section id="quiz_event_list">
    
    
</section>