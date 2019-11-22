package com.recette.tests;

import static org.junit.Assert.*;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import com.blogrecette.service.MembreService;
import com.recette.model.Membre;

import junit.framework.TestCase;

import junit.framework.TestSuite;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.Date;

import java.util.List;

public class TestMembreService extends TestCase {


    private static MembreService membreService = null;

    private static Membre membreTest = null;

    

    public TestMembreService(){
    

    }

    

    public TestMembreService(String testName){

        super(testName);      

    }

    

    @Before
    public void setUp() throws Exception {

        membreService = new MembreService();

        if (membreTest == null) {

        membreTest = new Membre();

        }

    }

    @Test
    public void testCreateMembre() throws Exception { 

        // action
   
    		  membreTest = membreService.createMembre(membreTest);

      

        Membre membreCree = membreService.getMembreFromId(membreTest.getId());

                

        // assert
        assertNotEquals((double)0, (double)membreTest.getId());

        assertEquals(membreTest.getId(), membreCree.getId());       

    }

    

    @Test
    public void testGetMembreFromId() throws Exception {
        
    	// action
        Membre membreFromId = membreService.getMembreFromId(membreTest.getId());

             

        // assert
        assertEquals(membreFromId.getId(), membreTest.getId());
    }

    

    @Test
    public void testgetAllMembre() throws Exception  {

        // action
        List <Membre> membres = membreService.getAllMembre();
            
        // assert
        assertEquals(membres.size(), 1);
    }

    

    

    @Test
    public void testUpdateMembre() throws Exception{

        Date newDate = new Date("2019/11/04");
     
        //membre.setId(4);
        membreTest.setNom("nom");

       
        

        // action
        membreService.updateMembre(membreTest);

        Membre membreUpdate = membreService.getMembreFromId(membreTest.getId());

        

        // assert
        assertEquals(membreUpdate.getNom(), membreTest.getNom());

    }

    

    @Test
    public void testDeleteMembre() throws Exception {
      

        //Creer un jeu de tests (arrange)
        membreService.deleteMembre(membreTest);

        membreTest = membreService.getMembreFromId(membreTest.getId());
      
        // assert
        assertNull(membreTest);

        

    }

    

    public static junit.framework.Test suite() {

        TestSuite suite = new TestSuite("Suite TestMembreService");
        suite.addTest(new TestMembreService("testCreateMembre"));
        suite.addTest(new TestMembreService("testGetMembreFromId"));    
        suite.addTest(new TestMembreService("testUpdateMembre"));   
        suite.addTest(new TestMembreService("testgetAllMembre"));
        suite.addTest(new TestMembreService("testDeleteMembre"));

        

        return suite;

    }
    
    
}