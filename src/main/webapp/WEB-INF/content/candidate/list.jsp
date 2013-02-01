<%@taglib prefix="s" uri="/struts-tags"%>
<h1>List Candidates</h1>
<table>
<s:iterator value="candidateList">
    <tr>
        <td><s:a namespace="/candidate" action="details" ><s:param name="id" value="id"/><s:property value="id"/></s:a></td>
        <td><s:textfield name="fname" placeholder="First Name" title="First Name" readonly="true"/></td>
        <td><s:textfield name="lname" placeholder="Last Name" title="Last Name" readonly="true"/></td>
        <td><s:textfield name="cellPhone" placeholder="Cell Phone" title="Cell Phone" readonly="true"/></td>
        <td><s:textfield name="email" placeholder="email" title="email"/></td>
        <td><s:property value="city"/>, <s:property value="state"/></td>
    </tr>
</s:iterator>
</table>