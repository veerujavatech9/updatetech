<jsp:include page="header.jsp" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
tfoot input {
	width: 100%;
	padding: 3px;
	box-sizing: border-box;
}
</style>

<!-- Page Content -->
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12"
				style="background-color: #ffffff; margin: 52px 14px 10px 7px;">
				<h3 class="page-header">Weekly Attendance Report</h3>
				<div class="col-sm-12">
					<div align="right">
						<a
							href="${pageContext.request.contextPath}/timeLog/exportWeeklyAttendanceReport"
							class="btn"
							style="color: #fff; background-color: #5bc0de; border-color: #46b8da;">Export
							Excel</a>
					</div>
					<br>
					<table width="100%"
						class="table table-striped table-bordered table-hover dataTable no-footer dtr-inline"
						id="example" role="grid"
						aria-describedby="dataTables-example_info"
						style="width: 100%; font-size: 13.5px; margin-top: 10px;">
						<thead>
							<tr role="row" class="bg-warning">
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Name: activate to sort column descending"
									style="width: 151px;">S.No</th>
								<th class="sorting_asc" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-sort="ascending"
									aria-label="Name: activate to sort column descending"
									style="width: 151px;">Job No</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Browser: activate to sort column ascending"
									style="width: 207px;">Activity No</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Employee No</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Posting Date</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Activity Hours</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Idle Hours</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">Idle Reason</th>
								<th class="sorting" tabindex="0"
									aria-controls="dataTables-example" rowspan="1" colspan="1"
									aria-label="Project Name: activate to sort column ascending"
									style="width: 148px;">User</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="attendance" items="${attendenaceList}"
								varStatus="counter">
								<tr class="gradeA odd " role="row">
									<td class="sorting_1">${counter.count}</td>
									<td>${attendance.job.jobNo}</td>
									<td>${attendance.activity.activityNo}</td>
									<td>${attendance.employee.empNo}</td>
									<td><fmt:formatDate value="${attendance.checkInTime}"
											pattern="dd MMM" /></td>
									<td>${attendance.activityHrs}</td>
									<td>${attendance.idealHrs}</td>
									<td>${attendance.idealReason.reason}</td>
									<td>${attendance.user.userName}</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th>S.No</th>
								<th>Job No</th>
								<th>Activity No</th>
								<th>Employee No</th>
								<th>Posting Date</th>
								<th>Activity Hours</th>
								<th>Idle Hours</th>
								<th>Idle Reason</th>
								<th>User</th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>

		<!-- ... Your content goes here ... -->

	</div>
</div>
</div>

<jsp:include page="footer.jsp" />

<script>
$(document).ready(function() {
    // Setup - add a text input to each footer cell
    $('#example tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
    } );
 
    // DataTable
    var table = $('#example').DataTable({
    	"lengthMenu": [[50, 100, 150, -1], [50, 100, 150, "All"]],
        initComplete: function () {
            // Apply the search
            this.api().columns().every( function () {
                var that = this;
 
                $( 'input', this.footer() ).on( 'keyup change clear', function () {
                    if ( that.search() !== this.value ) {
                        that
                            .search( this.value )
                            .draw();
                    }
                } );
            } );
        }
    });
 
} );
</script>

</body>
</html>