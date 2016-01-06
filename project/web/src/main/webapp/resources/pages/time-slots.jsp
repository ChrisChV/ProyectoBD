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
$('#nuevot').click(function () {
    $('#cambio_2').hide();
    $('#day1').hide();
    $('#start1').hide();
    $('#end1').hide();

    $('#day').show();
    $('#start').show();
    $('#end').show();

    $('#botones').show();
});
$('#borrart').click(function () {
    $('#cambio_2').hide();
    $('#day1').show();
    $('#start1').show();
    $('#end1').show();

    $('#day').hide();
    $('#start').hide();
    $('#end').hide();

    $('#botones').show();
});

$('#editart').click(function () {
    $('#cambio_2').hide();
    $('#day1').hide();
    $('#start1').hide();
    $('#end1').hide();

    $('#day').show();
    $('#start').show();
    $('#end').show();

    $('#botones').show();
});

$('#outsidet').click(function () {
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#botones').show();
});

$('#ft').click(function () {
	console.log("holaaaaaaaaaaaaaaa");
   	actualizarEntity('time', 'first');
})

$('#lt').click(function () {
	console.log("holaaaaaaaaaaaaaaa");
    actualizarEntity('time', 'last');
})
$('#nt').click(function () {
	console.log("holaaaaaaaaaaaaaaa");
    actualizarEntity('time', 'next');
})
$('#pt').click(function () {
	console.log("holaaaaaaaaaaaaaaa");
    actualizarEntity('time', 'prev');
})
</script>
<div id="time">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo"   id="nuevot"/>
                <input type="button" value="editar" name="editar" id="editart"/>
                <input type="button" value="buscar" name="buscar" id="outsidet"/>
                <input type="button" value="borrar" name="borrar" id="borrart"/>
            </td>

        </tr>
    </table>
    <table>
        <tr>
            <td>day</td>
            <td><select name="day" id="day"></select> 
                <input type="text" name="day1" id="day1" /> </td>

        </tr>
        <tr>
            <td>start time</td>
            <td><select name="start" id="start"></select>
                <input type="text" name="start1" id="start1" /> </td>
        
        </tr>
        <tr>
            <td>end time</td>
            <td><select name="end" id="end"></select> 
                <input type="text" name="end1" id="end1" /> </td>
        
        </tr>
        <tr>
            <td>
                <input type="button" name="first" value="<|" id="ft"/>
                <input type="button" name="previous" value="<<"id="pt"/>
                <input type="button" name="next" value=">>" id="nt"/>
                <input type="button" name="last" value="|>" id="lt"/>
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