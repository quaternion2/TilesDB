<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <tiles:insertAttribute name="head"/>
    <body>
        <%--  website header --%>
        <div id="wrapper">
            <div>
                <tiles:insertAttribute name="header"/>
                <tiles:insertAttribute name="body"/>
                <div class ="outer content">
                    <tiles:insertAttribute name="footer"/>
                </div>
            </div>
        </div>
    </body>
</html>

