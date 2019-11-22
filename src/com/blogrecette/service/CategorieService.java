package com.blogrecette.service;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.recette.model.Categorie;
import com.recette.utils.HibernateConnect;

public class CategorieService {

	public Categorie createCategorie(Categorie categorie) throws Exception {
		Transaction transaction = null;
		
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.save(categorie);
				session.flush();
	
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();

		}

		return categorie;

	}
	
	public List<Categorie> getAllCategorie() throws Exception {
		Transaction transaction = null;
		List <Categorie> listOfCategorie = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfCategorie = session.createQuery("from Categorie").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfCategorie;
	}

	public void deleteCategorie(Categorie categorie) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.delete(categorie);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Categorie getCategorieFromId(int id) throws Exception {
		Transaction transaction = null;
		Categorie categorie = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			categorie = session.get(Categorie.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categorie;
	}



	

	public void updateCategorie(Categorie categorie) throws SQLException {

         Transaction transaction = null;
         try (Session session = HibernateConnect.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         if (categorie != null) {
         session.update(categorie);
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


}
