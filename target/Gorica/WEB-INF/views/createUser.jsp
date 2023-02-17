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
				<h3 class="page-header">Create User</h3>
				<div class="col-md-6">
					<form:form class="form-horizontal" modelAttribute="user"
						method="POST"
						action="${pageContext.request.contextPath}/user/saveUser">
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User Id</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="userName"
									id="userName" placeholder="User ID" required="required" />
								    <c:if test = "${user.errorMsg == ' is already Exist'}"><div style="color: red"><c:out value = "${user.userName}${user.errorMsg}"/></div></c:if>
									
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Full Name</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="fullName"
									id="" placeholder="User First name" required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Set
								Password</label>
							<div class="col-sm-7">
								<form:password class="form-control" path="password"
									id="password" placeholder="Set Password" data-toggle="password"
									required="required" />
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Mobile No</label>
							<div class="col-sm-7">
								<form:input type="text" class="form-control" path="mobileNo"
									id="" placeholder="+971 XXXXXXXXXX" />
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">Email ID</label>
							<div class="col-sm-7">
								<form:input type="email" class="form-control" path="emailId"
									id="" placeholder="Email ID" />
							</div>
						</div>
						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User Role</label>
							<div class="col-sm-7">
								<form:select class="form-control" type="text" path="role.id"
									required="required">
									<option value="">Please select Role</option>
									<c:forEach var="role" items="${rolesList}">
										<option value="${role.id}">${role.role}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>

						<div class="form-group">
							<label for=" " class="col-sm-5 control-label">User Active</label>
							<div class="col-sm-7">
								<form:checkbox path="enabled" checked="checked"
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

