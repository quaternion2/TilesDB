<%@taglib prefix="s" uri="/struts-tags"%>
<ul id="navBar">
    <li><span class="group"><s:a namespace="/" action="index">Home</s:a></span></li>
    <li><span class="group"><s:a namespace="/" action="managermenu">Manager</s:a></span></li>
    <li><span class="group"><s:a namespace="/" action="recruitermenu">Recruiter</s:a></span></li>                       
    <li><span class="group"><s:a namespace="/" action="devmenu">Dev</s:a></span> <span class="group"><s:a namespace="" value="/design/index">Design</s:a></span></li>              
    <li>
        <span class="group">
            <s:if test="#session['USER'] != null">
                <s:a cssClass="group" value="/">
                    <s:property value="'Welcome, ' +  #session['USER'].name + ' '"/>
                </s:a>
                <s:a style="text-decoration: underline;" namespace="/login" action="log-out"> logout</s:a>
            </s:if>
            <s:else>
                <s:a cssClass="group"  namespace="/login" action="login-input">Login</s:a>
            </s:else>
        </span>
    </li>
</ul>