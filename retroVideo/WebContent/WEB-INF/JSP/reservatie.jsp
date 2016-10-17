<%@page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Bestellen' />
</c:import>
</head>
<body>
	<c:url value='/index.htm' var='index'>
		<c:param name='id' value="${film.id}" />
	</c:url>
	<a href="<c:out value='${index}'/>">Reserveren</a>
	<c:if test='${film != null}'>
		<div class=simple>
			<h2>${film.titel}</h2>
			<img src='<c:url value="/images/${film.id}.jpg"/>'
				alt='${film.titel}'>
		</div>
		<div class=fancy>
			<dl>
				<dt>Prijs</dt>
				<dd>${film.prijs}&euro;</dd>
				<dt>Voorraad</dt>
				<dd>${film.voorraad}</dd>
				<dt>Gereserveerd</dt>
				<dd>${film.gereserveerd}</dd>
				<dt>Beschikbaar</dt>
				<dd>${film.voorraad - film.gereserveerd}</dd>
			</dl>
			<c:if test='${film.voorraad - film.gereserveerd > 0}'>
				<form method='post' id='reserveren'>
					<input type='submit' value='In mandje' id='inmandje'>
				</form>
			</c:if>
		</div>
	</c:if>
	<c:if test='${not empty filmsInMandje}'>
		<c:url value='/klanten.htm' var='index'>
			<c:param name='id' value="${film.id}" />
		</c:url>
		<a href="<c:out value='${index}'/>">Klant</a>
		<h2>Mandje</h2>
		<form method='post' id='bestelform'>
			<table>
				<tr>
					<th>Film</th>
					<th>Prijs</th>
					<th><input type='submit' value='Verwijderen'
						id='verwijderknop'></th>
				</tr>
				<c:forEach var='film' items='${filmsInMandje}'>
					<c:set var="totaal" value="${totaal = totaal + film.prijs}"
						scope="page" />
					<tr>
						<td>${film.titel}</td>
						<td>${film.prijs}&euro;</td>
						<td><input type='checkbox' name='id' value='${film.id}'></td>
					</tr>
				</c:forEach>
				<tr>
					<td>Totaal:</td>
					<td>${totaal}&euro;</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>