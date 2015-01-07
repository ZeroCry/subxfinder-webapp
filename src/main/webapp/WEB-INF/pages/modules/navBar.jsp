<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${ctx}">SubxFinder</a>
		</div>
		<form action="${ctx}/finder/search" method="POST" class="navbar-form navbar-right" role="search">
			<div class="form-group">
				<input name="title" type="text" class="form-control" placeholder="Title" value="${title}">
			</div>
			<div class="form-group">
				<input name="description" type="text" class="form-control" placeholder="Description" value="${description}">
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form>
	</div>
</nav>