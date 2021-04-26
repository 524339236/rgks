package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Users;
import service.UserService;
@WebServlet("/exists.do")
public class UserExistsController extends HttpServlet {
	
	private UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("adminname");

		try {
			Users exists = userService.isExistsUser(user_name);
			PrintWriter out = response.getWriter();

			//Gson g = new Gson();
			if (exists!=null) {
				out.print(1);
			} else {
				out.print(0);
			}

		} catch (Exception ex) {
			// Ìø×ª´íÎóÒ³Ãæ
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
