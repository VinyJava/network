package com.blogrecette.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.recette.model.Commentaire;
import com.recette.model.Membre;
import com.recette.model.Recette;
import com.recette.utils.HibernateConnect;

public class CommentaireService {

	public Commentaire createCommentaire(Commentaire commentaire) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.save(commentaire);
				session.flush();

			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();

		}

		return commentaire;

	}
	public List<Commentaire> getAllCommentaire() throws Exception {
		Transaction transaction = null;
		List <Commentaire> listOfCommentaire = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfCommentaire = session.createQuery("from Commentaire").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfCommentaire;
	}

	public void deleteCommentaire(Commentaire commentaire) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.delete(commentaire);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Commentaire getCommentaireFromId(int id) throws Exception {
		Transaction transaction = null;
		Commentaire commentaire = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			commentaire = session.get(Commentaire.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return commentaire;
	}



	public void updateCommentaire(Commentaire commentaire) throws Exception {

        Transaction transaction = null;
         try (Session session = HibernateConnect.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         if (commentaire != null) {
         session.update(commentaire);
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
         public List<Commentaire> getCommentaireFromRecette(int id) throws Exception {
      		
      		List <Commentaire> listOfCommentaire = null;
      		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
      			// start a transaction
      			
      			// get an user object
      			String hql = "SELECT c from Commentaire c join c.recette r where r.id = :id";
     			Query query = session.createQuery(hql);
     	     	query.setParameter("id", id);
     	     	listOfCommentaire = (List <Commentaire>) query.getResultList();
      			// commit transaction
      			
      		} catch (Exception e) {

      	         e.printStackTrace();

      	       }
      		return listOfCommentaire;
      	}
             
         public int getMoyenne(int id) throws Exception {
     		
     		int moy = 0; 
     		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
     			String hql = ("SELECT floor(avg(C.note)) from Commentaire C JOIN C.recette recette where recette.id = :idRecette");
     			Query query = session.createQuery(hql);
                query.setParameter("idRecette", id);
                moy = (int) query.getSingleResult();
     		
     		} catch (Exception e) {
   
     			e.printStackTrace();
     		}
     		return moy;
     	}
   
         
	
}
