<%@page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Klanten' />
</c:import>
</head>
<body>
	<nav>
		<ul>
			<li><c:url value='/index.htm' var='index'>
					<c:param name='id' value="${film.id}" />
				</c:url> <a href="<c:out value='${index}'/>">Reserveren</a></li>
			<li><c:url value='/mandje.htm' var='mandje'>
				</c:url> <a href="<c:out value='${mandje}'/>">Mandje</a></li>
		</ul>
	</nav>
	<h2>Klant</h2>
	<form method='post' id='zoekform'>
		Familienaam bevat: <br> <input name='naam' value='${param.naam}'
			autofocus><span>${fouten.naam}</span> <br> <input
			type='submit' value='Zoeken' id='zoekknop'>
	</form>
	<c:if test='${not empty klanten}'>
		<table>
			<tr>
				<th>Naam</th>
				<th>Straat-HuisNummer</th>
				<th>Postcode</th>
				<th>Gemeente</th>
			</tr>
			<c:forEach var='klant' items='${klanten}'>
				<tr>
					<td><c:url value='/bevestiging.htm' var='bevestiging'>
							<c:param name='id' value="${klant.id}" />
						</c:url> <a href="<c:out value='${bevestiging}'/>">${klant.voornaam}&nbsp${klant.familienaam}</a></td>
					<td>${klant.straatNummer}</td>
					<td>${klant.postcode}</td>
					<td>${klant.gemeente}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>