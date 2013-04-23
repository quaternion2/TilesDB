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
        <title>Employment System - Merge</title>
        <script>
            var currentQualLineNumber = 1;
            var totalQualLines = 3;
            var fieldsPerQual = 6;
            
            var qualData = new Array();
            qualData[0] = "1"; //qualification line number
            qualData[1] = "0"; //0 = desirable 1 = manditory
            qualData[2] = "2 years"; //experience required
            qualData[3] = "1";//credit for additional experience
            qualData[4] = "";//experience claimed
            qualData[5] = "";//cross-reference
            
            qualData[6] = "2"; //qualification line number
            qualData[7] = "1"; //0 = desirable 1 = manditory
            qualData[8] = "5 years"; //experience required
            qualData[9] = "0";//credit for additional experience
            qualData[10] = "";//experience claimed
            qualData[11] = "";//cross-reference
            
            qualData[12] = "3"; //qualification line number
            qualData[13] = "0"; //0 = desirable 1 = manditory
            qualData[14] = "4 years"; //experience required
            qualData[15] = "1";//credit for additional experience
            qualData[16] = "";//experience claimed
            qualData[17] = "";//cross-reference
            
            var resumeData = new Array();
            resumeData[0] = "Kentholomeyu McAdams";
            resumeData[1] = "1437 Apple Tree Lane";
            resumeData[2] = "Porridgeville, Michegan";
            resumeData[3] = "kennya@gmail.com";
            resumeData[4] = "1-800-wet-puss";
            resumeData[5] = "Education";
            resumeData[6] = "Alberta College - Business Analyst - 2010";
            resumeData[7] = "Work History";
            resumeData[8] = "Cashier, Staples, Edmonton";
            resumeData[9] = "-stole money from the cash when the boss wasn't looking";
            resumeData[10] = "- in charge of cory and trevor";
            resumeData[11] = "Store Manager, Liquor Depot, Calgary";
            resumeData[12] = "- drank all the profits";
            resumeData[13] = "-responsible for getting customers drunk";
            resumeData[14] = "- cleaned the toilets with own personal tooth brush";

            $(document).ready(function(){
                
                $("#goToNext").click(goToNext); //function declaration
                $("#goToPrevious").click(goToPrevious); //function declaration
                //$("#resumeLine").click(clickedResumeLine); //function declaration
                buildQualTable();
                buildResume();
                buildMergedForm();
            })
            var buildQualTable = function(){
                $("#currentQualLineNumber").text(currentQualLineNumber); //put the line number in the title bar
                $("#qualTable").html(''); //clear the table
                var tableString = '<table><thead><th>Line #</th><th>Manditory</th><th>Exp. Req.</th><th>Credit</th><th>Exp. Claimed</th><th>Cross Reference</th></thead>';   
                /*var currentQualLineNumber = 0;
                var totalQualLines = 3;
                var fieldsPerQual = 6;*/
                tableString += '<tr>';          
                var i = 0;
             
                for(i = (currentQualLineNumber-1)*fieldsPerQual; i<(currentQualLineNumber*fieldsPerQual); i++ ){
                    tableString += '<td>'+qualData[i]+'</td>';   
                }
                
                tableString += '</tr>'; 
                tableString += '</table>';
                $("#qualTable").append(tableString);
                
                buildMergedForm(); //refresh the merged table
            }
            var goToNext = function(){
                if(currentQualLineNumber <= totalQualLines){
                    currentQualLineNumber++;
                }
                if(currentQualLineNumber > totalQualLines){
                    currentQualLineNumber = 1;
                }
                buildQualTable();
            }
            var goToPrevious = function(){
                if(currentQualLineNumber >= 1){
                    currentQualLineNumber--;
                }
                if(currentQualLineNumber < 1){
                    currentQualLineNumber = totalQualLines;
                }
                buildQualTable();
            }
            var buildResume = function(){
                $("#resume").html('');
                var resumeString = '';
                for(i=0; i < resumeData.length; i++){
                    resumeString += '<div id="resumeLine" onclick="clickedResumeLine('+i+');">'+resumeData[i]+'</div><br>';
                    //resumeString += '<div id="resumeLine">'+resumeData[i]+'</div><br>';
                }
                $("#resume").append(resumeString);
            }    
            var clickedResumeLine = function(i) {
                //alert("div#"+i);
                var qualDataCrossReferenceArrayLocation = (currentQualLineNumber * fieldsPerQual) - 1;
                qualData[qualDataCrossReferenceArrayLocation] = resumeData[i];
                buildQualTable();
            }
            var buildMergedForm = function(){
                $("#mergedForm").html('');
                var mergedString = '<table><thead><th>Line #</th><th>Manditory</th><th>Exp. Req.</th><th>Credit</th><th>Exp. Claimed</th><th>Cross Reference</th></thead>';   
                var i = 0;
                for(i = 0; i< qualData.length; i++ ){
                    if(i%fieldsPerQual == 0){
                       mergedString += '<tr>';  
                    }
                    mergedString += '<td>'+qualData[i]+'</td>'; 
                    if(i%fieldsPerQual == 5){
                      mergedString += '</tr>';  
                    }
                }
                mergedString += '</table>';
                $("#mergedForm").append(mergedString);
            }

</script>


</head>

<body>
    <%--  website header --%>
    <div id="wrapper">
        <div id="content" >
            <%--  end website header --%>

            <div class="boxHeader">
                <h2 class="boxHeader">Qualification Forms Requirement # 
                    <span id="currentQualLineNumber"></span> 
                    <button type="button" id="goToPrevious">&lt;</button>
                    <button type="button" id="goToNext">&gt;</button>
                </h2>
            </div>
            <div class="boxBody">
                <div class="boxInterior">
                    <div id="qualTable"></div>                        
                </div>
            </div>
            <br>

            <div class="boxHeader">
                <h2 class="boxHeader">Resume</h2>
            </div>
            <div class="boxBody">
                <div class="boxInterior">
                    <div id="resume"></div>                        
                </div>
            </div>
            <br>



            <%--  website main page --%>
            <br>
            <div class="boxHeader">
                <h2 class="boxHeader">Merged Qualification Form</h2>
            </div>
            <div class="boxBody">
                <div class="boxInterior">
                    <div id="mergedForm"></div>                        
                </div>
            </div>

            <%--<div id="qualheader">
                <table>
                    <thead>Qualification Requirement #3</thead>
                    <tr><td>id</td></tr> 
                    <tr><td>parent qual form</td></tr> 
                    <tr><td>req#</td><td>a</td></tr>
                    <tr><td>description</td><td>b</td></tr>
                    <tr><td>manditory?</td><td>b</td></tr>
                    <tr><td>months</td><td>b</td></tr>
                </table>
            </div>
            <br><br><br>
            <table>
                <thead>Qualification form line</thead>
                <tr><td>aa</td><td>a</td></tr>
                <tr><td>bb</td><td>b</td></tr>
            </table>--%>



            <div> <%--  website footer --%>
                <br>
                <p class="siteFooter">
                    <br>© 2012, Scrotum Solutions Inc.<br>
                    All Rights Reserved.
                </p>
            </div> <%--  end website footer --%>

            </body>
            </html>