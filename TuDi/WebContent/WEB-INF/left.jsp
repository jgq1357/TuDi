<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="../js/tree.js"></script>
</head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top" class="td03"><br>
      <table width="90%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="25" class="F01"><p><img src="../images/index_35.gif" width="12" height="16" align="absmiddle"><strong>国土资源档案管理</strong></p>
          </td>
        </tr>
        <tr>
          <td height="2" background="../images/index_39.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
      <table width="90%" border="0" cellpadding="0" cellspacing="0" class="td01">
          <tr>
            <td width="12">&nbsp;</td>
            <td width="628"><p><img src="../images/spacer.gif" width="1" height="1"/><img src="../images/index_35.gif" width="12" height="16" align="absmiddle"/><strong>快速通道</strong></p></td>
          </tr>
          <tr>
            <td background="../images/index_41.gif">&nbsp;</td>
            <td>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
               	<td>
                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                   <tr>
                      <td width="19"><img id="top_1" onClick="menu('menu_1','top_1');" src="../images/tree_11.gif" width="19" height="20"/></td>
                      <td width="19"><img src="../images/tree_27.gif" width="19" height="20"/></td>
                      <td nowrap>档案检索</td>
                   </tr>
                 </table>
                 <div id="menu_1" style="DISPLAY: none">
				 <s:iterator value="dajss" var="dajs">
				    <table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td width="19"><img src="../images/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="${dajs.link }" target="mainFrame" class="a03">${dajs.subname}</a></td>
						  </tr>
				    </table>
				</s:iterator>
				</div>  
				  
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="19"><img id="top_3" onClick="menu('menu_3','top_3');" src="../images/tree_11.gif" width="19" height="20"></td>
                          <td width="19"><img src="../images/tree_27.gif" width="19" height="20"></td>
                          <td nowrap>借阅管理</td>
                        </tr>
                  </table>
				  <div id="menu_3" style="DISPLAY: none">
					<s:iterator value="jygls" var="jygl">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td width="19"><img src="../images/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="${jygl.link }" target="mainFrame" class="a03">${jygl.subname}</a></td>
						  </tr>
						</table>
					</s:iterator>
				  </div>
                       
					  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="19"><img id="top_4" onClick="menu('menu_4','top_4');" src="../images/tree_11.gif" width="19" height="20"></td>
                          <td width="19"><img src="../images/tree_27.gif" width="19" height="20"></td>
                          <td nowrap>档案室专栏</td>
                        </tr>
                      </table>
				  <div id="menu_4" style="DISPLAY: none">
					<s:iterator value="daszls" var="daszl">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td width="19"><img src="../images/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="${daszl.link }" target="mainFrame" class="a03">${daszl.subname}</a></td>
						  </tr>
						</table>
					</s:iterator>
				  </div>
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="19"><img id="top_5" onClick="menu('menu_5','top_5');" src="../images/tree_11.gif" width="19" height="20"></td>
                          <td width="19"><img src="../images/tree_27.gif" width="19" height="20"></td>
                          <td nowrap>系统维护</td>
                        </tr>
                      </table>
				  <div id="menu_5" style="DISPLAY: none">
					<s:iterator value="xtwhs" var="xtwh">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td width="19"><img src="../images/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="${xtwh.link }" target="mainFrame" class="a03">${xtwh.subname}</a></td>
						  </tr>
						</table>
					</s:iterator>
				  </div>
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="19"><img id="top_6" onClick="menu('menu_6','top_6');" src="../images/tree_11.gif" width="19" height="20"></td>
                          <td width="19"><img src="../images/tree_27.gif" width="19" height="20"></td>
                          <td nowrap>后台管理</td>
                        </tr>
                      </table>
				  <div id="menu_6" style="DISPLAY: none">
					<s:iterator value="htgls" var="htgl">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td width="19"><img src="../images/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="${htgl.link }" target="mainFrame" class="a03">${htgl.subname}</a></td>
						  </tr>
						</table>
					</s:iterator>
				  </div>
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="19"><img id="top_7" onClick="menu('menu_7','top_7');" src="../images/tree_11.gif" width="19" height="20"></td>
                          <td width="19"><img src="../images/tree_27.gif" width="19" height="20"></td>
                          <td nowrap>统计报表</td>
                        </tr>
                      </table>
				  <div id="menu_7" style="DISPLAY: none">
					<s:iterator value="tjbbs" var="tjbb">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td width="19"><img src="../images/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="../images/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="${tjbb.link }" target="mainFrame" class="a03">${tjbb.subname}</a></td>
						  </tr>
						</table>
					</s:iterator>
				  </div>
				  
                </td>
              </tr>
            </table>
            </td>
            <td width="67" background="../images/index_43.gif">&nbsp;</td>
          </tr>
          <tr>
            <td><img src="../images/index_50.gif" width="12" height="12"></td>
            <td background="../images/index_51.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
            <td><img src="../images/index_53.gif" width="12" height="12"></td>
          </tr>
      </table>
        <br>
    </td>
  </tr>
</table>
</body>
</html>

