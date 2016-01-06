<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>"Proyecto Base de Datos"<</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/stiles.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/jquery-ui.css"/>">
        
        <!--  
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.css" />">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.min.css" />">
        -->
        
        
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/s/dt/dt-1.10.10,af-2.1.0,b-1.1.0,b-colvis-1.1.0,cr-1.3.0,fc-3.2.0,fh-3.1.0,kt-2.1.0,r-2.0.0,rr-1.1.0,sc-1.4.0,se-1.1.0/datatables.min.css" />
		
		 
        
 		 
 		 
 		<script src="<c:url value="https://code.jquery.com/jquery-1.10.2.js"/>"></script>
        <script src="<c:url value="/resources/js/controladores.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/eventos.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/tests.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/resources/js/jquery-ui.js"/>" type="text/javascript"></script>
		<script type="text/javascript" src="<c:url value="https://cdn.datatables.net/s/dt/dt-1.10.10,af-2.1.0,b-1.1.0,b-colvis-1.1.0,cr-1.3.0,fc-3.2.0,fh-3.1.0,kt-2.1.0,r-2.0.0,rr-1.1.0,sc-1.4.0,se-1.1.0/datatables.min.js"/>"></script>
		
		
		<!--  
		<script src="<c:url value="/resources/js/jquery.dataTables.js"/>"type="text/javascript"></script>
		<script src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"type="text/javascript"></script>
        -->
        
        <h1 id="Tittle"></h1>
    </head>


    <body>
        <div>

            <div id="barra_lateral" class="barras">
                <p id="departamento">Departamento</p>
                <p id="clase">Classroom</p>
                <p id="timeslot">TimeSlot</a>
                <p id="curso"> Curso </p>
                <p id="estudiante"> Estudiante </p>
                <p id="profesor"> Profesor </p>
            </div>

            <div id="cambio_1" class="barras">
                Aca entra el formulario del menu que se seleccione
                en la barra lateral
            </div>
            <div id="cambio_2" class="barras">   
                <div id ="buscadores"> </div>
				<div id="tablasa"> </div>
            </div>
        </div>


    </body>

    <tail>



    </tail>

</html>