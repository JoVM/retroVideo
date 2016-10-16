<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='RetroVideo' />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<c:if test='${not empty films}'>
		<c:forEach var='film' items='${films}'>
			<img src='images/${film.id}.jpg' alt='${film.titel}'>
		</c:forEach>
	</c:if>
</body>
</html>