package com.blogrecette.service;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recette.model.Membre;
import com.recette.utils.HibernateConnect;

public class MembreService {

	public Membre createMembre(Membre membre) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.save(membre);
				session.flush();

			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();

		}

		return membre;

	}
	public List<Membre> getAllMembre() throws Exception {
		Transaction transaction = null;
		List <Membre> listOfMembre = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfMembre = session.createQuery("from Membre").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfMembre;
	}

	public void deleteMembre(Membre membre) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (membre != null) {
				session.delete(membre);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Membre getMembreFromId(int id) throws Exception {
		Transaction transaction = null;
		Membre membre = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			membre = session.get(Membre.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return membre;
	}



	public void updateMembre(Membre membre) throws Exception {

        Transaction transaction = null;
         try (Session session = HibernateConnect.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         if (membre != null) {
         session.update(membre);
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
         public Membre getMembreFromPseudoandMdp(String pseudo, String mdp)throws Exception {
     	 	
     	 	Membre membre = null;
     	 
     try (Session session = HibernateConnect.getSessionFactory().openSession()) {
     	String hql = "From Membre M where M.pseudo = :pseudo and M.mdp = :mdp"; 
     	Query query = session.createQuery(hql);
     	query.setParameter("pseudo", pseudo);
     	query.setParameter("mdp", mdp);
        List results = query.getResultList();
        if (results.isEmpty()) {
        	membre = null;
        }
        else {
        	membre =  (Membre) results.get(0);
        }
        
       } catch (Exception e) {
		e.printStackTrace();
      }
    
     return membre;
      
      }
  }	


