<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div style="opacity:0.5;position:absolute;left:50px;width:300px;height:150px;background-color:#41c0f1"></div>
	<div style="font-family:verdana;padding:20px;border-radius:10px;border:10px solid #cf6d17;">
	<div style="opacity:0.3;position:absolute;left:120px;width:100px;height:200px;background-color:#82ad1e"></div>
	<h3>Look! Styles and colors</h3>
	<div style="letter-spacing:12px;">
	The property of KangJinquan
	</div>
	<c:choose>
        <c:when test="${empty sessionScope.user}">
        <!-- 未登录状态  -->
        	<div style="color:#2daadb;">Kang
			<span style="background-color:#a50d93;color:#ffffff;">Jinquan</span>
			</div>
		</c:when>
        <c:otherwise>
        <!-- 登录状态  -->
            <div style="color:#2daadb;">您好
			<span style="background-color:#a50d93;color:#ffffff;">${sessionScope.user.personName }</span>
			</div>
        </c:otherwise>
    </c:choose>
	
	<div style="color:#000000;">and more...</div>
	</div>
	<c:choose>
        	<c:when test="${empty sessionScope.user}">
            <!-- 未登录状态  -->
            <div class="login_out">
                <a href="login.jsp">登录</a>
                <a href="register.jsp">注册</a>
            </div>
            </c:when>
            <c:otherwise>
            <!-- 登录状态  -->
            <div class="login">
                <span>欢迎回来，${sessionScope.user.personName }</span><br>
                <a href="userlogout.do">退出</a>
            </div>
            </c:otherwise>
        </c:choose>
</body>
</html>