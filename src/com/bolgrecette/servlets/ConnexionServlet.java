package com.bolgrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recette.model.Membre;
import com.blogrecette.service.MembreService;
import com.blogrecette.service.CommentaireService;
import com.blogrecette.service.IngredientService;
import com.blogrecette.service.MembreService;
import com.blogrecette.service.RecetteService;
import com.recette.utils.HibernateConnect;

/**
 * Servlet implementation class connexion
 */
@WebServlet(name="/Connexion", urlPatterns= {"/connexion"})
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(true);
		session.invalidate();



		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		HttpSession session = request.getSession();

		String erreur ="";

		String pseudo = request.getParameter("pseudo");

		if (pseudo.isEmpty()) {
			erreur += "Veuillez saisir un pseudo <br>";
		}

		String mdp = request.getParameter("mdp");

		if (mdp.isEmpty()) {
			erreur += "Veuillez saisir un mot de passe <br>";
		}

		if (erreur.isEmpty()) {
			
			try {


				MembreService membreService = new MembreService();
				Membre membre = membreService.getMembreFromPseudoandMdp(pseudo, mdp);

				if(membre != null) {
					
					// envoie l'objet member à la session
					session.setAttribute("membre", membre);;	
					response.sendRedirect("index");

				}

				else {
					erreur += "Veuillez saisir un mot de passe ou pseudo valide<br>";
					request.setAttribute("erreur", erreur);
					doGet(request, response);
				}


			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		else {

			//renvoie l'objet erreur et l'objet member à la jsp
			//request.setAttribute("erreur", erreur);
			//ce qui nous permet de garder ce qui a été saisi en cas d'erreur
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("mdp", mdp);


			//this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);


			doGet(request, response);
			//this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response)

		}		

	}

}
