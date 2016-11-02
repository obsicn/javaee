package company_management.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company_management.bean.CompanyBean;
import company_management.bean.EmployeeBean;
import company_management.dao.SaveMySQL;

/**
 * Servlet implementation class CompanyManagementServlet
 */
@WebServlet("/CompanyManagementServlet")
public class CompanyManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String whatsend = request.getParameter("whatsend");
		System.out.println("whatsend:" + whatsend);
		if (whatsend.equals("employeeInsert")){
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
			rd.forward(request, response);
		} else if (whatsend.equals("employee")) {
			request.getSession().removeAttribute("EMPLOYEE");
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
			rd.forward(request, response);
		} else if (whatsend.equals("company")) {
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formCompanyFull.jsp");
			rd.forward(request, response);		
		} else if (whatsend.equals("homepage")) {
			response.sendRedirect("/company_management/");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		String whatsend = request.getParameter("whatsend");
		
		System.out.println("POST Whatsend:" + whatsend);
		
		if(whatsend.equals("employee")) {
			String idemployee = request.getParameter("idemployee");
			System.out.println("idemployee:" + idemployee);
			String name = request.getParameter("name");
			System.out.println("name:" + name);
			String surname = request.getParameter("surname");
			System.out.println("surname:" + surname);
			String badge = request.getParameter("badge");
			System.out.println("badge:" + badge);
			String fk_company = request.getParameter("fk_company");
			System.out.println("fk_company:" + fk_company);	
			
			EmployeeBean employee = new EmployeeBean();
			employee.setIdemployee(idemployee);
			employee.setName(surname);
			employee.setSurname(surname);
			employee.setBadge(badge);
			employee.setFk_company(fk_company);
			
			ServletContext sc = request.getSession().getServletContext();
			
			request.getSession().removeAttribute("EMPLOYEE");
			request.getSession().setAttribute("EMPLOYEE", employee);
			
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployeeFull.jsp");
			rd.forward(request, response);
			
			CompanyBean company = new CompanyBean();
			if (request.getSession() !=null  && request.getSession().getAttribute("COMPANY") != null){
				company = (CompanyBean) request.getSession().getAttribute("COMPANY");
				ArrayList<EmployeeBean> companyEmployees = company.getCompanyEmployees();
				companyEmployees.add(employee);
				company.setCompanyEmployees(companyEmployees);
				request.getSession().removeAttribute("COMPANY");
				request.getSession().setAttribute("COMPANY", company);
			}
		}
		else if(whatsend.equals("company")){
			String idcompany = request.getParameter("idcompany");
			System.out.println("idcompany:" + idcompany);
			String company_name = request.getParameter("company_name");
			System.out.println("company_name:" + company_name);
			String phone = request.getParameter("phone");
			System.out.println("phone:" + phone);
			String email = request.getParameter("email");
			System.out.println("email:" + email);	
			
			CompanyBean company = new CompanyBean();
			ArrayList<EmployeeBean> companyEmployees = new ArrayList<EmployeeBean>();
			company.setIdcompany(idcompany);
			company.setCompany_name(company_name);
			company.setEmail(email);
			company.setPhone(phone);
			company.setCompanyEmployees(companyEmployees);
			
			ServletContext sc = request.getSession().getServletContext();
			
			request.getSession().removeAttribute("COMPANY");
			request.getSession().setAttribute("COMPANY", company);
			
			RequestDispatcher rd = sc.getRequestDispatcher("/formCompanyFull.jsp");
			rd.forward(request, response);
		} else if (whatsend.equalsIgnoreCase("saveCompany")) {
			CompanyBean company = new CompanyBean();
			
			String idcompany = request.getParameter("idcompany");
			System.out.println("idcompany:" + idcompany);
			String company_name = request.getParameter("company_name");
			System.out.println("company_name:" + company_name);
			String phone = request.getParameter("phone");
			System.out.println("phone:" + phone);
			String email = request.getParameter("email");
			System.out.println("email:" + email);	
			
			ArrayList<EmployeeBean> companyEmployees = new ArrayList<EmployeeBean>();
			company.setIdcompany(idcompany);
			company.setCompany_name(company_name);
			company.setEmail(email);
			company.setPhone(phone);
			company.setCompanyEmployees(companyEmployees);
			
			ServletContext sc = request.getSession().getServletContext();
			
			request.getSession().removeAttribute("COMPANY");
			request.getSession().setAttribute("COMPANY", company);
			
			SaveMySQL saveCompany = new SaveMySQL();
			
			try {
				saveCompany.insertCompany(company);
			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			}
			ArrayList<CompanyBean> companyInDB = new ArrayList<CompanyBean>();
			try {
				companyInDB = saveCompany.searchCompanies();
			} catch (SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode() + ":" + e.getMessage());
				e.printStackTrace();
			}
			
			request.getSession().removeAttribute("COMPANIES");
			request.getSession().setAttribute("COMPANIES", companyInDB);
			
			RequestDispatcher rd = sc.getRequestDispatcher("/listCompany.jsp");
			rd.forward(request, response);
			
			
		}
	}

}
