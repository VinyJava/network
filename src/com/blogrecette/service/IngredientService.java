package com.blogrecette.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recette.model.Commentaire;
import com.recette.model.Ingredient;
import com.recette.model.Recette;
import com.recette.utils.HibernateConnect;

public class IngredientService {

	public Ingredient createIngredient(Ingredient ingredient) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.save(ingredient);
				session.flush();

			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();

		}

		return ingredient;

	}
	public List<Ingredient> getAllIngredient() throws Exception {
		Transaction transaction = null;
		List <Ingredient> listOfIngredient = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfIngredient = session.createQuery("from Ingredient").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfIngredient;
	}

	public void deleteIngredient(Ingredient ingredient) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.delete(ingredient);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Ingredient getIngredientFromId(int id) throws SQLException {
		Transaction transaction = null;
		Ingredient ingredient = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ingredient = session.get(Ingredient.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return ingredient;
	}



	public void updateIngredient(Ingredient ingredient) throws SQLException {

        Transaction transaction = null;
         try (Session session = HibernateConnect.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         if (ingredient != null) {
         session.update(ingredient);
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
         public List<Ingredient> getIngredientFromCategorie(int id) throws Exception {
       		
       		List <Ingredient> listOfIngredient = null;
       		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
       			// start a transaction
       		
       			// get an user object
       			String hql = "SELECT i from Ingredient i join i.recette rec where rec.id = :id";
     			Query query = session.createQuery(hql);
     	     	query.setParameter("id", id);
     	     	listOfIngredient = (List<Ingredient>) query.getResultList();
       			
       		} catch (Exception e) {

       	         e.printStackTrace();

       	       }
       		return listOfIngredient;
       	}
    }	
	

