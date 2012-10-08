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
        <s:a namespace="/design" action="enoch-ttd">Enoch TTD</s:a> - Enochs tasks<br/>
        <s:a namespace="/design" action="ken-ttd">Ken TTD</s:a> - Kens tasks<br/>

        <div style="color:#FF0000">
            <h1>RED ALERT HELP SECTION</h1>
            <p>Ken... when you made #content.li{} in the style.css thats what fucked up the header on all the pages...I quoted it out if you want to reimplement what you were doing with a different variable name</p>
        </div>
    </body>
</html>
