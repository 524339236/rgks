package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Users;
import exception.NameException;
import exception.PassException;
import service.UserService;


@WebServlet("/userlogin.do")
public class UserIsLoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 接收登陆请求，
		String userName = request.getParameter("adminname");
		String userPass = request.getParameter("password");
		String reandom_code = (String)request.getSession().getAttribute("random-captcha");
		String reandom_code_input = request.getParameter("randomCode");
        
		
		if (reandom_code.equals(reandom_code_input)){
		//2 调用业务逻辑，
		UserService userService = new UserService();
		try {
			Users user = userService.isLogin(userName, userPass);
			
			//获取会话对象,session是一个可以长期保存数据的容器，并且每个用户有自己的会话，记录个人的登陆状态
			HttpSession session = request.getSession();
			//将管理员保存到会话
			session.setAttribute("user", user);
			
			//3 页面导航
			response.sendRedirect(request.getContextPath()+"/index.jsp"); //getContextPath()获得根目录。
		} catch (NameException e) {
			e.printStackTrace();
			//将错误信息保存到会话
			request.setAttribute("error", e.getMessage());
			//转发不会丢失数据
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
		} catch (PassException e1){
			e1.printStackTrace();
			//将错误信息保存到会话
			request.setAttribute("error", e1.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		}
		else {
			request.setAttribute("error", "验证码输入 错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
