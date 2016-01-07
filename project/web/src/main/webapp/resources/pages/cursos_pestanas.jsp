<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/stiles.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/jquery-ui.css"/>">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/ui-lightness/jquery-ui.css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" />
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
    

<head>
    <script>
        $(function () {
            $("#tab").tabs();
        });
        
    </script>
</head>
<body>
    <div id="tab">
        <ul class="tabs">
            <li><a href="#tabs-1">Prerequisito</a></li>
            <li><a href="#tabs-2">Detalles</a></li>
        </ul>
        <div class="tab-content" id="tabs-1">
            <input type="button" id="nuevo" value="nuevo" />
            <input type="button" id="borrar" value="borrar" />
            <input type="button" id="editar" value="editar" /></p>
            <table id="cur_tab1" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>ID del Curso</th>
               	<th>Nombre</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>ID del Curso</th>
               	<th>Nombre</th>
            </tr>
        </tfoot>
            
            </table>
            
            
        </div>
        <div class="tab-content" id="tabs-2">
            <input type="button" id="nuevo" value="nuevo" />
            <input type="button" id="borrar" value="borrar" />
            <input type="button" id="editar" value="editar" /></p>

            <table id="cur_tab2" class="display" cellspacing="0" width="100%">
                <thead>
            <tr>
                <th>ID del Curso</th>
                <th>ID del Section</th>
               	<th>Semestre</th>
                <th>Edificio</th>
                <th>Room Number</th>
                <th>Id Time</th>
                <th>Year</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>ID del Curso</th>
                <th>ID del Section</th>
               	<th>Semestre</th>
                <th>Edificio</th>
                <th>Room Number</th>
                <th>Id Time</th>
                <th>Year</th>
            </tr>
        </tfoot>
            </table>
        </div>
</html>
<script>

</script>

<script>
    
</script>