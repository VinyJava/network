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
import com.blogrecette.service.RecetteService;
import com.recette.utils.HibernateConnect;

/**
 * Servlet implementation class Categorie
 */
@WebServlet(name="/Categorie", urlPatterns= {"/categorie"})
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		try {
		     
			 String idc = request.getParameter("idCategorie");
		     int id = Integer.parseInt(idc);
		     
			 
		     RecetteService recetteService = new RecetteService();
		     CategorieService categorieService= new CategorieService();
		    
		     List<Recette> recettes = recetteService.getRecetteFromCategorie(id);
		     
		     List<Categorie> categories = categorieService.getAllCategorie();
		     
		     //envoie les objets créés à la jsp 
		     request.setAttribute("recettes", recettes);
		     request.setAttribute("categories", categories);
		     
		    
		
			
		     this.getServletContext().getRequestDispatcher("/WEB-INF/categorie.jsp").forward(request, response);
	
		}
			catch (Exception e)  {
	    	 
				e.printStackTrace();
	     }
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
