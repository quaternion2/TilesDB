<%-- 
    Document   : ui-tag-test
    Created on : 30-Aug-2012, 11:52:28 PM
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
        <s:set name="myList" value="{'one','two','three'}"/>
        <s:select list="myList" class=""/>
        <s:textarea  class=""/>
        <s:textfield class=""/>
    </body>
</html>
