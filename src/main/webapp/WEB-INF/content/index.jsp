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
        <div class="siteHeader">
            <div class ="siteHeaderName"  
                 onmouseover="style.color='#FFFFFF'"
                 onmouseout="style.color='#DDDDDD'" >
                EMPLOYMENT SYSTEM </div>
            <div class ="searchBar">
                <input type="text" name="firstname"/>
            </div>
            <div class ="resumeLink">
                <div>Resume</div>
            </div>
            <div class ="qualificationLink">
                <div>Qualification</div>
            </div>

            <div class ="loginName">
                Login
            </div>
            <div class ="date">
                <script language="javascript">
                    <!--
                    var today = new Date();
                    document.write(today);
                    //-->
                </script>
            </div>
        </div>

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
                        <li></li>
                    </ul>
                </div>
                <div class="yui3-u-1-2">
                    <h2>TTD</h2>
                    <ul>
                        <li>Delete qualification lines</li>
                        <li>Edit qualification form description</li>
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
