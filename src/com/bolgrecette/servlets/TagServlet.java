package com.bolgrecette.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogrecette.service.RecetteService;
import com.blogrecette.service.TagService;
import com.recette.model.Recette;
import com.recette.model.Tag;

/**
 * Servlet implementation class tag
 */
@WebServlet(name="Tag", urlPatterns= {"/tag"})
public class TagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TagServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			TagService tagService= new TagService();

			List<Tag> tags = tagService.getAllTag();

			request.setAttribute("tags", tags);




			this.getServletContext().getRequestDispatcher("/WEB-INF/tag.jsp").forward(request, response);

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

		request.setCharacterEncoding("UTF-8");
		String erreur  = null;
		String nom = request.getParameter("name");

		
		if (nom.isEmpty()) {
			erreur += "Veuillez saisir un nom <br>";
		}
		
		Tag tag = new Tag(nom);

	
		try { 

			TagService tagService = new TagService();
			tagService.createTag(tag);
		
			


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		doGet(request, response);
	}	

}
