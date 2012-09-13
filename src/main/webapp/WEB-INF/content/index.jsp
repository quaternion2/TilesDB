<%-- 
    Document   : index
    Created on : 23-Aug-2012, 10:54:20 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./style/main.css" rel="stylesheet" type="text/css">
        <title>Employment System</title>
    </head>
    <body>
        <div class="siteHeader">
            Employment System
            
        </div>
        
        <h1>Qual Services</h1>
        <ul>
            <li><s:a namespace="/qual" action="list-quals">List Qualification Forms</s:a></li>
            <li><s:a namespace="/qual" action="new-qual-input">New Qualification Form</s:a></li>
        </ul>
        <h1>Resume Services</h1>
        <ul>
            <li><s:a namespace="/resume" action="list-resumes">List Qualification Forms</s:a></li>
            <li><s:a namespace="/resume" action="new-resume-input">New Qualification Form</s:a></li>
        </ul>
        <h1>New/In Testing</h1>
        <ul>
            <li><s:a value="/crud/qual/read.action?id=9">Crud - qual - id 9</s:a></li>
        </ul>
        <h1>Enochs Stuff</h1>
        <ul>
            <li><s:a value="/enoch/qualificationFormEntry7.jsp">qualificationFormEntry7</s:a></li>
            <li><s:a value="/enoch/resumeEntry005.jsp">resumeEntry005</s:a></li>
            <li></li>
        </ul>
        <h2>TTD</h2>
        <ul>
            <li>Delete qualification lines</li>
            <li>Edit qualification form description</li>
        </ul>
    </body>
    <div>
        <p class="siteFooter">
            <br>© 2012, Scrotum Solutions Inc.<br>
            All Rights Reserved.
        </p>
    </div>
</html>
