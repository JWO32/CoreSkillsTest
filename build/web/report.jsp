<jsp:include page="inc/header-inc.jsp" flush="false" />

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
                <label>Select a Group</label>
                <select id="group_list">
                    <option value="0">No Groups</option>
                </select>
                <label>Select Start Date:</label>
                <input type="text" id="start_date" />
                <label>Select End Date:</label>
                <input type="text0" id="end_date" />
                
                <input type="button" id="find_results_button" value="Find Results" />
                <input type="button" id="clear_results_button" value="Clear Results" />
                
            </fieldset>    
        </form>   
    </div>
</div>


<div id="result_wrapper">
    
    
    
</div>

<jsp:include page="inc/footer-inc.jsp" flush="false" />