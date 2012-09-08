<%-- 
    Document   : vector-test-input
    Created on : 3-Sep-2012, 12:10:16 AM
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
        <h1>Hello World!</h1>
        <s:form>
            <s:textfield name="testVector.size"/>
            <s:submit/>
        </s:form>
        Size: <s:property  value="testVector.size"/>
    </body>
</html>
