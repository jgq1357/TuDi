<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>用户登陆</TITLE><LINK 
href="../css/Default.css" type=text/css rel=stylesheet><LINK 
href="../css/xtree.css" type=text/css rel=stylesheet><LINK 
href="../css/User_Login.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.6000.16674" name=GENERATOR>
<script type="text/javascript">
	function login() {
		var userName = document.getElementById("TxtUserName").value; 
      var pwd = document.getElementById("TxtPassword").value; 
           
      var postForm = document.createElement("form");//  
      postForm.method="post" ;   
      postForm.action = 'login' ;   
       
      var userNameInput = document.createElement("input") ; 
      userNameInput.setAttribute("name", "user.userName") ;   
      userNameInput.setAttribute("value", userName);   
      postForm.appendChild(userNameInput) ;   
      var pwdInput = document.createElement("input");
      pwdInput.setAttribute("name","user.password"); 
      pwdInput.setAttribute("value",pwd); 
      postForm.appendChild(pwdInput); 
      
      document.body.appendChild(postForm) ;   
      postForm.submit() ;   
      document.body.removeChild(postForm) ; 
	}
</script>

</HEAD>
<BODY id=userlogin_body>
<DIV></DIV>

<DIV id=user_login>
<DL>
  <DD id=user_top>
  <UL>
    <LI class=user_top_l></LI>
    <LI class=user_top_c></LI>
    <LI class=user_top_r></LI></UL>
  <DD id="user_main">
  <UL>
    <LI class=user_main_l></LI>
    <LI class=user_main_c>
    <DIV class=user_main_box>
    <UL>
      <LI class=user_main_text>用户名： </LI>
      <LI class=user_main_input><INPUT class=TxtUserNameCssClass id=TxtUserName 
      maxLength=20 name=TxtUserName> </LI></UL>
    <UL>
      <LI class=user_main_text>密码： </LI>
      <LI class=user_main_input><INPUT class=TxtPasswordCssClass id=TxtPassword 
      type=password name=TxtPassword> </LI></UL>
    <UL>
      <LI class=user_main_text></LI>
      <LI class=user_main_input><span id="loginInfo" style="color: red"> <s:property
					value="#errors['loginerror']" />&nbsp;
			</span> </LI>
	</UL>
	
	
	</DIV>
	</LI>
    <LI class=user_main_r><INPUT class=IbtnEnterCssClass id=IbtnEnter 
    style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
    onclick='login()' type="image" src="../images/user_botton.gif" name=IbtnEnter> </LI>
	</UL>
  <DD id=user_bottom>
  <UL>
    <LI class=user_bottom_l></LI>
    <LI class=user_bottom_c> </LI>
    <LI class=user_bottom_r></LI></UL></DD></DL></DIV><SPAN id=ValrUserName 
style="DISPLAY: none; COLOR: red"></SPAN><SPAN id=ValrPassword 
style="DISPLAY: none; COLOR: red"></SPAN><SPAN id=ValrValidateCode 
style="DISPLAY: none; COLOR: red"></SPAN>
<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

<DIV></DIV>

</BODY></HTML>
