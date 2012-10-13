
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
        <script>
            var totalEntryCount = 0;
            var start = 1; //first line number on page
            var count = 10; //number of lines per page display
            var end = count; //last line number on page
            
            $(document).ready(function(){
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
                            if(key==="id"){
                                $(jTd).addClass("id"); 
                            }
                            $(jRow).append(jTd);                        
                        }
                        
                        $(jRow).append("<button class='delete'>Delete</button>");
                        $(jRow).append("<button class='copy'>Copy<button>");
                        $(jRow).append("<button class='edit'>Edit<button>");
                    }
                    $("#testTable").append(jTable);
                    $("button.delete").click(deleteButtonHandler); //function declaration to make it work on refresh
                    $("button.copy").click(copyButtonHandler); //function declaration to make it work on refresh
                    $("button.edit").click(editButtonHandler); //function declaration to make it work on refresh
                    $("button.add").click(addButtonHandler); //DOES NOT BELONG HERE MOVE IT LATER
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
                //make call to lightbox make pop up with empty boxes
                //when press save send to server
                
            }
            
            var editButtonHandler = function(){
                //make call to lightbox make pop up with empty boxes
                //when press save send to server
                alert("in edit");
            }
        </script>
        <style>
            td{
                border: thin solid #ffa500;

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


                    <form> 
                        <input type=button 
                               value="New Qualification Form"
                               onClick="self.location='qualificationFormEntry010'">
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
