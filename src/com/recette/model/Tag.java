package com.recette.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
	
	@Column(name = "nom") 
	private String nom;


	public Tag(String nom) {
		super();
		this.nom = nom;
}


public Tag() {
	super();
}


@ManyToMany()
private Collection<Recette> recettes;

public Collection<Recette> getRecette(){
    return  recettes;
}

public void addRecette(Recette recette){
    recettes.add(recette);
    recette.addTag(this);
}

public void removeRecette(Recette recette){
    recettes.remove(recette);
    recette.removeTag(this);
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
