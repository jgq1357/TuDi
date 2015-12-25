<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../css/attributes-style.css" />
		<link rel="stylesheet" type="text/css" href="../css/main-style.css" />
		<title>档案属性</title>
		<script type="text/javascript" src="../js/prototype1.6.js">
		</script>
		<script type="text/javascript">
		function showTif(id,dangAnHao,pageNum,tmId){

			window.open("showTiff.action?djywId="+id+"&dangAnHao="+dangAnHao+"&pageNum="+pageNum+"&tmId="+tmId);
		}
			function action(index){
				$('m'+index+'_content').style.display='block';
				$('bt'+index).style.background='#aceadd';
				if(index==1) {
					$('m2_content').style.display='none';
					$('m3_content').style.display='none';
					$('bt2').style.background='#eceadd';
					$('bt3').style.background='#eceadd';
				}else if(index==2) {
					$('m1_content').style.display='none';
					$('m3_content').style.display='none';
					$('bt1').style.background='#eceadd';
					$('bt3').style.background='#eceadd';
				}else if(index==3) {
					$('m1_content').style.display='none';
					$('m2_content').style.display='none';
					$('bt2').style.background='#eceadd';
					$('bt1').style.background='#eceadd';
				}
			}
		</script>

	</head>
<body>

	<div id="attributes_page_l">
<div id="basic_situation">
<p>基本情况</p>
<table id="basicInfo">
	<tr>
		<td align="center">土地使用者&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.tdsyz}" readonly="readonly" /></td>
		<td align="center">宗地号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.zdh }" readonly="readonly"/></td>
		<td id="address" align="center">土地证号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.tdzh }" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">图幅号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.tfh }" readonly="readonly"/></td>
		<td align="center">登记类别&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.djlb }" readonly="readonly"/></td>
		<td align="center">使用者性质&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.syzxz }" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">权属性质&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.qsxz}" readonly="readonly"/></td>
		<td align="center">使用权性质&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.syqxz}" readonly="readonly"/></td>
		<td align="center">使用权类型&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.syqlx}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">土地用途&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.tdyt}" readonly="readonly"/></td>
		<td align="center">地类&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.dl}" readonly="readonly"/></td>
		<td align="center">使用年限&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.synx}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">终止日期&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.zzrq}" readonly="readonly"/></td>
		<td align="center">建筑容积率&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.jzrjl}" readonly="readonly"/></td>
		<td align="center">建筑密度&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.jzmd}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">建筑类型&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.jzlx}" readonly="readonly"/></td>
		<td align="center">建筑物权属&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.jzwqs}" readonly="readonly"/></td>
		<td align="center">土地等级&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.tddj}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">标定地价&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.bddj}" readonly="readonly"/></td>
		<td align="center">申报地价&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.sbdj}" readonly="readonly"/></td>
		<td align="center">证件编号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.zjbh}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">独自面积&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.dzmj}" readonly="readonly"/></td>
		<td align="center">分摊面积&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.ftmj}" readonly="readonly"/></td>
		<td align="center">使用权面积&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.syqmj}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">建筑面积&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.jzmj}" readonly="readonly"v/></td>
		<td align="center">建筑占地面积&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.jzzdmj}" readonly="readonly"/></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td id="position" colspan="3" align="center">土地坐落&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="text" value="${djyw.tdzl}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td id="position" colspan="3" align="center">东至&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="text" value="${djyw.dz}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td id="position" colspan="3" align="center">南至&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="text" value="${djyw.nz}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td id="position" colspan="3" align="center">西至&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="text" value="${djyw.xz}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td id="position" colspan="3" align="center">北至&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="text" value="${djyw.bz}" readonly="readonly"/></td>
	</tr>
</table>
</div>
<div id="transfer_information">
<p>转让信息</p>
<table id="transInfo">
	<tr>
		<td align="center">土地使用者&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.tdsyz}" readonly="readonly"/></td>
		<td id="address" align="center">土地证号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.tdzh}" readonly="readonly"/></td>
		<td align="center">证件编号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.zjbh}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">联系电话&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.lxdh}" readonly="readonly"/></td>
		<td id="address" colspan="2" align="center">通讯地址&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="text" value="${djyw.txdz}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">原土地使用者&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.ytdsyz}" readonly="readonly"/></td>
		<td id="address" align="center">原土地证号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.ytdzh}" readonly="readonly"/></td>
		<td align="center">原证件编号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.yzjbh}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td align="center">原联系电话&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.ylxdh}" readonly="readonly"/></td>
		<td id="address" colspan="2" align="center">原通讯地址&nbsp;&nbsp;&nbsp;&nbsp;<input
			type="text"  value="${djyw.ylxdh}" readonly="readonly"/></td>
	</tr>
</table>
</div>
</div>
<div id="attributes_page_r">
<div id="approval_process">
<p>审批流程</p>
<table id="adminInfo">
	<tr>
		<td colspan="2" align="center">经办人意见&nbsp;&nbsp;&nbsp;&nbsp;<textarea
			id="comments" readonly="readonly">${djyw.jbryj}</textarea></td>
	</tr>
	<tr>
		<td align="center">经办人&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.jbr}"readonly="readonly" /></td>
		<td align="center">经办日期&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.jbrq}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center">初审人意见&nbsp;&nbsp;&nbsp;&nbsp;<textarea
			id="comments" readonly="readonly">${djyw.csryj}</textarea></td>
	</tr>
	<tr>
		<td align="center">初审人&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.csr}" readonly="readonly"/></td>
		<td align="center">初审日期&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.csrq}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center">审核人意见&nbsp;&nbsp;&nbsp;&nbsp;<textarea
			id="comments" readonly="readonly">${djyw.csryj}</textarea></td>
	</tr>
	<tr>
		<td align="center">审核人&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.shr}" readonly="readonly"/></td>
		<td align="center">审核日期&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.shrq}" readonly="readonly"/></td>
	</tr>
</table>
</div>
<div id="filing_information">
<p>立卷信息</p>
<table>
	<tr>
		<td colspan="2" align="center">全宗号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.qzh}" readonly="readonly"/></td>
		<td colspan="2" align="center">目录号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.mlh}" readonly="readonly"/></td>
		<td colspan="2" align="center">案卷号&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="${djyw.ajh}" readonly="readonly"/></td>
	</tr>
</table>
</div>
<div id="ownership">
<p>权属关系追溯表</p>
<div id="dang1">
<input id="bt1" type="button" value="原档案" onclick="action(1)" style="background: #eceadd"/>
</div>
<div id="dang2">
<input id="bt2" type="button" value="当前档案" onclick="action(2)" style="background: #aceadd"/>
</div>
<div id="dang3">
<input id="bt3" type="button" value="子档案" onclick="action(3)" style="background: #eceadd"/>
</div>
<div>
<table >
				<tr>
					<th>序号</th>
					<th>档案号</th>
					<th>土地使用者</th> 
					<th>土地坐落</th>
					<th>土地面积</th>
					<th>权属码</th>
					<th>链接</th>
				</tr>
			<tbody id="m1_content" style="display: none">
				<s:iterator id="d" value="olds" status="o" var="old">
					<tr id="tr1">
						<td><s:property value="#o.index"/></td>
						<td id="key">${old.qzh}-${old.mlh}-${old.flh}-${old.ajh}</td>
						<td>${old.tdsyz}</td>
						<td>${old.tdzl}</td>
						<td>${old.dzmj}</td>
						<td>${old.qsm}</td>
						<td><a href="#" onclick="showTif('${old.id}','${old.qzh}-${old.mlh}-${old.flh}-${old.ajh}','000001','ds10')">查看</a></td>
					</tr>
				</s:iterator>
			</tbody>
			<tbody id="m2_content" style="display: block">
				
				<tr id="tr2">
					<td>0</td>
					<td>${djyw.qzh}-${djyw.mlh}-${djyw.flh}-${djyw.ajh}</td>
					<td>${djyw.tdsyz}</td>
					<td>${djyw.tdzl}</td>
					<td>${djyw.dzmj}</td>
					<td>${djyw.qsm}</td>
					<td><a href="#" onclick="showTif('${djyw.id}','${djyw.qzh}-${djyw.mlh}-${djyw.flh}-${djyw.ajh}','000001','ds10')">查看</a></td>
				</tr>
			</tbody>
			<tbody id="m3_content" style="display: none">
				<s:iterator id="d" value="futures" status="f" var="future">
					<tr id="tr3">
						<td><s:property value="#f.index"/></td>
						<td id="key">${future.qzh}-${future.mlh}-${future.flh}-${future.ajh}</td>
						<td>${future.tdsyz}</td>
						<td>${future.tdzl}</td>
						<td>${future.dzmj}</td>
						<td>${future.qsm}</td>
						<td><a href="#" onclick="showTif('${future.id}','${future.qzh}-${future.mlh}-${future.flh}-${future.ajh}','000001','ds10')">查看</a></td>
					</tr>
				</s:iterator>

			</tbody>
			
		</table>
	</div>
</div>
</div>
</body>
</html>