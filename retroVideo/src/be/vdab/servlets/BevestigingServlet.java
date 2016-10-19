package be.vdab.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
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
import be.vdab.entities.Reservatie;
import be.vdab.repositories.FilmRepository;
import be.vdab.repositories.KlantenRepository;
import be.vdab.repositories.ReservatieRepository;

/**
 * Servlet implementation class BevestigingServlet
 */
@WebServlet("/bevestiging.htm")
public class BevestigingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";
	private static final String MANDJE = "mandje";
	private static final String REDIRECT_URL = "%s/rapport.htm";
	private final transient KlantenRepository klantenRepository = new KlantenRepository();
	private final transient ReservatieRepository reservatieRepository = new ReservatieRepository();
	private final transient FilmRepository filmRepository = new FilmRepository();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("klant", klantenRepository.read(id));
		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE);
			if (mandje != null) {
				request.setAttribute("aantalitems", mandje.size());
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
		HttpSession session = request.getSession(false);
		Map<String, String> fouten = new HashMap<>();
		int klantid = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("klant", klantenRepository.read(klantid));
		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE);
			if (mandje != null) {
				if (mandje.size() != 0) {
					for (Integer filmId : mandje) {
						Film film = filmRepository.read(filmId);
						if (film.isBeschikbaar()) {
							reservatieRepository.voegReservatieToe(new Reservatie(klantid, film.getId(),
									new Date(Calendar.getInstance().getTime().getTime())));
							filmRepository.updateGereserveerd(film.getId());
						} else {
							fouten.put(film.getTitel(), "Reservatie van: " + film.getTitel() + " is mislukt");
						}
					}
				}
			}
		}
		if (fouten.isEmpty()) {
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
		} else {
			request.setAttribute("fouten", fouten);
			doGet(request, response);
		}
	}

	@Resource(name = KlantenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantenRepository.setDataSource(dataSource);
		reservatieRepository.setDataSource(dataSource);
		filmRepository.setDataSource(dataSource);
	}

}
