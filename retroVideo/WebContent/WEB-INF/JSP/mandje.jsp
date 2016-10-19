<%@page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Mandje' />
</c:import>
</head>
<body>
	<c:url value='/index.htm' var='index'>
	</c:url>
	<a href="<c:out value='${index}'/>">Reserveren</a>
	<c:url value='/klanten.htm' var='klanten'>
	</c:url>
	<a href="<c:out value='${klanten}'/>">Klant</a>
	<h2>Mandje</h2>
	<c:if test='${not empty filmsInMandje}'>
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