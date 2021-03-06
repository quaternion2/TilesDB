<%-- 
    Document   : pager
    Created on : 27-Sep-2012, 9:16:38 PM
    Author     : bob
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script>
    <s:url var="pageUrl" escapeAmp="false"  namespace="/crud/qual" action="page"/>
    <s:url var="countUrl" escapeAmp="false"  namespace="/crud/qual" action="count"/>
        var totalEntryCount = 0;
        var start = 1; //first line number on page
        var count = 3; //number of lines per page display
        var end = count; //last line number on page
            
        $(document).ready(function(){
                
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
                        $(jRow).append(jTd);
                        //alert("data "+ jTable);
                    }
                }
                $("#testTable").append(jTable);
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
            
</script>
<style>
    td{
        border: thin solid #ffa500;

    }
</style>
<div><%-- website body --%>
    <br>
    <h1>Qualification</h1>  
    <p> <button>New Qualification Form</button></p>
    <br>
    <%--  Contact Info --%>
    <div class="boxHeader"><h2 class="boxHeader">Qualification Forms</h2></div>
    <div class="boxBody">
        <div class="boxInterior"> 
            <div id="pageBar">
                <table>
                    <tr>
                        <td>table records <span id="tableStartNumber"></span> to <span id="tableEndNumber"></span> of <span id="totalEntryCount"></span></td>
                        <td><button type="button" id="goToStart">|&lt;</button></td>
                        <td><button type="button" id="goToPrevious">&lt;</button></td>
                        <td><button type="button" id="goToNext">&gt;</button></td>
                        <td><button type="button" id="goToEnd">&gt;|</button></td>
                    </tr>
                </table>
            </div>
            <div id="testTable"></div>
            <br>
        </div>
    </div>
    <br>
</div><%-- end website body --%>
