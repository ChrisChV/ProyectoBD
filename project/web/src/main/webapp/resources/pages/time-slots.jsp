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


<script>
var a = $('#day2').val();
console.log(a);
$('#cancelart').click(function(){
	$('#botones').hide();
	$('#cambio_2').hide();
	$('.iteradores').show();
	$('.botonest').prop('disabled',false);

    $('#day1').hide();
    $('#starth1').hide();
    $('#startm1').hide();
    $('#endh1').hide();
	$('#endm1').hide();
    $('#day2').show();
    $('#start2').show();
	$('#end2').show();
	$('#idtime').attr('readonly',true);
	
	$('.iteradores').show();
	getDpt();
});

$('#guardart').click(function(){
	
	$('#botones').hide();
	$('#cambio_2').hide();
	$('.iteradores').show();
	$('.botonest').prop('disabled',false);


    $('#day1').hide();
    $('#starth1').hide();
    $('#startm1').hide();
    $('#endh1').hide();
	$('#endm1').hide();
    $('#day2').show();
    $('#start2').show();
	$('#end2').show();
	$('#idtime').attr('readonly',true);
	$('.iteradores').show();
	if(DMLActual == "search"){
		console.log("eeeeeeeee");
		var id = tab_time.cell('.selected',0).data();
		var day = tab_time.cell('.selected',1).data();
		var start = tab_time.cell('.selected',2).data();
		var end = tab_time.cell('.selected',3).data();
		$('#idtime').val(id);
		$('#day2').val(day);
		$('#start2').val(start);
		$('#end2').val(end);
	}
	else{
		var id = $('#idtime').val();
		var day = $('#day1').val();
		var Ih = $('#starth1').val();
		var Im = $('#startm1').val();
		var Eh = $('#endh1').val();
		var Em = $('#endm1').val();
		var json = {"id" : id, "day" : day, "startHr" : Ih, "startMin" : Im, "endHr" : Eh, "endMin" : Em};
		console.log(json);
		DML(entityActual, DMLActual, json);		
	}
	if(DMLActual == "delete"){
		var id = $('#idtime').val();
		var json = {"id" : id};
		DML(entityActual, DMLActual, json);
		actualizarEntity(entityActual, "first");
		getTime();
	}
});

$('#nuevot').click(function () {
	DMLActual = "insert";
    $('#cambio_2').hide();
    $('.selectores').hide();


    $('#day1').show();
    $('#starth1').show();
    $('#startm1').show();
    $('#endh1').show();
	$('#endm1').show();
    $('#day2').hide();
    $('#start2').hide();
	$('#end2').hide();
	$('#idtime').attr('readonly',false);
    $('.iteradores').hide();
    $('.botonest').prop('disabled',true);

    $('#botones').show();
});
$('#borrart').click(function () {
	DMLActual = "delete";
    $('#cambio_2').hide();

    $('#day1').hide();
    $('#starth1').hide();
    $('#startm1').hide();
    $('#endh1').hide();
	$('#endm1').hide();
    $('#day2').show();
    $('#start2').show();
	$('#end2').show();
	$('#idtime').attr('readonly',true);
	$('#day2').attr('readonly',true);
    $('#start2').attr('readonly',true);
	$('#end2').attr('readonly',true);
	
	 $('.iteradores').hide();
	$('.botonest').prop('disabled',true);
    $('#botones').show();
});

$('#editart').click(function () {
	DMLActual = "update";
	$('#cambio_2').hide();
    $('.selectores').hide();

    $('#day1').show();
    $('#starth1').show();
    $('#startm1').show();
    $('#endh1').show();
	$('#endm1').show();
    $('#day2').hide();
    $('#start2').hide();
	$('#end2').hide();
	
    $('.iteradores').hide();
    $('#idtime').attr('readonly',true);
    $('.botonest').prop('disabled',true);
    $('#botones').show();

});

$('#outsidet').click(function () {
	DMLActual = "search";
	$('#tablasa').html('');
    $('#tablasa').load('resources/pages/timeslot_buscar.jsp', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success"){
                tab_time = $('#time_tab').DataTable({
            		"bProcessing": true,
                    "bServerSide": true,
                    "bLenthChange" : false,
                    "iDisplayLength" : 10,
                    "sAjaxSource": "timetable.do",
                    'bJQueryUI': true,
                    "aoColumns":[
                                 {
                                	 "sTitle":"ID",
                                	 "mData":"id"
                                 },                                 
                                 {
                                	 "sTitle":"Dia",
                                	 "mData":"day"
                                 },
                                 {
                                	 "sTitle":"Inicio",
                                	 "mData":"start"
                                 },
                                 {
                                	 "sTitle":"Final",
                                	 "mData":"end"
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
                $('#body_time').on('click', 'tr', function () {
                    if ($(this).hasClass('selected')) {
                        $(this).removeClass('selected');
                    }
                    else {
                        tab_time.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
               }
        if (statusTxt == "error")
            alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#botones').show();
    $('.botonest').prop('disabled',true);
    $('.iteradores').hide();
});

$('#ft').click(function () {
   	actualizarEntity('time', 'first');
})

$('#lt').click(function () {
    actualizarEntity('time', 'last');
})
$('#nt').click(function () {
    actualizarEntity('time', 'next');
})
$('#pt').click(function () {
    actualizarEntity('time', 'prev');
})
$( "#id_time" ).validate({
  rules: {
    field: {
      required: true,
	  number: true
    }
  }
});

$( "#day2" ).validate({
  rules: {
    field: {
      required: true,
	  number: true;
    }
  }
});

$( "#start2" ).validate({
  rules: {
    field: {
      required: true,
      number: true
    }
  }
});

$( "#end2" ).validate({
  rules: {
    field: {
      required: true,
      number: true
    }
  }
});

</script>
<div id="time">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo"   id="nuevot" class="botonest"/>
                <input type="button" value="editar" name="editar" id="editart"class="botonest"/>
                <input type="button" value="buscar" name="buscar" id="outsidet"class="botonest"/>
                <input type="button" value="borrar" name="borrar" id="borrart"class="botonest"/>
            </td>

        </tr>
    </table>
    <table>
    	<tr>
    		<td>ID </td>
    		<td> <input type="text" name="IDtime" id="idtime"minlenght="1" maxlength="4"/> </td>
    	
    	</tr>
        <tr>
            <td>day</td>
            <td><select name="day" id="day1">
            	<option value="M">Monday</option>
            	<option value="T">Tuesday</option>
            	<option value="W">Wednesday</option>
            	<option value="R">Thursday</option>
            	<option value="F">Freeday</option>
            
            </select> 
                <input type="text" name="day1" id="day2" class="nselecotres"minlenght="1" maxlength="1"/> </td>

        </tr>
        <tr>
            <td>start time</td>
            <td><select name="start" id="starth1" class="selectores">
            	<option value="00">00</option>
            	<option value="01">01</option>
            	<option value="02">02</option>
            	<option value="03">03</option>
            	<option value="04">04</option>
            	<option value="05">05</option>
            	<option value="06">06</option>
            	<option value="07">07</option>
            	<option value="08">08</option>
            	<option value="09">09</option>
            	<option value="10">10</option>
            	<option value="11">11</option>
            	<option value="12">12</option>
            	<option value="13">13</option>
            	<option value="14">14</option>
            	<option value="15">15</option>
            	<option value="16">16</option>
            	<option value="17">17</option>
            	<option value="18">18</option>
            	<option value="19">19</option>
            	<option value="20">20</option>
            	<option value="21">21</option>
            	<option value="22">22</option>
            	<option value="23">23</option>
            </select><select name="start" id="startm1" class="selectores">
            	<option value="00">00</option>
            	<option value="10">10</option>
            	<option value="20">20</option>
            	<option value="30">30</option>
            	<option value="40">40</option>
            	<option value="50">50</option>
            </select>
                <input type="text" name="start2" id="start2" minlenght="1" maxlength="5"/> </td>
        
        </tr>
        <tr>
            <td>end time</td>
                        <td><select name="end" id="endh1" class="selectores">
            	<option value="00">00</option>
            	<option value="01">01</option>
            	<option value="02">02</option>
            	<option value="03">03</option>
            	<option value="04">04</option>
            	<option value="05">05</option>
            	<option value="06">06</option>
            	<option value="07">07</option>
            	<option value="08">08</option>
            	<option value="09">09</option>
            	<option value="10">10</option>
            	<option value="11">11</option>
            	<option value="12">12</option>
            	<option value="13">13</option>
            	<option value="14">14</option>
            	<option value="15">15</option>
            	<option value="16">16</option>
            	<option value="17">17</option>
            	<option value="18">18</option>
            	<option value="19">19</option>
            	<option value="20">20</option>
            	<option value="21">21</option>
            	<option value="22">22</option>
            	<option value="23">23</option>
            </select>
            <select name="end" id="endm1" class="selectores">
            	<option value="00">00</option>
            	<option value="10">10</option>
            	<option value="20">20</option>
            	<option value="30">30</option>
            	<option value="40">40</option>
            	<option value="50">50</option>
            </select>
                <input type="text" name="end2" id="end2" minlenght="1" maxlength="5"/></td>
        
        </tr>
        <tr>
            <td>
                <input type="button" name="first" value="<|" id="ft" class="iteradores"/>
                <input type="button" name="previous" value="<<"id="pt"class="iteradores"/>
                <input type="button" name="next" value=">>" id="nt"class="iteradores"/>
                <input type="button" name="last" value="|>" id="lt"class="iteradores"/>
            </td>
            <td>
                <div id="botones">
                <input type="submit" name="guardar" value="guardar" id="guardart"/>
                <input type="submit" name="cancelar" value="cancelar"id="cancelart" />
                </div> 
           </td>
        </tr>
    </table>


</div>