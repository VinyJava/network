package com.bolgrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recette.model.Categorie;
import com.recette.model.Recette;
import com.blogrecette.service.CategorieService;
import com.blogrecette.service.CategorieService;
import com.blogrecette.service.RecetteService;
import com.recette.utils.HibernateConnect;

/**
 * Servlet implementation class Index
 */
@WebServlet(name="/Index", urlPatterns= {"", "/index"})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// TODO Auto-generated method stub
			
		try {
		     
		     RecetteService recetteService = new RecetteService();
		     CategorieService categorieService= new CategorieService();
		    
		     List<Recette> recettes = recetteService.getAllRecette();
		     
		     List<Categorie> categories = categorieService.getAllCategorie();
		     
		     //envoie les objets créés à la jsp 
		     request.setAttribute("recettes", recettes);
		     request.setAttribute("categories", categories);
		     
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
