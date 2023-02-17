<jsp:include page="header.jsp" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" />
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
				<h3 class="page-header">Add Break Timings</h3>
				<div class="col-md-6">
					<form:form class="form-horizontal" modelAttribute="breakTimings"
						method="POST"
						action="${pageContext.request.contextPath}/jobActivity/saveBreakTime">
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Break
								Reason</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="breakReason"
									id="breakReason" placeholder="Break Reason" required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Start Time</label>
							<div class="col-sm-7">
								<div class="input-group date" id="datepicker1">
									<form:input type="text" class="form-control" path="startTime"
										id="data-date" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-time"></span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">End Time</label>
							<div class="col-sm-7">
								<div class="input-group date" id="datepicker2">
									<form:input type="text" class="form-control" path="endTime"
										id="data-date2" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon-time"></span>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Break Time</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="breakTime"
									id="breakTime" name="result" placeholder="Break Time" value="0"
									required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Break
								Active</label>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		/* $.fn.datepicker.defaults.showOnFocus = false; */

		$('#datepicker1').datetimepicker({
			format: 'HH:mm'
		});

		$('#datepicker2').datetimepicker({
			format: 'HH:mm'
		});
	})

	$('#datepicker2').on('dp.change', function(e) {
		var a = $('#datepicker1').data("DateTimePicker").date();
		var b = $('#datepicker2').data("DateTimePicker").date();
		var timeDiff = Math.floor((b - a) / 60000);
		$('#breakTime').val(timeDiff);
	});
</script>
</body>
</html>

