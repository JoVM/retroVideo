<%@page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:url value='/index.htm' var='index'>
		<c:param name='id' value="${film.id}" />
	</c:url>
	<a href="<c:out value='${index}'/>">Reserveren</a>
	<c:url value='/mandje.htm' var='mandje'>
	</c:url>
	<a href="<c:out value='${mandje}'/>">Mandje</a>
	<h2>Klant</h2>
	<form method='post' id='zoekform'>
		Familienaam bevat:<span>${fouten.naam}</span> <br> <input
			name='naam' value='${param.naam}' autofocus required> <br>
		<input type='submit' value='Zoeken' id='zoekknop'>
	</form>
</body>
</html>