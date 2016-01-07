<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/stiles.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/jquery-ui.css"/>">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/ui-lightness/jquery-ui.css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" />
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
    

<head>
    <script>
        $(function () {
            $("#tab").tabs();
        });

		$('#nuevo_tab_pre').click(function(){
			    $('#cambio_2').html('');
				$('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
				
				$('#tablasa').append("'ID curso' <select id ='idpre'></select> 'Curso' <select id ='cursopre'></select>");
				
				$('#tablasa').append(" <input type='button' id='agregar_tab_pre' value='nuevo' class='tabs_cursos'/>  <input type='button' id='cancelar_tab_pre' value='borrar' class='tabs_cursos'/>);
				
		});
		
		$('#borrar_tab_pre').click(function(){
				var idcurso_pre = tab_curso.cell('.selected',0).data();
				});
		$('#tablasa').append(" <input type='button' id='agregar_tab_pre' value='nuevo' class='tabs_cursos'/>  <input type='button' id='cancelar_tab_pre' value='borrar' class='tabs_cursos'/>);
				
		});		
		
		$('#nuevo_tab_det').click(function(){
			    $('#cambio_2').html('');
				$('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
				$('#tablasa').append("<select id ='idcurso'></select> <select id ='idsection'></select> <select id ='semestre'> <select id ='edificio'> <select id ='room_no'><select id ='idtime'><select id ='year'>");
				
	$('#tablasa').append(" <input type='button' id='agregar_tab_det' value='nuevo' class='tabs_cursos'/>  <input type='button' id='cancelar_tab_det' value='borrar' class='tabs_cursos'/>);
					});
		
		$('#borrar_tab_det').click(function(){
				var IDcurso = tab_curso.cell('.selected',0).data();
				var IDsection = tab_curso.cell('.selected',1).data();
				var semester = tab_curso.cell('.selected',2).data();
				var year = tab_curso.cell('.selected',7).data();

		$('#tablasa').append(" <input type='button' id='agregar_tab_det' value='nuevo' class='tabs_cursos'/>  <input type='button' id='cancelar_tab_det' value='borrar' class='tabs_cursos'/>);
				});		
		if(DMLActual == "insert")	
			var idcurso = tab_curso.cell('.selected',0).data();
			var curso = tab_curso.cell('.selected',1).data();
			$('#idcurso').val(idcurso);
			$('#curso').val(curso);
			var json = {"id" : idcurso, "day" : curso};
			console.log(json);
			DML(entityActual, DMLActual, json);		
		}
		if(DMLActual == "delete"){
			var idcurso = $("#curso_id").val()
			var json = {"courseId" : idcurso_pre,"prereqId" : idcurso };
			DML(entityActual, DMLActual, json);
			actualizarEntity(entityActual, "first");
			getCourse();
		}	
			});

		$('#cancelar_tab_pre').click(function(){
			pestanasC();
		});
		
		$('#agregar_tab_det').click(function(){
		
		});
		$('#cancelar_tab_det').click(function(){
		
		});
    </script>
</head>
<body>
    <div id="tab">
        <ul class="tabs">
            <li><a href="#tabs-1">Prerequisito</a></li>
            <li><a href="#tabs-2">Detalles</a></li>
        </ul>
        <div class="tab-content" id="tabs-1">
            <input type="button" id="guardarpc" value="guardar" class="tabs_cursos"/>
            <input type="button" id="cancelarpc" value="cancelar" class="tabs_cursos"/></p>
            <table id="cur_tab1" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>ID del Curso</th>
               	<th>Nombre</th>
            </tr>
        </thead>
		<tbody id="body_pre_cur">
		</tbody>
        <tfoot>
            <tr>
                <th>ID del Curso</th>
               	<th>Nombre</th>
            </tr>
        </tfoot>
            
            </table>
            
            
        </div>
        <div class="tab-content" id="tabs-2">
            <input type="button" id="nuevo_tab_det" value="nuevo" />
            <input type="button" id="borrar_tab_det" value="borrar" /></p>

            <table id="cur_tab2" class="display" cellspacing="0" width="100%">
                <thead>
            <tr>
                <th>Id del Curso</th>
                <th>Id del Section</th>
               	<th>Semestre</th>
                <th>Edificio</th>
                <th>Room Number</th>
                <th>Id Time</th>
                <th>Year</th>
            </tr>
        </thead>
		<tbody id="body_det_cur">
		</tbody>
        <tfoot>
            <tr>
                <th>Id del Curso</th>
                <th>Id del Section</th>
               	<th>Semestre</th>
                <th>Edificio</th>
                <th>Room Number</th>
                <th>Id Time</th>
                <th>Year</th>
            </tr>
        </tfoot>
            </table>
        </div>
</html>