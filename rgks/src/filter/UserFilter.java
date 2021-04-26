package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class UserFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		
		// ��¼״̬�ж������ǻỰ����û���û�����
		HttpSession session = httprequest.getSession();
		if (session.getAttribute("user") == null) {
			//httpresponse.sendRedirect( "/login.jsp");
			httpresponse.sendRedirect(httprequest.getContextPath()+"/login.jsp");
		} else {
			chain.doFilter(request, response);// ����
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	public void destroy() {
	}

}
