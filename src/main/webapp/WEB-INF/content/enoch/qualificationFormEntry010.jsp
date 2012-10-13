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
        <s:url var="addUrl" escapeAmp="false"  namespace="/crud/qual" action="add"/>
        <script>
            $(document).ready(function(){
                $("#save").click(saveButtonHandler);
            })
        </script>
    </head>


    <script>
        
        
        var saveButtonHandler = function(){
            //alert("save button pressed");        
            $.getJSON(
            '<s:property escape="false" value="addUrl"/>', 
            {description: $("#description").val(),
                name:$("#name").val(),
                role:$("#role").val()
            },
            function(){
                    
                self.location='listQualificationForms004'; //change to struts
            });
                
        }
    </script>

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

                        <table>
                            <tr>
                                <th>DESCRIPTION</th>
                                <th>NAME</th>
                                <th>ROLE</th>
                            </tr>
                            <tr>
                                <td><input id="description" type="text" size="25" value="description"></td>
                                <td><input id="name" type="text" size="25" value="name"></td>
                                <td><input id="role" type="text" size="25" value="role"></td>

                                <td>
                                    <button type="button" id="save">SAVE</button>

                                </td>
                            <tr>

                        </table>
                    </div>
                </div>

                <div> <%--  website footer --%>
                    <br>
                    <p class="siteFooter">
                        <br>© 2012, Scrotum Solutions Inc.<br>
                        All Rights Reserved.
                    </p>
                </div> <%--  end website footer --%>

                </body>
                </html>