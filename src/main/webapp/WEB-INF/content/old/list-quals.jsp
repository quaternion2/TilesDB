<%-- 
    Document   : show-qual
    Created on : 25-Aug-2012, 4:20:30 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Qualification Form</title>
    </head>
    <body>
        <h1>Qualification Forms</h1>
        <p><s:a namespace="/qual" action="new-qual-input">New Qualification Form</s:a></p>
        <s:iterator value="quals">
            <s:a namespace="/qual" action="delete-qual">
                delete<s:param name="id" value="id"/>
            </s:a>
            <s:a namespace="/qual" action="edit-qual-lines">
                edit resume<s:param name="id" value="id"/>
            </s:a>
            <s:a namespace="/qual" action="show-qual">
                edit qual<s:param name="id" value="id"/>
            </s:a>
            <s:property value="id"/><br/>
            <s:property value="name"/><br/>
            <s:property value="role"/><br/>
            <s:property value="description"/><br/>
            <hr/>
        </s:iterator>
    </body>
</html>
