<% pageContext.getSession().setAttribute("PageTitle", "Generate Result Report"); %>

<jsp:include page="inc/header-inc.jsp" flush="false" />

<script type="text/javascript">
    $(document).ready(function()
    {
        ReportManager.init();
        
        $('#find_results_button').on('click', function()
        {
            ReportManager.downloadReportEvent();
        });
        
        $('#clear_results_button').on('click', function()
        {
        
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

<script id="result_template" type="text/x-handlebars-template">
<div id="r_{{GroupId}}">
<ul>
    <li><strong>Group Name:</strong>{{GroupName}}</li>
    {{#each user}}
    <ul>
        <li><strong>Name:</strong>{{FirstName}} {{LastName}}</li>
        {{#each result}}
            <ul>
                <li>Quiz: {{QuizName}}</li>
                <li>Marks: {{Score}}</li>
                <li>Percentage: {{Percentage}}</li>
                <li>Category: {{Feedback}}</li>
            </ul>
        {{/each}}
     </ul>
     {{/each}}
</ul>

</div>    
</script>

<div id="MainContent">
    <div id="report_options_wrapper">
        <form id="report_options">
            <fieldset>
                <legend>Choose Report Options</legend>
                <label>Select a Group:</label>
                <select id="group_list">
                    <option value="0">No Groups</option>
                </select>
                <label>Select a Quiz:</label>
                <select id="quiz_list">
                    <option value="0">No Quiz</option>
                </select>
                <label>Select Start Date:</label>
                <input type="datetime" id="start_date" />
                <label>Select End Date:</label>
                <input type="datetime" id="end_date" />
                
                <input type="button" id="find_results_button" value="Find Results" />
                <input type="button" id="clear_results_button" value="Clear Results" />
                
            </fieldset>    
        </form>   
    </div>
    
    <div id="result_wrapper">
    
    
    
    </div>
</div>


<jsp:include page="inc/footer-inc.jsp" flush="false" />