<%-- 
    Document   : dynamic-tag-attributes
    Created on : 9-Sep-2012, 5:29:15 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dynamic Tag Attributes</title>
    </head>
    <body>
        <h1>Dynamic Tag Attributes</h1>
        <s:textfield dynamicAttributes="%{'placeholder':'input','foo':'bar'}"/>
    </body>
</html>
