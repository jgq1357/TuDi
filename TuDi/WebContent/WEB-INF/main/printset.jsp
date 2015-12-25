<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>打印设置</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
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
		str +=	'<meta http-equiv="content-type" content="text/html; charset=utf-8"></head>';	
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
		 window.open();
		window.document.body.innerHTML=str;
		str="";
	}

	function checkalls(id) {
		var objs = window.document.getElementsByName('print');
		var i; 
		for(i=0;i<objs.length;i++) {
			if(objs[i].type=='checkbox'&& objs[i].id==id) { 
				var c = window.document.getElementById('h'+id);
				if(c.checked==true) {
					objs[i].checked=true;
				}else {
					objs[i].checked=false;
				}
			}
		}
			
	}

	function alls() {
		var objs = document.getElementsByTagName("input");
		var i; 
		for(i=0;i<objs.length;i++) {
			if(objs[i].type=='checkbox') { 
				var c = window.document.getElementById('all');
				if(c.checked==true) {
					objs[i].checked=true;
				}else {
					objs[i].checked=false;
				}
			}
		}
	}
 
 </script> 

</head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top" background="../images/main_17.gif"><br>
      <table width="95%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><img src="../images/main_24.gif" width="97" height="11"></td>
        </tr>
      </table>
      <br>
      <table width="95%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td width="5"><img src="../images/main_11.gif" width="5" height="5"></td>
         <td background="../images/main_12.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
         <td width="5"><img src="../images/main_14.gif" width="5" height="5"></td>
       </tr>
       <tr>
         <td background="../images/main_19.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
         <td align="center" class="td01">
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td height="25"><img src="../images/main_28.gif" width="9" height="9" align="absmiddle"> <strong>选择要打印的页码, 档案号:  ${dangAnHao}</strong></td>
               <td width="35" align="right"></td>
             </tr>
             <tr>
               <td height="1" class="td05"><img src="../images/spacer.gif" width="1" height="1"></td>
               <td class="td05"><img src="../images/spacer.gif" width="1" height="1"></td>
             </tr>
             <tr>
               <td height="5"><img src="../images/spacer.gif" width="1" height="1"></td>
               <td><img src="../images/spacer.gif" width="1" height="1"></td>
             </tr>
           </table>
          
		   <table width="70%" border="0" cellspacing="0" cellpadding="0">
			  <tr id="dah_line" style="display: inline;" height="50">
				<td class="td2" align="left">
				<ul>
				<li>
				<input type="checkbox" id="all" onclick="alls()"/><STRONG>全选</STRONG>
				</li>
				</ul>
				</td>
			</tr>				
			 <tr>
			  	<td class="td2" align="left">
			  	<ul>
			  		<s:iterator value="damaps" status="da">
			  		<li><input type="checkbox" id="hda${da.index }" onclick="checkalls('da${da.index }')"/><STRONG><s:property value="key"/></STRONG></li>
			  		
			  		<ul>
			  			<li>
			  			 <s:iterator value="value" var="str">
			  				<input type="checkbox" name="print" id="da${da.index }" value="water?type=P&dangAnHao=${str.dangAnHao}&pageNum=${str.pageNum}&tmId=${str.tmId}&dalb=${dalb}"/>第${str.pageNum}页
			  			</s:iterator>
			  			</li>
			  		</ul>
			  		
			  		</s:iterator>
			  	</ul>
			  	
			  	</td>
			  </tr>
			  
			  <tr>
			  	<td><img src="../images/spacer.gif" width="1" height="15"/></td>
			  </tr>
			  <tr>
			  	<td class="td2" align="center"><input type="button" value=" 确定 " onclick="print()"/></td>
			  	
			  </tr>
		   </table> 
		   <br>
		   
		  
           <br>
		</td>
        <td background="../images/main_21.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
      </tr>
      <tr>
        <td><img src="../images/main_34.gif" width="5" height="5"></td>
        <td background="../images/main_35.gif"><img src="../images/spacer.gif" width="1" height="1"></td>
        <td><img src="../images/main_36.gif" width="5" height="5"></td>
      </tr>
    </table>
        <br>
        
        
        
    </td>
    
  </tr>
</table>
</body>
</html>

