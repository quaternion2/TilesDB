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
        <link href="./style/style.css" rel="stylesheet" type="text/css">
        <title>Employment System</title>
    </head>
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
        
        <%--  website main page --%>
        <div class ="outer">
            <br><br><h1>Main Menu</h1><br><br>

            <div class="yui3-g">

                <div class="yui3-u-1-2">
                    <h2>Qualification Form Services</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/enoch/qualificationFormEntry009">Enter New Qualification Form</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/listQualificationForms002">List Qualification Forms</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/viewQualificationForm">View Qualification Form</s:a></li>
                    </ul>
                </div>


                <div class="yui3-u-1-2">
                    <h2>Resume Services</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/enoch/resumeEntry007">Enter New Resume</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/listResumes001">List Resumes</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/viewResume">View Resume</s:a></li>
                    </ul>
                </div>
                <br><br><br>
                <div class="yui3-u-1-2">
                    <h2>Ken's Stuff</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/crud/qual/read.action?id=9">Crud- Read - qual - id 9</s:a></li>
                        <li><s:a cssClass="group" value="/crud/qual/page?start=0&count=10">Crud- Paged Read - qual - start 0, count 10</s:a></li>
                    </ul>
                </div>
                <div class="yui3-u-1-2">
                    <h2>Enoch's Stuff</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/enoch/qualificationFormEntry7">qualificationFormEntry7</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/resumeEntry005">resumeEntry005</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/test">test</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/pager">pager</s:a></li>
                    </ul>
                </div>

                <br><br><br>  

                <div class="yui3-u-1-2">
                    <h1 style="color:#FF0000">RED ALERT HELP SECTION</h1>

                    <p style="color:#FF0000">all crud read operations should only return records from that table(don't recursively descend)</p>
                    <p style="color:#FF0000">read operations should only return lines from the table</p>
                    <p style="color:#FF0000">include parameters should only return associated collections</p>
                    <p style="color:#FF0000">exclude parameters should work on individual fields</p>
                    <br><br><br>
                    
                </div>
                <%--  website footer --%>
                <div>
                    <p class="siteFooter">
                        <br>© 2012, Scrotum Solutions Inc.<br>
                        All Rights Reserved.
                    </p>
                </div>
                <%--  end website footer --%>
            </div>
        </div>
    </body>
</html>
