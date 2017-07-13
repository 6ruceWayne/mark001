<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${contextPath}/resources/css/bootstrap.min.css">
<script src="${contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 align="left">
		<c:url var="formTestUrl" value="/tests/list" />
		<a href="${formTestUrl}"><spring:message code="back.to.proposals" /></a>
	</h1>

	<span style="float: right"><a href="?lang=ru">ru</a> | <a
		href="?lang=ua">ua</a> | <a href="?lang=en">en</a> </span>
	<br>

	<c:url var="addAction" value="/tests/add"></c:url>
	<form:form modelAttribute="ourTest" method="POST" action="tests/add">
		<table>
			<tr>
				<td><spring:message code="test.name" /></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><spring:message code="test.free" /></td>
				<td><form:input path="free" /></td>
			</tr>
			<c:forEach items="${ourTest.questions}" varStatus="vs">
				<tr>
					<td><form:label path="questions[${vs.index}].question">
							<spring:message code="test.question" />:</form:label> <form:input
							path="questions[${vs.index}].question"></form:input></td>
				</tr>

				<c:forEach items="${vs.getCurrent().answers}" varStatus="answ">
					<tr>
						<td><form:label path="${answ.getCurrent()}.answer">
								<spring:message code="test.answer" />:</form:label> <form:input
								path="${answ.getCurrent()}.answer"></form:input></td>
					</tr>
				</c:forEach>
			</c:forEach>
			<tr>
				<td colspan="2"><c:if test="${!empty ourTest.name}">
						<input type="submit" value="<spring:message text="Save Test"/>" />
					</c:if> <c:if test="${empty ourTest.name}">
						<input type="submit" value="<spring:message text="Add Test"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>


	<div class="container">
		<div class="btn-group">
			<button type="button" class="btn btn-primary">
				<spring:message code="choise" />
			</button>
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown">
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a
					href="<c:url value='/tests/choise/${ourTest.id}/${"Approve"}' />"><spring:message
							code="aprove" /></a></li>
				<li><a
					href="<c:url value='/tests/choise/${ourTest.id}/${"Return"}' />"><spring:message
							code="return" /></a></li>
				<li><a
					href="<c:url value='/tests/choise/${ourTest.id}/${"Refuse"}' />"><spring:message
							code="refuse" /></a></li>
			</ul>
		</div>
	</div>
</body>
</html>