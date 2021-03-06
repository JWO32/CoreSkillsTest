<% pageContext.getSession().setAttribute("PageTitle", "About Core Skills Test"); %>
<% pageContext.getSession().setAttribute("PageHeading", "About Core Skills Test");%>

<jsp:include page="inc/header-inc.jsp" flush="false" />
<jsp:include page="inc/pageheader-inc.jsp" flush="false"/>
<jsp:include page="inc/navigation-inc.jsp" flush="false" />


<h1>Core Skills Test Update - Version 1.0</h1>
<p>
    This package has been developed by James Oliver as a candidate to eventually replace the Core Skills Diagnostic Quiz currently used at Angus College.  It has been developed as
    his project for MSc in Computing at the University of Dundee.
</p>
<p>
    If you have any questions about this software, or you wish to report a problem, please e-mail <a href="mailto:jwzoliver@dundee.ac.uk">James Oliver</a>
</p>
<h3>Road-map</h3>
<p>The road-map for further development of the quiz system has been agreed and is outlined below:</p>

<ul>
    <li>Version 1.1</li>
    <ul>
        <li>Ability to add media such as images to questions.</li>
        <li>Ability to bulk import users from external sources.</li>
        <li>Ability to add multiple result rules to one quiz.</li>
        <li>Ability to distinctly add multiple choice and multiple selection questions.</li>
    </ul>
    <li>Version 1.2</li>
    <ul>
        <li>Ability to retrieve data for integration to external systems.</li>
        <li>Ability to add gap-fill and "point and click" type questions.</li>
        <li>Ability to add sounds and videos to questions.</li>
        <li>Ability to prepare reports on individual student progress</li>
    </ul>
    
</ul>

<jsp:include page="inc/footer-inc.jsp" flush="false" />