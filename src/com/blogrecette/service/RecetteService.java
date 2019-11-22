package com.blogrecette.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.recette.model.Recette;
import com.recette.utils.HibernateConnect;

public class RecetteService {

	
	public Recette createRecette(Recette recette) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.save(recette);
				session.flush();

			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();

		}

		return recette;

	}
	public List<Recette> getAllRecette() throws Exception {
		Transaction transaction = null;
		List <Recette> listOfRecette = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfRecette = session.createQuery("from Recette").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfRecette;
	}

	public void deleteRecette(Recette recette) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.delete(recette);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Recette getRecetteFromId(int id) throws SQLException {
		
		Recette recette = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			recette= session.get(Recette.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recette;
	}



	public void updateRecette(Recette recette) throws SQLException {

        Transaction transaction = null;
         try (Session session = HibernateConnect.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         if (recette != null) {
         session.update(recette);
         session.flush();
         }
         transaction.commit();
         } catch (Exception e) {

         if (transaction != null) {

         transaction.rollback();

         }

         e.printStackTrace();

         }
	}

         public List<Recette> getRecetteFromCategorie(int id) throws Exception {
     		
     		List <Recette> listOfRecette = null;
     		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
     			     			// get an recette object
     			String hql = "SELECT r from Recette r join r.categorie cat where cat.id = :id";
     			Query query = session.createQuery(hql);
     	     	query.setParameter("id", id);
     	     	listOfRecette = (List <Recette>) query.getResultList();

     		} catch (Exception e) {

     	         e.printStackTrace();

     	       }
     		return listOfRecette;
     	}
         
         
    
	
}

	

