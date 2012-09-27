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
    <body>

        <%--  website header --%>
        <%--<div class="siteHeader">
            <span class ="menuItem">&nbsp Employment System &nbsp &nbsp</span>
            <span class ="menuItem">&nbsp <s:a cssClass="noUnderlineNoColor" value="/enoch/resumeEntry006.jsp">Resume</s:a> &nbsp</span>
            <span class ="menuItem">&nbsp <s:a cssClass="noUnderlineNoColor" value="/enoch/qualificationFormEntry008.jsp">Qualification</s:a> &nbsp</span>
            <span class ="menuItem">&nbsp Other &nbsp</span>
        </div>
        <%--  end website header --%>
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
        
        <%--  website main page --%>
        <div class ="outer">
            <br><br><h1>Test Programs & Design Documents</h1><br><br>

            <div class="yui3-g">

                <div class="yui3-u-1-2">
                    <h2>Qualification Form Services</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/enoch/qualificationFormEntry009.jsp">Enter New Qualification Form (Enoch)</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/listQualificationForms001.jsp">List Qualification Forms (Enoch)</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/viewQualificationForm.jsp">View Qualification Form (Enoch)</s:a></li>
                        <li><s:a cssClass="group" namespace="/qual" action="list-quals">List Qualification Forms</s:a></li>
                        <li><s:a cssClass="group" namespace="/qual" action="new-qual-input">Ken's New Qualification Form</s:a></li>
                    </ul>
                </div>


                <div class="yui3-u-1-2">
                    <h2>Resume Services</h2>
                    <ul>
                        <li><s:a cssClass="group" value="/enoch/resumeEntry007.jsp">Enter New Resume(Enoch)</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/listResumes001.jsp">List Resumes(Enoch)</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/viewResume.jsp">View Resume(Enoch)</s:a></li>
                        <li><s:a cssClass="group" namespace="/resume" action="list-resumes">List Resumes(Ken)</s:a></li>
                        <li><s:a cssClass="group" namespace="/resume" action="new-resume-input">Ken's New Resume</s:a></li>
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
                        <li><s:a cssClass="group" value="/enoch/qualificationFormEntry7.jsp">qualificationFormEntry7</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/resumeEntry005.jsp">resumeEntry005</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/test.jsp">test</s:a></li>
                    </ul>
                </div>

                <br><br><br>  

                <div class="yui3-u-1-2">
                    <h1 style="color:#FF0000">RED ALERT HELP SECTION</h1>

                    <p style="color:#FF0000">enter problems here</p>
                    <br><br><br>
                    <h2>Design Document</h2>

                    <h3>To Implement for Version 0.5</h3>

                    <h4>General</h4>
                    <ul>
                        <li>ensure same look and feel on every page (best effort until Apache Tiles is implemented)</li>
                        <li><strike>make thin nice professional gradient toolbar in style of ubuntu</strike></li>
                        <li><strike>make text in header bar bold white and highlight when you go over it</strike></li>
                        <li><strike>make links work to go to main pages in site</strike></li>
                        <li>fix date glitch when shrinking window</li>
                    </ul>
                    <h4>Kens Generic CRUD Progress</h4>
                    Note: with the addition of this heading some of the following points are obsolete. That is crud which is directed at a particular entity. 
                    <ul>
                        <li>Create - class, parameters (use paramsPrepareParamsStack, fish out type construct entity, nest all properties under "entity" property)-> return object with id</li>
                        <li><strike>Read - Both single read and paging have been implemented</strike></li>
                        <li>Update - by id (use paramsPrepareParamsStack)</li>
                        <li>Delete - by id</li>
                    </ul>
                    <h4>Qualification Form Entry System</h4>
                    <ul>
                        <li>Ability to C.<strike>R</strike>.U.D.</li>
                        <li>Ability to drag and move items around (use JQuery)</li>
                    </ul>

                    <h4>Resume Entry System</h4>
                    <ul>
                        <li>Ability to C.<strike>R</strike>.U.D.</li>
                        <li>Ability to drag and move items around (use JQuery)</li>
                    </ul>

                    <h4>Qualification+Resume System</h4>
                    <ul>
                        <li>Ability to select the parts of the resume to match up with the item in the qualification form</li>
                        <li>resume is in accordion form and can select to automatically fill out the qualification form using check boxes in front of the line</li>
                        <li>make a save to PDF option</li>
                    </ul>

                    <h3>To Implement for Version 1.0</h3>
                    <ul>
                        <li>make a login system</li>
                        <li>need tagging system for job roles that are universal so its easy to compare them</li>
                        <li>search function</li>
                        <li>make a save to PDF option (deferred from version 0.5)</li>
                    </ul>
                    <h3>To Implement for Version 2.0</h3>
                    <ul>
                        <li>enter ideas here</li> 
                    </ul>
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
