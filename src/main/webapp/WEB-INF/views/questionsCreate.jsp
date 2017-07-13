<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="index.jsp">Back</a>

	<h3>Questions List</h3>
	<button type="submit" class="btn btn-info">
		<span class="glyphicon glyphicon-search"></span> Search
	</button>
	<c:if test="${!empty questionsList}">
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="25">Question ID</th>
					<th width="80">Question Name</th>
				</tr>
			</thead>
			<c:forEach items="${questionsList}" var="question">
				<tr>
					<td>${question.id}</td>
					<td>${question.question}</td>
					<td><a href="<c:url value='/tests/review/${question.id}' />">Review</a></td>
					<td><a href="<c:url value='/tests/remove/${question.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>