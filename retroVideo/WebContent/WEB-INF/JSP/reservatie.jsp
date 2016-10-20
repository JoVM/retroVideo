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
	<nav>
		<ul>
			<li><c:url value='/index.htm' var='index'>
					<c:param name='id' value="${film.id}" />
				</c:url> <a href="<c:out value='${index}'/>">Reserveren</a></li>
		</ul>
	</nav>
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
</body>
</html>