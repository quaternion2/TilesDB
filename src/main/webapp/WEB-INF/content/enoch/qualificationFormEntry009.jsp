<%-- 
    Document   : qualificationFormEntry3
    Created on : Aug 30, 2012, 10:29:36 AM
    Author     : bob
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./../style/cssreset-min.css" rel="stylesheet" type="text/css">
        <link href="./../style/cssbase-min.css" rel="stylesheet" type="text/css">
        <link href="./../style/cssfonts-min.css" rel="stylesheet" type="text/css">
        <link href="./../style/grids-min.css" rel="stylesheet" type="text/css">
        <link href="./../style/style.css" rel="stylesheet" type="text/css">
        <script src="./../script/jquery/1.8.1/jquery.min.js"></script>
        <title>Employment System - Qualification Form Entry</title>
        <script>
            //set global variables
            var formArray = []; //array used to hold all of the entered data
            var formLineNumber = 0; //used to make 2D array
            var numFieldsPerLine = 4; //number of fields per line used for formArray[]
            
            
            $(document).ready(function(){
                $("#saveAddLineButton").click(saveAddLineButton);
            });   
            
            var saveAddLineButton = function(){
                $('#qualificationFormEntryTable tr:last').after(function() {
                   
                    //transfer values from form input box into temporary array
                    var tempfield = [];
                    tempfield[0]= $("#lineItem1").val();
                    tempfield[1]= $("#lineItem2").val();
                    tempfield[2]= $("#lineItem3").val();
                    tempfield[3]= $("#lineItem4").val();
                    
                    //fill up the qualification form array with the new data
                    for(i=0; i < tempfield.length; i+=1){
                        formArray.splice(i+formLineNumber, 0, tempfield[i]);
                    } 
                    //increment the line number by amount of input boxes per line
                    formLineNumber += 4;

                    //append contents of newly saved row to the existing table
                    return '<tr><td>' + $("#lineItem1").val() + '</td>' +
                        '<td>' + $("#lineItem2").val() + '</td>' +
                        '<td>' + $("#lineItem3").val() + '</td>' +
                        '<td>' + $("#lineItem4").val() + '</td>' +
                        
                        //make edit row button
                    '<td><input type="button" value="EDIT" onclick="editRow(this)"></td>'+
                        //make delete row button
                    '<td><input type="button" value="DELETE" onclick="deleteRow(this)"></td></tr>';                   
                });
            };
            
            $(document).ready(function(){
                $("#displayArrayButton").click(displayArrayButton);
            });   
            
            //display the contents of the qualification form
            var displayArrayButton = function(){
                //empty the array to repopulate it correctly
                $('#arrayText').empty();//empties array but keeps its larger size; is this needed???
                $('#arrayText').append('Array Contents:<br>');
                for(i=0; i < formArray.length; i+=1){
                    $('#arrayText').before(function() {
                        $('#arrayText').append(' formArray['+i +']= <b>'+ formArray[i]+ '</b> ');
                        //make a line break after all elements in line are displayed
                        if(i%numFieldsPerLine == 3){
                            $('#arrayText').append('<br>');   
                        }
                    });   
                }   
            };       
            
            function deleteRow(r)
            {
                var i=r.parentNode.parentNode.rowIndex;
                document.getElementById('qualificationFormEntryTable').deleteRow(i);
                formArray.splice(((i-1)*4), 4); 
                //alert("array element"+((i-1)*4)+" to " + (((i-1)*4)+4) +"was deleted."+"array length:"+formArray.length);               
            }
            
            
        </script>
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
                        <li><s:a cssClass="group" namespace="" value="/design/index">Design</s:a></li>              
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
                
                
                
                <%--  website main page --%>
                <br>
                <h1>Qualification Form Entry</h1>
                <br>
                <div class="boxHeader"><h2 class="boxHeader">Qualification Form Entry</h2></div>
                <div class="boxBody">
                    <div class="boxInterior"> 
                        <table id ="qualificationFormEntryTable" cellspacing="0"  border="1">
                            <tr>
                                <th>REQUIREMENT #</th>
                                <th>SKILL DESCRIPTION</th>
                                <th>EXPERIENCE REQUIRED</th>
                                <th>CREDIT FOR ADDITIONAL EXPERIENCE</th>
                            </tr>
                            
                        </table> 
                        <table>
                            <tr>
                                <td><input id="lineItem1" type="text" size="25" value="field-1"></td>
                                <td><input id="lineItem2" type="text" size="25" value="field-2"></td>
                                <td><input id="lineItem3" type="text" size="25" value="field-3"></td>
                                <td><input id="lineItem4" type="text" size="25" value="field-4"></td>
                                <td>
                                    <button id="saveAddLineButton">SAVE + ADD LINE</button>
                                </td>
                            <tr>
                            
                        </table>
                    </div>
                </div>




                <button id="displayArrayButton">DISPLAY ARRAY</button>
                <p id="arrayText"><br>Array Contents:<br></p>
                <ul>
                    <li>should be able to name the qualification form</li>
                    <li>need to convert to draggable + reorderable</li>
                    <li></li>
                    <li></li>
                </ul>
                <div> <%--  website footer --%>
                    <br>
                    <p class="siteFooter">
                        <br>© 2012, Scrotum Solutions Inc.<br>
                        All Rights Reserved.
                    </p>
                </div> <%--  end website footer --%>

                </body>
                </html>