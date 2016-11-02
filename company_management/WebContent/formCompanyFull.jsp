<%@page import = "company_management.bean.CompanyBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String company_name = "";
String idcompany = "";
String phone = "";
String email = "";

CompanyBean company = new CompanyBean();
boolean companyFilled = false;
if (request.getSession() != null && request.getSession().getAttribute("COMPANY") != null){
	company = (CompanyBean) request.getSession().getAttribute("COMPANY");
	company_name = company.getCompany_name();
	idcompany = company.getIdcompany();
	phone = company.getPhone();
	email = company.getEmail();

	companyFilled = true;
}

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<a href="/company_management/CompanyManagementServlet?whatsend=homepage" target="_top">Home Page</a>
				<% if(companyFilled){ %>
					<a href="/company_management/CompanyManagementServlet?whatsend=employeeInsert" target="_top">Insert Employee</a>
				<% } %>
			</td>
		</tr>
		<tr>
			<td>
				<form name="companyForm"
						    
					action="/company_management/CompanyManagementServlet" method="post">
					<table>
						<tr>
							<td>Company ID (*)</td>
							<td>
							<% if(companyFilled){ %>
								<%= idcompany %>
							<%}else{ %>
							<input name="idcompany" value="<%= idcompany %>" type="text"
								maxlength="16"></td>
							<% } %>
						</tr>
						<tr>
							<td>Company Name (*)</td>
							<td><input name="company_name" value="<%= company_name %>" type="text"
								maxlength="45"></td>
						</tr>
						<tr>
							<td>Phone (*)</td>
							<td><input name="phone" value="<%= phone %>" type="text" maxlength="45">
							</td>
						</tr>
						<tr>
							<td>Email (*)</td>
							<td><input name="email" value="<%= email %>" type="text" maxlength="45">
							</td>
						</tr>

						<tr>
							<td colspan="2">(*) Mandatory field</td>
						</tr>
					</table>

					<input name="whatsend" value="company" type="hidden"> 
					<input type="submit" value="Insert Company">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>