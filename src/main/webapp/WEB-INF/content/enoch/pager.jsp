<%-- 
    Document   : pager
    Created on : 27-Sep-2012, 9:16:38 PM
    Author     : bob
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="./style/cssreset-min.css" rel="stylesheet" type="text/css">
        <link href="./style/cssbase-min.css" rel="stylesheet" type="text/css">
        <link href="./style/cssfonts-min.css" rel="stylesheet" type="text/css">
        <link href="./style/grids-min.css" rel="stylesheet" type="text/css">
        <link href="./../style/main.css" rel="stylesheet" type="text/css">
        <script src="../script/jquery/1.8.1/jquery.min.js"></script>
        <s:url var="pageUrl" escapeAmp="false"  namespace="/crud/qual" action="page">
            <s:param name="start" value="0"/>
            <s:param name="count" value="10"/>
        </s:url>
        <script>
            
            $(document).ready(function(){
       
                $.getJSON('<s:property escape="false" value="pageUrl"/>', function(data){
                    var nRows = data.length;
                    
                    //TODO: check data and data[0] are greater than 0
                    var nCols = Object.keys(data[0]).length;
                    
                    var jTable = $("<table>");
                    //make sure to do data checking
                    var jTHead = $("<thead>");
                    $(jTable).append(jTHead);
                    var jTHeadRow = $("<tr>");
                    $(jTHead).append(jTHeadRow);
                     //$(jTable).append(jTHead);
                    for(var key in data[0]){
                        var jHTd = $("<td>").html(key);
                        $(jTHeadRow).append(jHTd);
                        
                    }
                    for(row=0; row < nRows; row++){
                        
                        var jRow = $("<tr>"); 
                            $(jTable).append(jRow);
                        
                       for(var key in data[row]){
                           //alert(data[row][key]);
                           var jTd = $("<td>").html(data[row][key]);
                          $(jRow).append(jTd);
                          //alert("data "+ jTable);
                       }
                    }
                    $("#testTable").append(jTable);
                });
            })
        </script>
        <style>
            td{
                border: thin solid #ffa500;
                
            }
        </style>
        
    </head>
    <body>
        <h1>Pager</h1>
        <div id="testTable">
            
        </div>
    </body>
</html>
