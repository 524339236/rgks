package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Users;
import service.UserService;

@WebServlet("/register.do")
public class RegisterController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		String user_name = request.getParameter("adminname");
		String user_pass = request.getParameter("password");
		String user_person_name = request.getParameter("name");
		String user_phone = request.getParameter("phone");
		
		String reandom_code = (String)request.getSession().getAttribute("random-captcha");
		
		Users user;
		try {
			user = userService.isExistsUser(user_name);
		
		//判断随机码是否正确
		String reandom_code_input = request.getParameter("check");

		
		if (reandom_code.equals(reandom_code_input) && user_name!="" &&
				user_pass!="" && user_phone!="" && user_person_name!="" && user==null) {
			//注册用户
			try {
				userService.register(user_name, user_pass, user_person_name, user_phone);
				System.out.println("注册成功");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}
		}else {
			//返回错误信息
			System.out.println("注册失败");
			//转发不会丢失此页面数据
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/register.do");
			//要重定向到一个 包含错误信息的页面,会丢失该页面数据
		 }
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
