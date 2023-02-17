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
				<h3 class="page-header">Create Employee</h3>
				<div class="col-md-6">
					<form:form class="form-horizontal" modelAttribute="emp"
						method="POST"
						action="${pageContext.request.contextPath}/emp/saveEmp">
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Employee No</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="empNo"
									id="empNo" placeholder="Employee No" required="required" />
								<c:if test = "${emp.errorMsg == ' is already Exist'}"><div style="color: red"><c:out value = "${emp.empNo}${emp.errorMsg}"/></div></c:if>
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Employee
								Name</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="empName"
									id="empName" placeholder="Employee Name" required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Employee
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

<script type="text/javascript">
	$("#password").password('toggle');
</script>
</body>
</html>

