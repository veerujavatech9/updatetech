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

<script src="${pageContext.request.contextPath}/static/js/bootstrap-datepicker.js"></script>
<!-- DataTables JavaScript -->
<script
	src="${pageContext.request.contextPath}/static/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
	$(document).ready(function() {
		$('#dataTables-example').DataTable({
			responsive : true,
			"lengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]]
		});
	});
</script>