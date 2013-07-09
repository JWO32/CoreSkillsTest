<%-- 
    Document   : UserManager
    Created on : Jul 7, 2013, 8:10:21 PM
    Author     : JWO
--%>
<!doctype html>

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
<!--    -->

<!--Custom Scripts -->
<script type="text/javascript" src="js/jquery.nestable.js"></script>
<!--     -->

<title>Quiz Management: User Management</title>
</head>
<body>
    <section id="header">        
        <!-- Include page header here -->      
    </section>
    <section id="navgiation">
        <nav id="tabbed_links">
            <ul>
                <li>Create a New Quiz</li>
                <li>Manage Quizzes</li>
                <li>Manage Users</li>
                <li>Create a Quiz Event</li>
            </ul>  
        </nav>
    </section>
   
    <section id="content_body">
        <h1>Manage Users and Groups</h1>
        
        <h3>Current List of Groups</h3>
        <select id="main_group_list">
            <option>No Groups</option>
        </select>
        
        <h3>Current List of Users</h3>
        <select id="main_user_list">
            <option>No Users</option>
        </select>
        
        <section id="hierarchical_list">
            <ol id="user_group_list">
                
                
            </ol>
        </section>
        
        
    </section>
</body>
</html>