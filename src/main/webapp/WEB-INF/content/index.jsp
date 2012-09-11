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
        <title>Index Action</title>
    </head>
    <body>
        <h1>Index Action!</h1>
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
        <h1>Enochs Stuff</h1>
        <ul>
            <li><s:a value="/enoch/qualificationFormEntry7.jsp">qualificationFormEntry7</s:a></li>
            <li></li>
            <li></li>
        </ul>
        <h2>TTD</h2>
        <ul>
            <li>Delete qualification lines</li>
            <li>Edit qualification form description</li>
        </ul>
    </body>
</html>
