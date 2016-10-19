package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Film;
import be.vdab.repositories.FilmRepository;
import be.vdab.repositories.GenreRepository;

/**
 * Servlet implementation class VulMandjeServlet
 */
@WebServlet("/mandje.htm")
public class VulMandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String MANDJE = "mandje";
	private final transient FilmRepository filmRepository = new FilmRepository();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			System.out.println("session: true");
			@SuppressWarnings("unchecked")
			Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE);
			if (mandje != null) {
				List<Film> filmsInMandje = new ArrayList<>();
				for (Integer filmId : mandje) {
					filmsInMandje.add(filmRepository.read(filmId));
				}
				request.setAttribute("filmsInMandje", filmsInMandje);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterValues("id") != null) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE);
			if (mandje == null) {
				mandje = new LinkedHashSet<>();
			}
			for (String id : request.getParameterValues("id")) {
				mandje.remove(Integer.parseInt(id));
			}
			session.setAttribute(MANDJE, mandje);
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}

	@Resource(name = GenreRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		filmRepository.setDataSource(dataSource);
	}

}
