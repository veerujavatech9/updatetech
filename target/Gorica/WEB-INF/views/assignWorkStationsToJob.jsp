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
				<h3 class="page-header">Assign Activities to Job No: <b>${job.jobNo }</b></h3>
				<div class="col-sm-12">
					<input type="hidden" value="${jobId }" name="jobId" id="jobId">
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
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Employee NameBrowser: activate to sort column descending"
									style="width: 171px;"><input type="checkbox"
									id="checkBoxAll" />Select All</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Name: activate to sort column descending"
									style="width: 151px;">Activity No</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 207px;">Description</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="activity" items="${activityList}"
								varStatus="counter">
								<tr class="gradeA odd " role="row">
									<td class="sorting_1">${counter.count}</td>
									<td><c:choose>
											<c:when test="${activity.status eq true}">
												<input type="checkbox" id="activityId" class="chkCheckBoxId"
													name="activityId" value="${activity.id}" checked>
											</c:when>
											<c:otherwise>
												<input type="checkbox" id="activityId" class="chkCheckBoxId"
													name="activityId" value="${activity.id}">
											</c:otherwise>
										</c:choose></td>
									<td>${activity.activityNo}</td>
									<td class="center">${activity.activityDescription}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
					<div class="col-sm-offset-4 col-sm-8" style="margin-top: 10px;">
						<input type="button" class="btn btn-primary"
							onclick="getSelectedChbox()" value="Assign / Remove">
					</div>
				</div>
			</div>
		</div>

		<!-- ... Your content goes here ... -->

	</div>
</div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>

<!-- toogle button -->
<script src="${pageContext.request.contextPath}/static/js/toogle-btn.js"></script>

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

<script
	src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.js"></script>
<!-- DataTables JavaScript -->
<script
	src="${pageContext.request.contextPath}/static/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->

<script>
var oTable;
$(document).ready(function() {
	 oTable = $('#dataTables-example').dataTable({
		responsive : true,
		"lengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
	});

	var allPages = oTable.fnGetNodes();

	$('body').on('click', '#checkBoxAll', function() {
		if ($(this).hasClass('allChecked')) {
			$('input[type="checkbox"]', allPages).prop('checked', false);
		} else {
			$('input[type="checkbox"]', allPages).prop('checked', true);
		}
		$(this).toggleClass('allChecked');
	})
});

	function getSelectedChbox() {
		var selected = new Array();
		var jobId=document.getElementById("jobId").value;
		$("input:checkbox[name=activityId]:checked",oTable.fnGetNodes()).each(function() {
			selected.push($(this).val());
		});

		$.ajax({
		    type : "POST",
		    url : "<%=request.getContextPath()%>/jobActivity/assignWorkStationsToJob",
		    data : {
		    	selected: selected,
		    	jobId: jobId 
		    },
		    /* beforeSend: function(){
		        // Show image container
		    	$(".loader").show();
		       }, */
		    success : function(response) {
			    window.location="<%=request.getContextPath()%>/jobActivity/workStationsForJob/"
								+ jobId;

					},
					error : function(e) {
						alert('Please select at least one Work Station');
					},
				/* complete:function(data){
				    // Hide image container
					$(".loader").hide();
				   } */
				});

	}
</script>

</body>
</html>

<%-- <jsp:include page="footer.jsp" /> --%>