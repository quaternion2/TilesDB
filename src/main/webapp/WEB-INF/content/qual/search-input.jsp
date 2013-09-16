<%-- 
    Document   : search-input
    Created on : 25-Jun-2013, 11:45:48 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script>
    $(document).ready(function() {
        var entityDetailsUrl = "<s:url namespace="/qual" action="details"/>";
        var addEnityUrl = "<s:url namespace="/crud/qual" action="add"/>";

        var searchUrl = "<s:url namespace="/crud/qual" action="search"/>";
        $("#search-button").click(function() {
            var formObject = $('#qual-form').serializeForm();
            $.getJSON(searchUrl, formObject, fnListEnities);
        });

        $("#clear-button").click(function() {
            $("#qual-form")[0].reset();
        });

        //TODO: This function exists in all the entities, should factor
        var fnListEnities = function(data) {
            var doOnce = true;
            var headerRow = $("<tr>");
            var tbody = $("<tbody>");
            //set tableColumns
            tableColumns = [];
            $("#nRecords").text(": " + data.count + " quals");

            $.each(data.ordinals, function(index, columnName) {
                console.log(columnName);
                tableColumns.push(columnName);
            });

            $.each(data.entityList, function(index, qual) {
                //console.log("start row");
                var row = $("<tr>");

                $.each(tableColumns, function(index, columnName) {
                    if (doOnce === true) { //generate the header only once
                        $(headerRow).append($("<th>").html(columnName));
                    }
                    //console.log( field + ": " + value );
                    if (columnName.localeCompare("id") === 0) {
                        //id is in first position
                        var td = $("<td>");
                        var url = $("<a>").attr("href", "" + entityDetailsUrl + "?id=" + qual["id"]).html(qual[columnName]);
                        $(td).append(url);
                        $(row).append(td);
                    } else {
                        $(row).append($("<td>").html(qual[columnName]));
                    }
                });
                //end new
                doOnce = false;
                $(tbody).append(row); //TODO better to tbody in memory and then replace at once 
                //console.log("end row");
            });
            $("#qual-list thead tr").replaceWith(headerRow);
            $("#qual-list tbody").replaceWith(tbody);
        };

        $("#new-qual-button").click(function() {
            var formObject = $('#qual-form').serializeForm();
            console.log(formObject);
            formObject = popFromObjectName(formObject, "candidate.");
            console.log(formObject);
            $.getJSON(addEnityUrl, formObject, function(data) {
                console.log(data);
            });
        });

        //TODO: All this code is the same a the candidates, should be factored out
        //TODO: I can see the following useful in many forms (pushing and popping name attribuites)
        //TODO: no effort at making this recursive, forms are not typically recursive
        var pushToObjectName = function(obj, pushStr) {
            var newObj = {};
            $.each(obj, function(key, value) {
                var newKey = pushStr + key;
                newObj[newKey] = value;
            });
            return newObj;
        };

        var popFromObjectName = function(obj, popStr) {
            var newObj = {};
            $.each(obj, function(key, value) {
                var newKey = key.replace(popStr, '');
                newObj[newKey] = value;
            });
            return newObj;
        };
    });
</script>
<h1>Qualification Form Management</h1>
<s:form cssClass="framed" id="qual-form" namespace="/qual" action="search">
    <button type="button" id="search-button">Search</button>
    <button type="button" id="clear-button">Clear</button>
    <button type="button" id="new-qual-button">Add Qualification Form</button>

    <div>
        <s:textfield name="model.name" placeholder="Name" title="First Name"/>
        <s:textfield name="model.role" placeholder="Role" title="Middle Name"/>
        <s:textfield  name="model.description" placeholder="Description" title="Last Name"/>
    </div>
</s:form>


<div class="boxHeader">Found <span id="nRecords"></span></div>
<table id="qual-list">
    <thead>
        <tr>
        </tr>
    </thead>
    <tbody>

    </tbody>
</table>        
