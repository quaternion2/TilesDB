<%@taglib prefix="s" uri="/struts-tags"%>
<script>
    $(document).ready(function(){
        
    });
</script>
<h1>Update Candidate</h1>
<s:push value="candidate">
    <div class="framed inline-block">
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
        <s:form cssClass="boxsizingBorder" namespace="/candidate" action="add-log">
            <s:hidden name="candidateId" value="%{id}"/>
            <div>
                <s:textarea name="log" rows="6"/>
            </div>
            <div>
                <s:submit value="Add Log"/>
            </div>
        </s:form>
    </div>
    <div class="inline-block">
        List of opportunities for client goes here
    </div>
    <s:iterator value="logs">
        <div class="log">
            <div class="boxHeader">
                <span><s:date name="stamp" format="yyyy/MM/dd hh:mm"/></span>
                <span><s:property value="recruiterId.person.fname"/> <s:property value="recruiterId.person.lname"/></span>
            </div>
            <div class="log-body">
                <s:property value="note"/>
            </div>
        </div>
    </s:iterator>

    <p>TTD: use ajax to load candidate description values</p>
    <p>TTD: ensure only logged in user can access this page (and all pages not in default package or public package)</p>
    <p>TTD: Add ownership fields to candidate (audit fields : date created/updated/ManagedBy)</p>
    <p>TTD: Add ownership field to candidate</p>

    <p>TTD: add tag collection...</p>
    <p>TTD: add opportunity collection...</p>
    <p>TTD: add resume collection...</p>
    <p>TTD: add opportunity collection...</p>

    <p>TTD: add communication skill drop down</p>
    <p>TTD: add personality drop down</p>
    <p>TTD: add VIP check box</p>
</s:push>