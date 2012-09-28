<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./../style/style.css" rel="stylesheet" type="text/css">
        <script src="../script/jquery/1.8.1/jquery.min.js"></script>

        <title>Employment System - List Resume</title>
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
                    <h1>View Resume</h1>  
                    <br> 
                            <ul>
                                <li>enter to do stuff here</li>
                            </ul>
                    <br>

                </div><%-- end website body --%>

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
