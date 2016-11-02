package company_management.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import company_management.bean.CompanyBean;
import company_management.bean.EmployeeBean;

public class SaveMySQL {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/company_management";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "passw0rd";
	
	private static Connection getDBConnection(){
		System.out.println("------MySQL JDBC Connection------");
		Connection dbConnection = null;
		
		try{
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR: MySQL JDBC Driver not found!!");
		}
		
		try{
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			System.out.println("Connection to Company_Management database established");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Connection to Company_Management database failed");
		}
		
		return dbConnection;

	}
	
	public void insertCompany(CompanyBean company) throws SQLException, IOException {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String insertCompany = "INSERT INTO COMPANY (idcompany, company_name, phone, email, date_ins)";
			
			insertCompany += "VALUES ('"
					+ company.getIdcompany() + "', '"
					+ company.getCompany_name() + "', '"
					+ company.getPhone() + "', '"
					+ company.getEmail() + "', "
					+ "SYSDATE())";
			
			System.out.println("INSERT QUERY:" + insertCompany);
			int resultInsertCompany = stmt.executeUpdate(insertCompany);
			System.out.println("result insert query: " + resultInsertCompany);
			
			ArrayList<EmployeeBean> employees = company.getCompanyEmployees();
			
			for (EmployeeBean employee:employees) {
				String insertEmployee = "INSERT INTO EMPLOYEE (idemployee, surname, name, badge, FK_company, date_ins)";
				
				insertEmployee += " VALUES ('"
						+ employee.getIdemployee() + "', '"
						+ employee.getName() + "', '"
						+ employee.getSurname() + "', '"
						+ employee.getBadge() + "', '"
						+ employee.getFk_company() + "', "
						+ " SYSDATE())";
				
				System.out.println("INSERT EMPLOYEE: " + insertEmployee);
				int resultInsertEmployee = stmt.executeUpdate(insertEmployee);
				System.out.println("result insert query:" + resultInsertEmployee);
			}
			conn.commit();
		} catch (SQLException sqle) {
			conn.rollback();
			System.out.println("INSERT ERROR: transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			conn.rollback();
			System.out.println("GENERIC ERROR: transaction is being rolled back");
			err.printStackTrace();
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}

	
	public ArrayList<CompanyBean> searchCompanies() throws SQLException, IOException {
		Statement stmt = null;
		Connection conn = null;
		
		try {
			conn = getDBConnection();
			stmt = conn.createStatement();
			
			String searchCompany = "SELECT * from COMPANY ";
			System.out.println("QUERY: " + searchCompany);
			ResultSet companyList = stmt.executeQuery(searchCompany);
			ArrayList<CompanyBean> companyListInDB = new ArrayList<CompanyBean>();
			
			while (companyList.next()){
				
				CompanyBean company = new CompanyBean();
				String idcompany = companyList.getString("idcompany");
				String company_name = companyList.getString("company_name");
				System.out.println("Result Set: " + companyList.getRow() + idcompany);
				company.setIdcompany(idcompany);
				company.setCompany_name(company_name);
				companyListInDB.add(company);				
			}
			return companyListInDB;
		} catch (SQLException sqle) {
			throw new SQLException(sqle.getErrorCode() + ":" + sqle.getMessage());
		} catch (Exception err) {
			throw new SQLException(err.getMessage());
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
	}
}
