<jsp:include page="header.jsp" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.dataTables_filter>#dataTables-example_filter {
	float: right;
}
</style>
<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12"
				style="background-color: #ffffff; margin: 52px 14px 10px 7px;">
				<h3 class="page-header">Edit Job</h3>
				<div class="col-md-6">
					<form:form class="form-horizontal" modelAttribute="job"
						method="POST"
						action="${pageContext.request.contextPath}/jobActivity/updateJob">
						<form:hidden class="form-control disabled" path="id"
							value="${job.getId()}" readonly="true" />
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Job No</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="jobNo"
									id="jobNo" placeholder="Job No" value="${job.jobNo }"
									required="required" />
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Job
								Description</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control"
									path="jobDescription" id="jobDescription" value="${job.jobDescription }"
									placeholder="Description" required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Start Date</label>
							<div class="col-sm-7">
								<div class="input-group date" data-provide="datepicker">
									<form:input type="text" value="${job.strStartDate }"
										class="form-control" path="startDate" id="data-date"  required="required" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">End Date</label>
							<div class="col-sm-7">
								<div class="input-group date" data-provide="datepicker">
									<form:input type="text" value="${job.strEndDate }"
										class="form-control" path="endDate" id="data-date2" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-th"></span>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Job Active</label>
							<div class="col-sm-7">
								<c:choose>
									<c:when test="${job.status eq true}">
										<form:checkbox path="status" checked="checked"
											data-toggle="toggle" data-on="Active" data-off="In Active"
											data-onstyle="success" data-offstyle="danger" />
									</c:when>
									<c:otherwise>
										<form:checkbox path="status" data-toggle="toggle"
											data-on="Active" data-off="In Active" data-onstyle="success"
											data-offstyle="danger" />
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-6 col-sm-8" style="margin-top: 10px;">
								<button type="submit" class="btn btn-primary">Update</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<!-- ... Your content goes here ... -->

	</div>
</div>
</div>

<jsp:include page="footer.jsp" />

<script type="text/javascript">
	$("#password").password('toggle');

	$(document).ready(function() {
		$.fn.datepicker.defaults.showOnFocus = false;

		$('#datepicker').datepicker(); //Initialise any date pickers
	})
</script>
</body>
</html>

