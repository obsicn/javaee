<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<form name="companyForm"
					action="/company_management/CompanyManagementServlet" method="post">
					<table>
						<tr>
							<td>Employee ID (*)</td>
							<td><input name="idemployee" value="" type="text"
								maxlength="16"></td>
						</tr>
						<tr>
							<td> Name (*)</td>
							<td><input name="name" value="" type="text"
								maxlength="45"></td>
						</tr>
						<tr>
							<td>Surname (*)</td>
							<td><input name="surname" value="" type="text" maxlength="45">
							</td>
						</tr>
						<tr>
							<td>Company Badge (*)</td>
							<td><input name="badge" value="" type="text" maxlength="45">
							</td>
						</tr>

						<tr>
							<td colspan="2">(*) Mandatory field</td>
						</tr>
					</table>
					<input name="fk_company" value="******" type="hidden">
					<input name="whatsend" value="employee" type="hidden"> 
					<input type="submit" value="Insert Employee">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>