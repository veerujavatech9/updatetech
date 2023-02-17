<!DOCTYPE html>
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
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/static/css/startmin.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/css/Gorica.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/css/bootstrap-datepicker.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/css/tabler-align.css"
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
<style>
</style>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> <img
					src="${pageContext.request.contextPath}/static/img/logo-white.png"
					width="220" alt="">
				</a>
			</div>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<!-- Top Navigation: Left Menu -->

			<!-- Top Navigation: Right Menu -->
			<ul class="nav navbar-right navbar-top-links">
				<!--<li class="dropdown navbar-default">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-bell fa-fw"></i> <b class="caret"></b>
                </a>
                <ul class="dropdown-menu dropdown-alerts">
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-comment fa-fw"></i> New Comment
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
            </li>-->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						${sessionScope['scopedTarget.sessionObj'].getUser().getUserName()}<b
						class="caret"></b>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li><a
							href="${pageContext.request.contextPath}/user/changePassword/
							${sessionScope['scopedTarget.sessionObj'].getUser().getId()}"><i
								class="fa fa-lock fa-fw"></i> Change Password </a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/sign_out"><i
								class="fa fa-sign-out fa-fw"></i> Logout</a></li>
					</ul></li>
			</ul>

			<c:choose>
				<c:when
					test="${sessionScope['scopedTarget.sessionObj'].getUser().getRole().getId()==1}">
					<!-- Sidebar -->
					<div class="navbar-default sidebar" role="navigation">
						<!--margin-top: 16px; background-color: #0d4272-->
						<div class="sidebar-nav navbar-collapse">
							<div class=""
								style="background-color: #094D8D; min-height: 100vh">
								<ul class="nav" id="side-menu">
									<li><a
										href="${pageContext.request.contextPath}/admin_Dashboard"
										class="active"><i class="fa fa-dashboard fa-fw"></i>
											Dashboard</a></li>
									<li><a href="#"><i class="fa fa-user-md fa-fw"></i>
											User Management<span class="fa arrow"></span></a>
										<ul class="nav nav-second-level">
											<li><a
												href="${pageContext.request.contextPath}/user/createUser">
													<i class="fa fa-user-plus fa-fw"></i> Create User
											</a></li>
											<li><a
												href="${pageContext.request.contextPath}/user/allUsers"><i
													class="fa fa-search fa-fw"></i> View And Edit Users </a></li>
										</ul></li>

									<li><a href="#"><i class="fa fa-user-md fa-fw"></i>
											Employee Management<span class="fa arrow"></span></a>
										<ul class="nav nav-second-level">
											<li><a
												href="${pageContext.request.contextPath}/emp/createEmp">
													<i class="fa fa-user-plus fa-fw"></i> Create Employee
											</a></li>
											<li><a
												href="${pageContext.request.contextPath}/emp/allEmps"><i
													class="fa fa-search fa-fw"></i> View And Edit Employees</a></li>
										</ul></li>

									<li><a href="#"><i class="fa fa-user-md fa-fw"></i>
											Idle Reasons Management<span class="fa arrow"></span></a>
										<ul class="nav nav-second-level">
											<li><a
												href="${pageContext.request.contextPath}/timeLog/createReason">
													<i class="fa fa-user-plus fa-fw"></i> Create Reason
											</a></li>
											<li><a
												href="${pageContext.request.contextPath}/timeLog/allReasons"><i
													class="fa fa-search fa-fw"></i> View And Edit Reasons</a></li>
										</ul></li>

									<li><a href="#"><i class="fa fa-coffee fa-fw"></i>
											Break Timings Management<span class="fa arrow"></span></a>
										<ul class="nav nav-second-level">
											<li><a
												href="${pageContext.request.contextPath}/jobActivity/createBreakTiming">
													<i class="fa fa-coffee fa-fw"></i> Add Break Timing
											</a></li>
											<li><a
												href="${pageContext.request.contextPath}/jobActivity/allBreakTimings"><i
													class="fa fa-search fa-fw"></i> View And Edit Break Timing</a></li>
										</ul></li>

									<li><a href="#"><i class="fa fa-truck fa-fw"></i> Job
											& Activity Management<span class="fa arrow"></span></a>
										<ul class="nav nav-second-level">
											<li><a
												href="${pageContext.request.contextPath}/jobActivity/createJob">
													<i class="fa fa-truck fa-fw"></i> Create Job
											</a></li>
											<li><a
												href="${pageContext.request.contextPath}/jobActivity/allJobs">
													<i class="fa fa-search fa-fw"></i> Jobs List
											</a></li>
											<%-- <li><a
												href="${pageContext.request.contextPath}/jobActivity/createWorkStation">
													<i class="fa fa-user-plus fa-fw"></i> Create Work Station
											</a></li> --%>
											<%-- <li><a
												href="${pageContext.request.contextPath}/jobActivity/allWorkStations">
													<i class="fa fa-search fa-fw"></i> Work Stations
											</a></li> --%>
											<li><a
												href="${pageContext.request.contextPath}/jobActivity/ActiveJobsToWorkStation">
													<i class="fa fa-link fa-fw"></i> Assign Work Station
											</a></li>
										</ul></li>

									<li><a href="#"><i class="fa fa-table fa-fw"></i>
											Attendance Reports <span class="fa arrow"></span></a>
										<ul class="nav nav-second-level">
											<li><a
												href="${pageContext.request.contextPath}/timeLog/dailyAttendeanceReport"><i
													class="fa fa-clock-o fa-fw"></i> Daily Report </a></li>

											<li><a
												href="${pageContext.request.contextPath}/timeLog/weeklyAttendeanceReport"><i
													class="fa fa-calendar fa-fw"></i> Weekly Report </a></li>

											<li><a
												href="${pageContext.request.contextPath}/timeLog/monthlyAttendeanceReport"><i
													class="fa fa-calendar-check-o fa-fw"></i> Monthly Report </a></li>

										</ul></li>

								</ul>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when
							test="${sessionScope['scopedTarget.sessionObj'].getUser().getRole().getId()==4}">

							<!-- Sidebar -->
							<div class="navbar-default sidebar" role="navigation">
								<!--margin-top: 16px; background-color: #0d4272-->
								<div class="sidebar-nav navbar-collapse">
									<div class=""
										style="background-color: #094D8D; min-height: 100vh">
										<ul class="nav" id="side-menu">
											<li><a
												href="${pageContext.request.contextPath}/prd_Dashboard"
												class="active"><i class="fa fa-dashboard fa-fw"></i>
													Dashboard</a></li>


											<li><a href="#"><i class="fa fa-user-md fa-fw"></i>
													Employee Management<span class="fa arrow"></span></a>
												<ul class="nav nav-second-level">
													<li><a
														href="${pageContext.request.contextPath}/emp/createEmp">
															<i class="fa fa-user-plus fa-fw"></i> Create Employee
													</a></li>
													<li><a
														href="${pageContext.request.contextPath}/emp/allEmps"><i
															class="fa fa-search fa-fw"></i> View Employees</a></li>
												</ul></li>

											<li><a href="#"><i class="fa fa-user-md fa-fw"></i>
													Idle Reasons Management<span class="fa arrow"></span></a>
												<ul class="nav nav-second-level">
													<li><a
														href="${pageContext.request.contextPath}/timeLog/createReason">
															<i class="fa fa-user-plus fa-fw"></i> Create Reason
													</a></li>
													<li><a
														href="${pageContext.request.contextPath}/timeLog/allReasons"><i
															class="fa fa-search fa-fw"></i> View And Edit Reasons</a></li>
												</ul></li>

											<li><a href="#"><i class="fa fa-truck fa-fw"></i>
													Job & Activity Management<span class="fa arrow"></span></a>
												<ul class="nav nav-second-level">
													 <li><a
														href="${pageContext.request.contextPath}/jobActivity/createJob">
															<i class="fa fa-truck fa-fw"></i> Create Job
													</a></li>
													<li><a
														href="${pageContext.request.contextPath}/jobActivity/allJobs">
															<i class="fa fa-search fa-fw"></i> Jobs List
													</a></li>
													<%-- <li><a
														href="${pageContext.request.contextPath}/jobActivity/createWorkStation">
															<i class="fa fa-user-plus fa-fw"></i> Create Work Station
													</a></li> --%>
													<%-- <li><a
														href="${pageContext.request.contextPath}/jobActivity/allWorkStations">
															<i class="fa fa-search fa-fw"></i> Work Stations
													</a></li> --%>
													<li><a
														href="${pageContext.request.contextPath}/jobActivity/ActiveJobsToWorkStation">
															<i class="fa fa-link fa-fw"></i> Assign Work Station
													</a></li>
												</ul></li>
											<li><a href="#"><i class="fa fa-table fa-fw"></i>
													Attendance Reports <span class="fa arrow"></span></a>
												<ul class="nav nav-second-level">
													<li><a
														href="${pageContext.request.contextPath}/timeLog/weeklyAttendeanceReport"><i
															class="fa fa-calendar fa-fw"></i> Weekly Report </a></li>


													<li><a
														href="${pageContext.request.contextPath}/timeLog/monthlyAttendeanceReport"><i
															class="fa fa-calendar-check-o fa-fw"></i> Monthly Report
													</a></li>

												</ul></li>

										</ul>
									</div>
								</div>
							</div>

						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when
									test="${sessionScope['scopedTarget.sessionObj'].getUser().getRole().getId()==5}">

									<!-- Sidebar -->
									<div class="navbar-default sidebar" role="navigation">
										<!--margin-top: 16px; background-color: #0d4272-->
										<div class="sidebar-nav navbar-collapse">
											<div class=""
												style="background-color: #094D8D; min-height: 100vh">
												<ul class="nav" id="side-menu">
													<li><a
														href="${pageContext.request.contextPath}/accounts_Dashboard"
														class="active"><i class="fa fa-dashboard fa-fw"></i>
															Dashboard</a></li>

													<li><a href="#"><i class="fa fa-user-md fa-fw"></i>
															Employee Management<span class="fa arrow"></span></a>
														<ul class="nav nav-second-level">
															<li><a
																href="${pageContext.request.contextPath}/emp/createEmp">
																	<i class="fa fa-user-plus fa-fw"></i> Create Employee
															</a></li>
															<li><a
																href="${pageContext.request.contextPath}/emp/allEmps"><i
																	class="fa fa-search fa-fw"></i> View And Edit Employees</a>
															</li>
														</ul></li>

													<li><a href="#"><i class="fa fa-table fa-fw"></i>
															Attendance Reports <span class="fa arrow"></span></a>
														<ul class="nav nav-second-level">
															<li><a
																href="${pageContext.request.contextPath}/timeLog/dailyAttendeanceReport"><i
																	class="fa fa-clock-o fa-fw"></i> Daily Report </a></li>

															<li><a
																href="${pageContext.request.contextPath}/timeLog/weeklyAttendeanceReport"><i
																	class="fa fa-calendar fa-fw"></i> Weekly Report </a></li>

															<li><a
																href="${pageContext.request.contextPath}/timeLog/monthlyAttendeanceReport"><i
																	class="fa fa-calendar-check-o fa-fw"></i> Monthly
																	Report </a></li>
														</ul></li>

												</ul>
											</div>
										</div>
									</div>

								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</nav>