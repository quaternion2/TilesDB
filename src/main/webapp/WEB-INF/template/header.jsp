<%@taglib prefix="s" uri="/struts-tags"%>
<ul id="navBar">
    <li><s:a cssClass="group" value="/index">Employment System</s:a></li>
    <li><s:a cssClass="group" value="/enoch/listResumes001">Resume</s:a></li>
    <li><s:a cssClass="group" value="/enoch/listQualificationForms002">Qualification</s:a></li>                       
    <li><s:a cssClass="group" namespace="" value="/design/index">Design</s:a></li>              
    <li>
        <s:if test="#session['user'] != null">
            <s:a cssClass="group" value="/">
                <s:property value="'Welcome, ' +  #session['user'].name"/>
            </s:a>
        </s:if>
        <s:else>
            <s:a cssClass="group"  namespace="/login" action="login-input">Login</s:a>
        </s:else>
    </li>
</ul>