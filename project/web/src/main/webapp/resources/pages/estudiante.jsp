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

function pestanasS(){
	$('#cambio_2').html('');
	$('#cambio_2').load('resources/pages/estudiante_pestanas.jsp', function (responseTxt, statusTxt, xhr) {
tab_ase_est=$('#est_tab1').DataTable({
			"bProcessing": true,
		    "bServerSide": true,
		    "bLenthChange" : false,
		    "iDisplayLength" : 10,
		    "sAjaxSource": "advisorbyStudent.do",
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
		$('#body_ase_est').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_ase_est.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});
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
	});
}

$('#borrare').click(function () {
	DMLActual = "delete";
    $('#cambio_2').hide();
    $('.botoneses').prop('disabled',true);
    $('#departamentoe').hide();
    $('#departamentoe1').show();
    $('.edit').attr('readonly', true);
    $('#botones').show();
    $('.iteradores').hide();
});

$('#guardares').click(function(){
	$('#cambio_2').html('');
    $('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
    $('.botoneses').prop('disabled',false);
    $('#botones').hide();
    $('.iteradores').show();
    $('.edit').attr('readonly', true);
    $('#estudiante_id').attr('readonly',true);
    $('#cambio_2').show();
    $('#departamentoe').hide();
    $('#departamentoe1').show();
		if(DMLActual == "search"){
		var estudianteid = tab_alumno.cell('.selected',0).data();
		var nombre = tab_alumno.cell('.selected',1).data();
		var departamentoe = tab_alumno.cell('.selected',2).data();
		var creditos_t = tab_alumno.cell('.selected',3).data();
		$('#estudiante_id').val(estudianteid);
		$('#nombre').val(nombre);
		$('#departamentoe1').val(departamentoe);
		$('#creditos_t').val(creditos_t);
		}
		if(DMLActual == "insert" || DMLActual == "update"){	
			var idestudiante = $('#estudiante_id').val();
			var nombre = $('#nombre').val();
			var departamento = $('#departamentoe').val();
			var creditos = $('#creditos_t').val();
			var json = {"id" : idestudiante, "name" : nombre, "depId" :  departamento, "totCred" : creditos};
			console.log(json);
			DML(entityActual, DMLActual, json);
			$('#departamentoe1').val($('#departamentoe').val());
		}
		if(DMLActual == "delete"){
			var estudianteid = $("#estudiante_id").val()
			var json = {"id" : estudianteid};
			DML(entityActual, DMLActual, json);
			actualizarEntity(entityActual, "first");
			getStudent();
		}	
	pestanasS();
});

$('#cancelares').click(function(){
	$('#cambio_2').html('');
    $('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
    $('#cambio_2').show();
    $('#departamentoe').hide();
    $('.botoneses').prop('disabled',false);
    $('.edit').attr('readonly', true);
    $('#estudiante_id').attr('readonly',true);
    $('#departamentoe1').show();
    $('.iteradores').show();
    $('#botones').hide();
	pestanasS();
});

$('#nuevoe').click(function () {
	DMLActual = "insert";
	$('.edit').attr('readonly', false);
	$('#cambio_2').hide();
	$('.botoneses').prop('disabled',true);
	$('#estudiante_id').attr('readonly', false);
	$('.edit').val('');
	$('#estudiante_id').val('');
	$('#departamentoe1').hide();
	$('#departamentoe').show();
	$('#botones').show();
	$('.iteradores').hide();
	$.ajax({
		async:false,
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url:'/web/department/all',
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	        	console.log(data);
	        	$('#departamentoe').html('');
	        	$.each(data, function(index, value) {
	        		$('#departamentoe').append("<option value = '" + value.dptName + "'>" + value.dptName + "</option>");	
	        	});
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
});

$('#buscare').click(function () {
	DMLActual = "search";
    $('.edit').attr('readonly', true);
	$('#cambio_2').html('');
    $('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
    $('.botoneses').prop('disabled',true);
    $('#cambio_2').show();
    $('.iteradores').hide();
    $('#tablasa').load('resources/pages/estudiante_buscar.jsp', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success"){
        	$('#botones').show();
        	tab_alumno=$('#es_tab').DataTable({
        		"bProcessing": true,
                "bServerSide": true,
                "bLenthChange" : false,
                "iDisplayLength" : 10,
                "sAjaxSource": "studenttable.do",
                'bJQueryUI': true,
                "aoColumns":[
							{
	 							"sTitle":"Id del Estudiante",
	 							"mData":"id"
							},
                             {
                            	 "sTitle":"Nombre",
                            	 "mData":"name"
                             },
                             {
                            	 "sTitle":"Departamentp",
                            	 "mData":"dptName"
                             },
                             {
                            	 "sTitle":"Creditos",
                            	 "mData":"totCred"
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
			
							$('#body_est').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_alumno.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});
        }
        if (statusTxt == "error")
            alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
});

$('#editare').click(function () {
	DMLActual = "update";
	$('.botoneses').prop('disabled',true);
	$('.edit').attr('readonly', false);
	$('#cambio_2').hide();
	$('.edit').attr('readonly', false);
	$('#departamentoe1').hide();
	$('#departamentoe').show();
	$('#botones').show();
	$('.iteradores').hide();
	$.ajax({
		async:false,
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url:'/web/department/all',
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	        	console.log(data);
	        	$('#departamentoe').html('');
	        	$.each(data, function(index, value) {
	        		$('#departamentoe').append("<option value = '" + value.dptName + "'>" + value.dptName + "</option>");	
	        	});
	        	$('#departamentoe').val($('#departamentoe1').val());
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
});

$('#fe').click(function () {
    actualizarEntity('student', 'first');
    pestanasS();
})

$('#le').click(function () {
    actualizarEntity('student', 'last');
    pestanasS();
})
$('#ne').click(function () {
    actualizarEntity('student', 'next');
    pestanasS();
})
$('#pe').click(function () {
    actualizarEntity('student', 'prev');
    pestanasS();
})
</script>

<script src="<c:url value="/resources/js/eventos.js"/>" type="text/javascript"></script>
<div id="estudiante">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo" id="nuevoe" class="botoneses"/>
                <input type="button" value="editar" name="editar" id="editare" class="botoneses"/>
                <input type="button" value="buscar" name="buscar" id="buscare" class="botoneses"/>
                <input type="button" value="borrar" name="borrar" id="borrare" class="botoneses"/>
            </td>

        </tr>
    </table>
    <table>
        <tr>
            <td>Estudiante ID</td>
            <td>
                <input type="text" name="estudiante_id" id="estudiante_id" />
            </td>

        </tr>
        <tr>
            <td>Nombre</td>
            <td>
                <input type="text" name="nombre" id="nombre" class="edit"/>
            </td>

        </tr>
        <tr>
            <td>Departamento</td>
            <td>
                <select name="departamentoe" id="departamentoe"></select>
                <input type="text" name="departamentoe1" id="departamentoe1" class="edit"/>
            </td>

        </tr>
        <tr>
            <td>Creditos Totales</td>
            <td>
                <input type="text" name="creditos_t" id="creditos_t" class="edit" />
            </td>

        </tr>
        <tr>
            <td>
                <input type="button" name="first" value="<|" id="fe"class="iteradores"/>
                <input type="button" name="previous" value="<<"id="pe"class="iteradores"/>
                <input type="button" name="next" value=">>" id="ne"class="iteradores"/>
                <input type="button" name="last" value="|>" id="le"class="iteradores"/>
            </td>
            <td>
                <div id="botones">
                    <input type="submit" name="guardar" value="guardar" id = "guardares" />
                    <input type="submit" name="cancelar" value="cancelar" id = "cancelares" />
                </div>
            </td>
        </tr>
    </table>


</div>