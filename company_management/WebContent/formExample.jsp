<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
        <form action="/company_management/FirstServlet" method="post">
                name:<input type="text" value="" name="name" />
                <input type="submit" value="POST SUBMIT" />
        </form>
        <form action="/company_management/FirstServlet" method="get">
                idemployee:<input type="text" value="" name="idemployee" />
                <input type="submit" value="GET SUBMIT">
        </form>
</body>
</html>