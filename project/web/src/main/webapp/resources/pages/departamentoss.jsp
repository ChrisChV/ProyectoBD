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
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>

<!--  <script src="<c:url value="/resources/js/eventos.js"/>" type="text/javascript"></script>-->

<script>
			
$('#cancelard').click(function(){
	$('#departamentos').attr('readonly',true);
	$('.edit').attr('readonly',true);
	$('#botones').hide();
	$('#edificio').hide();
	$('#edificio1').show();
	$('#cambio_2').hide();
	$('.iteradores').show();
	$('.botonesd').prop('disabled',false);
	getDpt();
});

$('#guardard').click(function(){
	$('#departamentos').attr('readonly',true);
	$('.edit').attr('readonly',true);
	$('#botones').hide();
	$('#edificio').hide();
	$('#edificio1').show();
	$('#cambio_2').hide();
	$('.iteradores').show();
	$('.botonesd').prop('disabled',false);
	if(DMLActual == "search")
	{
		var data = tab_dep.row('.selected').data();
		$('#departamentos').val(data.Nombre);
		$('#edificio1').val(data.Edificio);
		$('#presupuesto').val(data.Presupuesto);
	}
	else {
		var nombre = $('#departamentos').val();
		var edificio = $('#edificio1').val();
		var presupuesto = $('#presupuesto').val();
		var json = {"name" : nombre, "building" : edificio, "budget" : presupuesto};
		DML(entityActual, DMLActual, json);
	}
	if(DMLActual == "delete"){
		actualizarEntity(entityActual, "first");
	}

});

$('#nuevod').click(function () {
	DMLActual = "insert";
    $('#cambio_2').hide();
    $('#edificio1').show();
    $('#edificio').hide();
    $('.edit').attr('readonly', false);
    $('#departamentos').attr('readonly',false);
    $('#botones').show();
	$('.iteradores').hide();
	$('#departamentos').val('');
	$('.edit').val('');
	$('.botonesd').prop('disabled',true);
});
$('#borrard').click(function () {
	DMLActual = "delete";
    $('#cambio_2').hide();
    $('#edificio1').show();
    $('#edificio').hide();
    $('.edit').attr('readonly', true);
    $('#botones').show();
    $('.iteradores').hide();
    $('.botonesd').prop('disabled',true);
});

$('#editard').click(function () {
	DMLActual = "update";
    $('#cambio_2').hide();
    $('#edificio1').show();
    $('#edificio').hide();
    $('.edit').attr('readonly', false);
    $('#botones').show();
    $('.iteradores').hide();
    $('.botonesd').prop('disabled',true);
});

$('#outsided').click(function () {
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#botones').show();
    $('.iteradores').hide();
    $('.botonesd').prop('disabled',true);
});

$('#fd').click(function () {
    actualizarEntity('department', 'first');
})

$('#ld').click(function () {
    actualizarEntity('department', 'last');
})
$('#nd').click(function () {
    actualizarEntity('department', 'next');
})
$('#pd').click(function () {
    actualizarEntity('department', 'prev');
})
</script>

<div id="departamento">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo"   id="nuevod" class="botonesd"/>
                <input type="button" value="editar" name="editar" id="editard"class="botonesd"/>
                <input type="button" value="buscar" name="buscar" id="outsided"class="botonesd"/>
                <input type="button" value="borrar" name="borrar" id="borrard"class="botonesd"/>
            </td>

        </tr>
    </table>
    
    <table>
        <tr>
            <td> Nombre de Departamento</td>
            <td> <input type="text" name="departamento" id="departamentos" minlenght="1" maxlength="20"/> </td>
        </tr>
        <tr>
            <td>Edificio</td>
            <td> <select name="edificio" id="edificio" ></select>
            <input type="text" name="edificio1" id="edificio1" class="edit" minlenght="1" maxlength="15" />
            </td>

        </tr>
        <tr>
            <td>Presupuesto</td>
            <td> <input type="text" name="presupuesto" id="presupuesto" class="edit" minlenght="1" maxlength="15"/> </td>

        </tr>
        <tr>
            <td >
                <input type="button" name="first" value="<|" id="fd" class="iteradores"/>
                <input type="button" name="previous" value="<<"id="pd"class="iteradores"/>
                <input type="button" name="next" value=">>" id="nd"class="iteradores"/>
                <input type="button" name="last" value="|>" id="ld"class="iteradores"/>
            </td>
            <td>
                <div id="botones" >
                <input type="submit" name="guardar" value="guardar"  id="guardard"/>
                <input type="submit" name="cancelar" value="cancelar" id="cancelard"/>
                </div>
            </td>

        </tr>
    </table>


</div>