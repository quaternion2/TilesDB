<%@taglib prefix="s" uri="/struts-tags"%>
<div><%-- website body --%>
    <br>
    <h1>Resume List</h1>  
    <br>
    <%--  Contact Info --%>
    <div class="boxHeader"><h2 class="boxHeader">Resume List</h2></div>
    <div class="boxBody">
        <div class="boxInterior"> 
            <table cellspacing="0"  border="1">
                <tr>
                    <th>Resume Name</th>
                    <th>Last Updated</th>
                    <th>Resume Options</th>
                </tr>
                <tr>
                    <td><s:a cssClass="group" value="/enoch/doesnotexist.jsp">Afaf Malik</s:a></td>
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
                        <td><s:a cssClass="group" value="/enoch/doesnotexist.jsp">Alonso Poof</s:a></td>
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

