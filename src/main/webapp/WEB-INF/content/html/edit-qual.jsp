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
            <s:form namespace="/html" action="update-qual-header">
                <span>Id:</span><s:textfield name="qual.id"/>
                <span>Name:</span><s:textfield name="qual.name"/>
                <span>Role:</span><s:textfield name="qual.role"/>
                <span>Description:</span><s:textfield name="qual.description"/>
                <s:submit/>
            </s:form>
        </div>
    </body>
</html>
