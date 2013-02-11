<%@taglib prefix="s" uri="/struts-tags"%>
<h1>Search Candidate</h1>

<s:form id="addCandidate" namespace="/candidate" action="search">
    <div>
        <s:textfield name="candidate.fname" placeholder="First Name" title="First Name"/>
        <s:textfield name="candidate.mname" placeholder="Middle Name" title="Middle Name"/>
        <s:textfield  name="candidate.lname" placeholder="Last Name" title="Last Name"/>
    </div>
    <div>
        <s:textfield name="candidate.homePhone" placeholder="Day Phone" title="Day Phone"/>
        <s:textfield name="candidate.cellPhone" placeholder="Cell Phone" title="Cell Phone"/>
        <s:textfield name="candidate.otherPhone" placeholder="Other Phone" title="Other Phone"/>
    </div>
    <div>
        <s:textfield name="candidate.street" placeholder="Address" title="Address"/>
    </div>
    <div>
        <s:textfield name="candidate.city" placeholder="City" title="City"/>
        <s:textfield name="candidate.state" placeholder="Province" title="Province"/>
        <s:textfield name="candidate.poCode" placeholder="Area Code" title="Area Code"/>
    </div>
    <div>
        <s:textfield name="candidate.email" placeholder="email" title="email"/>
        <s:textfield name="candidate.altEmail" placeholder="alt email" title="alt email"/>
    </div>
    <div>
        <s:textfield name="candidate.skype" placeholder="Skype ID" title="Skype ID"/>
    </div> 
    <div>
        <s:textfield name="candidate.desiredRateHour" placeholder="Desired Rate 0.00" title="Desired Rate 0.00"/>
    </div>
    <s:submit value="Search"/>
</s:form>
