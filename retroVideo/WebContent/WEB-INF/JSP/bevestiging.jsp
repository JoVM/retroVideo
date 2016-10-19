<%@page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Bevestigen' />
</c:import>
</head>
<body>
	<c:url value='/index.htm' var='index'>
		<c:param name='id' value="${film.id}" />
	</c:url>
	<a href="<c:out value='${index}'/>">Reserveren</a>
	<c:url value='/mandje.htm' var='mandje'>
	</c:url>
	<a href="<c:out value='${mandje}'/>">Mandje</a>
	<c:url value='/klanten.htm' var='klanten'>
	</c:url>
	<a href="<c:out value='${klanten}'/>">Klant</a>
	<h2>Bevestigen</h2>
	<div>${aantalitems}&nbspfilm(s) voor
		${klant.voornaam}&nbsp${klant.familienaam}</div>
	<form method='post' id='bevestigform'>
		<input type='submit' value='Bevestigen' id='bevestigknop'>
	</form>
</body>
</html>