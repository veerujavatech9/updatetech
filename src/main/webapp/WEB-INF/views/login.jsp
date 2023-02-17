<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title>Gorica Industries</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS 
    <link href="css/metisMenu.min.css" rel="stylesheet">-->

<!-- Timeline CSS 
    <link href="css/timeline.css" rel="stylesheet">-->

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/static/css/startmin.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,300,700,600,800,400"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/static/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="lg-bg">
	<div id="wrapper">
		<!-- Page Content -->
		<div id="">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12"
						style="margin-left: -15px; margin-top: 130px;">
						<div class="col-lg-6 col-md-6" style="margin-left: -15px"></div>
						<div class="col-lg-6 col-md-6">
							<div class="row">
								<div class="col-md-8 col-md-offset-2">
									<img
										src="${pageContext.request.contextPath}/static/img/Login-Logo.png"
										alt="" style="margin-bottom: 25px;" />

									<h4 class="text-center">Please login to your account.</h4>
									<form:form method="post" modelAttribute="user"
										action="${pageContext.request.contextPath}/validate">
										<div class="form-group">
											<label for="exampleInputEmail1">User Name</label>
											<form:input type="text" class="form-control" path="userName"
												id="exampleInputEmail1" placeholder="User Name" />
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1">Password</label>
											<form:input type="password" class="form-control"
												path="password" id="password" data-toggle="password"
												placeholder="Password" />
										</div>
										<div class="checkbox">
											<label> <input type="checkbox"> Keep me
												signed in
											</label>
										</div>
										<!--<button type="submit" class="btn btn-default btn-primary">Submit</button>-->
										<button class="btn-primary btn" type="submit">Login</button>
									</form:form>
								</div>
							</div>
							<div class="row table-gap">
								<div
									class="col-lg-6 col-xs-10 col-sm-6 col-md-5 col-sm-offset-5 col-md-offset-5 col-xs-offset-1">
									<div id="alert_display_boxParent">
										<div id="alert_display_box">
											<span style="color: red">${sessionScope['scopedTarget.sessionObj'].getStatusMessage()}</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- ... Your content goes here ... -->

			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

	<script type="text/javascript"
		src="https://unpkg.com/bootstrap-show-password@1.2.1/dist/bootstrap-show-password.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/static/js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="${pageContext.request.contextPath}/static/js/startmin.js"></script>

	<script type="text/javascript">
		$("#password").password('toggle');
	</script>
</body>
</html>
