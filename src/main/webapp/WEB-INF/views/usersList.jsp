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
									style="width: 151px;">User Id</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 207px;">Full Name</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Email ID</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Contact No</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Role</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Status</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Change Password</th>
								<th style="width: 40px;">Edit</th>
								<th style="width: 40px;">Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${usersList}" varStatus="counter">
								<tr class="gradeA odd " role="row">
									<td class="sorting_1">${counter.count}</td>
									<td>${user.userName}</td>
									<td class="center">${user.fullName}</td>
									<td>${user.emailId}</td>
									<td class="center">${user.mobileNo}</td>
									<td>${user.role.role}</td>
									<td><c:choose>
											<c:when test="${user.enabled eq true}"> Active</c:when>
											<c:otherwise>In-Active</c:otherwise>
										</c:choose></td>
									<td class="center"><a
										href="${pageContext.request.contextPath}/user/changePassword/${user.id}"
										class="btn btn-xs btn-warning" title="Change Password"><i class="fa fa-lock fa-fw"></i></a></td>
									<td class="center"><a
										href="${pageContext.request.contextPath}/user/editUser/${user.id}"
										class="btn btn-xs btn-primary" title="Edit"><i class="fa fa-edit fa-fw"></i></a></td>
									<td class="center"><button class="btn btn-xs btn-danger" title="Delete"
											onclick="if(confirm('Are you sure you want to Delete this User?')) window.location.href = '${pageContext.request.contextPath}/user/delete/${user.id}';">
											<i class="fa fa-trash fa-fw"></i>
										</button></td>
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