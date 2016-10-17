<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<header>
	<nav>
		<ul>
			<c:if test='${not empty genres}'>
				<c:forEach var='genre' items='${genres}' varStatus='status'>
					<li><c:url value='/films.htm' var='index'>
							<c:param name='id' value="${genre.id}" />
						</c:url> <a href="<c:out value='${index}'/>">${genre.naam}</a></li>
				</c:forEach>
			</c:if>
		</ul>
	</nav>
</header>