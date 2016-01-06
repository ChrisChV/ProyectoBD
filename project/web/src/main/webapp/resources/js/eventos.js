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
                getDpt();
                $('#tablasa').html('');
                $('#tablasa').load('resources/pages/departamento_buscar.jsp', function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "success"){
                        $('#cambio_2').hide();
                        var tab_dep =$('#dep_tab').DataTable({
                    		"bProcessing": true,
                            "bServerSide": true,
                            "bLenthChange" : false,
                            "iDisplayLength" : 20,
                            "searchObj" : "Biology",
                            "sAjaxSource": "departmenttable.do",
                            'bJQueryUI': true,
                            "aoColumns":[
                                         {
                                        	 "sTitle":"Nombre",
                                        	 "mData":"dptName"
                                         },
                                         {
                                        	 "sTitle":"Edificio",
                                        	 "mData":"building"
                                         },
                                         {
                                        	 "sTitle":"Presupuesto",
                                        	 "mData":"budget"
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
                        $('#body_dep').on('click', 'tr', function () {
                            if ($(this).hasClass('selected')) {
                                $(this).removeClass('selected');
                            }
                            else {
                                tab_dep.$('tr.selected').removeClass('selected');
                                $(this).addClass('selected');
                            }
                        });
                       }
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
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
                getClassroom();
                $('#tablasa').html('');
                $('#tablasa').load('resources/pages/classroom_buscar.jsp', function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "success"){
                    	var tab_clas =$('#clas_tab').DataTable({
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
                    	$('#cambio_2').hide();
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
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
	            

	        	getTime();
                $('#tablasa').html('');
                $('#tablasa').load('resources/pages/timeslot_buscar.jsp', function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "success"){
                        $('#cambio_2').hide();
                            var tab_time =$('#time_tab').DataTable({
                        		"bProcessing": true,
                                "bServerSide": true,
                                "bLenthChange" : false,
                                "iDisplayLength" : 10,
                                "sAjaxSource": "timetable.do",
                                'bJQueryUI': true,
                                "aoColumns":[
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
                                    tab_dep.$('tr.selected').removeClass('selected');
                                    $(this).addClass('selected');
                                }
                            });
                           }
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#curso').click(function () {
    	entityActual = "course";
    	DMLActual = "null";
    	$('#cambio_1').html('');
        $('#cambio_1').load('resources/pages/cursos.jsp', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
            	/*$('#cambio_1').load('resources/pages/cursos_pestañas.jsp', function (responseTxt, statusTxt, xhr) {
            		$('#cur_tab1').DataTable({
            			"bProcessing": true,
            		    "bServerSide": true,
            		    "bLenthChange" : false,
            		    "iDisplayLength" : 10,
            		    "sAjaxSource": "departmenttable.do",
            		    'bJQueryUI': true,
            		    "aoColumns":[
            		                 {
            		                	 "sTitle":"Nombre",
            		                	 "mData":"dptName"
            		                 },
            		                 {
            		                	 "sTitle":"Edificio",
            		                	 "mData":"building"
            		                 },
            		                 {
            		                	 "sTitle":"Presupuesto",
            		                	 "mData":"budget"
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
            	$('#cur_tab1').DataTable({
                    columns: [
                    

                    ]
                });
                */
                $("#botones").hide();
                $("#departamento0").hide();
                $("#curso_id").attr('readonly', true);
                $("#titulo").attr('readonly', true);
                $("#departamento1").attr('readonly', true);
                $("#creditos").attr('readonly', true);
                $("#cambio_2").hide();
                getCourse();
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#estudiante').click(function () {
    	entityActual = "student";
    	DMLActual = "null";
    	$('#cambio_1').html('');
        $('#cambio_1').load('resources/pages/estudiante.jsp', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#cambio_2").hide();
                $("#departamentoe").hide();
                $("#estudiante_id").attr('readonly', true);
                $("#nombre").attr('readonly', true);
                $("#departamentoe1").attr('readonly', true);
                $("#creditos_t").attr('readonly', true);
                getStudent();
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#profesor').click(function () {
    	entityActual = "instructor";
    	DMLActual = "null";
    	$('#cambio_1').html('');
        $('#cambio_1').load('resources/pages/profesor.jsp', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#departamentop").hide();
                $("#profesor_id").attr('readonly', true);
                $("#nombre_p").attr('readonly', true);
                $("#departamentop1").attr('readonly', true);
                $("#salario").attr('readonly', true);
                $("#cambio_2").hide();
                getInstructor();
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
});

    
    
    