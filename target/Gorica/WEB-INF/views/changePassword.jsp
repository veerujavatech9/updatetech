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
				<h3 class="page-header">Change Password</h3>
				<div class="col-md-6">
					<form:form class="form-horizontal" modelAttribute="user"
						method="POST"
						action="${pageContext.request.contextPath}/user/updatePassword">
						<form:hidden class="form-control disabled" path="id"
							value="${user.getId()}" readonly="true" />
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User Id</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="userName"
									id="userName" value="${user.userName }" placeholder="User ID"
									disabled="true" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">New
								Password</label>
							<div class="col-sm-7">
								<form:password class="form-control" path="password"
									id="password" placeholder="New Password" required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Confirm
								Password</label>
							<div class="col-sm-7">
								<input type="password" class="form-control"
									id="txtConfirmPassword" placeholder="Confirm Password"
									data-toggle="password" required="required" />
							</div>
						</div>


						<div class="form-group">
							<div class="col-sm-offset-6 col-sm-8" style="margin-top: 10px;">
								<button type="submit" onclick="return Validate()"
									class="btn btn-primary">Update Password</button>
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

	function Validate() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("txtConfirmPassword").value;
		if (password != confirmPassword) {
			alert("Passwords do not match.");
			return false;
		}
		return true;
	}
</script>
</body>
</html>

