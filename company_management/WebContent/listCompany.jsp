<%@ page import="java.util.ArrayList" %>
<%@ page import="company_management.bean.CompanyBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<CompanyBean> companiesList = new ArrayList<CompanyBean>();
	if (request.getSession().getAttribute("COMPANIES") != null) {
		companiesList = (ArrayList<CompanyBean>) request.getSession().getAttribute("COMPANIES");
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Companies List</title>
</head>
<body>
	<table>
		<tr>
			<th>id company</th>
			<th>company name</th>
		</tr>
		<%
			for (CompanyBean company:companiesList) {
				String idcompany = company.getIdcompany();
				String company_name = company.getCompany_name();
		%>
		<tr>
			<td><%= idcompany %></td>
			<td><%= company_name %></td>
		</tr>
		<%} %>

	</table>
</body>
</html>