package com.recette.model;



import javax.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	 @ManyToOne
	 @JoinColumn(name="idrecette")
	  private Recette recette;
	
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "quantite")
	private int quantite;
	
	@Column(name = "unit")
	private String unit;
	
	
	
	public Ingredient() {
		super();
	}


	public Ingredient(Recette recette, String nom, int quantite, String unit) {
		super();
		this.recette = recette;
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	


	public Recette getRecette() {
		return recette;
	}


	public void setRecette(Recette recette) {
		this.recette = recette;
		recette.addIngredient(this);
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
		
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
