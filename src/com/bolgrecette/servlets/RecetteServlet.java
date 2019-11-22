package com.bolgrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recette.model.Categorie;
import com.recette.model.Commentaire;
import com.recette.model.Ingredient;
import com.recette.model.Recette;
import com.recette.model.Tag;
import com.blogrecette.service.CategorieService;
import com.blogrecette.service.CommentaireService;
import com.blogrecette.service.IngredientService;
import com.blogrecette.service.RecetteService;
import com.blogrecette.service.TagService;
import com.recette.utils.HibernateConnect;

/**
 * Servlet implementation class Recette
 fait le entre l'url (localhost/recette) et la servelet*/
@WebServlet(name="/Recette", urlPatterns= {"/recette"})
public class RecetteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecetteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		try {

			String idc = request.getParameter("id");
			int id = Integer.parseInt(idc);

			TagService tagService= new TagService();
			RecetteService recetteService = new RecetteService();
			IngredientService ingredientService = new IngredientService();
			CommentaireService commentaireService =  new CommentaireService();

			Recette recette = recetteService.getRecetteFromId(id);

			List<Ingredient> ingredients = ingredientService.getIngredientFromCategorie(id); 

			List<Commentaire> commentaires = commentaireService.getCommentaireFromRecette(id); 
			
			List<Tag> tags = tagService.getAllTag(); 
			
			double moy = commentaireService.getMoyenne(id);
			
			//envoie les objets créés à la jsp getRecetteFromid(id) idem pour les autres;
			request.setAttribute("recette", recette);
			request.setAttribute("ingredients", ingredients);
			request.setAttribute("commentaires", commentaires);
			request.setAttribute("tags", tags);
			request.setAttribute("moy", moy);

			this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);
		}


		catch (ClassNotFoundException | SQLException e)  {

			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		request.setCharacterEncoding("UTF-8");
		String erreur ="";
		Date dateCreation = new Date();
		
		String auteur = request.getParameter("auteur");

		
		if (auteur.isEmpty()) {
			erreur += "Veuillez saisir un nom <br>";
		}
		String contenu = request.getParameter("contenu");

		if (contenu.isEmpty()) {
			erreur += "Veuillez saisir un commentaire <br>";
		}
		

		String notec = request.getParameter("note");
		int note = Integer.parseInt(notec);
		
		String idt = request.getParameter("id");
		int idTag = Integer.parseInt(idt);


		String idc = request.getParameter("id");
		int idRecette = Integer.parseInt(idc);
		
		Recette recette = new Recette();
		recette.setId(idRecette);

		Commentaire commentaire = new Commentaire(recette, auteur, contenu, note, dateCreation);
							
		if (erreur.isEmpty()) {
			request.setAttribute("commentaire", commentaire);
			
			// "recette?id=" + idRecette permet d'insérer le idRecette dans l'id de l'url
			response.sendRedirect("recette?id=" + idRecette);

			try { 
				
				CommentaireService commentaireService = new CommentaireService();
				commentaireService.createCommentaire(commentaire);

			}
			catch (ClassNotFoundException | SQLException e)  {

				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
			else {

				//renvoie l'objet erreur et l'objet member à la jsp
				request.setAttribute("erreur", erreur);
				//ce qui nous permet de garder ce qui a été saisi en cas d'erreur
				
				
				//appel du service commentaire pour reccupérer tous les commentaires
				
				 CommentaireService cs = new CommentaireService();
				 ArrayList<Commentaire> commentaires = null;
				try {
					commentaires = (ArrayList<Commentaire>) cs.getCommentaireFromRecette(idRecette);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("commentaire", commentaires);
				
				
				 TagService ts = new TagService();
				 ArrayList<Tag> tags = null;
				try {
					tags = (ArrayList<Tag>) ts.getAllTag();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("tags", tags);
				
				doGet(request, response);
				//this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);


			}

		}

}
