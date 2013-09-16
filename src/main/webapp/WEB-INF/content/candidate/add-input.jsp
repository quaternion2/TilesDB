<%@taglib prefix="s" uri="/struts-tags"%>
<script>
    alert("hello!");
    $(document).ready(function(){
        asyncFormById("#addCandidate");
    });
    
    var actionUrl = "<s:url action="add" namespace="/crud/candidate"/>";
    
    var asyncFormById = function(formId){
        //get the form, save the form action, remove the action method, prevent submit from submitting
        //itterate over input fields, creating new object with same properties and values as form
        //send that with json to the servers action 
        
        $(formId).submit(function(){
            $.getJSON(actionUrl, function(data) {
                var items = [];
                $.each(data, function(key, val) {
                    items.push('<li id="' + key + '">' + val + '</li>');
                });
                $('<ul/>', {
                    'class': 'my-new-list',
                    html: items.join('')
                }).appendTo('body');
            });
            return false; //prevents normal submit   
        });
    };
    
</script>
<s:if test="id == null">
    <h1>New Candidate</h1>
</s:if>
<s:else>
    <h1>Update Candidate</h1>
</s:else>

<s:form id="addCandidate" namespace="/html/candidate" action="add">
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
    <s:if test="id == null">
        <s:submit value="Save"/>
    </s:if>
    <s:else>
        <s:submit value="Update"/>
    </s:else>
</s:form>