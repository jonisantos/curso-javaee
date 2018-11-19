package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import beans.Produto;
import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class ServletProduto extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ServletProduto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.deletarProduto(produto);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProduto());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {

				Produto produtoBean = daoProduto.consultarProduto(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", produtoBean);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProduto());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProduto());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			Produto produto = new Produto();

			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);
			produto.setQuantidade(Double.parseDouble(quantidade));
			produto.setValor(Double.parseDouble(valor));

			try {
				
				String msg = null;
				boolean podeInserir = true;
				
				if(nome == null || nome.isEmpty()) {
					
					msg = " Nome do Produto deve ser informado";
					podeInserir = false;
					
				} else if(quantidade == null || quantidade.isEmpty()) {
					msg = "Quantidade do Produto deve ser informado";
					
					podeInserir = false;
					
				}else if(valor == null || valor.isEmpty()) {
					msg = " do Produto deve ser informado";
					podeInserir = false;				
				}
				
				
				 if (id == null || id.isEmpty() && !daoProduto.validarProduto(nome)) {

					msg = "Produto já existe com o mesmo nome!";
					podeInserir = false;

				} 
				
				if(msg != null) {
					
					request.setAttribute("msg", msg);
				}
				
				if (id == null || id.isEmpty() && daoProduto.validarProduto(nome) && podeInserir) {

					daoProduto.salvarProduto(produto);

				} else if (id != null && !id.isEmpty()) {
						
						daoProduto.atualizarProduto(produto);

				}
				
				if(!podeInserir) {
					
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				
				request.setAttribute("produtos", daoProduto.listarProduto());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
