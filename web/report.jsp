<% pageContext.getSession().setAttribute("PageTitle", "Generate Result Report"); %>
<% pageContext.getSession().setAttribute("PageHeading", "Quiz Reporting");%>

<jsp:include page="inc/header-inc.jsp" flush="false" />
<jsp:include page="inc/pageheader-inc.jsp" flush="false"/>
<jsp:include page="inc/navigation-inc.jsp" flush="false" />

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
            ReportManager.clearCurrentReportEvent();
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
<div id="r_{{ResultId}}">
<ul>
    <li><strong>Name:</strong>{{FirstName}} {{LastName}}</li>
        <ul>
            <li>Date: {{ResultDate}}</li>
            <li>Marks: {{Score}}</li>
            <li>Percentage: {{Percentage}}%</li>
            <li>Category: {{Category}}</li>
        </ul>
</ul>

</div>    
</script>

    <div id="report_options_wrapper">
        <form id="report_options">
            <fieldset>
                <legend>Choose Report Options</legend>
                <div id="report_details">
                    <ul>
                        <li><label>Select a Group:</label></li>
                        <li>
                            <select id="group_list">
                                <option value="0">No Groups</option>
                            </select>
                        </li>
                        <li>
                            <label>Select a Quiz:</label>
                        </li>
                        <li>
                            <select id="quiz_list">
                                <option value="0">No Quiz</option>
                            </select>
                        </li>
                    </ul>
                </div>
                <div id="report_dates">
                    <ul>
                        <li>
                            <label>Select Start Date:</label>
                        </li>
                        <li>
                            <input type="datetime" id="start_date" />
                        </li>
                        <li>
                            <label>Select End Date:</label>
                        </li>
                        <li>
                            <input type="datetime" id="end_date" />
                        </li>
                </div>
                </ul>
                <div id="buttons">
                    <input type="button" id="find_results_button" value="Find Results" />
                    <input type="button" id="clear_results_button" value="Clear Results" />
                </div>
            </fieldset>    
        </form>   
    </div>
    
    <div id="result_wrapper">
    
    
    
    </div>


<jsp:include page="inc/footer-inc.jsp" flush="false" />