package com.recette.tests;

import static org.junit.Assert.*;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import com.recette.model.Commentaire;

import com.blogrecette.service.CommentaireService;

import junit.framework.TestCase;

import junit.framework.TestSuite;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.Date;

import java.util.List;

public class TestCommentaireService extends TestCase {


    private static CommentaireService commentaireService = null;

    private static Commentaire commentaireTest = null;

    

    public TestCommentaireService(){
    

    }

    

    public TestCommentaireService(String testName){

        super(testName);      

    }

    

    @Before
    public void setUp() throws Exception {

        commentaireService = new CommentaireService();

        if (commentaireTest == null) {

        commentaireTest = new Commentaire();

        }

    }

    @Test
    public void testCreateCommentaire() throws Exception { 

        // action
   
    		  commentaireTest = commentaireService.createCommentaire(commentaireTest);

      

        Commentaire commentaireCree = commentaireService.getCommentaireFromId(commentaireTest.getId());

                

        // assert
        assertNotEquals((double)0, (double)commentaireTest.getId());

        assertEquals(commentaireTest.getId(), commentaireCree.getId());       

    }

    

    @Test
    public void testGetCommentaireFromId() throws Exception {
        
    	// action
        Commentaire commentaireFromId = commentaireService.getCommentaireFromId(commentaireTest.getId());

                

        // assert
        assertEquals(commentaireFromId.getId(), commentaireTest.getId());
    }

    

    @Test
    public void testgetAllCommentaire() throws Exception  {

        // action
        List <Commentaire> commentaires = commentaireService.getAllCommentaire();
            
        // assert
        assertEquals(commentaires.size(), 1);
    }

    

    

    @Test
    public void testUpdateCommentaire() throws Exception{

        Date newDate = new Date("2019/11/04");
     
        //membre.setId(4);
        commentaireTest.setAuteur("auteur");

        

        

        // action
        commentaireService.updateCommentaire(commentaireTest);

        Commentaire commentaireUpdate = commentaireService.getCommentaireFromId(commentaireTest.getId());

        

        // assert
        assertEquals(commentaireUpdate.getAuteur(), commentaireTest.getAuteur());

    }

    

    @Test
    public void testDeleteCommentaire() throws Exception {
      

        //Creer un jeu de tests (arrange)
        commentaireService.deleteCommentaire(commentaireTest);

        commentaireTest = commentaireService.getCommentaireFromId(commentaireTest.getId());
      
        // assert
        assertNull(commentaireTest);

        

    }

    

    public static junit.framework.Test suite() {

        TestSuite suite = new TestSuite("Suite TestCommentaitreService");
        suite.addTest(new TestCommentaireService("testCreateCommentaire"));
        suite.addTest(new TestCommentaireService("testGetCommentaireFromId"));    
        suite.addTest(new TestCommentaireService("testUpdateCommentaire"));   
        suite.addTest(new TestCommentaireService("testgetAllCommentaire"));
        suite.addTest(new TestCommentaireService("testDeleteCommentaire"));

        

        return suite;

    }
    
    
}