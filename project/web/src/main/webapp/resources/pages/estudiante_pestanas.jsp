<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/stiles.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/jquery-ui.css"/>">

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" />
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/ui-lightness/jquery-ui.css" />

    <script>
        $(function () {
            $("#tab").tabs();
			
		$('#nuevo_tab_asee').click(function(){
			    $('#cambio_2').html('');
				$('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
				
				$('#tablasa').append("'ID estudiante' <input type ='text' id ='id'></input> 'Nombre' <input type='text' id ='cursopre'></input>");
				
				$('#tablasa').append(" <input type='button' id='agregar_tab_asee' value='nuevo' class='tabs_estudiante'/>  <input type='button' id='cancelar_tab_asee' value='borrar' class='tabs_estudiante'/>);
				
		});
		
		$('#borrar_tab_asee').click(function(){
				var idestudiante_pre = tab_estudiante.cell('.selected',0).data();
		$('#tablasa').append(" <input type='button' id='agregar_tab_asee' value='nuevo' class='tabs_estudiante'/>  <input type='button' id='cancelar_tab_asee' value='borrar' class='tabs_estudiante'/>);
				
		});		
		
		$('#nuevo_tab_cure').click(function(){
				});
		
		$('#borrar_tab_cure').click(function(){
				var IDestudiante = $('#estudiante_id')
				var IDcurso = tab_curso.cell('.selected',0).data();
				var IDsection = tab_curso.cell('.selected',1).data();
				var semester = tab_curso.cell('.selected',2).data();
				var year = tab_curso.cell('.selected',7).data();
		
				$('#tablasa').append(" <input type='button' id='agregar_tab_cure' value='nuevo' class='tabs_cursos'/>  <input type='button' id='cancelar_tab_det' value='borrar' class='tabs_cursos'/>);
				});
			$('#editar_tab_cure').click(function(){

			});			
		$('#agregar_tab_cure').click(function(){		
			
			});

		$('#cancelar_tab_asee').click(function(){
			pestanasC();
		});
		
		$('#nuevo_tab_asee').click(function(){
		$('#cambio_2').html('');
	$('#cambio_2').load('resources/pages/estudiante_pestanas.jsp', function (responseTxt, statusTxt, xhr) {
tab_ase_est=$('#est_tab1').DataTable({
			"bProcessing": true,
		    "bServerSide": true,
		    "bLenthChange" : false,
		    "iDisplayLength" : 10,
		    "sAjaxSource": "instructorTable.do",
		    'bJQueryUI': true,
		    "aoColumns":[
		                 {
		                	 "sTitle":"Id del Profesor",
		                	 "mData":"id"
		                 },
		                 {
		                	 "sTitle":"Nombre",
		                	 "mData":"name"
		                 },
		                 {
		                	 "sTitle":"Departamento",
		                	 "mData":"dptName"
		                 },
		                 {
		                	 "sTitle":"Salario",
		                	 "mData":"salary"
		                 },
		             ],
		    "fnServerData": function ( sSource, aoData, fnCallback ) {
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
		$('#body_ase_est').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_ase_est.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});
		
		});
		$('#agregar_tab_cure').click(function(){
			if(DMLActual == "insert"){			
			table.row('.selected').remove().draw( false );
			var json = {"id" : IDcurso, "section" : IDsection, "semester" : semester,"year" : year};
			console.log(json);
			DML(entityActual, DMLActual, json);		
		}
		if(DMLActual == "delete"){
			var idcurso = $("#curso_id").val()
			tab_curso.row('.selected').remove().draw( false );
			var json = {"courseId" : idcurso_pre,"prereqId" : idcurso };
			DML(entityActual, DMLActual, json);
			actualizarEntity(entityActual, "first");
			getCourse();
		}	
			});
		});
		$('#cancelar_tab_cure').click(function(){
			pestanasC();
		});
	
       });
    </script>
</head>
<body>
    <div id="tab">
        <ul class="tabs">
            <li><a href="#tabs-1">Asesor</a></li>
            <li><a href="#tabs-2">Curso</a></li>
        </ul>
        <div class="tab-content" id="tabs-1">
            <input type="button" id="nuevo_tab_asee" value="nuevo" />
            <input type="button" id="borrar_tab_asee" value="borrar" /></p>
            
            <table id="est_tab1" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>ID del Profesor</th>
               	<th>Nombre</th>
               	<th>Departamento</th>
               	<th>Salario</th>
            </tr>
        </thead>
		<tbody id="body_ase_est">
		</tbody>
        <tfoot>
            <tr>
                <th>ID del Profesor</th>
               	<th>Nombre</th>
               	<th>Departamento</th>
               	<th>Salario</th>
            </tr>
        </tfoot>
            </table>
        </div>
        <div class="tab-content" id="tabs-2">
            <input type="button" id="nuevo_tab_cure" value="nuevo" />
            <input type="button" id="borrar_tab_cure" value="borrar" />
			<input type="button" id="editar_tab_cure" value="editar" /></p>
            
            
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
               	<th>Nota</th>
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
               	<th>Nota</th>
            </tr>
        </tfoot>
                
            </table>
        </div>
</html>
