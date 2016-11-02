package company_management.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        System.out.println("GET");
        String idemployee = request.getParameter("idemployee");
        System.out.println("idemployee(GET):" + idemployee);
        PrintWriter out = response.getWriter();
        java.util.Date today = new java.util.Date();
        out.println("<html><body>" + today +
                        "GET paramter:" + idemployee + "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
        System.out.println("SONO IN POST");
        String name = request.getParameter("name");
        System.out.println("name(POST):" + name);
        PrintWriter out = response.getWriter();
        java.util.Date today = new java.util.Date();
        out.println("<html><body>" + today +
                        "POST paramter:" + name + "</body></html>");
	}

}
