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
				<h3 class="page-header">
					Assign Activities to Job No: <b>${job.jobNo }</b>
				</h3>
				<div class="col-md-6">
					<form:form class="form-horizontal" modelAttribute="activity"
						method="POST"
						action="${pageContext.request.contextPath}/jobActivity/assignWorkStationsToJob">
						<input type="hidden" value="${jobId }" name="jobId" id="jobId">
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Activity No</label>
							<div class="col-sm-7">
								<form:input type="number" min="1" class="form-control" path="activityNo"
									id="activityNo" placeholder="Work Station No"
									required="required" />
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Description</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="activityDescription" id="activityDescription"
									placeholder="Description" required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Work
								Station Active</label>
							<div class="col-sm-7">
								<form:checkbox path="status" checked="checked"
									data-toggle="toggle" data-on="Active" data-off="In Active"
									data-onstyle="success" data-offstyle="danger" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-5 col-sm-8" style="margin-top: 10px;">
								<button type="submit" class="btn btn-primary">Assign
									Activity</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12"
			style="background-color: #ffffff; margin: 52px 14px 10px 7px;">
			<h3 class="page-header">
				Added Activities for Job No: <b>${job.jobNo }</b>
			</h3>
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
								style="width: 151px;">Activity No</th>
							<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Browser: activate to sort column ascending"
								style="width: 207px;">Description</th>
							<th class="sorting" tabindex="0"
								aria-controls="dataTables-example" rowspan="1" colspan="1"
								aria-label="Browser: activate to sort column ascending"
								style="width: 207px;">Remove</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="activity" items="${activityList}"
							varStatus="counter">
							<tr class="gradeA odd " role="row">
								<td class="sorting_1">${counter.count}</td>
								<td>${activity.activityNo}</td>
								<td class="center">${activity.activityDescription}</td>
								<td class="center"><button class="btn btn-xs btn-danger"
										onclick="if(confirm('Are you sure you want to Remove this Activity?')) window.location.href = '${pageContext.request.contextPath}/jobActivity/removeAssignedActivity/${activity.id}/${jobId }';">
										Remove</button></td>
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

