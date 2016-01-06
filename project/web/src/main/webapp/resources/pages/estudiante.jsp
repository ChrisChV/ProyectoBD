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
$('#borrare').click(function () {
    $('#cambio_2').hide();
    $('#departamentoe').hide();
    $('#departamentoe1').show();
    $('.edit').attr('readonly', true);
    $('#botones').show();
});

$('#nuevoe').click(function () {

});

$('#buscare').click(function () {
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#tablasa').load('resources/pages/estudiante_buscar.jsp', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success"){
        	$('#botones').show();
        	$('#es_tab').DataTable({
        		"bProcessing": true,
                "bServerSide": true,
                "bLenthChange" : false,
                "iDisplayLength" : 10,
                "sAjaxSource": "studenttable.do",
                'bJQueryUI': true,
                "aoColumns":[
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
        }
        if (statusTxt == "error")
            alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
});

$('#editare').click(function () {

});

$('#fe').click(function () {
    actualizarEntity('student', 'first');
})

$('#le').click(function () {
    actualizarEntity('student', 'last');
})
$('#ne').click(function () {
    actualizarEntity('student', 'next');
})
$('#pe').click(function () {
    actualizarEntity('student', 'prev');
})
</script>

<script src="<c:url value="/resources/js/eventos.js"/>" type="text/javascript"></script>
<div id="estudiante">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo" id="nuevoe" />
                <input type="button" value="editar" name="editar" id="editare" />
                <input type="button" value="buscar" name="buscar" id="buscare" />
                <input type="button" value="borrar" name="borrar" id="borrare" />
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
                <input type="text" name="nombre" id="nombre" />
            </td>

        </tr>
        <tr>
            <td>Departamento</td>
            <td>
                <select name="departamentoe" id="departamentoe"></select>
                <input type="text" name="departamentoe1" id="departamentoe1" />
            </td>

        </tr>
        <tr>
            <td>Creditos Totales</td>
            <td>
                <input type="text" name="creditos_t" id="creditos_t" />
            </td>

        </tr>
        <tr>
            <td>
                <input type="button" name="first" value="<|" id="fe"/>
                <input type="button" name="previous" value="<<"id="pe"/>
                <input type="button" name="next" value=">>" id="ne"/>
                <input type="button" name="last" value="|>" id="le"/>
            </td>
            <td>
                <div id="botones">
                    <input type="submit" name="guardar" value="guardar" />
                    <input type="submit" name="cancelar" value="cancelar" />
                </div>
            </td>
        </tr>
    </table>


</div>