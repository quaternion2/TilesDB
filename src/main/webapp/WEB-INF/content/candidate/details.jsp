<%@taglib prefix="s" uri="/struts-tags"%>
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
    
    
</s:push>