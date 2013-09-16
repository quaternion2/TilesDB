<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--  website header --%>
<div id="wrapper">
    <%--  end website header --%>
    <div id="content">
        <div><%-- website body --%>
            <h1>Design Document</h1>
            <p>The design should take into account: Requirements, How the feature will look (basic interface), how the feature will be tested (basic description of test).</p>

            <hr/>
            <h1>Requirements</h1>
            <p>When referring to requirements will use the following numbering and append E, D or N where <strong>Essential[E], Desirable[D], Nice To have[N]</strong>. So CRUD features for qualification form headers is currently E1.1</p>
            <h2>Essential</h2>
            <ol>
                <li>Qualification forms
                    <ol>
                        <li>qualification headers - Add, Delete, Update, List</li>
                        <li>qualification lines - Add, Delete, Update, List</li>
                    </ol>
                </li>
                <li>Resumes
                    <ol>
                        <li>resume headers - Add, Delete, Update, List</li>
                        <li>resume lines - Add, Delete, Update, List</li>
                    </ol>
                </li>
                <li>Apply to Position (Merge qual - resume)
                    <ol>
                        <li>Select resume lines for each qualification</li>
                    </ol>
                </li>
            </ol>
            <h2>Desirable</h2>
            <ol>
                <li></li>
            </ol>
            <h2>Nice to have </h2>
            <ol>
                <li></li>
            </ol>
            <hr/>
            <h1>Release Schedule</h1>
            <h3>To Implement for Version 0.5</h3>
            E1.1 - E1.2, E2.1 - E2.2
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
            <hr/>
            <h1>Testing</h1>
            <p>Needs to address server and client side testing for each requirement</p>
            <hr/>
            <h1>Here down is old stuff should be moved into other sections</h1>

            <h4>Qualification+Resume System</h4>
            <ul>
                <li>Ability to select the parts of the resume to match up with the item in the qualification form</li>
                <li>resume is in accordion form and can select to automatically fill out the qualification form using check boxes in front of the line</li>
                <li>make a save to PDF option</li>
            </ul>
        </div><%-- end website body --%>
    </div>
</div>