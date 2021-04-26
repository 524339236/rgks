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
		//1 ���յ�½����
		String userName = request.getParameter("adminname");
		String userPass = request.getParameter("password");
		String reandom_code = (String)request.getSession().getAttribute("random-captcha");
		String reandom_code_input = request.getParameter("randomCode");
        
		
		if (reandom_code.equals(reandom_code_input)){
		//2 ����ҵ���߼���
		UserService userService = new UserService();
		try {
			Users user = userService.isLogin(userName, userPass);
			
			//��ȡ�Ự����,session��һ�����Գ��ڱ������ݵ�����������ÿ���û����Լ��ĻỰ����¼���˵ĵ�½״̬
			HttpSession session = request.getSession();
			//������Ա���浽�Ự
			session.setAttribute("user", user);
			
			//3 ҳ�浼��
			response.sendRedirect(request.getContextPath()+"/index.jsp"); //getContextPath()��ø�Ŀ¼��
		} catch (NameException e) {
			e.printStackTrace();
			//��������Ϣ���浽�Ự
			request.setAttribute("error", e.getMessage());
			//ת�����ᶪʧ����
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
		} catch (PassException e1){
			e1.printStackTrace();
			//��������Ϣ���浽�Ự
			request.setAttribute("error", e1.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		}
		else {
			request.setAttribute("error", "��֤������ ����");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
