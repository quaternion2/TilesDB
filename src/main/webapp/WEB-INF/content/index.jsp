<%-- 
    Document   : index
    Created on : 23-Aug-2012, 10:54:20 PM
    Author     : ken, enoch
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./style/cssreset-min.css" rel="stylesheet" type="text/css">
        <link href="./style/cssbase-min.css" rel="stylesheet" type="text/css">
        <link href="./style/cssfonts-min.css" rel="stylesheet" type="text/css">
        <link href="./style/grids-min.css" rel="stylesheet" type="text/css">
        <link href="./style/main.css" rel="stylesheet" type="text/css">
        <title>Employment System</title>
    </head>
    <body bgcolor="#DDDDDD">

        <%--  website header --%>
        <div class="siteHeader">
            <span class ="menuItem">&nbsp Employment System &nbsp &nbsp</span>
            <span class ="menuItem">&nbsp <s:a cssClass="noUnderlineNoColor" value="/enoch/resumeEntry005.jsp">Resume</s:a> &nbsp</span>
            <span class ="menuItem">&nbsp <s:a cssClass="noUnderlineNoColor" value="/enoch/qualificationFormEntry7.jsp">Qualification</s:a> &nbsp</span>
            <span class ="menuItem">&nbsp Other &nbsp</span>
            <div class ="dateItem">
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
                    document.write("<b>"+dayarray[day]+", "+montharray[month]+" "+daym+", "+year+"</b>")
                </script>
            </div>
        </div>
        <%--  website main page --%>
        <div class ="outer">
            <br><br><h1>Test Programs & To Do List</h1><br><br>

            <div class="yui3-g">

                <div class="yui3-u-1-2">
                        <h2>Qualification Form Services</h2>
                        <ul>
                            <li><s:a cssClass="group" value="/enoch/qualificationFormEntry008.jsp">Enter New Qualification Form</s:a></li>
                            <li><s:a cssClass="group" namespace="/qual" action="list-quals">List Qualification Forms</s:a></li>
                            <li><s:a cssClass="group" namespace="/qual" action="new-qual-input">Ken's New Qualification Form</s:a></li>
                        </ul>
                </div>


                <div class="yui3-u-1-2">
                    <h2>Resume Services</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/enoch/resumeEntry006.jsp">Enter New Resume</s:a></li>
                        <li><s:a cssClass="group" namespace="/resume" action="list-resumes">List Resumes</s:a></li>
                        <li><s:a cssClass="group" namespace="/resume" action="new-resume-input">Ken's New Resume</s:a></li>
                    </ul>
                </div>
                    <br><br><br>
                <div class="yui3-u-1-2">
                    <h2>Ken's Stuff</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/crud/qual/read.action?id=9">Crud - qual - id 9</s:a></li>
                    </ul>
                </div>
                <div class="yui3-u-1-2">
                    <h2>Enoch's Stuff</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/enoch/qualificationFormEntry7.jsp">qualificationFormEntry7</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/resumeEntry005.jsp">resumeEntry005</s:a></li>
                    </ul>
                </div>
                    
                <br><br><br>  
                <div class="yui3-u-1-2">
                    
                    <h2>To Do List</h2>
                    <ul>
                        <li>Delete qualification lines</li>
                        <li>Edit qualification form description</li>
                        <li><strike>make thin nice professional gradient toolbar in style of ubuntu</strike></li>
                        <li><strike>make text in header bar bold white and highlight when you go over it</strike></li>
                        <li><strike>make links work to go to main pages in site</strike></li>
                        <li>fix date glitch when shrinking window</li>
                        <li>make a login system</li>
                        <li>make a qualification form to resume matching page</li>
                        <li>make a save to PDF option</li>
                        <li>make the ability to move around objects by dragging and dropping them</li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
            </div>
            <div>
                <p class="siteFooter">
                    <br>© 2012, Scrotum Solutions Inc.<br>
                    All Rights Reserved.
                </p>
            </div>
        </div>
    </body>
</html>
