package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Telefone;
import beans.Usuario;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet("/salvarTelefones")
public class TelefonesServlets extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
  
	DaoTelefone daotelefone = new DaoTelefone();
	
    public TelefonesServlets() {
        super();
       
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String user = request.getParameter("user");
			
			
				Usuario usuario = daoUsuario.consultar(user);
				
				request.getSession().setAttribute("userEscolhido", usuario);
				
				request.setAttribute("userEscolhido",usuario);
				
			
				
				//request.getSession().setAttribute("user", usuario.getId());
				//request.getSession().setAttribute("nomeUser", usuario.getNome());			
				
					
			RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefones.jsp");
			//request.setAttribute("telefones",daotelefone.listar(usuario.getId()));
			request.setAttribute("msg" , "Salvo com sucesso!");
			
			view.forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
	}

	
			protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			try {
				
			Usuario beansusuarios = (Usuario) request.getSession().getAttribute("userEscolhido");
			
			String numero = request.getParameter("numero");
			
			String tipo = request.getParameter("tipo");
			
			Telefone telefone = new Telefone();
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setUsuario(beansusuarios.getId());
			
			daotelefone.salvar(telefone);
			
			request.getSession().setAttribute("userEscolhido",beansusuarios);			
			request.setAttribute("userEscolhido", beansusuarios);
			
			RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefones.jsp");
			request.setAttribute("telefones",daotelefone.listar(beansusuarios.getId()));
			request.setAttribute("msg" , "Salvo com sucesso!");
			
			view.forward(request, response);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
	}

}
