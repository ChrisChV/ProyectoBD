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

$('#buscarcu').click(function () {
    $('.edit').attr('readonly', true);
    $('#cambio_2').html('');
    $('#cambio_2').append("<div id ='buscadores'> </div> <div id='tablasa'> </div>");
    $('#cambio_2').show();
	$('.iteradores').hide();

    $('#tablasa').html('');
    $('#tablasa').load('resources/pages/cursos_buscar.jsp', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success"){
                       tab_curso = $('#cur_tab').DataTable({
                    		"bProcessing": true,
                            "bServerSide": true,
                            "bLenthChange" : false,
                            "iDisplayLength" : 10,
                            "sAjaxSource": "coursetable.do",
                            'bJQueryUI': true,
                            "aoColumns":[
                                         {
                                        	 "sTitle":"ID del Curso",
                                        	 "mData":"id"
                                         },
                                         {
                                        	 "sTitle":"Titulo",
                                        	 "mData":"dptName"
                                         },
                                         {
                                        	 "sTitle":"Departamento",
                                        	 "mData":"title"
                                         },
                                         {
                                        	 "sTitle":"Creditos",
                                        	 "mData":"credits"
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
							$('#body_cur').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_curso.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});
						   }
                if (statusTxt == "error")
                    alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
    }); 
$('#borrarcu').click(function () {
	DMLActual = "delete";
    $('#cambio_2').hide();
    $('#departamento0').hide();
    $('#departamento1').show();
    $('.edit').attr('readonly', true);
    $('#botones').show();
	$('.iteradores').hide();
	$('.botonescu').hide();
});

$('#nuevocu').click(function () {
	DMLActual = "insert";
	$('#cambio_2').hide();
	$('#departamento0').hide();
	$('#departamento1').show();
	$('.edit').attr('readonly',true);
	$('#botones').show();
	$('.iteradores').hide();
});

$('#editarcu').click(function () {
	DMLActual = "update";
	$('#departamento1').hide();
	$('#departamento0').show();
	$('.edit').attr('readonly',false);
	$('#botones').show();
	$('#botonescu').show();
	$('.iteradores').hide();
	pestanasC();
	$('.tab_cursos').show();

});

$('#fcu').click(function () {
    actualizarEntity('course', 'first');
    pestanasC();
})

$('#lcu').click(function () {
    actualizarEntity('course', 'last');
    pestanasC();
})
$('#ncu').click(function () {
    actualizarEntity('course', 'next');
    pestanasC();
})
$('#pcu').click(function () {
    actualizarEntity('course', 'prev');
    pestanasC();
})

function pestanasC(){

			$('.tab_cursos').hide();
        	$('#cambio_2').html('');
        	$('#cambio_2').load('resources/pages/cursos_pestanas.jsp', function (responseTxt, statusTxt, xhr) {
        		tab_det_cur = $('#cur_tab2').DataTable({
            		"bProcessing": true,
        		    "bServerSide": true,
        		    "bLenthChange" : false,
        		    "iDisplayLength" : 10,
        		    "sAjaxSource": "section.do",
        		    'bJQueryUI': true,
        		    "aoColumns":[
        		                 {
        		                	 "sTitle":"Id del Curso",
        		                	 "mData":"courseId"
        		                 },
        		                 {
        		                	 "sTitle":"Id del Section",
        		                	 "mData":"secId"
        		                 },
        		                 {
        		                	 "sTitle":"Semestre",
        		                	 "mData":"semester"
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
        		                	 "sTitle":"Id Time",
        		                	 "mData":"timeSlotId"
        		                 },
        		                 {
        		                	 "sTitle":"Year",
        		                	 "mData":"year"
        		                 },
        		             ],
        		    "fnServerData": function ( sSource, aoData, fnCallback ) {
        		    	var j = $('#curso_id').val();
        		    	var s = {"name" : "courseId", "value" : j};
        		    	aoData = aoData.concat(s);
        		    	console.log(aoData);
        		        $.ajax( {
        		        	"async":false,
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
				$('#body_det_cur').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_det_cur.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});
        		tab_pre_cur = $('#cur_tab1').DataTable({
        			"bProcessing": true,
        		    "bServerSide": true,
        		    "bLenthChange" : false,
        		    "iDisplayLength" : 10,
        		    "sAjaxSource": "prereq.do",
        		    'bJQueryUI': true,
        		    "aoColumns":[
        		                 {
        		                	 "sTitle":"Id",
        		                	 "mData":"id"
        		                 },
        		                 {
        		                	 "sTitle":"Nombre",
        		                	 "mData":"title"
        		                 },
        		             ],
        		    "fnServerData": function ( sSource, aoData, fnCallback ) {
        		    	var j = $('#curso_id').val();
        		    	console.log("log ->" + j);
        		    	var s = {"name" : "courseId", "value" : j};
        		    	aoData = aoData.concat(s);
        		    	console.log(aoData);
        		        $.ajax( {
        		        	"async":false,
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
				$('#body_pre_cur').on('click', 'tr', function () {
							if ($(this).hasClass('selected')) {
							$(this).removeClass('selected');
							}
							else {
							tab_pre_cur.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
							}
							});
        	});
        };
 

</script>

<div id="curso">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo" id="nuevocu" class="botonescu"/>
                <input type="button" value="editar" name="editar" id="editarcu" class="botonescu"/>
                <input type="button" value="buscar" name="buscar" id="buscarcu" class="botonescu"/>
                <input type="button" value="borrar" name="borrar" id="borrarcu" class="botonescu"/>
            </td>

        </tr>
    </table>
    <table>
        <tr>
            <td>Curso ID</td>
            <td>
                <input type="text" name="curso_id" id="curso_id" />
            </td>

        </tr>
        <tr>
            <td>Titulo</td>
            <td>
                <input type="text" name="titulo" id="titulo" class="edit" />
            </td>

        </tr>
        <tr>
            <td>Departamento</td>
            <td>
                <select name="departamento" id="departamento0"></select>
                <input type="text" name="departamento1" id="departamento1" class="edit"/>
            </td>

        </tr>
        <tr>
            <td>Creditos</td>
            <td>
                <input type="text" name="creditos" id="creditos" />
            </td>

        </tr>
        <tr>
            <td>
                <input type="button" name="first" value="<|" id="fcu"class="iteradores"/>
                <input type="button" name="previous" value="<<"id="pcu"class="iteradores"/>
                <input type="button" name="next" value=">>" id="ncu"class="iteradores"/>
                <input type="button" name="last" value="|>" id="lcu"class="iteradores"/>
            </td>
            <td>
                <div id="botones">
                    <input type="submit" name="guardar" value="guardar" id="guardarcu"/>
                    <input type="submit" name="cancelar" value="cancelar"id="cancelarcu" />
                </div>
            </td>
        </tr>
    </table>
</div>