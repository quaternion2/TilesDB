<%-- 
    Document   : map-test
    Created on : 28-Nov-2012, 8:20:35 PM
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
        <h1>Map Test</h1>
        <h2>Natural</h2>
        <s:iterator value="filters">
            <s:property/><br/>
        </s:iterator>
        <h2>Key Set</h2>
        <s:iterator value="filters.keySet()">
            <s:property/><br/>
        </s:iterator>
        <h2>Values</h2>
        <s:iterator value="filters.values()">
            <s:property/><br/>
        </s:iterator>
    </body>
</html>
