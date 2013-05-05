<%@taglib prefix="s" uri="/struts-tags"%>
<h1>Opportunity Details</h1>
<script>
    //TODO: set the URL for the dynamic crud action to read this opportunity along
    //with the id. And display the opportunity.
</script>
<s:form>
    <s:textfield name="id" placeholder="id"></s:textfield>
    <s:textfield name="title" placeholder="title"></s:textfield>
    <s:textfield name="client" placeholder="client"></s:textfield>
    <s:textfield name="submissionMethod" placeholder="submission method"></s:textfield>
    <s:textfield name="closingTime" placeholder="closing time"></s:textfield>
    <s:textfield name="requestTime" placeholder="request time"></s:textfield>
    <s:textfield name="descrption" placeholder="description"></s:textfield>
    <s:textfield name="qualId" placeholder="qual id"></s:textfield>
    <s:textfield name="priority" placeholder="priority"></s:textfield>
</s:form>

<h1>TTD</h1>
id, title, client_id, sumission_method, closing_time, request_time, description, qual_id, priority

<ul>
    <li>put down all fields</li>
    <li>There should be a way to get all the fields - dynamically</li>
    <li>Should supply a link to the client details</li>
    <li>Should be able to supply a list of all active candidates and their level of progress</li>
</ul>