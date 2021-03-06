<%@taglib prefix="s" uri="/struts-tags"%>
<script>
    $(document).ready(function(){
        var entityDetailsUrl = "<s:url namespace="/opportunity" action="details"/>";
        var addEnityUrl = "<s:url namespace="/crud/opportunity" action="add"/>";
        
        var tableColumns = [];
        
        var fnListEnities = function(data){
            var doOnce = true;
            var headerRow = $("<tr>");
            var tbody = $("<tbody>");
            //set tableColumns
            tableColumns = [];
            $("#nRecords").text(": " + data.count + " oportunities");
            
            $.each(data.ordinals, function(index, columnName){
                console.log(columnName);
                tableColumns.push(columnName);
            });
            
            $.each(data.entityList, function(index, opportunity){
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
                        var url = $("<a>").attr("href", "" + entityDetailsUrl + "?id=" +  opportunity["id"]).html(opportunity[columnName]);
                        $(td).append(url);
                        $(row).append(td);
                    }else{
                        $(row).append($("<td>").html(opportunity[columnName]));
                    }
                });
                //end new
                doOnce = false;
                $(tbody).append(row); //TODO better to tbody in memory and then replace at once 
                //console.log("end row");
            });
            $("#opportunity-list thead tr").replaceWith(headerRow);
            $("#opportunity-list tbody").replaceWith(tbody);
        }
        
        var searchUrl = "<s:url namespace="/crud/opportunity" action="search"/>";
        $("#search-button").click(function(){      
            var formObject = $('#opportunity-form').serializeForm();
            $.getJSON(searchUrl,formObject,fnListEnities);     
        });
        
        $("#clear-button").click(function(){
            $("#opportunity-form")[0].reset();
        });
        
        $("#new-opportunity-button").click(function(){
            var formObject = $('#opportunity-form').serializeForm();
            console.log(formObject);
            formObject = popFromObjectName(formObject, "opportunity.");
            console.log(formObject);
            $.getJSON(addEnityUrl,formObject,function(data){
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
<h1>Find Opportunities</h1>
<s:form id="opportunity-form" namespace="/opportunity" action="search">
    <button type="button" id="search-button">Search</button>
    <button type="button" id="clear-button">Clear</button>
    <button type="button" id="new-opportunity-button">Add opportunity</button>

    <div>
        <s:textfield name="model.title" placeholder="Opp Title" title="Title"/>
    </div>
    <div>
        <s:textfield name="model.clientId" placeholder="Select Client" title="Select Client"/><button type="button" id="new-client">New Client</button>
    </div>
    <div>
        <s:textfield name="model.submissionMethod" placeholder="Submission Method" title="Submission Method"/>
    </div>
    <div>
        <s:textfield name="model.closingTime" placeholder="Closing Time" title="Closing Time"/>
        <s:textfield name="model.requestTime" placeholder="Request Time (default to now)" title="Request Time"/>
    </div>
    <div>
        <s:textfield name="model.qualId" placeholder="Qualification Form" title="Qualification Form"/>
    </div>
    <div>
        <s:textfield name="model.description" placeholder="Description" title="Description"/>
    </div>
    
        <div>
        <s:textfield name="model.description" placeholder="Description" title="Description"/>
    </div>
        <div>
        <s:textfield name="model.description" placeholder="Description" title="Description"/>
    </div>
    <s:submit/>
</s:form>


    <div class="boxHeader">Found <span id="nRecords"></span></div>
<table id="opportunity-list">
    <thead>
        <tr>
        </tr>
    </thead>
    <tbody>

    </tbody>
</table>