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
				<h3 class="page-header">Create Job</h3>
				<div class="col-md-6">
					<form:form class="form-horizontal" modelAttribute="job"
						method="POST"
						action="${pageContext.request.contextPath}/jobActivity/saveJob">
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Job No</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="jobNo"
									id="jobNo" placeholder="Job No" required="required" />
								<c:if test = "${job.errorMsg == ' is already Exist'}"><div style="color: red"><c:out value = "${job.jobNo}${job.errorMsg}"/></div></c:if>
								
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Job
								Description</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="jobDescription"
									id="jobDescription" placeholder="Description" required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Start Date</label>
							<div class="col-sm-7">
								<div class="input-group date" data-provide="datepicker">
									<form:input type="text" class="form-control" path="startDate"   required="required"
										id="data-date" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">End Date</label>
							<div class="col-sm-7">
								<div class="input-group date" data-provide="datepicker">
									<form:input type="text" class="form-control" path="endDate" 
										id="data-date2" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Job Active</label>
							<div class="col-sm-7">
								<form:checkbox path="status" checked="checked"
									data-toggle="toggle" data-on="Active" data-off="In Active"
									data-onstyle="success" data-offstyle="danger" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-6 col-sm-8" style="margin-top: 10px;">
								<button type="submit" class="btn btn-primary">Submit</button>
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
	$(document).ready(function() {
		$.fn.datepicker.defaults.showOnFocus = false;

		$('#datepicker').datepicker(); //Initialise any date pickers
	})
</script>
</body>
</html>

