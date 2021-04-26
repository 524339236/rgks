<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
	<!--导入angularJS文件-->
    <script src="js/angular.min.js"></script>
	<!--导入jquery-->
	<script src="js/jquery-3.3.1.js"></script>
</head>
<body>
	<jsp:include page="/header.jsp"   />
    <!-- 头部 end -->


	<h1>注册</h1>
	<p>一个的段落</p>
	<p>请输入您想用的的用户名和密码</p>
	<!--注册表单-->
	<form id="registerForm" action="register.do" method="post">
		<input type="hidden" name="action" value="register">
		<table border="0">
		<tr>
			<td class="td_left" colspan="2">
			<script>
				function checkUserName(){
					$.post("exists.do",
							{"adminname":$("#adminname").val()},
							function(result){
								//<!--alert(result);
								if(result=="1"){
									$("#usernamespan").text("该用户名已存在 不可用");
									$("#usernamespan").css("color","red");
								} else{
									$("#usernamespan").text("该用户名不存在 可用")
									$("#usernamespan").css("color","green");
								}
							});
					}
			</script>
			<center><span id="usernamespan"></span></center>
			</td>
		</tr>
			<tr>
				<td class="td_left"><label for="adminname">AdminName:</label></td>
				<td class="td_right"><input type="text" id="adminname"
					name="adminname" placeholder="请输入账号"" onblur="checkUserName()"></td>
				<td><input type="button" onclick="checkUser()" value="查询用户名是否被占用" /></td>
			</tr>
			<tr>
				<td class="td_left"><label for="password">PassWord:</label></td>
				<td class="td_right"><input type="password" id="password"
					name="password" placeholder="请输入密码"" onblur="checkPassword()"></td>
				<td>
					<label for="ps" id="ps">PS:</label>
					<script type="text/javascript">
					function checkPassword(){
						var pwd = $("#password").val();
						var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
						if(!reg.test(pwd)){
							alert("密码长度要大于6位，由数字和字母组成");
		　　　　				//var htm = '<label id="pwd-error" class="error" style="display: inline-block;">密码长度要大于6位，由数字和字母组成</label>';
		　　　　				//$("#ps").append(htm);
		　　　　				$("input[name='password']").val("").focus(); // 将name=test的文本框清空并获得焦点，以便重新输入
		　　　　				return;
						}
					}
					</script>
				</td>
			</tr>
			<tr>
				<td class="td_left"><label for="name">Name:</label></td>
				<td class="td_right"><input type="text" id="name"
					name="name" placeholder="请输入真实姓名"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="phone">手机号:</label></td>
				<td class="td_right"><input type="text" id="phone"
					name="phone" placeholder="请输入您的手机号"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="check">验证码</label></td>
				<td class="td_right check">
					<input type="text" id="check" name="check" class="check"> 
					<a href="javaScript:randomcode_refresh()">
					<img id="randomcode_img" onclick="refreshRandom()"
       				 src="randomCode.captcha" height="30" width="120">
       				</a>
				</td>
				<td><input type="button" onclick="display_alert()" value="Display alert box" /></td>
			</tr>
			<tr>
				<td><input type="submit"
					class="submit" value="注册" onclick="checkPassword()"> </td>
				<td><button type="unable_button" 
					disabled="disabled">Don't Click Me!</button></td>
			</tr>
		</table>
	</form>

			<div>
				<p>
					已有账号？ <a href="login.jsp">立即登录</a>
				</p>
			</div>

	<h2>一个网址</h2>
	<a href="https://www.baidu.com/">百度</a>
	<p>另一个段落</>

	<h3>春晓</h3>

	<p>春眠不觉晓，</p>
	<p>处处闻啼鸟。</p>
	<p>夜来风雨声，</p>
	<p>花落知多少。</p>
	
	<!--导入布局js-->
	<script type="text/javascript" src="js/include.js"></script>
	<script>
		function randomcode_refresh() {
			$("#randomcode_img").attr("src",
					"randomCode.captcha?date=" + new Date());
		}
		
		function display_alert()
		  {
		  alert("I am an alert box!!")
		  }
    	
		function checkUser(){
			var username = $("#adminname").val();
			
			alert(username);
			
			$.get("exists.do",{
				user_name:username 
			},function(result){ 
				if (result==1){
					alert("用户已存在")
				}
			});
			
		}
		
		function checkPsw(){
			var pwd = $("#password").val();
			var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
			if(!reg.test(pwd)){
				alert("密码长度要大于6位，由数字和字母组成");
				return 0;
		　　　　	//var htm = '<label id="pwd-error" class="error" style="display: inline-block;">密码长度要大于6位，由数字和字母组成</label>';
		　　　　	//$("#apped").append(htm);
		　　　　	//return;
			}
		}
    </script>
</body>
</html>