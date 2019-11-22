package com.blogrecette.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recette.model.Commentaire;
import com.recette.model.Tag;
import com.recette.utils.HibernateConnect;

public class TagService {

	public Tag createTag(Tag tag) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.save(tag);
				session.flush();

			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();

		}

		return tag;

	}
	public List<Tag> getAllTag() throws Exception {
		Transaction transaction = null;
		List <Tag> listOfTag = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			listOfTag = session.createQuery("from Tag").getResultList();
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfTag;
	}

	public void deleteTag(Tag tag) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.delete(tag);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Tag getTagFromId(int id) throws Exception {
		Transaction transaction = null;
		Tag tag = null;
		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			tag = session.get(Tag.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return tag;
	}



	public void updateTag(Tag tag) throws SQLException {

        Transaction transaction = null;
         try (Session session = HibernateConnect.getSessionFactory().openSession()) {
         transaction = session.beginTransaction();

         if (tag != null) {
         session.update(tag);
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
         public List<Tag> getTagFromRecette(int id) throws Exception {
       		
       		List <Tag> listOfTag = null;
       		try (Session session = HibernateConnect.getSessionFactory().openSession()) {
       			// start a transaction
       			
       			// get an user object
       			String hql = "SELECT t FROM Tag t JOIN t.recettes rec WHERE rec.id = :id";
      			Query query = session.createQuery(hql);
      	     	query.setParameter("id", id);
      	     	listOfTag = (List <Tag>) query.getResultList();
       			// commit transaction
       			
       		} catch (Exception e) {

       	         e.printStackTrace();

       	       }
       		return listOfTag;
       	}
	
	} 


