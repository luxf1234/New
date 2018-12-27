<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css"> 
input{
	height:30px;
}
</style> 
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyUI/demo.css">
	<script type="text/javascript" src="../easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="../easyUI/jquery.easyui.min.js"></script>
</head>
<body>
		<div style="padding:60px">
	    <form class="form" method="post">
	    	<table cellpadding="10">
	
	    		<tr>
	    			<td>姓名:</td>
	    			<td><input class="easyui-textbox" 
	    			type="text" id="userName" name="userName"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-textbox" 
	    			type="password" id="password" name="password"></input></td>
	    		</tr>
	 </table>
	  <input name="" type="button" onclick="login()" 
	  style="margin-left:100px;margin-top:30px;color:blue" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">    
     </form>   
     </div>
	
		<script>
		
		function login(){
			var params={
					"userName" : $("#userName").val(),
					"password" : $("#password").val()
			}
			$.post("/user/login", params, function(data) {
				alert(data);
				if(data=="登录成功"){
				window.location.href='/index.html';}
			});
		}

	</script>
</body>
</html>