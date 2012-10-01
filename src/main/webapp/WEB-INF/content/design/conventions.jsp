<%-- 
    Document   : conventions
    Created on : 30-Sep-2012, 6:22:28 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conventions</title>
        <style>
            .attention{
                color:red;
            }
        </style>
    </head>
    <body>
        <h1>Conventions</h1>
        <p>This document outlines the conventions used in the program.</p>
        <p><strong>Note:</strong> the red points are to attract attention, remove the attention classes after reading.</p>
        
        <ul>
            <li class="attention">JSPs: use design-document.jsp and not designDocument.jsp</li>
            <li>JAVA: Follow java style guidelines within Java: All ways use camel case (jsps are not java), classes always start with a capital, variables are always lower case, packages are always lowercase.</li>
            <li>HTML: always use lowercase letters in tags</li>
            <li>FOLDERS: use lowercase to start, use camel case, but try to stick to single words</li>
            <li class="attention">DB: We need to establish formal conventions for DB, most notably not using reserved SQL works for tables (which I have a bad tendency of doing)</li> 
            
        </ul>
        <div ></div>
</html>
