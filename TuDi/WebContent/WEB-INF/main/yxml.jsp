<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="../js/tree.js"></script>
<script type="text/javascript"  src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/prototype1.6.js"></script>
<script type="text/javascript">
function getImage1(dangAnHao, tmId, pageNum, next,tmTotal) {
	$('dj'+next).style.background = '#aceadd';
	var index = 0;	
	while(index < tmTotal) {
			if(index!=next) {
			$('dj'+index).style.background = '#eceadd';
			}
		index = index+1;
	}
}

function init(dalb) {
	$('dj0').style.background = '#aceadd';
}

function print(allow,dangAnHao,dalb) {
	if(allow=="Y") {
		window.open('../main/printset?dangAnHao='+dangAnHao+'&dalb='+dalb);
	}else {
		alert("对不起，您没有权限打印！");
	}
}

</script>

</head>

<body onload="init('${dalb}')">

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top" class="td03"><br>
      <table width="90%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="25" class="F01"><p><img src="../images/index_35.gif" width="12" height="16" align="absmiddle"><strong>国土资源档案查看</strong></p>
          </td>
        </tr>
        <tr>
          <td height="2" background="../images/index_39.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
      <table width="90%" border="0" cellpadding="0" cellspacing="0" class="td01">
          <tr>
            <td width="12">&nbsp;</td>
            <td width="628"><p><img src="../images/spacer.gif" width="1" height="1"/><img src="../images/index_35.gif" width="12" height="16" align="absmiddle"/><strong>影像目录</strong></p></td>
          </tr>
          <tr>
            <td background="../images/index_41.gif">&nbsp;</td>
            <td>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
               	<td>
                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                   <s:iterator value="djljs" status="djlj">
						<tr>
							<td id="dj${order_num}" style="background: #eceadd"><a href="../main/yxxs?dangAnHao=${dangAnHao}&tmId=${lj}&dalb=${dalb}" target="picFrame" onclick="getImage1('${dangAnHao}','${lj}',${pageNum},${order_num},${tmAmount})" ><font size="3">${tm}</font> </a></td>
						</tr>
						<tr>
             			 <td height="1" background="../images/main_57.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
            			</tr>
					</s:iterator>
					<tr>
             			 <td height="1"><img src="../images/spacer.gif" height="10"></td>
            		</tr>
                 </table>
                 
				  <table width="100%" border="0" cellspacing="0" cellpadding="0">
				 
			
				  	<s:if test="dalb=='djyw'">
                        <tr>
                          <td nowrap><input id="button" type="button" value="显示档案属性" onclick="window.open('showDjywAllInfo.action?dah=${dangAnHao}')"></input></td>
                        </tr>
                    </s:if>
                    
                   	<s:if test="dalb=='gtzyjcl'">
                        <tr>
                          <td nowrap><input id="button" type="button" value="显示档案属性" onclick="window.open('showGtzyjclAllInfo.action?dah=${dangAnHao}')"></input></td>
                        </tr>
                    </s:if>
                    
                    <s:if test="dalb=='tdlyghl'">
                        <tr>
                          <td nowrap><input id="button" type="button" value="显示档案属性" onclick="window.open('showTdlyghlAllInfo.action?dah=${dangAnHao}')"></input></td>
                        </tr>
                    </s:if>
                    
                    <s:if test="dalb=='jsydl'">
                        <tr>
                          <td nowrap><input id="button" type="button" value="显示档案属性" onclick="window.open('showJsydlAllInfo.action?dah=${dangAnHao}')"></input></td>
                        </tr>
                    </s:if>
                    
                    <s:if test="dalb=='dkl'">
                        <tr>
                          <td nowrap><input id="button" type="button" value="显示档案属性" onclick="window.open('showDklAllInfo.action?dah=${dangAnHao}')"></input></td>
                        </tr>
                    </s:if>
                    
                 	<s:if test="dalb=='zhwsl'">
                        <tr>
                          <td nowrap><input id="button" type="button" value="显示档案属性" onclick="window.open('showZhwslAllInfo.action?dah=${dangAnHao}')"></input></td>
                        </tr>
                    </s:if>
          
          
                    <tr>
             			 <td height="1"><img src="../images/spacer.gif" height="10"></td>
            		</tr>
                  </table>
                   
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

