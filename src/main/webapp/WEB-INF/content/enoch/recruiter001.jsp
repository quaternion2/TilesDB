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
        <title>Employment System - Recruiter Summary</title>


    </head>

    <body>
        <%--  website header --%>
        <div id="wrapper">
            <div id="content" >
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

                <div class="boxHeader">
                    <h2 class="boxHeader">Qualification Forms</h2>
                </div>
                <div class="boxBody">
                    <div class="boxInterior">
                        <ul>
                            <li>Enter new qualification form</li>
                            <li>Edit/view/search qualification forms</li>
                            <li>Print/pdf/word/email qualification form</li>
                            <li></li>
                        </ul>                     
                    </div>
                </div>
                <br>

                <div class="boxHeader">
                    <h2 class="boxHeader">Resumes</h2>
                </div>
                <div class="boxBody">
                    <div class="boxInterior">
                        <ul>
                            <li>Enter new resume</li>
                            <li>Edit/view/search resumes</li>
                            <li>View different resume versions...some would be modified due to changes done in the merging process</li>
                            <li>Print/pdf/word/email resume</li>
                        </ul>                      
                    </div>
                </div>
                <br>


                <br>
                <div class="boxHeader">
                    <h2 class="boxHeader">Merged Qualification Forms</h2>
                </div>
                <div class="boxBody">
                    <div class="boxInterior">
                        <ul>
                            <li>Set up alerts whenever there is activity on behalf of the recruiter and the client</li>
                            <li>list all currently worked on merged documents</li>
                            <li>timestamp all of these updates on merged documents</li>
                            <li>Add logging system to these merged documents</li>
                        </ul>                      
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
                </html