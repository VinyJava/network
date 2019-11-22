<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@include file="header.jsp"%>
<h1>Connection</h1>
<div id="connection">

<form method="post" action="connexion" ><input id="pseudo" name="pseudo" type="text"
class="inputChamp" placeholder="Votre pseudo *" /><br />
<br />
<input id="mdp" name="mdp" type="password"
class="inputChamp" placeholder="Votre mot de passe *" /><br />
<br />
<input type="submit" value="Connection"
class="submitBtn" />
</form>
</div>
<div id="erreur">
<p> ${erreur}</p>
</div>

<%@include file="footer.jsp"%>