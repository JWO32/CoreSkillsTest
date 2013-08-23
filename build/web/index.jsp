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

<p>Welcome to the Core Skills testing system.  Please select an option from the menu to begin.</p>


<jsp:include page="inc/footer-inc.jsp" flush="false" />