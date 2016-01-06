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

<!--<script src="<c:url value="/resources/js/eventos.js"/>" type="text/javascript"></script> -->


<script>
$('#nuevoc').click(function () {
    $('#cambio_2').hide();
    $('#clase1').show();
    $('#clase0').hide();
    $('.edit').attr('readonly', false);
    $('#botones').show();
});
$('#borrarc').click(function () {
    $('#cambio_2').hide();
    $('#clase1').show();
    $('#clase0').hide();
    $('.edit').attr('readonly', true);
    $('#botones').show();
});

$('#editarc').click(function () {
    $('#cambio_2').hide();
    $('#clase1').hide();
    $('#clase0').show();
    $('.edit').attr('readonly', false);
    $('#botones').show();
});

$('#outsidec').click(function () {
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#botones').show();
});


$('#fc').click(function () {
    actualizarEntity('classroom', 'first');
})

$('#lc').click(function () {
    actualizarEntity('classroom', 'last');
})
$('#nc').click(function () {
    actualizarEntity('classroom', 'next');
})
$('#pc').click(function () {
    actualizarEntity('classroom', 'prev');
})
</script>


<div id="classroom">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo"   id="nuevoc" />
                <input type="button" value="editar" name="editar" id="editarc" />
                <input type="button" value="buscar" name="buscar" id="outsidec" />
                <input type="button" value="borrar" name="borrar" id="borrarc"/>
            </td>

        </tr>
    </table>

    <table>
        <tr>
            <td>Building</td>
            <td><input type="text" name="edificio" id="building"/> </td>

        </tr>
        <tr>
            <td>Room_no </td>
            <td><select name="clase0" id="clase0"></select>
            <input type="text" name="clase1" id="clase1" class="edit"/> </td>

        </tr>
        <tr>
            <td>Capacity</td>
            <td><input type="text" name="capacidad" id="capacidad" class="edit"/> </td>
        </tr>
        <tr>
            <td>
                <input type="button" name="first" value="<|" id="fc"/>
                <input type="button" name="previous" value="<<"id="pc"/>
                <input type="button" name="next" value=">>"  id="nc"/>
                <input type="button" name="last" value="|>"  id="lc"/>
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