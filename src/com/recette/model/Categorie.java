package com.recette.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "categorie")
public class Categorie {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;

	@Column(name = "nom")
	private String nom;

	@OneToMany(mappedBy = "categorie")
	private Collection<Recette> recettes;


	public Categorie(String nom) {

		super();
		this.nom = nom;
		this.recettes = new ArrayList<Recette>();
	}	
	public Categorie() {		
		super();
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

}
