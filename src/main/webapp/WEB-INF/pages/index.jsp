<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
	<head>
		<jsp:include page="modules/header.jsp" />
	</head>
	
	<body style="padding: 75px;">
		<jsp:include page="modules/navBar.jsp" />
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Instructions</div>
						<div class="panel-body">
							<p>To make a search you need to enter the title of the movie or show. For example, you can search The Big Bang Theory. Then on the description, you can add some filters like LOL, VTV, YIFY. You can add them with spaces, to add more criteria to filter.</p>
							<p>
								<strong><u>Example Search:</u></strong>
								</br><u>Title:</u> The Big Bang Theory
								</br><u>Description:</u> LOL
							</p>
							<p>Then, when your search is done, you can apply more filters with the filter on the table. <u>Like:</u> S01E01.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>