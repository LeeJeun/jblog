<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:if test="${empty viewPost.title }">
						<h4>게시물이 없습니다.</h4>
					</c:if>
					<h4>${viewPost.title }</h4>
					<p>
						${fn:replace(viewPost.contents, replaceChar, "<br/>")}
					<p>
				</div>
				<ul class="blog-list">
					<c:if test="${empty postVo }">
						<h4>게시물이 없습니다.</h4>
					</c:if>
					<c:forEach items="${postVo }" var="postVo" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/${postVo.categoryNo}/${postVo.no}">${postVo.title}</a> <span>2015/05/02</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryVo }" var="categoryVo" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${blogVo.blogId}/${categoryVo.no}">${categoryVo.name }</a></li>
				</c:forEach>
			</ul>
			
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>