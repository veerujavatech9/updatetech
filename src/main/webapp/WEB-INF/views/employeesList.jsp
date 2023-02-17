<jsp:include page="header.jsp" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12"
				style="background-color: #ffffff; margin: 52px 14px 10px 7px;">
				<h3 class="page-header">${title }</h3>
				<div class="col-sm-12">
					<table width="100%"
						class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline"
						id="dataTables-example" role="grid"
						aria-describedby="dataTables-example_info"
						style="width: 100%; font-size: 13.5px; margin-top: 10px;">
						<thead>
							<tr role="row" class="bg-warning">
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Name: activate to sort column descending"
									style="width: 151px;">S.No</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Name: activate to sort column descending"
									style="width: 151px;">Employee No</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 207px;">Employee Name</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Status</th>

								<c:choose>
									<c:when
										test="${sessionScope['scopedTarget.sessionObj'].getUser().getRole().getId()!=4}">
										<th style="width: 40px;">Edit</th>
										<th style="width: 40px;">Delete</th>
									</c:when>
								</c:choose>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="emp" items="${empList}" varStatus="counter">
								<tr class="gradeA odd " role="row">
									<td class="sorting_1">${counter.count}</td>
									<td>${emp.empNo}</td>
									<td class="center">${emp.empName}</td>
									<td><c:choose>
											<c:when test="${emp.status eq true}"> Active</c:when>
											<c:otherwise>In-Active</c:otherwise>
										</c:choose></td>
									<c:choose>
										<c:when
											test="${sessionScope['scopedTarget.sessionObj'].getUser().getRole().getId()!=4}">
											<td class="center"><a
												href="${pageContext.request.contextPath}/emp/editEmp/${emp.id}"
												class="btn btn-xs btn-warning"><i
													class="fa fa-edit fa-fw"></i></a></td>
											<td class="center"><button class="btn btn-xs btn-danger"
													onclick="if(confirm('Are you sure you want to Delete this Employee?')) window.location.href = '${pageContext.request.contextPath}/emp/deleteEmp/${emp.id}';">
													<i class="fa fa-trash fa-fw"></i>
												</button></td>
										</c:when>
									</c:choose>

								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- ... Your content goes here ... -->

	</div>
</div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>