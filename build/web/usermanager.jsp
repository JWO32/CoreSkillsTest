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

        controller = new UserManagerController($treeList, $newUserDialogueElem, $newGroupDialogueElem);
        
        controller.init();

        $('#add_user_button').on('click', function ()
        {
            controller.addUserEvent(controller.addUserCallback);
        });
        
        $('#add_group_button').on('click', function ()
        {
            controller.addGroupEvent(controller.addGroupCallback);
        });
        
        $('#delete_selected_user').on('click', function ()
        {
            $.alertconfirmation('Are you sure?', 'If you delete this user, all results will also be deleted.', controller.deleteUserEvent);
            //controller.deleteUserEvent();
        });
        
        $('#delete_selected_group').on('click', function ()
        {
            $.alertconfirmation('Are you sure?', 'If you delete this group, all users and their results will also be deleted.', controller.deleteGroupEvent);
            //controller.deleteGroupEvent();
        });

        $('input[type="button"]').button();
        
        $('#buttons').tabs();
        // Download users and groups.
        controller.downloadGroupsandUsersEvent();
        
    });
</script>
   
    <section id="content_body">
        <div id="buttons">
            <ul>
                <li>Manage Users and Groups</li>
            </ul>
            <div id="buttons-1">
                <div id="user_buttons">
                    <legend>User Options</legend>
                    <img src="images/icons/user.jpg"/>
                    <input type="button" id="add_user_button" value="Add User"></input>
                    <input type="button" id="delete_selected_user" value="Delete User"></input>
                </div>
                <div id="group_buttons">
                    <legend>Group Options</legend>
                    <img src="images/icons/group.jpg"/>
                    <input type="button" id="add_group_button" value="Add Group"></input>
                    <input type="button" id="delete_selected_group" value="Delete Group"></input>
                </div>
            </div>
        </div>
        
        <h3>Student and Group List</h3>
        
        
        <div id="hierarchical_list">

        </div>
    
    </section>
   
    <!--jQuery Dialogues for use on the page -- inline style to hide-->
    <div id="new_user_dialogue" style="display: none;">       
    </div>
    <div id="new_group_dialogue" style="display: none;">       
    </div>
    
<jsp:include page="inc/footer-inc.jsp" flush="false" />