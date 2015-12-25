<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>国土资源局档案数字化管理系统</title>
</head>

<frameset id="index" rows="78,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="250,10,*" frameborder="no" border="0" framespacing="0" id="oa_frame">
    <frame src="left" name="leftFrame" scrolling="yes" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="middle" name="middleFrame" scrolling="No" noresize="noresize" id="middleFrame" title="middleFrame" />
    
    <frameset rows="50%,*" frameborder="no" border="0" framespacing="0" id="oa_frame_1">
	    <frame src="../main/presearch?daType=3" name="mainFrame" scrolling="yes" id="mainFrame" title="mainFrame" />
	    <frame src="../main/search" name="searchResult" scrolling="Yes" noresize="noresize" id="searchResult" title="searchResult" />
  </frameset>
  </frameset>
</frameset>
<noframes>
</noframes>
<body>
</body>
</html>
