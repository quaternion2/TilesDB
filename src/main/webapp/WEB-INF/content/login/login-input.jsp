<%-- 
    Document   : login
    Created on : 4-Mar-2013, 1:12:45 AM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <s:actionerror/>
        <s:form namespace="/login" action="login">
            <s:textfield name="userName" placeholder="User Name"/>
            <s:password name="password" placeholder="Password"/>
            <s:submit/>
        </s:form>
    </body>
</html>
