<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
	<head>
		<jsp:include page="modules/header.jsp" />
	</head>
	
	<script>
		$(document).ready(function (){
		    $('table').DataTable({
		    	lengthMenu: [[200, -1], [200, "All"]],
		    	paging: true,
		    	ordering: false
		    });
		});
	</script>
	
	<style>
		.dataTables_filter{
			padding-top: 15px;
			padding-bottom: 10px;
			padding-right: 20px;
		}
		
		#DataTables_Table_0_length{
			padding-top: 18px;
			padding-left: 15px;
		}
		
		#DataTables_Table_0_info{
			padding-left: 15px;
		}
		
		#DataTables_Table_0_paginate{
			padding-right: 20px;
		}
	</style>
	
	<body style="padding: 75px;">
		<jsp:include page="modules/navBar.jsp" />
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Results</div>
						<table class="table table-striped" style="font-size: 14px;">
							<thead>
								<tr>
									<th>Title</th>
									<th>Description</th>
									<th>Download</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="subtitle" items="${subtitles}">
									<tr>
										<td>
											<c:out value="${subtitle.title}" escapeXml="true"/>
										</td>
										<td>
											<c:out value="${subtitle.description}" escapeXml="true"/>
										</td>
										<td class="text-center">
											<a href="<c:out value="${subtitle.downloadLink}" escapeXml="true"/>" target="new">
												<img src="${ctx}/images/download.gif"></img>
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>