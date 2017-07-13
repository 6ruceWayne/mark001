<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/Testowiki/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/Testowiki/resources/css/common.css" rel="stylesheet">
<script src="/Testowiki/resources/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<a href="../index.jsp"><spring:message code="back" /></a>
	<br>
	<span style="float: right"><a href="?lang=ru">ru</a> | <a
		href="?lang=ua">ua</a> | <a href="?lang=en">en</a> </span>
	<br>
	<div class="btn-group">
		<button type="button" class="btn btn-primary">My results</button>
		<button type="button" class="btn btn-primary">My tests</button>
	</div>
	<c:if test="${!empty listTests}">
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="25"><spring:message code="test.id" /></th>
					<th width="80"><spring:message code="test.name" /></th>
					<th width="80"><spring:message code="test.author" /></th>
					<th width="120"><spring:message code="test.description" /></th>
					<th width="60"><spring:message code="test.isFree" /></th>
					<th width="120"><spring:message code="test.comment.to.admin" /></th>
					<th width="120"><spring:message code="test.section" /></th>
					<th width="120"><spring:message code="test.status" /></th>
					<th width="60"><spring:message code="test.review" /></th>
					<th width="60"><spring:message code="test.delete" /></th>
				</tr>
			</thead>
			<c:forEach items="${listTests}" var="test">
				<tr>
					<td>${test.id}</td>
					<td>${test.name}</td>
					<td>${test.author}</td>
					<td>${test.description}</td>
					<td>${test.isFree}</td>
					<td>${test.commentToAdmin}</td>
					<td>${test.section}</td>
					<td>${test.status}</td>
					<td><a href="<c:url value='/tests/review/${test.id}' />"><spring:message
								code="test.review" /></a></td>
					<td><a href="<c:url value='/tests/remove/${test.id}' />"><spring:message
								code="test.delete" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>