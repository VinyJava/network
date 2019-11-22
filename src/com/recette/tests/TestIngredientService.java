package com.recette.tests;

import static org.junit.Assert.*;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import com.blogrecette.service.IngredientService;

import com.recette.model.Ingredient;


import junit.framework.TestCase;

import junit.framework.TestSuite;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.Date;

import java.util.List;

public class TestIngredientService extends TestCase {


    private static IngredientService ingredientService = null;

    private static Ingredient ingredientTest = null;

    

    public TestIngredientService(){
    

    }

    

    public TestIngredientService(String testName){

        super(testName);      

    }

    

    @Before
    public void setUp() throws Exception {

        ingredientService = new IngredientService();

        if (ingredientTest == null) {

        ingredientTest = new Ingredient();

        }

    }

    @Test
    public void testCreateIngredient() throws Exception { 

        // action
   
    		  ingredientTest = ingredientService.createIngredient(ingredientTest);

      

        Ingredient ingredientCree = ingredientService.getIngredientFromId(ingredientTest.getId());

                

        // assert
        assertNotEquals((double)0, (double)ingredientTest.getId());

        assertEquals(ingredientTest.getId(), ingredientCree.getId());       

    }

    

    @Test
    public void testGetIngredientFromId() throws Exception {
        
    	// action
        Ingredient ingredientFromId = ingredientService.getIngredientFromId(ingredientTest.getId());

             

        // assert
        assertEquals(ingredientFromId.getId(), ingredientTest.getId());
    }

    

    @Test
    public void testgetAllIngredient() throws Exception  {

        // action
        List <Ingredient> ingredients = ingredientService.getAllIngredient();
            
        // assert
        assertEquals(ingredients.size(), 1);
    }

    

    

    @Test
    public void testUpdateIngredient() throws Exception{

        Date newDate = new Date("2019/11/04");
     
        //membre.setId(4);
        ingredientTest.setNom("nom");

       
        

        // action
        ingredientService.updateIngredient(ingredientTest);

        Ingredient ingredientUpdate = ingredientService.getIngredientFromId(ingredientTest.getId());

        

        // assert
        assertEquals(ingredientUpdate.getNom(), ingredientTest.getNom());

    }

    

    @Test
    public void testDeleteIngredient() throws Exception {
      

        //Creer un jeu de tests (arrange)
        ingredientService.deleteIngredient(ingredientTest);

        ingredientTest = ingredientService.getIngredientFromId(ingredientTest.getId());
      
        // assert
        assertNull(ingredientTest);

        

    }

    

    public static junit.framework.Test suite() {

        TestSuite suite = new TestSuite("Suite TestIngredientService");
        suite.addTest(new TestIngredientService("testCreateIngredient"));
        suite.addTest(new TestIngredientService("testGetIngredientFromId"));    
        suite.addTest(new TestIngredientService("testUpdateIngredient"));   
        suite.addTest(new TestIngredientService("testgetAllIngredient"));
        suite.addTest(new TestIngredientService("testDeleteIngredient"));

        

        return suite;

    }
    
    
}