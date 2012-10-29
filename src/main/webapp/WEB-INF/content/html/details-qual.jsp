<%-- 
    Document   : details-qual
    Created on : 29-Oct-2012, 10:09:12 AM
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
        <div>
            <span>Id</span><s:property value="header.id"/>
            <span>Name</span>Name: <s:property value="header.name"/>
            <span>Role</span><s:property value="header.role"/>
            <span>Description</span><s:property value="header.description"/>
        </div>
    </body>
</html>
