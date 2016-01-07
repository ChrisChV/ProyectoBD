<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/stiles.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/jquery-ui.css"/>">

<script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.10/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script>
 tab_cur_est=$('#est_tab2').DataTable({
			"bProcessing": true,
		    "bServerSide": true,
		    "bLenthChange" : false,
		    "iDisplayLength" : 10,
		    "sAjaxSource": "takesbyStudent.do",
		    'bJQueryUI': true,
		    "aoColumns":[
		                 {
		                	 "sTitle":"Id del Curso",
		                	 "mData":"courseId"
		                 },
		                 {
		                	 "sTitle":"Departamento",
		                	 "mData":"dptName"
		                 },
		                 {
		                	 "sTitle":"Título del Curso",
		                	 "mData":"courseTitle"
		                 },
		                 {
		                	 "sTitle":"Id del section",
		                	 "mData":"secId"
		                 },
		                 {
		                	 "sTitle":"Semestre",
		                	 "mData":"semester"
		                 },
		                 {
		                	 "sTitle":"Year",
		                	 "mData":"year"
		                 },
		                 {
		                	 "sTitle":"Edificio",
		                	 "mData":"building"
		                 },
		                 {
		                	 "sTitle":"Room Number",
		                	 "mData":"roomNumber"
		                 },
		                 {
		                	 "sTitle":"Time Slot Id",
		                	 "mData":"timeSlotId"
		                 },
		                 {
		                	 "sTitle":"Nota",
		                	 "mData":"takeGrade"
		                 },
		             ],
		    "fnServerData": function ( sSource, aoData, fnCallback ) {
		    	var j = $('#estudiante_id').val();
		    	var s = {"name" : "studentId", "value" : j};
		    	aoData = aoData.concat(s);
		    	console.log(aoData);
		        $.ajax( {
		            "dataType": "json",
		            "type": "GET",
		            "url": sSource,
		            "data": aoData,
		            "success": function(data, textStatus, jqXHR){
		            	if(data){
		            		console.log(data);
		            		fnCallback(data);
		            	}
		            }
		        } );
		    },
		    "sPaginationType" : "full_numbers"
		});
		$('#body_cur_est').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_cur_est.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});
</script>

<table id="est_tab2" class="display" cellspacing="0" width="100%">
                <thead>
            <tr>
                <th>Id del curso</th>
                <th>Departamento</th>
                <th>Título del Curso</th>
               	<th>Id del section</th>
               	<th>Semestre</th>
               	<th>Year</th>
               	<th>Edificio</th>
               	<th>Room Number</th>
               	<th>Time Slot Id</th>
            </tr>
        </thead>
		<tbody id="body_cur_est">
		</tbody>
        <tfoot>
            <tr>
                <th>Id del curso</th>
                <th>Departamento</th>
                <th>Título del Curso</th>
               	<th>Id del section</th>
               	<th>Semestre</th>
               	<th>Year</th>
               	<th>Edificio</th>
               	<th>Room Number</th>
               	<th>Time Slot Id</th>
            </tr>
        </tfoot>
                
            </table>