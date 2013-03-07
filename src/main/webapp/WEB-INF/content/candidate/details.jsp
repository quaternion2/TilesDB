<%@taglib prefix="s" uri="/struts-tags"%>
<p>TTD: use ajax to load candidate description values</p>
<p>TTD: make this fit in with the standard tiles template and see if I can load tiles components in here</p>
<p>TTD: ensure only logged in user can access this page (and all pages not in default package or public package)</p>
<p>TTD: create add new log input.</p>
<p>TTD: list log entries for this candidate - from most recent to oldest</p>
<p>TTD: add opportunity id to log</p>
<p>TTD: Add ownership fields to candidate (audit fields : date created/updated, who last created/updated)</p>
<p>TTD: Add ownership field to candidate</p>
<p>TTD: add VIP field for candidate</p>
<p>TTD: add tag collection...</p>
<p>TTD: add resume collection...</p>
<p>TTD: add opportunity collection...</p>
<h1>Update Candidate</h1>
<s:push value="candidate">
    <s:form id="addCandidate" namespace="/candidate" action="update">
        <s:hidden name="id" value="%{id}"/>
        <div>
            <s:textfield name="fname" placeholder="First Name" title="First Name"/>
            <s:textfield name="mname" placeholder="Middle Name" title="Middle Name"/>
            <s:textfield  name="lname" placeholder="Last Name" title="Last Name"/>
        </div>
        <div>
            <s:textfield name="homePhone" placeholder="Day Phone" title="Day Phone"/>
            <s:textfield name="cellPhone" placeholder="Cell Phone" title="Cell Phone"/>
            <s:textfield name="otherPhone" placeholder="Other Phone" title="Other Phone"/>
        </div>
        <div>
            <s:textfield name="street" placeholder="Address" title="Address"/>
        </div>
        <div>
            <s:textfield name="city" placeholder="City" title="City"/>
            <s:textfield name="state" placeholder="Province" title="Province"/>
            <s:textfield name="poCode" placeholder="Area Code" title="Area Code"/>
        </div>
        <div>
            <s:textfield name="email" placeholder="email" title="email"/>
            <s:textfield name="altEmail" placeholder="alt email" title="alt email"/>
        </div>
        <div>
            <s:textfield name="skype" placeholder="Skype ID" title="Skype ID"/>
        </div> 
        <div>
            <s:textfield name="desiredRateHour" placeholder="Desired Rate 0.00" title="Desired Rate 0.00"/>
        </div>
        <s:submit value="Update"/>
    </s:form>
    <h1>Log</h1>
    <s:form namespace="/candidate" action="add-log">
        <s:hidden name="candidateId" value="id"/>
        <div>
            <s:submit value="Add Log"/>
        </div>
        <div>
            <s:textarea name="log" cols="120" rows="5"/>
        </div>
    </s:form>
</s:push>