package com.bolgrecette.servlets;

import java.util.Date;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recette.model.Membre;
import com.blogrecette.service.MembreService;
import com.recette.utils.HibernateConnect;

/**
 * Servlet implementation class Inscription
 */
@WebServlet(name="/Inscription", urlPatterns= {"/inscription"})
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		HttpSession session = request.getSession();
		
		 Date dateInscription = new Date();
		 String erreur ="";
	     
		
		String nom = request.getParameter("nom");
		
		if (nom.isEmpty()) {
			erreur += "Veuillez saisir un nom <br>";
		}
		String pseudo = request.getParameter("pseudo");
		
		if (pseudo.isEmpty()) {
			erreur += "Veuillez saisir un pseudo <br>";
		}
		
		String mdp = request.getParameter("mdp");
		
		if (mdp.isEmpty()) {
			erreur += "Veuillez saisir un mot de passe <br>";
		}
		
		String email = request.getParameter("email");
		
		if (email.isEmpty()) {
			erreur += "Veuillez saisir un email <br>";
		}
		
		
		
		Membre membre = new Membre(nom, pseudo, mdp, email, dateInscription);
		
		if (erreur.isEmpty()) {
			request.setAttribute("membre", membre);
			
			
			
			// envoie l'objet membre à la session
			session.setAttribute("membre", membre);
			response.sendRedirect("index");
			
			
			try {
				
				
				 MembreService membreService = new MembreService();
				 membreService.createMembre(membre);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		else {
				
			//renvoie l'objet erreur et l'objet membre à la jsp
			request.setAttribute("erreur", erreur);
			//ce qui nous permet de garder ce qui a été saisi en cas d'erreur
			request.setAttribute("membre", membre);
			this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		}
		
				
		
	}
	

}
