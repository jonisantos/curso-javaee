package servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import dao.DaoLogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private DaoLogin daoLogin = new DaoLogin();
	
	public LoginServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		 try {
			
			 Usuario usuario = new Usuario();
			 
			 String login = req.getParameter("login");
			 String senha = req.getParameter("senha");
			 
			 if(daoLogin.validarLogin(login, senha)) {
				 RequestDispatcher dispatcher = req.getRequestDispatcher("acessoliberado.jsp");
				 dispatcher.forward(req, resp);
				 
			 }
			 else {
				 RequestDispatcher dispatcher = req.getRequestDispatcher("acessonegado.jsp");
				 dispatcher.forward(req, resp);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
