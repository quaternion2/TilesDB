<%@taglib prefix="s" uri="/struts-tags"%>
<%--  website main page --%>
<div class ="outer content">
    <br><br><h1>Main Menu</h1><br><br>
    <div class="yui3-g">
        <div class="yui3-u-1-2">
            <h2>Recruiter Tools</h2>
            <ul>
                <li><s:a cssClass="group" namespace="/candidate" action="index">Candidate - Index</s:a></li>
                <li><s:a cssClass="group" namespace="/candidate" action="search-input">Candidate - Find</s:a></li>
                <li><s:a cssClass="group" namespace="/candidate" action="list">Candidate - List</s:a></li>
            </ul>
        </div>    

        <div class="yui3-u-1-2">
            <h2>Qualification Form Services</h2>
            <ul>
                <li><s:a cssClass="group" value="/enoch/qualificationFormEntry010">Enter New Qualification Form</s:a></li>
                <li><s:a cssClass="group" value="/enoch/listQualificationForms004">List Qualification Forms</s:a></li>
                <li><s:a cssClass="group" value="/enoch/viewQualificationForm">View Qualification Form</s:a></li>
            </ul>
        </div>


        <div class="yui3-u-1-2">
            <h2>Resume Services</h2>
            <ul>
                <li><s:a cssClass="group" value="/enoch/resumeEntry007">Enter New Resume</s:a></li>
                <li><s:a cssClass="group" value="/enoch/listResumes001">List Resumes</s:a></li>
                <li><s:a cssClass="group" value="/enoch/viewResume">View Resume</s:a></li>
            </ul>
        </div>
        <br><br><br>
        <div class="yui3-u-1-2">
            <h2>Ken's Stuff</h2>
            <ul>

                <li><s:a cssClass="group" value="/config-browser/actionNames.action">config-browser</s:a></li>
                <li><s:a cssClass="group" value="/crud/qual/read.action?id=9">Crud- Read - qual - id 9</s:a></li>
                <li><s:a cssClass="group" value="/crud/qual-line/read.action?id=9">Crud - Read - qual-line - id 9, shows hyphen (qual-line) to camel case conversion (accessing QualLine class)</s:a></li>
                <li><s:a cssClass="group" value="/crud/qual/page?start=0&count=10">Crud- Paged Read - qual - start 0, count 10</s:a></li>
                <li><s:a cssClass="group" value="/crud/qual/count">Crud - count - total quals</s:a></li>
                <li><s:a cssClass="group" value="/crud/qual/add.action?description=the description&name=the name&role=the role">Crud - write - add new qual header</s:a></li>
                <li><s:a cssClass="group" value="/crud/qual/delete.action?id=13">Crud - delete by id</s:a></li>
                <li><s:a cssClass="group" value="/crud/list-tables">List all DataBase Tables</s:a></li>
            </ul>
        </div>
        <div class="yui3-u-1-2">
            <h2>Enoch's Stuff</h2>
            <ul>
                <li><s:a cssClass="group" value="/enoch/qualificationFormEntry7">qualificationFormEntry7</s:a></li>
                <li><s:a cssClass="group" value="/enoch/resumeEntry005">resumeEntry005</s:a></li>
                <li><s:a cssClass="group" value="/enoch/test">test</s:a></li>
                <li><s:a cssClass="group" value="/enoch/pager">pager</s:a></li>
                <li><s:a cssClass="group" value="/script/lightbox/index.jsp">lightbox</s:a></li>
                <li><s:a cssClass="group" value="/enoch/merge001">merge001</s:a></li>
                <li><s:a cssClass="group" value="/enoch/recruiter001">recruiter001</s:a></li>
            </ul>
        </div>
        <div class="yui3-u-1-2">
            <h2>HTML FORMS</h2>
            <ul>
                <li><s:a cssClass="group" value="/html/list-qualification-forms">html list qualification form</s:a></li>
                <li><s:a cssClass="group" namespace="/html/candidate" action="list">New Candidate</s:a></li> 
            </ul>
        </div>
        <div class="yui3-u-1-2">
            <h2>OTHER (tests)</h2>
            <ul>
                <li><s:a cssClass="group" namespace="/test" action="tiles">/test/tiles</s:a></li>
            </ul>
        </div>
        <div class="yui3-u-1-2">
            <h2>Candidate</h2>
            <ul>
                <li>Manage Candidates</li>
                <li>New Candidate</li>
                <li>Search Candidates</li>
            </ul>
        </div>
    </div>
</div>
