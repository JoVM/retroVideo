<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Films' />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<c:if test='${not empty films}'>
		<c:forEach var='film' items='${films}'>
			<c:url value='/reservatie.htm' var='index'>
				<c:param name='id' value="${film.id}" />
			</c:url>
			<a href="<c:out value='${index}'/>"> <img class='reeks'
				title='${film.gereserveerd == film.voorraad ? "reservatie niet mogelijk" : "reservatie mogelijk"}'
				src='<c:url value="/images/${film.id}.jpg"/>' alt='${film.titel}'>
			</a>
		</c:forEach>
	</c:if>
</body>
</html>