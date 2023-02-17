<jsp:include page="header.jsp" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">

		<div class="row">
			<p class="page-header"></p>
			<div class="col-lg-5 col-md-6">
				<label for=" " class="col-sm-3 control-label">Select Post
					Date</label>
				<div class="input-group date" id="date-picker">
					<input type="text" class="form-control" id="postDate"
						name="postDate" value="${selectedDate}"
						onchange="dashboardFunction()" />
					<div class="input-group-addon">
						<span class="glyphicon glyphicon-calendar"></span>
					</div>
				</div>
			</div>
		</div>

		<%-- <div class="row">
			<p class="page-header"></p>
			 <div class="col-lg-3 col-md-6">

				<div class="panel panel-blue">
					<div class="panel-heading">
						<a href="${pageContext.request.contextPath}/jobActivity/createJob">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-truck fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"></div>
									<div>Create Job</div>
								</div>
							</div>
						</a>
					</div>
				</div>

			</div>
			<div class="col-lg-3 col-md-6">
				<a
					href="${pageContext.request.contextPath}/jobActivity/ActiveJobsToWorkStation">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-link fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge"></div>
									<div>Assign Work Station</div>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-3 col-md-6">
				<a href="#">
					<div class="panel panel-purple">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-stack-overflow fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">125</div>
									<div>Over Time</div>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-3 col-md-6">
				<a href="#">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-3">
									<i class="fa fa-clock-o fa-5x"></i>
								</div>
								<div class="col-xs-9 text-right">
									<div class="huge">733</div>
									<div>Ontime</div>
								</div>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div> --%>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default" style="margin-top: 20px;">
					<div class="panel-heading ">
						<h3 class="page-header">Attendees List</h3>
					</div>

					<!-- /.panel-heading -->
					<div class="panel-body">
						<div id="dataTables-example_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row">
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
													style="width: 207px;">Emp ID</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Browser: activate to sort column ascending"
													style="width: 207px;">Emp Name</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Designation: activate to sort column ascending"
													style="width: 170px;">Job No</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 207px;">Activity No</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 176px;">Check In</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 176px;">Check out</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Idle-In Time</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Idle-Out Time</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">Idle Reason</th>
												<th class="sorting" tabindex="0"
													aria-controls="dataTables-example" rowspan="1" colspan="1"
													aria-label="Project Name: activate to sort column ascending"
													style="width: 148px;">User</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="actions" items="${todaysActions}"
												varStatus="counter">
												<tr class="gradeA odd " role="row">
													<td class="sorting_1">${counter.count}</td>
													<td>${actions.employee.empNo}</td>
													<td class="center">${actions.employee.empName}</td>
													<td>${actions.job.jobNo}</td>
													<td>${actions.activity.activityNo}</td>
													<td>${actions.checkInTime}</td>
													<td>${actions.checkOutTime}</td>
													<%-- <td><fmt:formatDate value="${actions.checkInTime}"
															pattern="HH:MM" /></td>
													<td><fmt:formatDate value="${actions.checkOutTime}"
															pattern="dd MMM yyyy HH:MM:SS" /></td> --%>
													<td>${actions.idealInTime}</td>
													<td>${actions.idealOutTime}</td>
													<td>${actions.idealReason.reason}</td>
													<td>${actions.user.userName}</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>

						</div>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>



		</div>
		<!-- ... Your content goes here ... -->

	</div>
</div>
</div>
<jsp:include page="footer.jsp" />
<script type="text/javascript">
	$('#date-picker').datepicker({
		endDate : new Date()
	});

	function dashboardFunction() {
		var postDate = $('#postDate').val();
		window.location.href = "${pageContext.request.contextPath}/accounts_Dashboard_Date?postDate="
				+ postDate;
	}
</script>
</body>
</html>

