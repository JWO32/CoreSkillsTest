<% pageContext.getSession().setAttribute("PageTitle", "Manage Quiz Users"); %>
<% pageContext.getSession().setAttribute("PageHeading", "Manage Users");%>

<jsp:include page="inc/header-inc.jsp" flush="false" />
<jsp:include page="inc/pageheader-inc.jsp" flush="false"/>
<jsp:include page="inc/navigation-inc.jsp" flush="false" />

<script type="text/javascript" src="js/jstree-plugin/jquery.jstree.js"></script>

<script type="text/javascript">
    $(document).ready(function()
    {
        $.ajaxSetup ({
            // Disable caching of AJAX responses
            cache: false
          });
        //Move this code to separate file.

        //Fetch the dynamic group list component to send to the controller
        var $treeList = $('#hierarchical_list');
        var $newGroupDialogueElem = $('#new_group_dialogue');
        var $newUserDialogueElem = $('#new_user_dialogue');

        var controller = new UserManagerController($treeList, $newUserDialogueElem, $newGroupDialogueElem);
        
        controller.init();

        $('#add_user_button').on('click', function()
        {
            controller.addUserEvent(controller.addUserCallback);
        });
        
        $('#add_group_button').on('click', function()
        {
            controller.addGroupEvent(controller.addGroupCallback);
        });
        
        $('#delete_selected_user').on('click', function()
        {
            alert('Warning: This user will be permanently deleted.');
        });
        
        $('#delete_selected_group').on('click', function()
        {
            alert('Warning: Current behaviour is to delete group and ALL users.'); 
        });

        $('input[type="button"]').button();
        
        // Download users and groups.
        controller.downloadGroupsandUsersEvent();
        
    });
</script>
   
    <section id="content_body">
        <form>
            <fieldset>
                <legend>Manage Users and Groups</legend>
                <input type="button" id="add_user_button" value="Add User"></input>
                <input type="button" id="add_group_button" value="Add Group"></input>
                <input type="button" id="delete_selected_user" value="Delete User"></input>
                <input type="button" id="delete_selected_group" value="Delete Group"></input>
            </fieldset>         
        </form>
        
        
        <h3>Student and Group List</h3>
        
        
        <div id="hierarchical_list">

        </div>
    
    </section>
    </div>
   
    <!--jQuery Dialogues for use on the page -- inline style to hide-->
    <div id="new_user_dialogue" style="display: none;">       
    </div>
    <div id="new_group_dialogue" style="display: none;">       
    </div>
</body>
</html>