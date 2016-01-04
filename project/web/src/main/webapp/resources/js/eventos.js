$(document).ready(function () {	
	console.log("inicie correctamente");
	listTest();
    $('#departamento').click(function () {
        $('#cambio_1').load('pages/departamentoss.html', function (responseTxt, statusTxt, xhr)
        {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#edificio").hide();
                $("#departamentos").attr('readonly', true);
                $("#edificio1").attr('readonly', true);
                $("#presupuesto").attr('readonly', true);
                $('#tablasa').load('pages/departamento_buscar.php', function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "success")
                        $('#cambio_2').hide();
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });

    $('#clase').click(function () {
        $('#cambio_1').load('pages/classrooms.html', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#clase0").hide();
                $("#building").attr('readonly', true);
                $("#clase1").attr('readonly', true);
                $("#capacidad").attr('readonly', true);
                $('#tablasa').load('pages/classroom_buscar.php', function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "success")
                        $('#cambio_2').hide();
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#timeslot').click(function () {
        $('#cambio_1').load('pages/time-slots.html', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#day1").attr('readonly', true);
                $("#day").hide();

                $("#start1").attr('readonly', true);
                $("#start").hide();

                $("#end1").attr('readonly', true);
                $("#end").hide();
                $('#tablasa').load('pages/timeslot_buscar.php', function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "success")
                        $('#cambio_2').hide();
                    if (statusTxt == "error")
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                });
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#curso').click(function () {
        $('#cambio_1').load('pages/cursos.html', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#departamento0").hide();
                $("#curso_id").attr('readonly', true);
                $("#titulo").attr('readonly', true);
                $("#departamento1").attr('readonly', true);
                $("#creditos").attr('readonly', true);
                $("#cambio_2").hide();
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#estudiante').click(function () {
        $('#cambio_1').load('pages/estudiante.html', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#cambio_2").hide();
                $("#departamentoe").hide();
                $("#estudiante_id").attr('readonly', true);
                $("#nombre").attr('readonly', true);
                $("#departamentoe1").attr('readonly', true);
                $("#creditos_t").attr('readonly', true);
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $('#profesor').click(function () {
        $('#cambio_1').load('pages/profesor.html', function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")
                $("#botones").hide();
                $("#departamentop").hide();
                $("#profesor_id").attr('readonly', true);
                $("#nombre_p").attr('readonly', true);
                $("#departamentop1").attr('readonly', true);
                $("#salario").attr('readonly', true);
                $("#cambio_2").hide();
            if (statusTxt == "error")
                alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
});

$('#nuevod').click(function () {
    $('#cambio_2').hide();
    $('#edificio1').show();
    $('#edificio').hide();
    $('.edit').attr('readonly', false);
    $('#botones').show();
});
$('#borrard').click(function () {
    $('#cambio_2').hide();
    $('#edificio1').show();
    $('#edificio').hide();
    $('.edit').attr('readonly', true);
    $('#botones').show();
});

$('#editard').click(function () {
    $('#cambio_2').hide();
    $('#edificio1').hide();
    $('#edificio').show();
    $('.edit').attr('readonly', false);
    $('#botones').show();
});


$('#outsided').click(function () {
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#botones').show();
});

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

$('#borrarcu').click(function () {
    $('#cambio_2').hide();
    $('#departamento0').hide();
    $('#departamento1').show();
    $('.edit').attr('readonly', true);
    $('#botones').show();
});

$('#nuevocu').click(function () {

});

$('#buscarcu').click(function () {
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#tablasa').load('pages/cursos_buscar.php', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success")
            $('#botones').show();
        if (statusTxt == "error")
            alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
});
$('#editarcu').click(function () {
    
});

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
    $('#tablasa').load('pages/estudiante_buscar.php', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success")
            $('#botones').show();
        if (statusTxt == "error")
            alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
});

$('#editare').click(function () {

});

$('#borrarp').click(function () {
    $('#cambio_2').hide();
    $('#departamentop').hide();
    $('#departamentop1').show();
    $('.edit').attr('readonly', true);
    $('#botones').show();
});

$('#nuevop').click(function () {

});

$('#buscarp').click(function () {
    $('.edit').attr('readonly', true);
    $('#cambio_2').show();
    $('#tablasa').load('pages/profesor_buscar.php', function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success")
            $('#botones').show();
        if (statusTxt == "error")
            alert("Error: " + xhr.status + ": " + xhr.statusText);
    });
});

$('#editarp').click(function () {

});
