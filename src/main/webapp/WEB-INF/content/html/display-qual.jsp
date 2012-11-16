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
        <title>Qualification Form Details</title>
    </head>
    <body>
        <h1>Qualification Form Details</h1>
        <div>
            <span>Id:</span><s:property value="qual.id"/>
            <span>Name:</span><s:property value="qual.name"/>
            <span>Role:</span><s:property value="qual.role"/>
            <span>Description:</span><s:property value="qual.description"/>
        </div>
    </body>
</html>
