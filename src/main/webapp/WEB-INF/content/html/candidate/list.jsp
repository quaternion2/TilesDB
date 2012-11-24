<%-- 
    Document   : list
    Created on : 17-Nov-2012, 5:32:39 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Candidates</title>
    </head>
    <body>
        <h1>List Candidates</h1>
        <s:iterator value="candidates" status="status">
            <s:property value="status.count"/><s:property value="fname"/><br/>
        </s:iterator>
    </body>
</html>
