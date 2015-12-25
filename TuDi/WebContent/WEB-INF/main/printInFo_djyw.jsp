<%@page contentType="text/html;charset=utf-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>档案属性</title>
<script type="text/javascript" src="../js/prototype1.6.js">
	
</script>
<script type="text/javascript">
	 function alertWin(title, msg, w, h,id) {
      
            var titleheight = "22px"; // 窗口标题高度

            var bordercolor = "#666699"; //窗口的边框颜色

            var titlecolor = "#FFFFFF"; // 窗口的标题颜色

            var titlebgcolor = "#666699"; // 窗口的标题背景色

            var bgcolor = "#FFFFFF"; // 内容背景色

            var iWidth = document.documentElement.clientWidth; //这个窗口的宽度

            var iHeight = document.documentElement.clientHeight; //这个窗口的高度

            //背景层的格式

            var bgObj = document.createElement("div");

            

            bgObj.style.cssText = "position:absolute;left:0px;top:0px;width:" + iWidth + "px;height:" + Math.max(document.body.clientHeight, iHeight) + "px;filter:Alpha(Opacity=30);opacity:0.3;background-color:#000000;z-index:1000;";

            

            document.body.appendChild(bgObj);

            

            var iframe2 = document.createElement("iframe");

            iframe2.style.cssText = "position:absolute; top:0px;filter:Alpha(Opacity=30);opacity:0.3;background-color:#000000; z-index:1001; border-style:none; border-width:0px; border:0px;width:" + iWidth + "px;height:" + iHeight + "px";

            bgObj.appendChild(iframe2);

           

            //创建一个弹出层

            var msgObj = document.createElement("div");

            //设置弹出的层的样式

            msgObj.style.cssText = "position:absolute;font:11px '宋体';top:" + (iHeight - h) / 2 + "px;left:" + (iWidth - w) / 2 + "px;width:" + w + "px;height:" + h + "px;text-align:center;border:1px solid " + bordercolor + ";background-color:" + bgcolor + ";padding:1px;line-height:22px;z-index:1001;";

            document.body.appendChild(msgObj);

            //创建一个table用于容纳层上的内容

            var table = document.createElement("table");

            //将Table放到弹出层上

            msgObj.appendChild(table);

            //设置table的格式

            table.style.cssText = "margin:0px;border:0px;padding:0px;";

            table.cellSpacing = 0;

            //插入一行用于显示标题

            var tr = table.insertRow(-1);

            //插入一个单元格用于容纳标题

            var titleBar = tr.insertCell(-1);

            titleBar.style.cssText = "width:30;height:" + titleheight + "px;text-align:left;padding:3px;margin:0px;font:bold 13px '宋体';color:" + titlecolor + ";border:1px solid " + bordercolor + ";cursor:move;background-color:" + titlebgcolor;

            titleBar.style.paddingLeft = "10px";

            //设置标题

            titleBar.innerHTML = title;

            //设置曾德拖动事件

            var moveX = 0;

            var moveY = 0;

            var moveTop = 0;

            var moveLeft = 0;

            var moveable = false;

            var docMouseMoveEvent = document.onmousemove;

            var docMouseUpEvent = document.onmouseup;

            //鼠标点击标题

            titleBar.onmousedown = function() {

                var evt = getEvent();

                moveable = true;

                moveX = evt.clientX;

                moveY = evt.clientY;

                moveTop = parseInt(msgObj.style.top);

                moveLeft = parseInt(msgObj.style.left);

                //鼠标拖动

                document.onmousemove = function() {

                    if (moveable) {

                        var evt = getEvent();

                        var x = moveLeft + evt.clientX - moveX;

                        var y = moveTop + evt.clientY - moveY;

                        if (x > 0 && (x + w < iWidth) && y > 0 && (y + h < iHeight)) {

                            msgObj.style.left = x + "px";

                            msgObj.style.top = y + "px";

                        }

                    }

                };

                document.onmouseup = function() {

                    if (moveable) {

                        document.onmousemove = docMouseMoveEvent;

                        document.onmouseup = docMouseUpEvent;

                        moveable = false;

                        moveX = 0;

                        moveY = 0;

                        moveTop = 0;

                        moveLeft = 0;

                    }

                };

            }

            

            //关闭按钮事件

            var closeBtn = tr.insertCell(-1);

            closeBtn.style.cssText = "cursor:pointer; text-align:right;padding:2px;background-color:" + titlebgcolor;

            closeBtn.innerHTML = "<span style='font-size:15pt;color:" + titlecolor + ";'>×</span>";

            closeBtn.onclick = function() {

                document.body.removeChild(bgObj);

                document.body.removeChild(msgObj);

            }

             

            //弹出的消息窗口内容

            var msgBox = table.insertRow(-1).insertCell(-1);

            msgBox.style.cssText = "font:10pt '宋体';";

            msgBox.colSpan = 2;

            //msgBox.innerHTML = msg;

            //层上模板名称的内容

            var nameBox = table.insertRow(-1);

            //var nameLable = nameBox.insertCell(-1);

            //nameLable.style.cssText = "font:12pt '宋体';text-align:left;";

            //nameLable.innerHTML = "&nbsp";

            var nametext = nameBox.insertCell(-1);

            nametext.style.cssText = "font:12pt '宋体';text-align:center; margin-left:0px";
						
			nametext.innerHTML = "<textarea id='content' cols='47' rows='5'>"+msg+"</textarea>";			
			
            //层上动作按钮的内容

            var submitBox = table.insertRow(-1);

            var submitBtn = submitBox.insertCell(-1);

            submitBtn.style.cssText = "text-align:center;margin-left:0px";

            submitBtn.colSpan = 2;

            submitBtn.innerHTML = "<input type='Button' value='确 定'/>";
						
						
						submitBtn.onclick = function() {
								
                var c = document.getElementById("content");
                
             
                var t = document.getElementById(id);
                t.innerText = c.value;
           
                
                document.body.removeChild(bgObj);

								document.body.removeChild(msgObj);
            }
									
            function getEvent() {

                return window.event || arguments.callee.caller.arguments[0];

            }

        }
</script>

</head>
<body bgcolor="#ECF5FF">
	<s:if test='ljxx=="Y"'>
	<center><font size="3"><strong>立卷信息</strong></font></center>
	<table width="70%" border="1" bordercolor="#000000" style="border-collapse:collapse; table-layout:fixed; " cellspacing="0" cellpadding="0" align="center">
		<tr>
		 	<td align="center"><font	size="2" color="#004B97"><strong>全宗号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px;"  onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_1" >${djyw.qzh}</td>
	    <td align="center"><font	size="2" color="#004B97"><strong>目录号</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px;" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_2" >${djyw.mlh}</td>
			<td align="center"><font	size="2" color="#004B97"><strong>案卷号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px;" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_3" >${djyw.ajh}</td>
		</tr>
	</table><br />
	</s:if>
	
	<s:if test='jbxx=="Y"'>
	<center><font size="3"><strong>基本情况</strong>
	
	
	</font></center>
	<table width="70%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse; table-layout:fixed; ">
		<tr>
			<td width="73" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>土地使用者</strong></font>&nbsp;&nbsp;</td><td width="140" nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_4">${djyw.tdsyz}</td>
			<td width="86" align="right" nowrap="nowrap"><font size="2" color="#004B97"><strong>宗地号</strong></font>&nbsp;&nbsp;</td><td width="140" nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_5">${djyw.zdh}</td>
			<td width="60" align="right" nowrap="nowrap"><font size="2" color="#004B97"><strong>土地证号</strong></font>&nbsp;&nbsp;</td><td width="140" nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_6">${djyw.tdzh}</td>
			<td width="99" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>图幅号</strong></font>&nbsp;&nbsp;</td><td width="148" nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_7">${djyw.tfh}</td>
		</tr>
		<tr>
			<td width="73" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>登记类别</strong></font>&nbsp;&nbsp;</td><td width="140" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_8">${djyw.djlb}</td>
			<td width="86" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>使用者性质</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_9">${djyw.syzxz}</td>
			<td width="60" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>权属性质</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_10">${djyw.qsxz}"</td>
			<td width="99" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>使用权性质</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_11">${djyw.syqxz}</td>
		</tr>
		<tr>
			<td width="73" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>使用权类型</strong></font>&nbsp;&nbsp;</td><td width="140" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_12">${djyw.syqlx}</td>
			<td width="86" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>土地用途</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_13">${djyw.tdyt}</td>
			<td width="60" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>地类</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_14">${djyw.dl}</td>
			<td width="99" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>使用年限</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_15">${djyw.synx}</td>
		</tr>
		<tr>
			<td width="73" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>终止日期</strong></font>&nbsp;&nbsp;</td><td width="140" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_16">${djyw.zzrq}</td>
			<td width="86" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>建筑容积率</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_17">${djyw.jzrjl}</td>
			<td width="60" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>建筑密度</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_18">${djyw.jzmd}</td>
			<td width="99" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>建筑类型</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_19">${djyw.jzlx}</td>
	  </tr>
		<tr>
			<td width="73" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>建筑物权属</strong></font>&nbsp;&nbsp;</td><td width="140" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_20">${djyw.jzwqs}</td>
			<td width="86" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>土地等级</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_21">${djyw.tddj}</td>
			<td width="60" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>标定地价</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_22">${djyw.bddj}</td>
			<td width="99" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>申报地价</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_23">${djyw.sbdj}</td>
		</tr>
		<tr>
			<td width="73" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>证件编号</strong></font>&nbsp;&nbsp;</td><td width="140" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_24">${djyw.zjbh}</td>
			<td width="86" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>独自面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_25">${djyw.dzmj}</td>
			<td width="60" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>分摊面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_26">${djyw.ftmj}</td>
			<td width="99" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>使用权面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px; font-weight: normal;" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_27">${djyw.syqmj}</td>
		</tr>
		<tr>
		  <td width="73" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>建筑面积</strong></font>&nbsp;&nbsp;</td><td width="140" nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_28">${djyw.jzmj}</td>
			<td width="86" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>建筑占地面积</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_29">${djyw.jzzdmj}</td>
		  <td width="60" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>土地坐落</strong></font>&nbsp;&nbsp;</td><td colspan="3" align="left" valign="middle" nowrap="nowrap"  style="font-size: 13px;" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_30">${djyw.tdzl}</td>
		</tr>
		<tr>
			<td width="73" align="right" nowrap="nowrap" id="position"><font	size="2" color="#004B97"><strong>东至</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px;" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_31">${djyw.east}</td>
			<td width="60" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>南至</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px; " onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_32">${djyw.south}</td>
		</tr>
		<tr>
			<td width="73" align="right" nowrap="nowrap" id="position"><font	size="2" color="#004B97"><strong>西至</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_33">${djyw.west}</td>
			<td width="60" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>北至</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_34">${djyw.north}</td>
		</tr>
		<tr>
			<td width="73" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>备注</strong></font>&nbsp;&nbsp;</td><td colspan="7" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_35">${djyw.bz}</td>
		</tr>
	
		
</table><br />
		</s:if>
		
		<s:if test='zrxx=="Y"'>
		<center><font size="3"><strong>转让信息</strong></font></center>
		<table width="70%" border="1" bordercolor="#000000" style="border-collapse:collapse; table-layout:fixed; " cellpadding="0" cellspacing="0" align="center">
		<tr>
		  <td width="24%" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>土地使用者</strong></font>&nbsp;&nbsp;</td><td width="24%" nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_36">${djyw.tdsyz}</td>
			<td width="28%" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>原土地使用者</strong></font>&nbsp;&nbsp;</td><td width="24%" nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_37">${djyw.ytdsyz}</td>
		</tr>
		<tr>
			<td align="right" nowrap="nowrap" id="address"><font	size="2" color="#004B97"><strong>土地证号</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_38">${djyw.tdzh}</td>
			<td align="right" nowrap="nowrap" id="address"><font	size="2" color="#004B97"><strong>原土地证号</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_39">${djyw.ytdzh}</td>
		</tr>
		<tr>
		  <td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>证件编号</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_40">${djyw.zjbh}</td>
			<td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>原证件编号</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_41">${djyw.yzjbh}</td>
		</tr>
		<tr>
		  <td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>联系电话</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_42">${djyw.lxdh}</td>
			<td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>原联系电话</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_43">${djyw.ylxdh}</td>
		</tr>
		<tr>
		  <td align="right" nowrap="nowrap" id="address"><font	size="2" color="#004B97"><strong>通讯地址</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_44">${djyw.txdz}</td>
			<td align="right" nowrap="nowrap" id="address"><font	size="2" color="#004B97"><strong>原通讯地址</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_45">${djyw.ylxdh}</td>
		</tr>
		</table><br />
		</s:if>
		
		<s:if test='splc=="Y"'>
		<center><font size="3"><strong>审批流程</strong></font></center>
		<table width="70%" border="1" bordercolor="#000000" style="border-collapse:collapse; table-layout:fixed; " cellpadding="0" cellspacing="0" align="center">
		
		<tr>
			<td width="148" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>经办人意见</strong></font>&nbsp;&nbsp;</td><td  colspan="3" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_46">${djyw.jbryj}</td>
		</tr>
		<tr>
		  <td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>经办人</strong></font>&nbsp;&nbsp;</td><td width="168" nowrap="nowrap" style="font-size: 13px; width:80px;" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_47">${djyw.jbr}</td>
			<td width="201" align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>经办日期</strong></font>&nbsp;&nbsp;</td><td width="170" nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_48">${djyw.jbrq}</td>
		</tr>
		
		<tr>
			<td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>初审人意见</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px; width: auto; height: auto;"onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_49">${djyw.csryj}</td>
		</tr>
		<tr>
		  <td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>初审人</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_50">${djyw.csr}</td>
			<td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>初审日期</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px"onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_51">${djyw.csrq}</td>
		</tr>
		
		<tr>
			<td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>审核人意见</strong></font>&nbsp;&nbsp;</td><td  colspan="3"  style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_52">${djyw.shryj}</td>
		</tr>
		<tr>
		  <td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>审核人</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_53">${djyw.shr}</td>
			<td align="right" nowrap="nowrap"><font	size="2" color="#004B97"><strong>审核日期</strong></font>&nbsp;&nbsp;</td><td nowrap="nowrap" style="font-size: 13px" onclick="alertWin('修改',this.innerText,425,140,this.id)" id="_54">${djyw.shrq}</td>
		</tr>
		</table><br />
		</s:if>
		
        <!-- <center><font size="3"><strong><input id="print" type="button" value="打印" onclick="print()" style="background: #eceadd"></input> </strong></font><br /><br /></center> -->
		
	
</body>
</html>