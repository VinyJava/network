package com.recette.tests;

import static org.junit.Assert.*;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;


import com.blogrecette.service.RecetteService;

import com.recette.model.Recette;

import junit.framework.TestCase;

import junit.framework.TestSuite;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.Date;

import java.util.List;

public class TestRecetteService extends TestCase {


    private static RecetteService recetteService = null;

    private static Recette recetteTest = null;

    

    public TestRecetteService(){
    

    }

    

    public TestRecetteService(String testName){

        super(testName);      

    }

    

    @Before
    public void setUp() throws Exception {

        recetteService = new RecetteService();

        if (recetteTest == null) {

        recetteTest = new Recette();

        }

    }

    @Test
    public void testCreateRecette() throws Exception { 

        // action
   
    		  recetteTest = recetteService.createRecette(recetteTest);

      

        Recette recetteCree = recetteService.getRecetteFromId(recetteTest.getId());

                

        // assert
        assertNotEquals((double)0, (double)recetteTest.getId());

        assertEquals(recetteTest.getId(), recetteCree.getId());       

    }

    

    @Test
    public void testGetRecetteFromId() throws Exception {
        
    	// action
        Recette recetteFromId = recetteService.getRecetteFromId(recetteTest.getId());

             

        // assert
        assertEquals(recetteFromId.getId(), recetteTest.getId());
    }

    

    @Test
    public void testgetAllRecette() throws Exception  {

        // action
        List<Recette> recettes = recetteService.getAllRecette();
            
        // assert
        assertEquals(recettes.size(), 1);
    }

    

    

    @Test
    public void testUpdateRecette() throws Exception{

        Date newDate = new Date("2019/11/04");
     
        //membre.setId(4);
        recetteTest.setTitre("titre");

       
        

        // action
        recetteService.updateRecette(recetteTest);

        Recette recetteUpdate = recetteService.getRecetteFromId(recetteTest.getId());

        

        // assert
        assertEquals(recetteUpdate.getTitre(), recetteTest.getTitre());

    }

    

    @Test
    public void testDeleteRecette() throws Exception {
      

        //Creer un jeu de tests (arrange)
        recetteService.deleteRecette(recetteTest);

        recetteTest = recetteService.getRecetteFromId(recetteTest.getId());
      
        // assert
        assertNull(recetteTest);

        

    }

    

    public static junit.framework.Test suite() {

        TestSuite suite = new TestSuite("Suite TestRecetteService");
        suite.addTest(new TestRecetteService("testCreateRecette"));
        suite.addTest(new TestRecetteService("testGetRecetteFromId"));    
        suite.addTest(new TestRecetteService("testUpdateRecette"));   
        suite.addTest(new TestRecetteService("testgetAllRecette"));
        suite.addTest(new TestRecetteService("testDeleteRecette"));

        

        return suite;

    }
    
    
}