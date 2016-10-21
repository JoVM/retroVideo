package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Klant;
import be.vdab.repositories.KlantenRepository;

/**
 * Servlet implementation class KlantenServlet
 */
@WebServlet("/klanten.htm")
public class KlantenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/klant.jsp";
	private final transient KlantenRepository klantenRepository = new KlantenRepository();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Klant> klanten = new ArrayList<>();
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Klant.isNaamValid(naam)) {
			fouten.put("naam", "tik minstens één letter");
		}
		if (fouten.isEmpty()) {
			klanten = klantenRepository.findKlantByFamilienaam(naam);
			request.setAttribute("klanten", klanten);
		} else {
			request.setAttribute("fouten", fouten);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Resource(name = KlantenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantenRepository.setDataSource(dataSource);
	}

}
