<!doctype html>
<head>

<!-- Stylesheets -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css"/>
<link rel="stylesheet" href="css/usermanager.css" type="text/css"/>
<link rel="stylesheet" href="css/main.css" type="text/css"/>

<!-- JavaScript Library Imports -->
<script type="text/javascript" src="js/UserManager/group.js"></script>
<script type="text/javascript" src="js/UserManager/user.js"></script>
<script type="text/javascript" src="js/UserManager/usermanagercontroller.js"></script>
<script type="text/javascript" src="js/UserManager/usermanagermodel.js"></script>
<script type="text/javascript" src="js/UserManager/usermanagerview.js"></script>
<script type="text/javascript" src="js/UserManager/userdialogue.js"></script>
<script type="text/javascript" src="js/UserManager/groupdialogue.js"></script>

<!-- jQuery and JQuery UI Imports -->
<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>-->
<script type="text/javascript"  src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script type="text/javascript" src="js/jstree-plugin/jquery.jstree.js"></script>
<!--    -->

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
<title>Quiz Management: User Management</title>
</head>

<body>
    <div id="MainContent">
    <section id="MainHeading">        
        <!-- Include page header here --> 
        <header>
            <h1>Manage Users and Groups</h1>
        </header>
    </section>
    <section id="Navigation">
        <nav id="TabbedLinks">
            <ul>
                <li><a href="#">Home Page</a></li>
                <li><a href="#">Create a New Quiz</a></li>
                <li><a href="#">Manage Quizzes</a></li>
                <li><a href="#">Manage Users</a></li>
                <li><a href="#">Create a Quiz Event</a></li>
            </ul>  
        </nav>
    </section>
   
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