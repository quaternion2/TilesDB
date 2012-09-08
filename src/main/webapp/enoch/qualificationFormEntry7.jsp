<%-- 
    Document   : qualificationFormEntry3
    Created on : Aug 30, 2012, 10:29:36 AM
    Author     : bob
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scrotum Solutions - Qualification Form Entry</title>

        <script src="./../script/jquery/1.8.1/jquery.min.js"></script>
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
        <h1>Qualification Form Entry version 7</h1>

        <table id ="qualificationFormEntryTable" cellspacing="0"  border="1">
            <tr>
                <th>REQUIREMENT #</th>
                <th>SKILL DESCRIPTION</th>
                <th>EXPERIENCE REQUIRED</th>
                <th>CREDIT FOR ADDITIONAL EXPERIENCE</th>
            </tr>
        </table> 

        <input id="lineItem1" type="text" size="25" value="">
        <input id="lineItem2" type="text" size="25" value="default">
        <input id="lineItem3" type="text" size="25" value="default">
        <input id="lineItem4" type="text" size="25" value="default">
        <br>
        <button id="saveAddLineButton">SAVE + ADD LINE</button>
        <br>
        <button id="displayArrayButton">DISPLAY ARRAY</button>
        <p id="arrayText"><br>Array Contents:<br></p>
    </body>
</html>