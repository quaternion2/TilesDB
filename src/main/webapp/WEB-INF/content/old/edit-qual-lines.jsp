<%-- 
    Document   : edit-qual
    Created on : 26-Aug-2012, 2:33:15 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Qual Details</title>
        <style>
            .description{
                width: 20em;
            }
        </style>
    </head>
    <body>
        <h1>Edit Qual Details</h1>
        <s:actionerror/> 
        <s:fielderror name="id"/>
        <s:fielderror name="qualLine.number"/>
        <s:fielderror name="qualLine.description"/>
        <s:fielderror name="qualLine.mandatory"/>
        <s:fielderror name="qualLine.months"/>
        <s:fielderror name="qualLine.id"/>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>#</th>
                    <th>Description</th>
                    <th>Mandatory</th>
                    <th>Months</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <s:set var="xyz" value="id"/>
                <s:iterator value="qual.qualLineCollection">
                    <s:form namespace="/qual" action="update-qual-line">
                        <s:hidden name="id" value="%{#xyz}"/>
                        <tr>
                            <td>
                                <s:textfield name="qualLine.id" value="%{id}"/>
                            </td>
                            <td>
                                <s:textfield name="qualLine.number" value="%{number}"/>
                            </td>
                            <td>
                                <s:textarea cssClass="description" name="qualLine.description"  value="%{description}"/>
                            </td>
                            <td>
                                <s:select name="qualLine.mandatory" list="{'true', 'false'}" value="%{mandatory}"/>
                            </td>
                            <td>
                                <s:textfield name="qualLine.months" value="%{months}"/>
                            </td>
                            <td>
                                <s:submit value="update"/>
                            </td>
                        </tr>
                    </s:form>
                </s:iterator>
                <s:form namespace="/qual" action="new-qual-line" method="get">
                    <s:hidden name="qualId" value="%{id}"/><s:fielderror name="qualId"/>
                    <tr>
                        <td></td>
                        <td>
                            <s:textfield name="qualLine.number" value=""/><s:fielderror name="number"/>
                        </td>
                        <td>
                            <s:textarea cssClass="description" name="qualLine.description" value=""/><s:fielderror name="description"/>
                        </td>
                        <td>
                            <s:select name="qualLine.mandatory" list="{'true', 'false'}" value="true"/><s:fielderror name="mandatory"/>
                        </td>
                        <td>
                            <s:textfield name="qualLine.months" value=""/><s:fielderror name="months"/>
                        </td>
                        <td>
                            <s:submit value="new"/>
                        </td>
                    </tr>
                </s:form>
            </tbody>
        </table>
    </body>
</html>
