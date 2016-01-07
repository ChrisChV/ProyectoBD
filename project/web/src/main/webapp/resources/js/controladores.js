var indexStudent = 0;
var indexCourse = 0;
var indexTime = 0;
var indexClassroom = 0;
var indexInstructor = 0;
var indexDpt = 0;

var entityActual = "null";
var DMLActual = "null";

function DML(classEntity, polarity, json){
	if(polarity == "null" || classEntity == "null") return;
	console.log(json);
	$.ajax({
        dataType:'json',
        type:'post',
        cache:false,
        data : json,
        url:'/web/' + classEntity + "/" + polarity,
        success: function(data, textStatus, jqXHR){
            if(data) {
            	console.log(data);
            	alert(data);
            }
            else {
            	console.log('msg_internal_server_error');
            }
        }
    });
}

function getAll(classEntity, table){
	$.ajax({
        dataType:'json',
        type:'post',
        cache:false,
        url:'/web/' + classEntity + "/all",
        success: function(data, textStatus, jqXHR){
            if(data) {
            	$('#' + table + "_b").html("");
            	$.each(data, function(index, value) {
            		$('#' + table + "_b").append("<tr>");
            		$('#' + table  + "_b").append("<td>" + value.building + "</td>");
            		$('#' + table  + "_b").append("<td>" + value.roomNumber + "</td>");
            		$('#' + table  + "_b").append("<td>" + value.capacity + "</td>");
            		$('#' + table  + "_b").append("</tr>");
            	});
            	
            }
            else {
            	console.log('msg_internal_server_error');
            }
        }
    });
}

function verificarIndex(classEntity, index, result){
	var json = {"index" : index};
	$.ajax({
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url: '/web/' + classEntity  + "/verificar" ,
	    data: json,
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	        	console.log("VERIFICAR_INDEX_" + classEntity + "->" + data);
	        	result(data);
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    } 
	});
}

function getLastIndex(classEntity, result){
	$.ajax({
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url: '/web/' + classEntity  + "/last" ,
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	        	console.log("LAST_INDEX_" + classEntity + "->" + data);
	        	result(data);
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    } 
	});
}

function getIndex(classEntity){
	if(classEntity == "student") return indexStudent;
	else if(classEntity == "instructor") return indexInstructor;
	else if(classEntity == "department") return indexDpt;
	else if(classEntity == "time") return indexTime;
	else if(classEntity == "classroom") return indexClassroom;
	else if(classEntity == "course") return indexCourse;
}

function actualizarIndex(classEntity, res){
	if(classEntity == "student") indexStudent = res;
	else if(classEntity == "instructor") indexInstructor = res;
	else if(classEntity == "department") indexDpt = res;
	else if(classEntity == "time") indexTime = res;
	else if(classEntity == "classroom") indexClassroom = res;
	else if(classEntity == "course") indexCourse = res;
}

function actualizarEntity(classEntity , polarity){
	if(polarity == "last"){
		getLastIndex(classEntity, function(res){
			actualizarIndex(classEntity, res);
			getEntity(classEntity);
		});
	}
	else if(polarity == "first"){
		actualizarIndex(classEntity, 0);
		getEntity(classEntity);
	}
	else{
		if(polarity == "next"){
			console.log("nnn->" + indexClassroom);
			actualizarIndex(classEntity, getIndex(classEntity) + 1);
			console.log("nnnnnnn->" + indexClassroom);
			
			
			
		}
		else if(polarity == "prev"){
			actualizarIndex(classEntity, getIndex(classEntity) - 1);
		}
		verificarIndex(classEntity, getIndex(classEntity), function(res){
			if(res == -1){
				actualizarIndex(classEntity, 0);
			}
			else if(res == 0){
				actualizarIndex(classEntity, getIndex(classEntity) - 1);
			}
			else{
				getEntity(classEntity);
			}
		});
	}
}

function getEntity(classEntity){
	if(classEntity == "student") getStudent();
	else if(classEntity == "instructor") getInstructor();
	else if(classEntity == "department") getDpt();
	else if(classEntity == "time") getTime();
	else if(classEntity == "classroom") getClassroom();
	else if(classEntity == "course") getCourse();
}

function getCourse(){
	var json = {"index" : indexCourse};
	$.ajax({
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url: '/web/course',
	    data: json,
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	            $('#curso_id').val(data.id);
	            $('#titulo').val(data.title);
	            $('#departamento1').val(data.dptName);
	            $('#creditos').val(data.credits);
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
}


function getClassroom(){
	var json = {"index" : indexClassroom};
	$.ajax({
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url: '/web/classroom',
	    data: json,
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	            $('#building').val(data.building);
	            $('#clase1').val(data.roomNumber);
	            $('#capacidad').val(data.capacity);

	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
}

function getInstructor(){
	var json = {"index" : indexInstructor};
	$.ajax({
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url: '/web/instructor',
	    data: json,
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	            $('#profesor_id').val(data.id);
	            $('#nombre_p').val(data.name);
	            $('#departamentop1').val(data.dptName);
	            $('#salario').val(data.salary);
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
}

function getTime(){
	var json = {"index" : indexTime};
	$.ajax({
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url: '/web/time',
	    data: json,
	    success: function(data, textStatus, jqXHR){
	        if (data) {
	            $('#day2').val(data.day);
	            $('#start2').val(data.start);
	            $('#end2').val(data.end);
	            $('#idtime').val(data.id);
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
}

function getDpt(){
	var json = {"index" : indexDpt};
	$.ajax({
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url:'/web/department',
	    data: json,
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	            $('#departamentos').val(data.dptName);
	            $('#edificio1').val(data.building);
	            $('#presupuesto').val(data.budget);
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
}

function getStudent(){
	var json = {"index" : indexStudent};
	$.ajax({
	    dataType:'json',
	    type:'post',
	    cache:false,
	    url:'/web/student',
	    data: json,
	    success: function(data, textStatus, jqXHR){
	        if(data) {
	        	console.log(data);
	            $('#estudiante_id').val(data.id);
	            $('#nombre').val(data.name);
	            $('#departamentoe1').val(data.dptName);
	            $('#creditos_t').val(data.totCred);	            
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
}