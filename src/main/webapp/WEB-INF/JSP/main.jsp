<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<title><tiles:insertAttribute name="title" ignore="true"/></title>--%>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="resources/booklet/jquery.easing.1.3.js" type="text/javascript"></script>
    <script src="resources/booklet/jquery.booklet.1.1.0.min.js" type="text/javascript"></script>

    <link href="resources/booklet/jquery.booklet.1.1.0.css" type="text/css" rel="stylesheet" media="screen" />
    <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="resources/css/caffee_menu.css" type="text/css" />
    <link rel="stylesheet" href="resources/css/all_pages.css" type="text/css" />


    <script src="resources/cufon/cufon-yui.js" type="text/javascript"></script>
    <script src="resources/cufon/RodeoExtraBold_400.font.js" type="text/javascript"></script>
    <script src="resources/cufon/Betina_400.font.js" type="text/javascript"></script>
    <script type="text/javascript">
        Cufon.replace('h1,p,.b-counter');
        Cufon.replace('.book_wrapper a', {hover:true});
        Cufon.replace('.title', {textShadow: '1px 1px #C59471', fontFamily:'RodeoExtraBold'});
        Cufon.replace('.reference a', {textShadow: '1px 1px #C59471', fontFamily:'RodeoExtraBold'});
        Cufon.replace('.loading', {textShadow: '1px 1px #000', fontFamily:'RodeoExtraBold'});
    </script>
</head>
<body>
    <table border="0" cellpadding="0" cellspacing="0" style=" top: 0; bottom: 0; left: 0; right: 0;">
        <tr>
            <td style="height:100px; right:0; vertical-align: top; padding-top:0px;" colspan="2"><tiles:insertAttribute name="header" />
            </td>
        </tr>
        <tr>
            <td style="width: 200px; top:0; left:0; vertical-align: top; padding-top:50px;"><tiles:insertAttribute name="menu" /></td>
            <td style="width: 100%; top:0; vertical-align: top; padding-top:0px;"><tiles:insertAttribute name="body" /></td>
        </tr>
    </table>
</body>
</html>