package com.recette.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "membre")
public class Membre {

	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 
	 @Column(name = "id")
	private int id;
	 
	@Column(name = "nom") 
	private String nom;
	
	@Column(name = "pseudo")
	private String pseudo;
	
	@Column(name = "mdp")
	private String mdp;
	
	@Column(name = "email")
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date dateInscription;
	
		
	@OneToMany(mappedBy = "membre")
	private Collection<Recette> recettes;
	
	 
	
	
	
	public Membre() {
		super();
		this.recettes = new ArrayList<Recette>();
	}



	public Membre(String nom, String pseudo, String mdp, String email, Date dateInscription) {	
		 
		super();
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.email = email;
		this.dateInscription = dateInscription;
		this.recettes = new ArrayList<Recette>();
		
	}


	public Collection<Recette> getRecettes(){
		return  recettes;
	}

	public void addRecette(Recette recette){
		recettes.add(recette);
	}

	public void removeRecette(Recette recette){
		recettes.remove(recette);
	}	
	



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	public String getMdp() {
		return mdp;
	}



	public void setMdp(String mdp) {
		this.mdp = mdp;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getDateInscription() {
		return dateInscription;
	}



	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}



	


	@Override
	public String toString() {
		return "Membre [id=" + id + ", nom=" + nom + ", pseudo=" + pseudo + ", mdp=" + mdp + ", email=" + email
				+ ", dateInscription=" + dateInscription + "]";
	}



	
	
}

