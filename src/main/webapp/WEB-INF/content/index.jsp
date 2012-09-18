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
    <body>

        <%--  website header --%>
        <div class="siteHeader">
            <span class ="menuItem">&nbsp Employment System &nbsp &nbsp</span>
            <span class ="menuItem">&nbsp <s:a class ="noUnderlineNoColor" value="/enoch/resumeEntry005.jsp">Resume</s:a> &nbsp</span>
            <span class ="menuItem">&nbsp <a class ="noUnderlineNoColor"href="http://www.w3schools.com">Qualification</a> &nbsp</span>
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

            
            <div class="yui3-g">
                <div class="yui3-u-1-2 enoch">
                    <h1>Qual Services</h1>
                    <ul>
                        <li><s:a namespace="/qual" action="list-quals">List Qualification Forms</s:a></li>
                        <li><s:a namespace="/qual" action="new-qual-input">New Qualification Form</s:a></li>
                    </ul>
                </div>
                <div class="yui3-u-1-2">
                    <h1>Resume Services</h1>
                    <ul>
                        <li><s:a namespace="/resume" action="list-resumes">List Qualification Forms</s:a></li>
                        <li><s:a namespace="/resume" action="new-resume-input">New Qualification Form</s:a></li>
                    </ul>
                </div>
                <div class="yui3-u-1-2">
                    <h1>New/In Testing</h1>
                    <ul>
                        <li><s:a value="/crud/qual/read.action?id=9">Crud - qual - id 9</s:a></li>
                    </ul>
                </div>
                <div class="yui3-u-1-2">
                    <h1>Enochs Stuff</h1>
                    <ul>
                        <li><s:a value="/enoch/qualificationFormEntry7.jsp">qualificationFormEntry7</s:a></li>
                        <li><s:a value="/enoch/resumeEntry005.jsp">resumeEntry005</s:a></li>
                    </ul>
                </div>
                <div class="yui3-u-1-2">
                    <h2>TTD</h2>
                    <ul>
                        <li>Delete qualification lines</li>
                        <li>Edit qualification form description</li>
                        <li>make thin nice professional gradient toolbar in style of ubuntu</li>
                        <li>make text in header bar bold white and highlight when you go over it</li>
                        <li>make links work to go to main pages in site</li>
                    </ul>
                </div>
                <div class="yui3-u-1-2">

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
