<%-- 
    Document   : dynamic-tag-test
    Created on : 31-Dec-2012, 1:46:06 AM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ken" uri="/dynamic" %>
<h1>Dynamic Tag Test</h1>
<p>This test creates a Candidate bean and then itterates the properties with
    the custom "properties" tag.</p>

<s:bean name="org.kenmcwilliams.employmentsystem.orm.Candidate" var="candidate">
    <p><ken:properties var="myVar2"/></p>
    <p>ken:properties: <s:property value="#myVar2"/></p>
</s:bean>


