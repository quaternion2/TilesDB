<%-- 
    Document   : pager
    Created on : 27-Sep-2012, 9:16:38 PM
    Author     : bob
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="./../style/style.css" rel="stylesheet" type="text/css">
        <script src="../script/jquery/1.8.1/jquery.min.js"></script>

        <title>JSP Page</title>

        <s:url var="pageUrl" escapeAmp="false"  namespace="/crud/qual" action="page">
            <s:param name="start" value="0"/>
            <s:param name="count" value="10"/>
        </s:url>
        <script>
            
            $(document).ready(function(){
       
                $.getJSON('<s:property escape="false" value="pageUrl"/>', function(data){
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
                        var jHTd = $("<td>").html(key);
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
                
                $("#goToNext").click(goToNext);

            })
            
            
            var goToNext = function(){
                alert("balls deep");      
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
                        <li><a href="http://EmploymentSystem.com" title="main menu">Employment System</a></li>
                        <li><a href="http://EmploymentSystem.com/resume" title="resume">Resume</a></li>
                        <li><a href="http://EmploymentSystem.com/qualification" title="qualification">Qualification</a></li>
                        <li><a href="http://EmploymentSystem.com/other" title="other">Other</a></li>              
                        <li><a href="http://EmploymentSystem.com/support" title="time">
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
                                </script>  </a></li>
                    </ul>
                </nav>
                <%--  end website header --%>
                <div><%-- website body --%>
                    <br>
                    <h1>Pager</h1>  
                    <br>
                    <%--  Contact Info --%>
                    <div class="boxHeader"><h2 class="boxHeader">Qualification form header list</h2></div>
                    <div class="boxBody">
                        <div class="boxInterior"> 
                            <div id="pageBar">
                                <table>
                                    <tr>
                                        <td>table records 1 to 5 of 231</td>
                                        <td><button type="button" id="goToStart">|&lt;</button></td>
                                        <td><button type="button" id="goToPrevious">&lt;</button></td>
                                        <td><button type="button" id="goToNext">&gt;</button></td>
                                        <td><button type="button" id="goToEnd">&gt;|</button></td>
                                    </tr>
                                </table>
                            </div>
                            <div id="testTable">

                            </div>
                            <br>
                        </div>
                    </div>
                    <br>
                </div><%-- end website body --%>
                dd
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
