
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

        <script>
            var totalEntryCount = 0;
            var start = 1; //first line number on page
            var count = 3; //number of lines per page display
            var end = count; //last line number on page
            
            $(document).ready(function(){
                $( "#tabs" ).tabs();
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

    </head>
    <body>




<div class="demo">

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Nunc tincidunt</a></li>
		<li><a href="#tabs-2">Proin dolor</a></li>
		<li><a href="#tabs-3">Aenean lacinia</a></li>
	</ul>
	<div id="tabs-1">
		<p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
	</div>
	<div id="tabs-2">
		<p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
	</div>
	<div id="tabs-3">
		<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
		<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
	</div>
</div>

</div><!-- End demo -->



<div style="display: none;" class="demo-description">
<p>Click tabs to swap between content that is broken into logical sections.</p>
</div><!-- End demo-description -->
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



<div class="demo">

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Nunc tincidunt</a></li>
		<li><a href="#tabs-2">Proin dolor</a></li>
		<li><a href="#tabs-3">Aenean lacinia</a></li>
	</ul>
	<div id="tabs-1">
		<p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
	</div>
	<div id="tabs-2">
		<p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
	</div>
	<div id="tabs-3">
		<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
		<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
	</div>
</div>

</div><!-- End demo -->



<div style="display: none;" class="demo-description">
<p>Click tabs to swap between content that is broken into logical sections.</p>
</div><!-- End demo-description -->
                    <br>
                    <h1>Qualification</h1>  
                    <p> <button>New Qualification Form</button></p>
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
