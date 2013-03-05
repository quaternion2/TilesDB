<%@taglib prefix="s" uri="/struts-tags"%>
<h1>Search Candidates</h1>
<script>
    $(document).ready(function(){
        var candidateDetailsUrl = "<s:url namespace="/candidate" action="details"/>";
        var addCandidatesUrl = "<s:url namespace="/crud/candidate" action="add"/>";
        
        var tableColumns = [];
        
        var fnListCandidates = function(data){
            var doOnce = true;
            var headerRow = $("<tr>");
            var tbody = $("<tbody>");
            //set tableColumns
            tableColumns = [];
            $.each(data.ordinals, function(index, columnName){
                console.log(columnName);
                tableColumns.push(columnName);
            });
            
            $.each(data.candidateList, function(index, candidate){
                //console.log("start row");
                var row = $("<tr>");

                $.each(tableColumns, function( index, columnName) {
                    if (doOnce == true){ //generate the header only once
                        $(headerRow).append($("<th>").html(columnName));
                    }
                    //console.log( field + ": " + value );
                    if(columnName.localeCompare("id") === 0){
                        //id is in first position
                        var td = $("<td>");
                        var url = $("<a>").attr("href", "" + candidateDetailsUrl + "?id=" +  candidate["id"]).html(candidate[columnName]);
                        $(td).append(url);
                        $(row).append(td);
                    }else{
                        $(row).append($("<td>").html(candidate[columnName]));
                    }
                });
                //end new
                doOnce = false;
                $(tbody).append(row); //TODO better to tbody in memory and then replace at once 
                //console.log("end row");
            });
            $("#candidate-list thead tr").replaceWith(headerRow);
            $("#candidate-list tbody").replaceWith(tbody);
        }
        
        var searchUrl = "<s:url namespace="/candidate" action="search"/>";
        $("#search-button").click(function(){      
            var formObject = $('#candidate-form').serializeForm();
            $.getJSON(searchUrl,formObject,fnListCandidates);     
        });
        
        $("#clear-button").click(function(){
            $("#candidate-form")[0].reset();
        });
        
        $("#new-candidate-button").click(function(){
            var formObject = $('#candidate-form').serializeForm();
            console.log(formObject);
            formObject = popFromObjectName(formObject, "candidate.");
            console.log(formObject);
            $.getJSON(addCandidatesUrl,formObject,function(data){
                console.log(data);
            });
        });
        
        //TODO: I can see the following useful in many forms (pushing and popping name attribuites)
        //TODO: no effort at making this recursive, forms are not typically recursive
        var pushToObjectName =  function(obj, pushStr){
            var newObj = {};
            $.each(obj, function(key, value){
               var newKey = pushStr + key; 
               newObj[newKey] = value;
            });
            return newObj;
        };
        
        var popFromObjectName = function(obj, popStr){
            var newObj = {};
            $.each(obj, function(key, value){
               var newKey = key.replace(popStr, ''); 
               newObj[newKey] = value;
            });
            return newObj;
        };
        
    });
</script>
<ul>
    <li>Get Adding logs to work!!! (add log for currently logged in user)</li>
</ul>
<s:form id="candidate-form" namespace="/candidate" action="search">
    <button type="button" id="search-button">Search</button>
    <button type="button" id="clear-button">Clear</button>
    <button type="button" id="new-candidate-button">Add Candidate</button>

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
    <s:submit/>
</s:form>
<h1>Found</h1>
<table id="candidate-list">
    <thead>
        <tr>
            <th>First</th>
            <th>Last</th>
            <th>Phone</th>
            <th>Location</th>
        </tr>
    </thead>
    <tbody>

    </tbody>
</table>