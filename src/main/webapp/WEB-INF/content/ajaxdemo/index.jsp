<%@taglib prefix="s" uri="/struts-tags"%>
<h1>AJAX Demos</h1>
<script>
    $(function() {
        //get all entities and put them into a drop down
        var ajaxTableUrl = '<s:url namespace="/crud" action="list-tables"/>';
        var ajaxCountUrl = '<s:url namespace="/crud" action=""/>';
        ajaxCountUrl = ajaxCountUrl.replace(".action", "");
        console.log("ajaxCountUrl: " + ajaxCountUrl);
        var select = $("<select>");
        var tables;
        var fWriteDropDown = function(data)  {
            tables = data;
            $.each(data, function(i, tableName){
                var option = $("<option>").attr("value",tableName).text(tableName);
                console.log("table: " + tableName);
                $(select).append(option);
            });
        };
        $.ajax({
            dataType: 'json',
            url: ajaxTableUrl,
            async: false,
            success: fWriteDropDown
        });
        
        $("#selectEntity").append(select);
        //build a table of entities to counts
        console.log("here 1 ");
        $.each(tables, function(i, tableName){
            console.log("here 2");
            var tr = $("<tr>");
            var thisAjaxCountUrl = ajaxCountUrl + tableName + "/count";
            console.log("thisAjaxCountUrl " + thisAjaxCountUrl);
            var setCount = function(count){
                var tdTable = $("<td>").text(tableName);
                var tdCount = $("<td>").text(count);
                $(tr).append(tdTable);
                $(tr).append(tdCount);
                $("#recordCountTable").append(tr);
                console.log("here 3");
            };
            $.ajax({
                dataType: 'json',
                url: thisAjaxCountUrl,
                async: false,
                success: setCount
            });
        });
        //click a button to load a list
    
        //forward and back buttons to page, start and end are textfield values 
        
        //*** Actions to test: AddAction, CountAction, DeleteAction, ListTablesAction, PageAction, ReadAction, UpdateAction
        //*** Need to add: DescribeAction
    });
</script>



<div class="yui3-g">
    <div class="yui3-u-1-2">
        <!-- Create a table of Entities and the count for each entity -->
        <table>
            <thead>
                <tr>
                    <th>Table Name</th>
                    <th># Records</th>
                </tr>
            </thead>
            <tbody id="recordCountTable"></tbody>
        </table>
    </div>
    <div class="yui3-u-1-2">
        <!-- Get all entities into a drop down list -->
        <div id="selectEntity"></div>
        <!-- Set up paging for entity selected in drop down -->
        <div>
            Start: <input size="4" type="text" name="start"/> 
            Count: <input size="4" type="text" name="count"/>
            <button id="previous">previous</button>
            <button id="next">next</button>
        </div>
    </div>
</div>



