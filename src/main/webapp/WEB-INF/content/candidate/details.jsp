<%@taglib prefix="s" uri="/struts-tags"%>
<script>
    
    (function( $ ) {
        $.fn.kenCrud = function( action, crudUrl ) {
            var crudUrl = "<s:url includeContext="true"  forceAddSchemeHostAndPort="true" value="/crud"/>" + "/";
            
            var crudDeleteCallback = function(params){
                var deleteUrl = crudUrl + params.data.entity + "/delete?id=" + params.data.id;
                $.getJSON(deleteUrl, function(){
                    $(params.data.parent).remove();
                });
            }
            if ( action === "create") {
                
            }else if ( action === "read" ) {
                
            }else if (action === "update"){
                
            }else if (action === "delete"){
                //TODO: this is page specific, find a way to factor out
                //need a target to create UI elements (create call back for this)
                console.log("start delete preparation");
                jQuery.each(this, function(index, value){
                    console.log("processing for delete tag: " + index);
                    var id = $(value).attr('data-id');
                    var entity = $(value).attr('data-entity');
                    var text = $(value).text();
                    var parent = $(value).parent();
                    var button = $("<button>").text(text).click({"id": id, "entity": entity, "parent": parent}, crudDeleteCallback);
                    $(value).replaceWith(button);
                });
            }else if (action === "count"){
                
            }else if (action === "page"){
                
            }else if (action === "search"){
                
            }else if (action === "describe"){
                //TODO: need to implement server side functionality
            }
            
            
        };
    }( jQuery ));
    
    $(document).ready(function(){
        $( "#tabs" ).tabs().width(400);
        $(".deleteResume").kenCrud("delete"); //need a source for id's and a location to tie the click event to... 
    });
    

</script>
<h1>Update Candidate</h1>
<!-- 

TODO: Block adding new candidate if fname, lname and phone are the same
TODO: Change tab order to ommit middle name
TODO: Create script to expand headings to top of input fields

-->
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
    <div class="framed inline-block">
        <h1>Resumes</h1>
        <s:form namespace="/candidate" action="resume-entry">
            <s:hidden name="id" value="%{id}"/>
            <s:submit value="New Resume"></s:submit>
        </s:form>
        <s:iterator value="resumes">
            <div>
                <span class="deleteResume" data-entity="resume" data-id="<s:property value="value0"/>">delete</span> 
                <span><s:a namespace="/candidate" action="resume-entry"><s:param name="id" value="id"/><s:param name="resumeId" value="value0"/><s:property value="value1"/></s:a></span> 
            </div>
        </s:iterator>
        <h1>Opportunities</h1>
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