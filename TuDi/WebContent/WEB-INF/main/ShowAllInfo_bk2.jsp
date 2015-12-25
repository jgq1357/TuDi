<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>档案属性</title>
<script type="text/javascript" src="../js/prototype1.6.js">
	
</script>
<script type="text/javascript">
	function showTif(id, dangAnHao, pageNum, tmId) {
		window.open("showTiff.action?djywId=" + id + "&dangAnHao=" + dangAnHao
				+ "&pageNum=" + pageNum + "&tmId=" + tmId);
	}
	function action(index) {
		$('m' + index + '_content').style.display = 'block';
		$('bt' + index).style.background = '#aceadd';
		if (index == 1) {
			$('m2_content').style.display = 'none';
			$('m3_content').style.display = 'none';
			$('bt2').style.background = '#eceadd';
			$('bt3').style.background = '#eceadd';
		} else if (index == 2) {
			$('m1_content').style.display = 'none';
			$('m3_content').style.display = 'none';
			$('bt1').style.background = '#eceadd';
			$('bt3').style.background = '#eceadd';
		} else if (index == 3) {
			$('m1_content').style.display = 'none';
			$('m2_content').style.display = 'none';
			$('bt2').style.background = '#eceadd';
			$('bt1').style.background = '#eceadd';
		}
	}
</script>

</head>
<body>
	<font size="3"><strong>立卷信息</strong></font>
	<table width="30%" border="0"  cellpadding="0" cellspacing="0">
		<tr>
			<td align="center"><font	size="2" color="#004B97"><strong>全宗号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.qzh}</td>
			<td align="center"><font	size="2" color="#004B97"><strong>目录号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.mlh}</td>
			<td align="center"><font	size="2" color="#004B97"><strong>案卷号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.ajh}</td>
		</tr>
	</table><br />
	<font size="3"><strong>基本情况</strong></font>
	<table border="0" width="80%" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>土地使用者</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.tdsyz}</td>
			<td align="right"><font size="2" color="#004B97"><strong>宗地号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.zdh }</td>
			<td id="address" align="right"><font size="2" color="#004B97"><strong>土地证号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.tdzh }</td>
			<td align="right"><font	size="2" color="#004B97"><strong>图幅号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.tfh }</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>登记类别</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.djlb }</td>
			<td align="right"><font	size="2" color="#004B97"><strong>使用者性质</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.syzxz }</td>
			<td align="right"><font	size="2" color="#004B97"><strong>权属性质</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.qsxz}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>使用权性质</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.syqxz}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>使用权类型</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.syqlx}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>土地用途</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.tdyt}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>地类</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.dl}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>使用年限</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.synx}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>终止日期</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.zzrq}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>建筑容积率</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.jzrjl}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>建筑密度</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.jzmd}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>建筑类型</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.jzlx}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>建筑物权属</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.jzwqs}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>土地等级</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.tddj}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>标定地价</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.bddj}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>申报地价</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.sbdj}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>证件编号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.zjbh}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>独自面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.dzmj}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>分摊面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.ftmj}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>使用权面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.syqmj}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>建筑面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.jzmj}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>建筑占地面积</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.jzzdmj}</td>
			<td id="position" align="right"><font	size="2" color="#004B97"><strong>土地坐落</strong></font>&nbsp;&nbsp;</td><td colspan="3"  style="font-size: 13px">${djyw.tdzl}</td>
		</tr>
		<tr>
			<td id="position" align="right"><font	size="2" color="#004B97"><strong>东至</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px">${djyw.dz}</td>
			<td id="position" align="right"><font	size="2" color="#004B97"><strong>南至</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px">${djyw.nz}</td>
		</tr>
		<tr>
			<td id="position" align="right"><font	size="2" color="#004B97"><strong>西至</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px">${djyw.xz}</td>
			<td id="position" align="right"><font	size="2" color="#004B97"><strong>北至</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px">${djyw.bz}</td>
		</tr>
		</table><br />
		<font size="3"><strong>转让信息</strong></font>
		<table width="60%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>土地使用者</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.tdsyz}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>原土地使用者</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.ytdsyz}</td>
		</tr>
		<tr>
			<td id="address" align="right"><font	size="2" color="#004B97"><strong>土地证号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.tdzh}</td>
			<td id="address" align="right"><font	size="2" color="#004B97"><strong>原土地证号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.ytdzh}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>证件编号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.zjbh}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>原证件编号</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.yzjbh}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>联系电话</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.lxdh}</td>
			<td align="right"><font	size="2" color="#004B97"><strong>原联系电话</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.ylxdh}</td>
		</tr>
		<tr>
			<td id="address" align="right"><font	size="2" color="#004B97"><strong>通讯地址</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.txdz}</td>
			<td id="address" align="right"><font	size="2" color="#004B97"><strong>原通讯地址</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.ylxdh}</td>
		</tr>
		</table><br />
		<font size="3"><strong>审批流程</strong></font>
		<table width="70%" border="0" cellpadding="0" cellspacing="0">
		
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>经办人意见</strong></font>&nbsp;&nbsp;</td><td  colspan="3" style="font-size: 13px">${djyw.jbryj}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>经办人</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.jbr}&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;<font	size="2" color="#004B97"><strong>经办日期</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.jbrq}</td>
		</tr>
		
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>初审人意见</strong></font>&nbsp;&nbsp;</td><td colspan="3" style="font-size: 13px">${djyw.csryj}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>初审人</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.csr}&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;<font	size="2" color="#004B97"><strong>初审日期</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.csrq}</td>
		</tr>
		
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>审核人意见</strong></font>&nbsp;&nbsp;</td><td  colspan="3"  style="font-size: 13px">${djyw.csryj}</td>
		</tr>
		<tr>
			<td align="right"><font	size="2" color="#004B97"><strong>审核人</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.shr}&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;<font	size="2" color="#004B97"><strong>审核日期</strong></font>&nbsp;&nbsp;</td><td style="font-size: 13px">${djyw.shrq}</td>
		</tr>
		</table><br />
		<font size="3"><strong>权属关系追溯表</strong></font>
		<table width="20%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td><input id="bt1" type="button" value="原档案"
				onclick="action(1)" style="background: #eceadd" /></td>
			<td><input id="bt2" type="button" value="当前档案"
				onclick="action(2)" style="background: #aceadd" /></td>
			<td colspan="5"><input id="bt3" type="button" value="子档案"
				onclick="action(3)" style="background: #eceadd" /></td>
		</tr></table>
		<table width="60%" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<th align="left"><font	size="2" color="#004B97"><strong>序号</strong></font></th>
			<th align="left"><font	size="2" color="#004B97"><strong>档案号</strong></font></th>
			<th align="left"><font	size="2" color="#004B97"><strong>土地使用者</strong></font></th>
			<th align="left"><font	size="2" color="#004B97"><strong>土地坐落</strong></font></th>
			<th align="left"><font	size="2" color="#004B97"><strong>土地面积</strong></font></th>
			<th align="left"><font	size="2" color="#004B97"><strong>权属码</strong></font></th>
			<th align="left"><font	size="2" color="#004B97"><strong>链接</strong></font></th>
		</tr>  
		<tbody id="m1_content" style="display: none">
			<s:iterator id="d" value="olds" status="o" var="old">
				<tr id="tr1">
					<td style="font-size: 13px"><s:property value="#o.index" /></td>
					<td id="key" style="font-size: 13px">${old.qzh}-${old.mlh}-${old.flh}-${old.ajh}</td>
					<td style="font-size: 13px">${old.tdsyz}</td>
					<td style="font-size: 13px">${old.tdzl}</td>
					<td style="font-size: 13px">${old.dzmj}</td>
					<td style="font-size: 13px">${old.qsm}</td>
					<td style="font-size: 13px"><a href="#" onclick="showTif('${old.id}','${old.qzh}-${old.mlh}-${old.flh}-${old.ajh}','000001','ds10')">查看</a></td>
				</tr>
			</s:iterator>
		</tbody>
		<tbody id="m2_content" style="display: block">

			<tr id="tr2">
				<td style="font-size: 13px">0</td>
				<td style="font-size: 13px">${djyw.qzh}-${djyw.mlh}-${djyw.flh}-${djyw.ajh}</td>
				<td style="font-size: 13px">${djyw.tdsyz}</td>
				<td style="font-size: 13px">${djyw.tdzl}</td>
				<td style="font-size: 13px">${djyw.dzmj}</td>
				<td style="font-size: 13px">${djyw.qsm}</td>
				<td style="font-size: 13px"><a href="#" onclick="showTif('${djyw.id}','${djyw.qzh}-${djyw.mlh}-${djyw.flh}-${djyw.ajh}','000001','ds10')">查看</a></td>
			</tr>
		</tbody>
		<tbody id="m3_content" style="display: none">
			<s:iterator id="d" value="futures" status="f" var="future">
				<tr id="tr3">
					<td style="font-size: 13px"><s:property value="#f.index" /></td>
					<td id="key" style="font-size: 13px">${future.qzh}-${future.mlh}-${future.flh}-${future.ajh}</td>
					<td style="font-size: 13px">${future.tdsyz}</td>
					<td style="font-size: 13px">${future.tdzl}</td>
					<td style="font-size: 13px">${future.dzmj}</td>
					<td style="font-size: 13px">${future.qsm}</td>
					<td style="font-size: 13px"><a href="#" onclick="showTif('${future.id}','${future.qzh}-${future.mlh}-${future.flh}-${future.ajh}','000001','ds10')">查看</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>