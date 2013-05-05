<%@taglib prefix="s" uri="/struts-tags"%>
<h1>Search Clients</h1>
<script>
    $(document).ready(function(){
        var entityDetailsUrl = "<s:url namespace="/client" action="details"/>";
        var addEnityUrl = "<s:url namespace="/crud/client" action="add"/>";
        
        var tableColumns = [];
        
        var fnListEnities = function(data){
            var doOnce = true;
            var headerRow = $("<tr>");
            var tbody = $("<tbody>");
            //set tableColumns
            tableColumns = [];
            $("#nRecords").text(": " + data.count + " clients");
            
            $.each(data.ordinals, function(index, columnName){
                console.log(columnName);
                tableColumns.push(columnName);
            });
            
            $.each(data.entityList, function(index, client){
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
                        var url = $("<a>").attr("href", "" + entityDetailsUrl + "?id=" +  client["id"]).html(client[columnName]);
                        $(td).append(url);
                        $(row).append(td);
                    }else{
                        $(row).append($("<td>").html(client[columnName]));
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
        
        var searchUrl = "<s:url namespace="/crud/client" action="search"/>";
        $("#search-button").click(function(){      
            var formObject = $('#candidate-form').serializeForm();
            $.getJSON(searchUrl,formObject,fnListEnities);     
        });
        
        $("#clear-button").click(function(){
            $("#candidate-form")[0].reset();
        });
        
        $("#new-candidate-button").click(function(){
            var formObject = $('#candidate-form').serializeForm();
            console.log(formObject);
            formObject = popFromObjectName(formObject, "candidate.");
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
<div class="framed">
    <s:form cssClass="framed" id="candidate-form" namespace="/candidate" action="search">
    <button type="button" id="search-button">Search</button>
    <button type="button" id="clear-button">Clear</button>
    <button type="button" id="new-candidate-button">Add Candidate</button>

    <div>
        <s:textfield name="model.name" placeholder="Name" title="Name"/>
        <s:textfield name="model.note" placeholder="Note" title="Note"/>
        <s:textfield  name="model.opportunityCollection" placeholder="opportunityCollection" title="opportunityCollection"/>
    </div>
</s:form>
</div>

    <div class="boxHeader">Found <span id="nRecords"></span></div>
<table id="candidate-list">
    <thead>
        <tr>
        </tr>
    </thead>
    <tbody>

    </tbody>
</table>
