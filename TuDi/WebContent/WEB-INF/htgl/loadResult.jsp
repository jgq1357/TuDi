<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<p align="center">
${resultMessage}
</p>
<br>
<p align="center">
<input type="button" name="first" value="回首页" onclick="window.location.href('../user/main')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="load" value="继续导入数据" onclick="window.location.href('../htgl/psjdr')"/>
</p>
</body>
</html>