<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" style="position: absolute; top: 0; bottom: 0; left: 0; right: 0;">
    <tr>
        <td style="height:30; width: 200; position: relative; right:0" colspan="0"><tiles:insertAttribute name="header" />
        </td>
    </tr>
    <tr>
        <td style="height: 90%; width: 200; position: relative; top:0; left:0"><tiles:insertAttribute name="menu" /></td>
        <td style="height: 90%; width: 100%; top:0; position: relative;"><tiles:insertAttribute name="body" /></td>
    </tr>
    <%--<tr>
        <td height="30" colspan="2"><tiles:insertAttribute name="footer" />
        </td>
    </tr>--%>
</table>
</body>
</html>