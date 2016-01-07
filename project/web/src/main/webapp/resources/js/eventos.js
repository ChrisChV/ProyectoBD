var tab_dep;
var tab_clas;
var tab_time;

$(document).ready(function () {	
	console.log("inicie correctamente");
    $('#departamento').click(function () {
    	entityActual = "department";
    	DMLActual = "null";
    	$('#buscadores').html('');
        $('#cambio_1').load('resources/pages/departamentoss.jsp', function (responseTxt, statusTxt, xhr)
        {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#edificio").hide();
                $("#departamentos").attr('readonly', true);
                $("#edificio1").attr('readonly', true);
                $("#presupuesto").attr('readonly', true);
                $('#cambio_2').hide();
                getDpt();
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });

    $('#clase').click(function () {
    	entityActual = "classroom";
    	DMLActual = "null";
    	$('#buscadores').html('');
        $('#cambio_1').load('resources/pages/classrooms.jsp', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#clase0").hide();
                $("#building").attr('readonly', true);
                $("#clase1").attr('readonly', true);
                $("#capacidad").attr('readonly', true);
                $('#cambio_2').hide();
                getClassroom();
            if (statusTxt == "error")
                alert("Error: " + xcambio_1hr.status + ": " + xhr.statusText);
        });
    });
    
    $('#timeslot').click(function () {
    	entityActual = "time";
    	DMLActual = "null";
    	$('#buscadores').html('');
        $('#cambio_1').load('resources/pages/time-slots.jsp', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
            	$('#idtime').attr('readonly',true);
	            $('#day2').show();
	            $('#start2').show();;
	        	$('#end2').show();
	            $('#day1').hide();
	            $('#starth1').hide();
	            $('#startm1').hide();
	            $('#endh1').hide();
	        	$('#endm1').hide();
	        	$('#day2').attr('readonly',true);
	            $('#start2').attr('readonly',true);
	        	$('#end2').attr('readonly',true);
	        	$('#cambio_2').hide();
	        	getTime();
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#curso').click(function () {
    	entityActual = "course";
    	DMLActual = "null";
    	$('#cambio_1').html('');
        $('#cambio_1').load('resources/pages/cursos.jsp', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success"){
            	getCourse();
            	$("#botones").hide();
                $("#departamento0").hide();
                $("#curso_id").attr('readonly', true);
                $("#titulo").attr('readonly', true);
                $("#departamento1").attr('readonly', true);
                $("#creditos").attr('readonly', true);
                pestanasC();
            }	
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#estudiante').click(function () {
    	entityActual = "student";
    	DMLActual = "null";
    	$('#cambio_1').html('');
        $('#cambio_1').load('resources/pages/estudiante.jsp', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success"){
            	getStudent();
                $("#botones").hide();
                $("#departamentoe").hide();
                $("#estudiante_id").attr('readonly', true);
                $("#nombre").attr('readonly', true);
                $("#departamentoe1").attr('readonly', true);
                $("#creditos_t").attr('readonly', true);
                pestanasS();
            }
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#profesor').click(function () {
    	entityActual = "instructor";
    	DMLActual = "null";
    	$('#cambio_1').html('');
        $('#cambio_1').load('resources/pages/profesor.jsp', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success"){
                $("#botones").hide();
                $("#departamentop").hide();
                $("#profesor_id").attr('readonly', true);
                $("#nombre_p").attr('readonly', true);
                $("#departamentop1").attr('readonly', true);
                $("#salario").attr('readonly', true);
                getInstructor();
                pestanasP();
            }
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
});

    
    
    