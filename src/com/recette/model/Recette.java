package com.recette.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "recette")
public class Recette {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id")
	private int id;
	
	@Column(name = "titre")
	private String titre;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "photo")
	private String photo;
	
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	
	@ManyToOne
	@JoinColumn(name = "idMembre")
	private Membre membre;
	
	@ManyToOne
	@JoinColumn(name = "idCategorie")
	private Categorie categorie;
	
	@OneToMany(mappedBy = "recette")
	private Collection<Commentaire> commentaires;
	
	
	@OneToMany(mappedBy = "recette")
	private Collection<Ingredient> ingredients;
	
	@ManyToMany(mappedBy = "recettes")
    private Collection<Tag> tags;

	public Recette() {
		super();
		this.commentaires = new ArrayList<Commentaire>();
		this.ingredients = new ArrayList<Ingredient>();
		this.tags = new ArrayList<Tag>();
	
	}

	public Recette(Membre membre, Categorie categorie, String titre, String description, String photo, Date dateCreation) {
		super();
		this.membre = membre;
		this.categorie = categorie;
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
		this.commentaires = new ArrayList<Commentaire>();
		this.ingredients = new ArrayList<Ingredient>();
		this.tags = new ArrayList<Tag>();
	}

	public Collection<Tag> tags(){
        return  tags;
       
   
	}
    public void addTag(Tag tag){
        tags.add(tag);
       tag.addRecette(this);
    }

    public void removeTag(Tag tag){
        tags.remove(tag);
        tag.removeRecette(this);
    }



	
	public Collection<Commentaire> getCommentaire(){
		return commentaires;
	}

	public void addCommentaire(Commentaire commentaire){
		commentaires.add(commentaire);
	}

	public void removeCommentaire(Commentaire commentaire){
		commentaires.remove(commentaire);
	}	

	public Collection<Ingredient> getIngredient(){
		return ingredients;
	}

	public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
	}

	public void removeIngredient(Ingredient ingredient){
		ingredients.remove(ingredient);
	}	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
		membre.addRecette(this);
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
		 categorie.addRecette(this);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return "Recette [id=" + id + ", titre=" + titre + ", description=" + description + ", photo=" + photo
				+ ", dateCreation=" + dateCreation + ", membre=" + membre + ", categorie=" + categorie + "]";
	}

	

}
