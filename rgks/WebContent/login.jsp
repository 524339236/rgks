<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
	<!--导入angularJS文件-->
    <script src="js/angular.min.js"></script>
	<!--导入jquery-->
	<script src="js/jquery-3.3.1.js"></script>
	<script type="text/javascript" language="javascript">
    	$(function() {
      		$("#log").click(function() {
    			$.ajax({
    				cache : true,
    				type : "post",
                    headers : {
    				    dateStr : encrypt(new Date().getTime())//加密的时间戳
                    },
    				url : "/login.jsp"//,
    			})
      		})
    				function encrypt(value) {
    				    //密钥
    			        var secretKey = CryptoJS.enc.Utf8.parse("asdfghjk18537gbe");
    			        var srcs = CryptoJS.enc.Utf8.parse(data);
    			        //加密
    			        var encrypted = CryptoJS.AES.encrypt(srcs, secretKey, {
    			           mode : CryptoJS.mode.ECB,
    			           padding : CryptoJS.pad.Pkcs7
    			        });
    			        return encrypted.toString();
    			    }
      			})
      		//})
    	//})
    </script>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<!-- 头部 end -->

	<h1>登陆</h1>
	<p>请输入您的用户名和密码</p>

	<form id="loginForm" action="userlogin.do" method="post"
		accept-charset="utf-8">
		<input type="hidden" name="action" value="login" />
		<table>
			<tr>
				<td class="td_left"><label for="adminname">Admin:</label></td>
				<td class="td_right"><input type="text" name="adminname"
					placeholder="请输入账号" autocomplete="off"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="password">Password:</label></td>
				<td class="td_right"><input type="password" name="password"
					placeholder="请输入密码" autocomplete="off"></td>
			</tr>
		</table>

		<input name="randomCode" type="text" placeholder="请输入验证码"
			autocomplete="off"> <a href="javaScript:randomcode_refresh()"><img
			id="randomcode_img" onclick="refreshRandom()"
			src="randomCode.captcha" height="30" width="120"></a> <br/>

		<div class="submit_btn">
			<button type="submit" id="log">登录</button>
		</div>
		<div class="reg">
			没有账户？<a href="register.jsp">立即注册</a>
		</div>
	</form>
	
	<!--导入布局js-->
	<script type="text/javascript" src="js/include.js"></script>
	<script>
		function randomcode_refresh() {
			$("#randomcode_img").attr("src",
					"randomCode.captcha?date=" + new Date());
		}
	</script>
</body>
</html>