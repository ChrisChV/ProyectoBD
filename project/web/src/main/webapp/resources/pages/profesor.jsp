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

function pestanasP(){
	$('#cambio_2').html('');
	$('#cambio_2').load('resources/pages/profesor_pestanas.jsp', function (responseTxt, statusTxt, xhr) {
    	tab_est_pro=$('#curs_tab1').DataTable({
			"bProcessing": true,
		    "bServerSide": true,
		    "bLenthChange" : false,
		    "iDisplayLength" : 10,
		    "sAjaxSource": "advisorbyInstructor.do",
		    'bJQueryUI': true,
		    "aoColumns":[
		                 {
		                	 "sTitle":"Id del estudiante",
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
		                	 "sTitle":"Credits",
		                	 "mData":"totCred"
		                 },
		             ],
		    "fnServerData": function ( sSource, aoData, fnCallback ) {
		    	var j = $('#profesor_id').val();
		    	var s = {"name" : "instructorId", "value" : j};
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
				$('#body_est_pro').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_est_pro.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});

    	tab_cur_pro=$('#curs_tab2').DataTable({
			"bProcessing": true,
		    "bServerSide": true,
		    "bLenthChange" : false,
		    "iDisplayLength" : 10,
		    "sAjaxSource": "teachesbyInstructor.do",
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
		                	 "sTitle":"Titulo del Curso",
		                	 "mData":"courseTitle"
		                 },
		                 {
		                	 "sTitle":"Creditos del Curso",
		                	 "mData":"courseCredits"
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
		             ],
		    "fnServerData": function ( sSource, aoData, fnCallback ) {
		    	var j = $('#profesor_id').val();
		    	var s = {"name" : "instructorId", "value" : j};
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
				$('#body_cur_pro').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_cur_pro.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});

	});
}

$('#guardarp').click(function () {
	$('#cambio_2').html('');
    $('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
    $('.botonesp').prop('disabled',false);
    $('#botones').hide();
    $('.iteradores').show();
    $('.edit').attr('readonly', true);
    $('#profesor_id').attr('readonly',true);
    $('#cambio_2').show();
    $('#departamentop').hide();
    $('#departamentop1').show(); 		
		if(DMLActual == "search"){
		var profesorid = tab_profesor.cell('.selected',0).data();
		var name = tab_profesor.cell('.selected',1).data();
		var departamento = tab_profesor.cell('.selected',2).data();
		var salary = tab_profesor.cell('.selected',3).data();
		$('#profesor_id').val(profesorid);
		$('#nombre_p').val(name);
		$('#departamentop1').val(departamento);
		$('#salario').val(salary);
	}
		if(DMLActual == "insert" || DMLActual == "update"){	
			var profesorid = $('#profesor_id').val();
			var name = $('#nombre_p').val();
			var departamento = $('#departamentop').val();
			var salary = $('#salario').val();
			var json = {"id" : profesorid, "name" : name, "depId" :  departamento, "salary" : salary};
			console.log(json);
			DML(entityActual, DMLActual, json);
			$('#departamentop1').val($('#departamentop').val());
		}
		if(DMLActual == "delete"){
			var profesorId = $("#profesor_id").val();
			var json = {"id" : profesorId};
			DML(entityActual, DMLActual, json);
			actualizarEntity(entityActual, "first");
			getInstructor();
		}	
	pestanasP();
});

$('#cancelarp').click(function () {
	$('#cambio_2').html('');
    $('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
    $('.botonesp').prop('disabled',false);
    $('#botones').hide();
    $('.iteradores').show();
    $('.edit').attr('readonly', true);
    $('#profesor_id').attr('readonly',true);
    $('#cambio_2').show();
    $('#departamentop').hide();
    $('#departamentop1').show();
    pestanasP();
});
	
$('#borrarp').click(function () {
	DMLActual = "delete";
    $('#cambio_2').hide();
    $('#departamentop').hide();
    $('.botonesp').prop('disabled',false);
    $('#departamentop1').show();
    $('.iteradores').hide();
    $('.edit').attr('readonly', true);
    $('#botones').show();
});

$('#nuevop').click(function () {
	DMLActual = "insert";
	$('.edit').attr('readonly', false);
	$('.edit').val('');
	$('#profesor_id').attr('readonly',false);
	$('#profesor_id').val('');
	$('#cambio_2').hide();
	$('.iteradores').hide();
	$('#departamentop').show();
    $('#departamentop1').hide();
	$('.botonesp').prop('disabled',true);
	$('#botones').show();
	$.ajax({
		async:false,
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url:'/web/department/all',
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	        	console.log(data);
	        	$('#departamentop').html('');
	        	$.each(data, function(index, value) {
	        		$('#departamentop').append("<option value = '" + value.dptName + "'>" + value.dptName + "</option>");	
	        	});
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
});

$('#buscarp').click(function () {
	DMLActual = "search";
	$('.iteradores').hide();
	$('.botonesp').prop('disabled',true);
	$('#cambio_2').html('');
    $('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#tablasa').load('resources/pages/profesor_buscar.jsp', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success"){
            $('#botones').show();
            tab_profesor = $('#pro_tab').DataTable({
        		"bProcessing": true,
                "bServerSide": true,
                "bLenthChange" : false,
                "iDisplayLength" : 10,
                "sAjaxSource": "instructortable.do",
                'bJQueryUI': true,
                "aoColumns":[
							{
	 							"sTitle":"Id del profesor",
	 							"mData":"id"
							},
                             {
                            	 "sTitle":"Nombre",
                            	 "mData":"name"
                             },
                             {
                            	 "sTitle":"Departemento",
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
            $('#body_prof').on('click', 'tr', function () {
				if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
				}
				else {
				tab_profesor.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				}
				});
        }
        if (statusTxt == "error")
            alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
});

$('#editarp').click(function () {
	DMLActual = "update";
	$('.edit').attr('readonly', false);
	$('#cambio_2').hide();
	$('.iteradores').hide();
	$('#departamentop').show();
    $('#departamentop1').hide();
	$('.botonesp').prop('disabled',true);
	$('#botones').show();
	$.ajax({
		async:false,
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url:'/web/department/all',
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	        	console.log(data);
	        	$('#departamentop').html('');
	        	$.each(data, function(index, value) {
	        		$('#departamentop').append("<option value = '" + value.dptName + "'>" + value.dptName + "</option>");	
	        	});
	        	$('#departamentop').val($('#departamentop1').val());
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
});

$('#fp').click(function () {
    actualizarEntity('instructor', 'first');
    pestanasP();
})

$('#lp').click(function () {
    actualizarEntity('instructor', 'last');
    pestanasP();
})
$('#np').click(function () {
    actualizarEntity('instructor', 'next');
    pestanasP();
})
$('#pp').click(function () {
    actualizarEntity('instructor', 'prev');
    pestanasP();
})



</script>

<div id="profesor">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo"  name="nuevo"  id="nuevop" class= "botonesp" />
                <input type="button" value="editar" name="editar" id="editarp" class= "botonesp" />
                <input type="button" value="buscar" name="buscar" id="buscarp" class= "botonesp"/>
                <input type="button" value="borrar" name="borrar" id="borrarp" class= "botonesp"/>
            </td>

        </tr>
    </table>
    <table>
        <tr>
            <td>Profesor ID</td>
            <td>
                <input type="text" name="profesor_id" id="profesor_id" />
            </td>

        </tr>
        <tr>
            <td>Nombre</td>
            <td>
                <input type="text" name="nombre_p" id="nombre_p" class = "edit"/>
            </td>

        </tr>
        <tr>
            <td>Departamento</td>
            <td>
                <select name="departamentop" id="departamentop" ></select>
                <input type="text" name="departamentop1" id="departamentop1" class = "edit"/>
            </td>

        </tr>
        <tr>
            <td>Salario</td>
            <td>
                <input type="text" name="salario" id="salario" class = "edit"/>
            </td>

        </tr>
        <tr>
            <td>
                <input type="button" name="first" value="<|" id="fp" class = "iteradores"/>
                <input type="button" name="previous" value="<<"id="pp" class = "iteradores"/>
                <input type="button" name="next" value=">>" id="np" class = "iteradores"/>
                <input type="button" name="last" value="|>" id="lp" class = "iteradores"/>
            </td>
            <td>
                <div id="botones">
                    <input type="submit" name="guardar" value="guardar"  id = "guardarp"/>
                    <input type="submit" name="cancelar" value="cancelar" id = "cancelarp"/>
                </div>
            </td>
        </tr>
    </table>


</div>