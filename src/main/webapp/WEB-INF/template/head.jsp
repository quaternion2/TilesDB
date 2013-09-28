<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%><%@taglib prefix="s" uri="/struts-tags"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><tiles:importAttribute name="cssList"/><tiles:importAttribute name="jsList"/><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <s:iterator value="#attr.cssList" var="cssValue"><link href="<s:url value="%{cssValue}"/>" rel="stylesheet" type="text/css">
    </s:iterator><script>
        var rootContextUrl = '/EmplymentSystem';//TODO(E): Don't hardcode determine on server
        var currentContextUrl = '<s:url forceAddSchemeHostAndPort="true" includeContext="true" value=""/>';
    </script>
    <s:iterator value="#attr.jsList" var="jsValue"><script src="<s:url value="%{jsValue}"/>"></script>
    </s:iterator><title><tiles:insertAttribute name="title" defaultValue="no title"/></title>
</head>
