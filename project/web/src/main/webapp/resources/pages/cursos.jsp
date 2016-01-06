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
    $('#cambio_2').show();
    console.log("holaaaaaaaaaaaa");
    $('#tablasa').html('');
    
    $('#tablasa').load('resources/pages/cursos_buscar.jsp', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success"){
                        $('#cur_tab').DataTable({
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
                       }
                if (statusTxt == "error")
                    alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
    }); 
$('#borrarcu').click(function () {
    $('#cambio_2').hide();
    $('#departamento0').hide();
    $('#departamento1').show();
    $('.edit').attr('readonly', true);
    $('#botones').show();
});

$('#nuevocu').click(function () {

});

$('#editarcu').click(function () {
    
});

$('#fcu').click(function () {
    actualizarEntity('course', 'first');
})

$('#lcu').click(function () {
    actualizarEntity('course', 'last');
})
$('#ncu').click(function () {
    actualizarEntity('course', 'next');
})
$('#pcu').click(function () {
    actualizarEntity('course', 'prev');
})

</script>

<div id="curso">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo" id="nuevocu" />
                <input type="button" value="editar" name="editar" id="editarcu" />
                <input type="button" value="buscar" name="buscar" id="buscarcu" />
                <input type="button" value="borrar" name="borrar" id="borrarcu" />
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
                <input type="text" name="titulo" id="titulo" />
            </td>

        </tr>
        <tr>
            <td>Departamento</td>
            <td>
                <select name="departamento" id="departamento0"></select>
                <input type="text" name="departamento1" id="departamento1" />
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
                <input type="button" name="first" value="<|" id="fcu"/>
                <input type="button" name="previous" value="<<"id="pcu"/>
                <input type="button" name="next" value=">>" id="ncu"/>
                <input type="button" name="last" value="|>" id="lcu"/>
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