<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>国土资源局档案数字化管理系统</title>
</head>

<frameset id="index" rows="35,*" cols="*" frameborder="no" border="0" framespacing="0">
<frame src="../main/yxtop" name="tFrame" scrolling="No" noresize="noresize" id="tFrame" title="tFrame" />
  <frameset cols="250,10,*" frameborder="no" border="0" framespacing="0" id="oa_frame">
    <frame src="../main/yxml?dangAnHao=${dangAnHao}&pageNum=${pageNum}&dalb=${dalb}" name="lFrame" scrolling="yes" noresize="noresize" id="lFrame" title="lFrame" />
    <frame src="../user/middle" name="mFrame" scrolling="No" noresize="noresize" id="mFrame" title="mFrame" />
    <frame src="../main/yxxs?dangAnHao=${dangAnHao}&tmId=${tmId}&dalb=${dalb}" name="picFrame" scrolling="yes" id="picFrame" title="picFrame" />
  </frameset>
</frameset>
<noframes>
</noframes>
<body>
</body>
</html>
