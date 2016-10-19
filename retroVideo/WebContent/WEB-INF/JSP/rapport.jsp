<%@page contentType='text/html' pageEncoding='UTF-8' session='false'
	trimDirectiveWhitespaces='true'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Rapport' />
</c:import>
</head>
<body>
	<c:url value='/index.htm' var='index'>
	</c:url>
	<a href="<c:out value='${index}'/>">Reserveren</a>
	<h2>Rapport</h2>
	<h3>De reservatie is OK</h3>
</body>
</html>