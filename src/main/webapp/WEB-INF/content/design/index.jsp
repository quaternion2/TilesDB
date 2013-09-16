<%-- 
    Document   : index
    Created on : 30-Sep-2012, 5:55:24 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Design Index</title>
    </head>
    <body>
        <h1>Design Index</h1>
        <p>We each have a section to put our lists of <em>things to do</em> so they don't 
            clutter the design document. Red alert section is best reserved when we need to 
            get each others attention.
        </p>
        <s:a namespace="/design" action="agenda">Agenda for meetings</s:a> - What we need to talk about next time we meet<br/>
        <s:a namespace="/design" action="design-document">Design Document</s:a> - Requirements, How realized, Test<br/>
        <s:a namespace="/design" action="conventions">Conventions</s:a> - Conventions used in the system<br/>
        <s:a namespace="/design" action="style-guide">Style Guide</s:a> - HTML, CSS and JS notes<br/>
        <s:a namespace="/design" action="ken-ttd">Ken TTD</s:a> - Kens tasks<br/>

        <div style="color:#FF0000">
            <h1>RED ALERT HELP SECTION</h1>
            <ul>
                <li>Fix Resume section
                    <ul>
                        <li>Unsaved resume lines don't sum!</li>
                        <li>Ask the user if they want to order by date, allow role lines to be dragged into different positions</li>
                        <li>Allow copy of months column from resume</li>
                        <li>Have formatting rules apply to months column as well as final value</li>
                        <li>Add formating rule for decimal years (in addition to existing: Years,Mos and Mos)</li>
                        <li>TAGS: for roles (as opposed to "title" which is user defined) and technology</li>
                    </ul>
                </li>
                <li>Qualification section
                    <ul>
                        <li>Set up Qual</li>
                        <li>Associate Qual with client (client not required)</li>
                        <li>Associate Qual with opportunity</li>
                        <li>Associate resumes with quals (the merge)</li>
                        <li>Reporting and Query based on merge</li>
                    </ul>
                </li>
                <li>Set up role based security</li>
                <li>Set up DB table and field level security</li>
            </ul>
            <p></p>
        </div>
    </body>
</html>
