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


<!--<script src="<c:url value="/resources/js/eventos.js"/>" type="text/javascript"></script> -->


<script>
$('#cancelarc').click(function(){
	$('#building').attr('readonly',true);
	$('.edit').attr('readonly',true);
	$('#botones').hide();
	$('#clase0').hide();
	$('#clase1').show();
	$('#cambio_2').hide();
	$('.iteradores').show();
	$('.botonesc').prop('disabled',false);
	getDpt();
});

$('#guardarc').click(function(){
	$('#building').attr('readonly',true);
	$('.edit').attr('readonly',true);
	$('#botones').hide();
	$('#clase0').hide();
	$('#clase1').show();
	$('#cambio_2').hide();
	$('.iteradores').show();
	$('.botonesc').prop('disabled',false);
	if(DMLActual == "search")
	{
		var building = tab_clas.cell('.selected',0).data();
		var clase = tab_clas.cell('.selected',1).data();
		var capacidad = tab_clas.cell('.selected',2).data();
		$('#building').val(building);
		$('#clase1').val(clase);
		$('#capacidad').val(capacidad);	
	}
	else{
	var building = $('#building').val();
	var clase = $('#clase1').val();
	var capacidad = $('#capacidad').val();
	var json = {"building" : building, "roomNumber" : clase, "capacity" : capacidad};
	DML(entityActual, DMLActual, json);
	}
	if(DMLActual == "delete"){
		actualizarEntity(entityActual, "first");
	}
});


$('#nuevoc').click(function () {
	DMLActual = "insert";
	$('#building').attr('readonly',false);
    $('#cambio_2').hide();
    $('#clase1').show();
    $('#clase0').hide();
    $('.edit').attr('readonly', false);
    $('#botones').show();
    $('.iteradores').hide();
    $('.botonesc').prop('disabled',true);
    $('#building').val('');
    $('#clase1').val('');
    $('#capacidad').val('');
});
$('#borrarc').click(function () {
	DMLActual = "delete";
    $('#cambio_2').hide();
    $('.iteradores').hide();
    $('#clase1').show();
    $('#clase0').hide();
    $('.edit').attr('readonly', true);
    $('#botones').show();
    $('.botonesc').prop('disabled',true);
});

$('#editarc').click(function () {
	DMLActual = "update";
    $('#cambio_2').hide();
    $('.iteradores').hide();
    $('#clase0').hide();
    $('#clase1').show();
    $('.edit').attr('readonly', false);
    $('#clase1').attr('readonly', true);
    $('#botones').show();
    $('.botonesc').prop('disabled',true);
});

$('#outsidec').click(function () {
	$('#tablasa').html('');
    $('#tablasa').load('resources/pages/classroom_buscar.jsp', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success"){
        	tab_clas =$('#clas_tab').DataTable({
        		"bProcessing": true,
                "bServerSide": true,
                "bLenthChange" : false,
                "iDisplayLength" : 10,
                "sAjaxSource": "classroomtable.do",
                'bJQueryUI': true,
                "aoColumns":[
                             {
                            	 "sTitle":"Building",
                            	 "mData":"building"
                             },
                             {
                            	 "sTitle":"Room Number",
                            	 "mData":"roomNumber"
                             },
                             {
                            	 "sTitle":"Capacity",
                            	 "mData":"capacity"
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
        	$('#body_clas').on('click', 'tr', function () {
        	    if ($(this).hasClass('selected')) {
        	        $(this).removeClass('selected');
        	    }
        	    else {
        	        tab_clas.$('tr.selected').removeClass('selected');
        	        $(this).addClass('selected');
        	    }
        	});
          }
        if (statusTxt == "error")
            alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
	DMLActual = "search";
    $('.edit').attr('readonly', true);
    $('.iteradores').hide();
    $('#cambio_2').show();
    $('#botones').show();
    $('.botonesc').prop('disabled',true);
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

$( "#building" ).validate({
  rules: {
    field: {
      required: true
    }
  }
});

$( "#clase1" ).validate({
  rules: {
    field: {
      required: true,
	  number: true;
    }
  }
});

$( "#capacidad" ).validate({
  rules: {
    field: {
      required: true,
      number: true
    }
  }
});

</script>


<div id="classroom">
    <table>
        <tr>
            <td>
                <input type="button" value="nuevo" name="nuevo"   id="nuevoc" class="botonesc"/>
                <input type="button" value="editar" name="editar" id="editarc" class="botonesc"/>
                <input type="button" value="buscar" name="buscar" id="outsidec" class="botonesc"/>
                <input type="button" value="borrar" name="borrar" id="borrarc" class="botonesc"/>
            </td>

        </tr>
    </table>

    <table>
        <tr>
            <td>Building</td>
            <td><input type="text" name="edificio" id="building" minlenght="1" maxlenght="15"/> </td>

        </tr>
        <tr>
            <td>Room_no </td>
            <td><select name="clase0" id="clase0"></select>
            <input type="text" name="clase1" id="clase1" class="edit"minlenght="1" maxlenght="7"/> </td>

        </tr>
        <tr>
            <td>Capacity</td>
            <td><input type="text" name="capacidad" id="capacidad" class="edit" minlenght="1" maxlenght="255"/> </td>
        </tr>
        <tr>
            <td>
                <input type="button" name="first" value="<|" id="fc" class="iteradores"/>
                <input type="button" name="previous" value="<<"id="pc"class="iteradores"/>
                <input type="button" name="next" value=">>"  id="nc"class="iteradores"/>
                <input type="button" name="last" value="|>"  id="lc"class="iteradores"/>
            </td>
            <td>
                <div id="botones">
                <input type="submit" name="guardar" value="guardar" id="guardarc"/>
                <input type="submit" name="cancelar" value="cancelar" id="cancelarc"/>
                </div>           
            </td>

        </tr>
    </table>


</div>