<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="../js/content_zoom.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$('div.small_pic a').fancyZoom({scaleImg: false, closeOnClick: true});
});

function printWithColor()
{
	var bdhtml=window.document.body.innerHTML;
	var str="<html>";	
	str +=	'<head><style>';	
	str +=	'BODY {font-size: 12px;}';	
	str +=	'img{border: none;}';	
	str +=	'TABLE {font-size: 12px;}';	
	str +=	'A:link {TEXT-DECORATION: none}';	
	str +=	'A:visited {TEXT-DECORATION: none}';	
	str +=	'A:active {TEXT-DECORATION: none}';	
	str +=	'A:hover {TEXT-DECORATION: underline}';	
	str +=	'</style>';	
	str +=	'<style media="print">';	
	str +=	'.noprint {display: none;}';	
	str +=	'</style>';	
	str +=	"<script type='text/javascript'>function doprint() {window.print();}</";
	str +=	"script>";
	str +=	'<meta http-equiv="content-type" content="text/html; charset=utf-8"> <title>影像打印</title> </head>';	
	str +=	"<body bgcolor=#ffffff topmargin=5 leftmargin=5 marginheight=5 marginwidth=5 onload='doprint()'>";	
	str +=	"<center>";
	
	var objs = window.document.getElementsByName('print'); 
	var i; 

	for(i=0;i<objs.length;i++) 
		{ 
		if(objs[i].type=='checkbox') { 
			if(objs[i].checked == true) { 
				str += "<div style='width: 100%; height: 100%;' >";
				str += '<img id="target" src="';
				str += objs[i].value;	
				str += '"/></div>';
				 
			} 
		} 
		} 
	str +=	"</center>";	
	 str +=	"</body></html>"; 
	 var newWindow=window.open();
	 newWindow.document.body.innerHTML=str;
}

function print() {
	var bdhtml=window.document.body.innerHTML;
	var str="<html>";	
	str +=	'<head><style>';	
	str +=	'BODY {font-size: 12px;}';	
	str +=	'img{border: none;}';	
	str +=	'TABLE {font-size: 12px;}';	
	str +=	'A:link {TEXT-DECORATION: none}';	
	str +=	'A:visited {TEXT-DECORATION: none}';	
	str +=	'A:active {TEXT-DECORATION: none}';	
	str +=	'A:hover {TEXT-DECORATION: underline}';	
	str +=	'</style>';	
	str +=	'<style media="print">';	
	str +=	'.noprint {display: none;}';	
	str +=	'</style>';	
	str +=	"<script type='text/javascript'>function doprint() {window.print();}</";
	str +=	"script>";
	str +=	'<meta http-equiv="content-type" content="text/html; charset=utf-8"> <title>影像打印</title></head>';	
	str +=	"<body bgcolor=#ffffff topmargin=5 leftmargin=5 marginheight=5 marginwidth=5 onload='doprint()'>";	
	str +=	"<center>";
	
	var objs = window.document.getElementsByName('print'); 
	var i; 

	for(i=0;i<objs.length;i++) 
		{ 
		if(objs[i].type=='checkbox') { 
			if(objs[i].checked == true) { 
				str += "<div style='width: 100%; height: 100%;' >";
				str += '<img id="target" src="';
				str += objs[i].value;	
				str += '&hbFlag=Y"/></div>';
				 
			} 
		} 
		} 
	str +=	"</center>";	
	 str +=	"</body></html>"; 
	 var newWindow=window.open();
	 newWindow.document.body.innerHTML=str;
}

</script>

</head>

<body>
<s:iterator value="bigs" var="big">
	<div id="pic_${big.pageNum }" style="display:none;"><img src="water?type=B&dangAnHao=${big.dangAnHao}&pageNum=${big.pageNum }&tmId=${big.tm}&dalb=${dalb}" width="700"/></div>
</s:iterator>


<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top" background="../images/main_17.gif"><br/>
      <table width="95%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><img src="../images/main_24.gif" width="97" height="11"/></td>
        </tr>
      </table>
      <br/>
      <table width="95%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td width="5"><img src="../images/main_11.gif" width="5" height="5"/></td>
         <td background="../images/main_12.gif"><img src="../images/spacer.gif" width="1" height="1"/></td>
         <td width="5"><img src="../images/main_14.gif" width="5" height="5"/></td>
       </tr>
       <tr>
         <td background="../images/main_19.gif"><img src="../images/spacer.gif" width="1" height="1"/></td>
         <td align="center" class="td01">
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
            
             <tr>
               <td height="1" class="td05"><img src="../images/spacer.gif" width="1" height="1"/></td>
               <td class="td05"><img src="../images/spacer.gif" width="1" height="1"/></td>
             </tr>
             <tr>
               <td height="5"><img src="../images/spacer.gif" width="1" height="1"/></td>
               <td><img src="../images/spacer.gif" width="1" height="1"/></td>
             </tr>
           </table>
          
		   <table width="70%" border="0" cellspacing="0" cellpadding="0">
		   <s:iterator value="smalls" var="small">
			  <tr id="dah_line">
				<td>
				<div class="small_pic">
					<a href="#pic_${small.pageNum1}"><img src="water?dangAnHao=${small.dangAnHao}&pageNum=${small.pageNum1 }&tmId=${small.tm}&dalb=${dalb}" width="250" /></a>
				</div>
				<strong>第${small.pageNum1}页</strong> <input type="checkbox"  name="print" id="print_${small.pageNum1}" value="water?type=P&dangAnHao=${small.dangAnHao}&pageNum=${small.pageNum1 }&tmId=${small.tm}&dalb=${dalb}"/><strong>打印</strong>
				</td>
				<td><img src="../images/spacer.gif" width="5"/></td>
				<td>
				<s:if test="#small.pageNum2!='N'">
				<div class="small_pic">
					<a href="#pic_${small.pageNum2}"><img src="water?dangAnHao=${small.dangAnHao}&pageNum=${small.pageNum2 }&tmId=${small.tm}&dalb=${dalb}" width="250" /></a>
				</div>
				<strong>第${small.pageNum2}页</strong> <input type="checkbox" name="print" id="print_${small.pageNum2}" value="water?type=P&dangAnHao=${small.dangAnHao}&pageNum=${small.pageNum2 }&tmId=${small.tm}&dalb=${dalb}"/><strong>打印</strong>
				</s:if>
				</td>
				<td><img src="../images/spacer.gif" width="5"/></td>
				<td>
				<s:if test="#small.pageNum3!='N'">
				<div class="small_pic">
					<a href="#pic_${small.pageNum3}"><img src="water?dangAnHao=${small.dangAnHao}&pageNum=${small.pageNum3 }&tmId=${small.tm}&dalb=${dalb}" width="250" /></a>
				</div>
				<strong>第${small.pageNum3}页</strong> <input type="checkbox" name="print" id="print_${small.pageNum3}" value="water?type=P&dangAnHao=${small.dangAnHao}&pageNum=${small.pageNum3 }&tmId=${small.tm}&dalb=${dalb}"/><strong>打印</strong>
				</s:if>
				</td>
			  </tr>
			  <tr>
			 	 <td class="td1" align="right"></td>
			  	<td class="td2" align="center"><span></span> </td>
			  	<td class="td3" align="center"><img src="../images/spacer.gif" width="1" height="5"/></td>
			  </tr>			
			 </s:iterator>
			 
			  <tr>
			 	 <td class="td1" align="right"></td>
			  	<td class="td2" align="center"><span></span> </td>
			  	<td class="td3" align="center"><img src="../images/spacer.gif" width="1" height="5"/></td>
			  </tr>
			  
			  <tr>
			  	<td><img src="../images/spacer.gif" width="1" height="15"/></td>
			  </tr>
			  
		   </table> 
		   
		   <br/>
           <br/>
		</td>
        <td background="../images/main_21.gif"><img src="../images/spacer.gif" width="1" height="1"/></td>
      </tr>
      <tr>
        <td><img src="../images/main_34.gif" width="5" height="5"/></td>
        <td background="../images/main_35.gif"><img src="../images/spacer.gif" width="1" height="1"/></td>
        <td><img src="../images/main_36.gif" width="5" height="5"/></td>
      </tr>
      
    </table>
        <br/>
    </td>
    
  </tr>
  <tr><td align="center"><input type="button" value="打印彩色影像" onclick="printWithColor()"/><input type="button" value="打印黑白影像" onclick="print()" /></td>    <td align="center"></td></tr>
</table>
	
</body>
</html>

