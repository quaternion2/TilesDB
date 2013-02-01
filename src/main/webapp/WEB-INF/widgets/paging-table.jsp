<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="wrapper">
    <div id="content">
        <div><%-- website body --%>
            <br>
            <script>
                $(function() {
                    $( "#tabs" ).tabs();
                });
            </script>
            <br>
            <h1>Qualification</h1>  
            <div> 
                <div id="dialog-edit" class="hidden, boxHeader" title="Edit Qualification Form">
                    <p>DESCRIPTION:</p><input id="description" type="text" size="25" value="description"><br>
                    <p>NAME:</p><input id="name" type="text" size="25" value="name"><br>
                    <p>ROLE:</p><input id="role" type="text" size="25" value="role"><br>
                </div>
                <div id="dialog-add" class="hidden, boxHeader" title="Add Qualification Form">
                    <p>DESCRIPTION:</p><input id="addDescription" type="text" size="25" value="description"><br>
                    <p>NAME:</p><input id="addName" type="text" size="25" value="name"><br>
                    <p>ROLE:</p><input id="addRole" type="text" size="25" value="role"><br>
                </div>
                <form> 
                    <input type=button class="add" value="New Qualification Form">
                </form> 
            </div>
            <br>
            <%--  Contact Info --%>
            <div class="boxHeader">
                <h2 class="boxHeader">Qualification Forms: 
                    <span id="tableStartNumber"></span> to <span id="tableEndNumber"></span> of <span id="totalEntryCount"></span>
                    <button type="button" id="goToStart">|&lt;</button>
                    <button type="button" id="goToPrevious">&lt;</button>
                    <button type="button" id="goToNext">&gt;</button>
                    <button type="button" id="goToEnd">&gt;|</button>
                </h2>
            </div>
            <div class="boxBody">
                <div class="boxInterior"> 
                    <div id="testTable"></div>
                    <br>
                </div>
            </div>
            <br>
        </div>
    </div>
</div>