<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<h2>
		Welcome ${pageContext.request.userPrincipal.name} | <a
			onclick="document.forms['logoutForm'].submit()">Logout</a>
	</h2>
	<br>
	<span style="float: right"> <c:url var="formPerson"
			value="/personalOffice" /> <a href="${formPerson}"> personal
			account</a>
	</span>
	<br>
	<span style="float: right"> <c:url var="formRegistrationUrl"
			value="/registration" /> <a href="${formRegistrationUrl}">
			registration</a>
	</span>
	<br>

	<span style="float: right"><a href="?lang=ru">ru</a> | <a
		href="?lang=ua">ua</a> | <a href="?lang=en">en</a> </span>
	<p>
		<spring:message code="label.registration" />
	</p>
	<p>
		<spring:message code="label.login" />
	</p>
	<h1 align="center">
		<c:url var="formTestUrl" value="/tests/list" />
		<a href="${formTestUrl}">Test proposals</a>
	</h1>

	<h3 align="center">
		<c:url var="formLoginUrl" value="/login" />
		<a href="${formLoginUrl}">Test login</a>
	</h3>
</body>
</html>