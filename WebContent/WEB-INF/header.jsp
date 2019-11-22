<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/style.css" />
<title>Mon Blog de Recettes</title>
</head>
<body>

	<header id="header">
		<a href="index"><h1 id="titreBlog">Mon Blog de Recettes</h1></a>
		<div style="width: 300px; margin: 20px auto;">Bienvenue sur mon blog de recettes</div>
		<div id="loginBar">
			<c:choose>
				<c:when test="${empty sessionScope.membre}">
						<a class="primaryBtn login" href="inscription">Inscription</a>
						<a class="primaryBtn login" href="connexion">Se connecter</a>													
				</c:when>
				<c:otherwise>
				<a class="primaryBtn tag" href="tag">Administration des tags</a>	
				Bonjour ${sessionScope.membre.nom}
					<div class="login">
						<a class="primaryBtn login" href="connexion?logout=true">Déconnexion</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</header>