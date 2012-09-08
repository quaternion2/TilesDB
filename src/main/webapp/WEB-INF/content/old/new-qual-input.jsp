<%-- 
    Document   : add-qual-description
    Created on : 25-Aug-2012, 11:02:49 PM
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
        <h1>Enter New Qual Description</h1>
        <s:form namespace="qual" action="new-qual">
            <span class="label">Name</span><s:textfield name="name"/>
            <span class="label">Role</span><s:textfield name="role"/>
            <span class="label">Description</span><s:textfield name="description"/>
            <s:submit/>
        </s:form>
    </body>
</html>
