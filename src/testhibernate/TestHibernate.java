package testhibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.recette.model.Categorie;
import com.recette.model.Commentaire;
import com.recette.model.Ingredient;
import com.recette.model.Membre;
import com.recette.model.Recette;


class TestHibernate {
	protected Session session;

	protected SessionFactory sessionFactory;
	public static void main(String args[]) throws Exception {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		SessionFactory sessionFactory = sf;

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		
		
		try {

			
			//insérer un objet en BDD 
			/*tx = session.beginTransaction();
			Membre membre = new Membre("Brel", "Jacky", "12345", "jbrel@gmail.com", new Date());
			session.save(membre);
			session.flush();
			tx.commit(); */ 
			
			
			tx = session.beginTransaction();
			Membre membre = new Membre("paul", "jfp", "12345", "jbrel", new Date());
			session.save(membre);
			session.flush();
			Categorie categorie = new Categorie("riz");
			session.save(categorie);
			session.flush();
			Recette recette = new Recette(membre, categorie, "borch", "riz goulach", "borch.jpg", new Date());
			session.save(recette);
			session.flush();
			Commentaire commentaire = new Commentaire(recette, "Jacky", "Blabla", 5, new Date());		
			session.save(commentaire);
			session.flush();
			Ingredient ingredient = new Ingredient(recette, "riz" , 13, "pl");
			session.save(ingredient);
			session.flush();			
			tx.commit();
			
			/*tx = session.beginTransaction();
			Categorie categorie = new Categorie("Très bon");
			session.save(categorie);
			session.flush();
			tx.commit();
			
			tx = session.beginTransaction();
			Ingredient ingredient = new Ingredient(2, "semoule", 30, "kg" );
			session.save(ingredient);
			session.flush();
			tx.commit();
			
			tx = session.beginTransaction();
			Recette recette = new Recette(2, 3, "Gateau", "chocolat", "borch",new Date());
			session.save(recette);
			session.flush();
			tx.commit();*/
			
			
			/*Afficher une liste d'objet d'une table
			String hql = "FROM Membre M";
			Query query = session.createQuery(hql);
			List results = query.getResultList();
			System.out.println(results);
			
			hql = "FROM Categorie C";
			query = session.createQuery(hql);
			results = query.getResultList();
			System.out.println(results);
			
			hql = "FROM Commentaire C";
			query = session.createQuery(hql);
			results = query.getResultList();
			System.out.println(results);
			
			hql = "FROM Ingredient I";
			query = session.createQuery(hql);
			results = query.getResultList();
			System.out.println(results);
			
			hql = "FROM Recette R";
			query = session.createQuery(hql);
			results = query.getResultList();
			System.out.println(results); */
			
			/* Retrouver un objet en BDD
			String hql = "FROM Recette R WHERE R.id = 1";
			Query query = session.createQuery(hql);
			List results = query.getResultList();
			System.out.println(results);  */
			
			// envoi de paramètre à la requête comme un select from where = condition
			/*String hql = "FROM Membre M WHERE M.dateInscription = :date and M.id = :id";
			Query query = session.createQuery(hql).setParameter("date", new Date())
												  .setParameter("id",2);
			List results = query.getResultList();
			System.out.println(results); */
			
			
			/*Membre membre = (Membre)session.load(Membre.class,1); 
			session.delete(membre);
			session.flush();*/
			
			
			
			
			// le parse est indispensable pour la date
			/*String sDate1="10/11/2019"; 
			Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
			System.out.println(sDate1+"\t"+date); 
			
			tx = session.beginTransaction();
			String hqlDelete = "DELETE FROM Membre M where M.id = :id"; 
			Query deleteQuery = session.createQuery(hqlDelete).setParameter("id",1); 
			deleteQuery.executeUpdate();
			session.flush();
			tx.commit();*/
			
			// mise à jour d'un champ
			/*tx = session.beginTransaction();
			Membre membre = (Membre)session.load(Membre.class, 2); 
			membre.setNom("Obama");
			session.flush();
			tx.commit();*/
			
			/*
			// jointure unidirectionnelle
			tx = session.beginTransaction();
			SecuriteSocial num = new SecuriteSocial ("9990");
			CarteIdentite carte = new CarteIdentite ("9990");
			Membre membre = new Membre("Manhouli", "Vincent", "test", "vman@gmail.com", num , new Date(), carte);
			
			session.save(carte);
			session.save(num);
			session.save(membre); 
			 carte = membre.getCarteIdentite();
			 num  = membre.getSecu_id();
			 session.flush();
			 session.close();*/
			
			
			/*tx = session.beginTransaction();
			Categorie cat = new Categorie ("Hors d'oeuvre");
			Recette rec = new Recette (3, 4, "Goulach", "riz sauce", "borch", new Date());
			
			
			session.save(cat);
			session.save(rec); 
			 cat = cat.getidRecette();
			 num  = membre.getSecu_id();*/
		
			 session.close();
			
			
			
		} catch (Exception e) {

			if (tx != null) {

				tx.rollback();

			}

			throw e;

		} finally {

			session.close();

		}

		sessionFactory.close();

	}


}