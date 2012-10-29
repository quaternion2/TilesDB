<%-- 
    Document   : new-qualification-form
    Created on : 21-Oct-2012, 12:17:32 PM
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
        <h1>Manage Qualification Forms</h1>
        <p>The view add, removes qualification form headers.</p>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>role</th>
                    <th>description</th>
                    <th>remove</th>
                    <th>edit</th>
                    <th>details</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="quals">
                    <tr>
                        <td><s:property value="id"/></td>
                        <td><s:property value="name"/></td>
                        <td><s:property value="role"/></td>
                        <td><s:property value="description"/></td>
                        <td><s:a namespace="/html" action="remove-qual"><s:param name="id" value="id"/>remove</s:a></td>
                        <td><s:a namespace="/html" action="edit-qual"><s:param name="id" value="id"/>edit</s:a></td>
                        <td><s:a namespace="/html" action="details-qual"><s:param name="id" value="id"/>details</s:a></td>
                    </tr>
                </s:iterator>  
            </tbody>
        </table>
        <!-- add qualification form -->
        <s:form namespace="/html" action="add-qual">
            <table>
                <tbody>
                    <tr>
                        <td><s:textfield name="name"/></td>
                        <td><s:textfield name="role"/></td>
                        <td><s:textfield name="description"/></td>
                        <td><s:submit/></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <th>name</th>
                        <th>role</th>
                        <th>description</th>
                        <th></th>
                    </tr>
                </tfoot>
            </table>
        </s:form>
    </body>
</html>
