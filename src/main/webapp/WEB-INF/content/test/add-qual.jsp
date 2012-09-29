<%-- 
    Document   : testAddEntity
    Created on : 29-Sep-2012, 12:36:01 AM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Qual Header</title>
    </head>
    <body>
        <h1>Add Qual Header</h1>
        <s:form namespace="/crud/qual" action="add">
            <!--s:textfield name="id"/-->
            <s:textfield name="name"/>
            <s:textfield name="role"/>
            <s:textfield name="description"/>
            <s:submit/>
        </s:form>
    </body>
</html>
