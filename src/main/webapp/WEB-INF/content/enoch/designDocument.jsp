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

                <div><%-- website body --%>
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
