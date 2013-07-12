<%-- 
    Document   : UserManager
    Created on : Jul 7, 2013, 8:10:21 PM
    Author     : JWO
--%>
<!doctype html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<!-- Stylesheets -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css"/>
<link rel="stylesheet" href="css/quizcreate.css" type="text/css"/>
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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript"  src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.nestable.js"></script>
<!--    -->

<!--Custom Scripts -->
<script type="text/javascript" src="js/jquery.nestable.js"></script>
<!--     -->

<script type="text/javascript">
    $(document).ready(function()
    {

        //Fetch the dynamic group list component to send to the controller
        var hierarchicalGroupList = $('#hierarchical_list');
        var newGroupDialogueElem = $('#new_group_dialogue');
        var newUserDialogueElem = $('#new_user_dialogue');

        var controller = new UserManagerController(hierarchicalGroupList, newUserDialogueElem, newGroupDialogueElem);

        $('#add_user_button').on('click', function()
        {
            controller.addUserEvent(controller.addUserCallback);
        });
        
        $('#add_group_button').on('click', function()
        {
            controller.addGroupEvent(controller.addGroupCallback);
        });



        $('input[type="button"]').button();
        $('.dd').nestable();
    });
</script>

<title>Quiz Management: User Management</title>
</head>
<body>
    <div id="MainContent">
    <section id="MainHeading">        
        <!-- Include page header here --> 
        <header>
            <h1>Quiz User Management</h1>
        </header>
    </section>
    <section id="Navigation">
        <nav id="TabbedLinks">
            <ul>
                <li>Home Page</li>
                <li>Create a New Quiz</li>
                <li>Manage Quizzes</li>
                <li>Manage Users</li>
                <li>Create a Quiz Event</li>
            </ul>  
        </nav>
    </section>
   
    <section id="content_body">
        <h1>Manage Users and Groups</h1>
        
        <!-- <h3>Current List of Groups</h3>
        <select id="main_group_list">
            <option>No Groups</option>
        </select>
        
        <h3>Current List of Users</h3>
        <select id="main_user_list">
            <option>No Users</option>
        </select> -->
        
        <input type="button" id="add_user_button" value="Add User"></input>
        <input type="button" id="add_group_button" value="Add Group"></input>
        
        <h3>Student and Group List</h3>
        <section id="hierarchical_list" class="dd">
            <ol id="user_group_list" class="dd-list">
                <li class="dd-item" data-id="1">No Group</li>
                <ol class="dd-list">
                    <li class="dd-item" data-id="2">Bob McTavish</li>
                    <li class="dd-item" data-id="3">Mary Smith</li>
                    <li class="dd-item" data-id="4">James Oliver</li>
                </ol>
                <li class="dd-item" data-id="5">NC Computing</li>
                <ol class="dd-list">    
                    <li class="dd-item" data-id="6">Roger Roger</li>
                    <li class="dd-item" data-id="7">Carol Smiley</li>
                    <li class="dd-item" data-id="8">Andy Randomname</li>
                </ol> 
            </ol>
        </section>  
    </section>
    </div>
    <div id="new_user_dialogue" style="display: none;">
        
    </div>
    <div id="new_group_dialogue" style="display: none;">
        
    </div>
</body>
</html>