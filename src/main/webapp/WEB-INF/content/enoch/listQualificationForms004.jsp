
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="./../style/style.css" rel="stylesheet" type="text/css">
        <script src="../script/jquery/1.8.1/jquery.min.js"></script>
        <script src="../script/jquery-ui-1.8.24.custom/js/jquery-ui-1.8.24.custom.min.js"></script>
        <link href ="../script/jquery-ui-1.8.24.custom/css/ui-lightness/jquery-ui-1.8.24.custom.css" rel="stylesheet" type="text/css">


        <title>JSP Page</title>

        <s:url var="pageUrl" escapeAmp="false"  namespace="/crud/qual" action="page"/>
        <s:url var="countUrl" escapeAmp="false"  namespace="/crud/qual" action="count"/>
        <s:url var="deleteUrl" escapeAmp="false"  namespace="/crud/qual" action="delete"/>
        <s:url var="addUrl" escapeAmp="false"  namespace="/crud/qual" action="add"/>
        <s:url var="updateUrl" escapeAmp="false"  namespace="/crud/qual" action="update"/>
        <script>
            var totalEntryCount = 0;
            var start = 1; //first line number on page
            var count = 10; //number of lines per page display
            var end = count; //last line number on page
            
            $(document).ready(function(){
                $(".add").click(addButtonHandler); 
                $( "#tabs" ).tabs();
                
                calculateTableLineCount();
                
                buildTable(start - 1, end);
                
                $("#goToNext").click(goToNext); //function declaration
                $("#goToEnd").click(goToEnd); //function declaration
                $("#goToPrevious").click(goToPrevious); //function declaration
                $("#goToStart").click(goToStart); //function declaration
               

            })
            
            var buildTable = function(start){
                $.getJSON('<s:property escape="false" value="pageUrl"/>'+"?start="+start+"&count="+count, function(data){
                    
                    $("#testTable").html('');
                    
                    var nRows = data.length;
                    
                    //TODO: check data and data[0] are greater than 0
                    var nCols = Object.keys(data[0]).length;
                    
                    //get index of id property in database
                    var idIndex=-1;
                    for(var i=0;i<data.length;i++){
                        if(data[i].name==="id"){idIndex=i;break;}
                    }
                    
                    
                    var jTable = $("<table>");
                    //make sure to do data checking
                    var jTHead = $("<thead>");
                    $(jTable).append(jTHead);
                    var jTHeadRow = $("<tr>");
                    $(jTHead).append(jTHeadRow);
                    //$(jTable).append(jTHead);
                    for(var key in data[0]){
                        var jHTd = $("<th>").html(key);
                        $(jTHeadRow).append(jHTd);
                        
                    }
                    for(row=0; row < nRows; row++){
                        
                        var jRow = $("<tr>"); 
                        $(jTable).append(jRow);
                        
                        for(var key in data[row]){
                            //alert(data[row][key]);
                            var jTd = $("<td>").html(data[row][key]);
                            //if(key==="id"){
                            $(jTd).addClass(key); 
                            //}
                            $(jRow).append(jTd);                        
                        }
                        $(jRow).append("  <button class='copy'  >Copy</button>");
                        $(jRow).append("<button class='delete'>Delete</button>");
                        $(jRow).append("<button class='edit'  >Edit</button>");
                    }
                    $("#testTable").append(jTable);
                    $("button.delete").click(deleteButtonHandler); //function declaration to make it work on refresh
                    $("button.copy").click(copyButtonHandler); //function declaration to make it work on refresh
                    $("button.edit").click(editButtonHandler); //function declaration to make it work on refresh
                    
                });
                 
            };
            var goToNext = function(){
                //should print table empty in results 
                if(((start + count) < totalEntryCount + 1)){
                    
                    start += count; //check if not going past end
                    end = start + count - 1; //check if not going past end
                }
                
                
                if(end>totalEntryCount+1){                  
                    end = totalEntryCount;
                    start = end - (end%count);
                    //alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
                    
                }
                //set table display
                $("#tableStartNumber").text(start); //to convert array to line number
                $("#tableEndNumber").text(end);
                $("#totalEntryCount").text(totalEntryCount);
                
                buildTable(start - 1, end);
            }
            var goToPrevious = function(){
                //should print table empty in results 
                start -= count; //check if not going past start
                end = start + count - 1; //check if not going past start
                
                //check to see if end is smaller that the number of lines per page
                if(start<=1){
                    start = 1; 
                    //check for if end is bigger than count
                    end = count;
                    if(end>totalEntryCount){                  
                        end = totalEntryCount;
                    }
                }
                
                //set table display
                $("#tableStartNumber").text(start); //to convert array to line number
                $("#tableEndNumber").text(end);
                $("#totalEntryCount").text(totalEntryCount);     
                
                buildTable(start-1, end);
            }
            var goToStart = function(){
                start = 1;
                end = count;
                if(end>totalEntryCount){                  
                    end = totalEntryCount;
                }
                //set table display
                $("#tableStartNumber").text(start); //to convert array to line number
                $("#tableEndNumber").text(end);
                $("#totalEntryCount").text(totalEntryCount);     
                
                buildTable(start - 1, end);
            }
            var goToEnd = function(){

                end = totalEntryCount;
                if(end-count < 0){
                    start = 1;
                }else{
                    if(count == 1){
                        start = end;
                    }
                    else{
                        start = end - (end%count) - 1;
                    }
                }
                //alert("after...start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
                //set table display
                $("#tableStartNumber").text(start); //to convert array to line number
                $("#tableEndNumber").text(end);
                $("#totalEntryCount").text(totalEntryCount);     
                
                buildTable(start -1, end);
            }    
            var deleteButtonHandler = function(){
                
                var number = $(this).parent().children(".id").first().html();
                deleteLineById(number);
                //alert("delete number:"+number);   
               
            }
            var deleteLineById = function(number){
                    
                $.getJSON('<s:property escape="false" value="deleteUrl"/>'+"?id="+number, function(data){
                    if(data.message ==='error'){
                        alert("error:"+data.message);
                            
                            
                    }else{
                        calculateTableLineCount();
                        buildTable(start -1, end);
                    }
                });
            }
            var copyButtonHandler = function(){
                
                var number = $(this).parent().children(".id").first().html();
                copyLineById(number);
                //alert("delete number:"+number);   
               
            }
            var copyLineById = function(number){
                    
                $.getJSON(
                '<s:property escape="false" value="addUrl"/>', 
                {description: "cow",
                    name:"dog",
                    role:"horse"
                },
                function(data){
                    
                    
                    if(data.message ==='error'){
                        alert("error:"+data.message);
                            
                            
                    }else{
                        calculateTableLineCount();
                        //fix this function need asynchronous receiving of data
                        setTimeout(goToEnd, 600);
                        
                        //buildTable(start -1, end);
                    }
                });
                
            }
            var calculateTableLineCount = function(){
                $.getJSON('<s:property escape="false" value="countUrl"/>', function(data){
                    totalEntryCount = data;
                    //alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
                    //test if catches error properly
                    if(totalEntryCount==0){
                        //should print table empty in results
                        alert("empty table");
                        end = 0;
                        // alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
                    }
                    if(totalEntryCount<count){
                        //figure out what to do
                        end = totalEntryCount;
                        //alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
                    }
                    
                    $("#tableStartNumber").text(start); //to convert array to line number
                    $("#tableEndNumber").text(end);
                    $("#totalEntryCount").text(totalEntryCount);
                        
                    // alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);                   
                });
            }
            
            var addButtonHandler = function(){

                $('#dialog-add').removeClass("hidden");
                $( "#dialog-add" ).dialog({
                    height: 500,
                    modal: true,
                    buttons: {
                        "Update": function() {
                            
                            $.getJSON(
                            '<s:property escape="false" value="addUrl"/>', 
                            {description: $("#addDescription").val(),
                                name:$("#addName").val(),
                                role:$("#addRole").val()
                            },
                            function(){
                                //fix this function need asynchronous receiving of data
                                setTimeout(goToEnd, 600);
                            });
                            ///ADD ERROR CORRECTING CODE TO HANDLE ERROR MESSAGE
                            $( this ).dialog( "close" );                            
                        },
                        Cancel: function() {
                            $( this ).dialog( "close" );
                        }
                    }
                });
                
                
            }
            
            var editButtonHandler = function(){
                //make call to lightbox make pop up with empty boxes
                //when press save send to server
                //edit qualification form pop up
                var description = $(this).parent().children(".description").first();
                $("#description").val($(this).parent().children(".description").first().html());
                var name = $(this).parent().children(".name").first();
                $("#name").val($(this).parent().children(".name").first().html());
                var role = $(this).parent().children(".role").first();
                $("#role").val($(this).parent().children(".role").first().html());
               
                var idInSelectedRow = $(this).parent().children(".id").first().html();
               
                //alert($("#de"));scription
                //KEN THINKS THAT THIS DIALOG BOX has weird properties 
                $('#dialog-edit').removeClass("hidden");
                $( "#dialog-edit" ).dialog({
                    height: 500,
                    modal: true,
                    buttons: {
                        "Update": function() {
                            
                            $.getJSON('<s:property escape="false" value="updateUrl"/>',
                            {
                                id: idInSelectedRow,
                                description: $("#description").val(),
                                name: $("#name").val(),
                                role: $("#role").val()
                            });
                            ///ADD ERROR CORRECTING CODE TO HANDLE ERROR MESSAGE
                            $( this ).dialog( "close" );
                            
                            $(description).html($('#description').val());
                            $(name).html($('#role').val());
                            $(role).html($('#name').val());
                            
                        },
                        Cancel: function() {
                            $( this ).dialog( "close" );
                        }
                    }
                });
                
            }
        </script>
        <style>
            table
            {
                border-width: 0 0 1px 1px;
                border-spacing: 0;
                border-collapse: collapse;
                border-style: solid;
            }

            td,th
            {
                margin: 0;
                padding: 4px;
                border-width: 1px 1px 0 0;
                border-style: solid;
            }
            #dialog-edit, #dialog-add{
                background: white;
            }

            .hidden{
                visibility: hidden;
                display: none;
            }



            /*dialog box properties*/
            .ui-widget-overlay { 
                background: #000000/*{bgColorOverlay}*/; 
                opacity: .3;
                filter:Alpha(Opacity=30)/*{opacityOverlay}*/; 
            }
            .ui-dialog .ui-dialog-buttonpane {
                background: white;
                border-width: 0px 0 0;
                margin: 0em 0 0;
                padding: 0em 0em 0em 0em;
                text-align: left;
            }
            .ui-dialog {
                padding: 0em;
            }
            /*fixes font issues with header bar in pop up dialog box*/
            .ui-dialog-titlebar{
                font-family: Trebuchet MS,Tahoma,Verdana,Arial,sans-serif;
                font-size: 20px;
                font-weight: normal;
                letter-spacing: -1px;
                line-height: 18px;
                padding: 0px;
                z-index: 0;
                color: white;
            }
            /*fix css in dialog box button*/
            .ui-state-default {
                background: white;
                border: 1px solid #CCCCCC;
                color: #1C94C4;
                font-weight: bold;
            }

        </style>

    </head>
    <body>

        <%--  website header --%>
        <div id="wrapper">
            <div id="content">
                <nav>
                    <ul id="navBar">
                        <li><s:a cssClass="group" value="/index">Employment System</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/listResumes001">Resume</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/listQualificationForms002">Qualification</s:a></li>                       
                        <li><s:a cssClass="group" value="/enoch/designDocument">Design</s:a></li>              
                        <li><s:a cssClass="group" value="/index" title="time">
                                <script type="text/javascript">
                                    var mydate=new Date()
                                    var year=mydate.getFullYear()
                                    var day=mydate.getDay()
                                    var month=mydate.getMonth()
                                    var daym=mydate.getDate()
                                    //if the current date is less than 10, pad it.
                                    if (daym<10)
                                        daym="0"+daym
                                    var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
                                    var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December")
                                    //write out the final results
                                    document.write(dayarray[day]+", "+montharray[month]+" "+daym+", "+year)
                                </script>  
                            </s:a></li>
                    </ul>
                </nav>
                <%--  end website header --%>
                <div><%-- website body --%>
                    <br>
                    <script>
                        $(function() {
                            $( "#tabs" ).tabs();
                        });
                    </script>

                    <br>
                    <h1>Qualification</h1>  
                    <p> 


                    <div id="dialog-edit" class="hidden" class="boxHeader" title="Edit Qualification Form">
                        <p>DESCRIPTION:</p><input id="description" type="text" size="25" value="description"><br>
                        <p>NAME:</p><input id="name" type="text" size="25" value="name"><br>
                        <p>ROLE:</p><input id="role" type="text" size="25" value="role"><br>
                    </div>

                    <div id="dialog-add" class="hidden" class="boxHeader" title="Add Qualification Form">
                        <p>DESCRIPTION:</p><input id="addDescription" type="text" size="25" value="description"><br>
                        <p>NAME:</p><input id="addName" type="text" size="25" value="name"><br>
                        <p>ROLE:</p><input id="addRole" type="text" size="25" value="role"><br>
                    </div>

                    <form> 
                        <input type=button class="add" value="New Qualification Form">
                    </form> 
                    </p>
                    <br>
                    <%--  Contact Info --%>
                    <div class="boxHeader">
                        <h2 class="boxHeader">Qualification Forms: 
                            <span id="tableStartNumber"></span> to <span id="tableEndNumber"></span> of <span id="totalEntryCount"></span>
                            <button type="button" id="goToStart">|&lt;</button>
                            <button type="button" id="goToPrevious">&lt;</button>
                            <button type="button" id="goToNext">&gt;</button>
                            <button type="button" id="goToEnd">&gt;|</button>

                        </h2>
                    </div>
                    <div class="boxBody">
                        <div class="boxInterior"> 
                            <div id="testTable"></div>
                            <br>
                        </div>
                    </div>
                    <br>
                </div><%-- end website body --%>

                <div> <%--  website footer --%>
                    <br>
                    <p class="siteFooter">
                        <br>© 2012, Scrotum Solutions Inc.<br>
                        All Rights Reserved.
                    </p>
                </div> <%--  end website footer --%>
            </div>
    </body>
</html>
