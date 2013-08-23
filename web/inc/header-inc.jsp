<%@page import="java.util.Set"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<!DOCTYPE html>
<html>
    <head>     
        <!-- jQuery and JQuery UI Imports -- these remain static-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script type="text/javascript" src="js/js-libs/jquery-extensions.js"></script>
        <script type="text/javascript" src="js/handlebars.js"/></script>
        <script type="text/javascript" src="js/js-libs/jquery-extensions.js"></script>
      
    <%
        //
        // Try to make loading of script resources automatic.
        // Not an ideal implementation -> examine the possibility of implementing the
        // FrontController pattern in future revisons of the software.
        // First deal with loading JavaScript resources
        String scriptResource = request.getRequestURI();
        String[] pathParameters = scriptResource.split("/");

        String directoryName = pathParameters[2].replaceAll(".jsp", "");
        String jsPath = "/js/"+directoryName;
        String cssPath = "css/"+directoryName+".css";

        Set<String> javaScriptFiles = pageContext.getServletContext().getResourcePaths(jsPath);

        for(String currentFile : javaScriptFiles)
        {
            //Need to remove the leading slash to get a relative path for the web browser to follow
            //Bit of a hack but a better way will take too long to implement.
            currentFile = currentFile.substring(1);
            %><script type="text/javascript" src="<%=currentFile %>"></script>
            <%
        }
     %>
        
        <!-- Stylesheets -->
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css"/>
        <!-- Customised page stylesheet -->
        <link rel="stylesheet" href="<%= cssPath %>" type="text/css"/>
        <!-- Global Stylesheet Static include-->
        <link rel="stylesheet" href="css/main.css" type="text/css"/>
        
        <!-- Page title obtained from the current session - this will be removed when the 'FrontController' pattern is adopted
        in future iterations of the software -->
        <title><%=pageContext.getSession().getAttribute("PageTitle") %></title>
    </head>