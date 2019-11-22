<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="header.jsp"%>


<div id="global">
	<article>
		<header>
			<img class="imgRecette" src="img/${recette.photo}" alt="Tartiflette" />
			<h1 class="titreRecette">${recette.titre}</h1>

			<c:forEach var="i" begin="1" end="${moy}">
				<span class="fa fa-star checked"></span>
			</c:forEach>
			<c:forEach var="i" begin="${moy + 1}" end="5">
				<span class="fa fa-star "></span>
			</c:forEach>
			${moy}
			
			<span class="Style3">
			<select>
			<c:forEach items="${tags}" var="tag">
				<option>${tag.nom}</option>
			</c:forEach>
			</select>
			 </p></span>
			</br>
			<time>${recette.dateCreation}</time>
		</header>


	</article>
	<hr />
	<header>
		<h2 id="titreIngredient">${recette.description}</h2>
		<ul>

			<c:forEach items="${ingredients}" var="ingredient">
				<li>${ingredient.nom}${ingredient.quantite} ${ingredient.unit}</li>
			</c:forEach>
		</ul>

	</header>

	<c:forEach items="${commentaires}" var="commentaire">
		<h2 id="titreCommentaire">${commentaire.auteur}</h2>
		<div class="divCommentaire">
			<p>${commentaire.contenu}</p>
			<p>Note : ${commentaire.note}/5</p>
			<p>${commentaire.dateCreation}</p>
			<hr>
		</div>
	</c:forEach>
	<form method="post" action="recette?id=${recette.id}">
		<input id="auteur" name="auteur" type="text"
			placeholder="Votre
nom *" class="inputChamp" /><br />
		<textarea id="txtCommentaire" name="contenu" rows="4"
			placeholder="Votre commentaire *" class="inputTextArea"></textarea>
		<br /> <label for="note">Note</label><br /> <select name="note"
			id="note" class="select">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select> <br /> <input type="submit" value="Commenter" class="submitBtn" />
	</form>
	<div id="erreur">
		<p>${erreur}</p>


	</div>



	<%@include file="footer.jsp"%>