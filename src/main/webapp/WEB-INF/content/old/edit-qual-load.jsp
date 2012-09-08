<%-- 
    Document   : edit-qual
    Created on : 7-Sep-2012, 9:53:34 PM
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
        <h1>Edit Qual</h1>
        <s:form namespace="/qual" action="edit-qual">
            <s:hidden name="id"/>
            <span class="label">Name</span><s:textfield name="name"/>
            <span class="label">Role</span><s:textfield name="role"/>
            <span class="label">Description</span><s:textfield name="description"/>
            <s:submit/>
        </s:form>
    </body>
</html>
