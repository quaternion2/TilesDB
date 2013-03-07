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
              <%--  end website header --%>

                <div><%-- website body --%>
                    <br>
                    <h1>Qualification Form List</h1>  
                    <br>
                    <%--  Contact Info --%>
                    <div class="boxHeader"><h2 class="boxHeader">Qualification Form List</h2></div>
                    <div class="boxBody">
                        <div class="boxInterior"> 
                            <table cellspacing="0"  border="1">
                                <tr>
                                    <th>Resume Name</th>
                                    <th>Last Updated</th>
                                    <th>Resume Options</th>
                                </tr>
                                <tr>
                                    <td><s:a cssClass="group" value="/enoch/doesnotexist.jsp">Senior Programmer #42525</s:a></td>
                                    <td>Sept 11, 2012 9:11AM EST</td>
                                    <td>
                                        <button>Rename</button>
                                        <button>Edit</button>
                                        <button>Copy</button>
                                        <button>Delete</button>
                                        <button>Export</button>
                                        <button>Send</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td><s:a cssClass="group" value="/enoch/doesnotexist.jsp">Marketing Director #Ageg32</s:a></td>
                                    <td>Sept 11, 2012 9:11AM EST</td>
                                    <td>
                                        <button>Rename</button>
                                        <img src="../image/edit.png"/>
                                        <img src="../image/copy.png"/>
                                        <img src="../image/delete.png"/>
                                        <img src="../image/mail.png"/>
                                        <img src="../image/print.png"/>
                                      
                                    </td>
                                </tr>
                            </table>
                           
                            <ul>
                                <li>have option buttons for every line</li>
                                <li>have submenus for export and send</li>
                                <li>export to: txt, rtf, Word, Pdf</li>
                                <li>send to:email, fax, print, distribute</li>
                            </ul>
                            <br>
                        </div>
                    </div>
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
