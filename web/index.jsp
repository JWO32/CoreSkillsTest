<% pageContext.getSession().setAttribute("PageTitle", "Home Page"); %>
<% pageContext.getSession().setAttribute("PageHeading", "Core Skills Quiz System");%>

<jsp:include page="inc/header-inc.jsp" flush="false" />
<jsp:include page="inc/pageheader-inc.jsp" flush="false"/>
<jsp:include page="inc/navigation-inc.jsp" flush="false" />

<h1>Core Skills Testing System</h1>

<h2>What is the Core Skills Testing System?</h2>
<p>The purpose of this system is as a replacement for the current Core Skills Test used at Angus College.  This version represents
    version 1.0, which has been defined as the minimum viable product.  This is the minimum set of functionality required for a 
    working and useful quiz system.  The system is rather basic, but a road-map of further features has been discussed and agreed to
    extend the functionality of the system in subsequent versions of the software.</p>
<p>The system has been developed using Java for the server with a completely web-based client.  The client has been created with extensive
    use of JavaScript, including jQuery and Handlebars.js.  The system relies almost entirely on AJAX transactions for data transmission between the client
    and server as a way of minimising server load, as it might occur when many quizzes are being taken simultaneously.</p>

<div id="angus_logo">
    <img src="images/angus-logo.png"/>
    <p>Developed in collaboration with Angus College</p>
</div>

<jsp:include page="inc/footer-inc.jsp" flush="false" />