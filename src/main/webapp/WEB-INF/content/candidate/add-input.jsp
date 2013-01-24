<%@taglib prefix="s" uri="/struts-tags"%>
<s:form namespace="/html/candidate" action="add">
    <div>
        <span class="label">First Name</span>
        <s:textfield name="fname"/>
    </div>
    <div>
        <span class="label">Middle Name</span>
        <s:textfield name="mname"/>
    </div>
    <div>
        <span class="label">Last Name</span>
        <s:textfield name="lname"/>
    </div>
    <div>
        <span class="label">Day Phone</span>
        <s:textfield name="dayPhone"/>
    </div>
    <div>
        <span class="label">Evening Phone</span>
        <s:textfield name="eveningPhone"/>
    </div>
    <div>
        <span class="label">Cell Phone</span>
        <s:textfield name="cellPhone"/>
    </div>
    <div>
        <span class="label">Address</span>
        <s:textfield name="address"/>
    </div>
    <div>
        <span class="label">City</span>
        <s:textfield name="city"/>
    </div>
    <div>
        <span class="label">Province</span>
        <s:textfield name="province"/>
    </div>
    <div>
        <span class="label">Area Code</span>
        <s:textfield name="areaCode"/>
    </div>
    <div>
        <span class="label">Country</span>
        <s:textfield name="country"/>
    </div>
    <div>
        <span class="label">email</span>
        <s:textfield name="email"/>
    </div>
    <div>
        <span class="label">alt email</span>
        <s:textfield name="email"/>
    </div>
    <div>
        <span class="label">Skype ID</span>
        <s:textfield name="skype"/>
    </div> 
    <div>
        <span class="label">Desired Rate</span>
        <s:textfield name="desired_rate_hour"/>
    </div>
    <s:submit/>
</s:form>
