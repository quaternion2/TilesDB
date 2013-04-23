<%-- 
    Document   : tiles-action-execute-tag
    Created on : 16-Apr-2013, 12:04:07 AM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Execute Tiles Result from Action Tag</title>
    </head>
    <body>
        <h1>Execute Tiles Result from Action Tag</h1>
        <s:action namespace="/test" name="tiles" executeResult="true"/>
    </body>
</html>
