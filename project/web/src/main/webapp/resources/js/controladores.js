var indexStudent = 0;
var indexCourse = 0;
var indexTime = 0;
var indexClassroom = 0;
var indexInstructor = 0;
var indexDtp = 0;

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
	else if(classEntity == "department") return indexDtp;
	else if(classEntity == "time") return indexTime;
	else if(classEntity == "classroom") return indexClassroom;
	else if(classEntity == "course") return indexCourse;
}

function actualizarIndex(classEntity, res){
	if(classEntity == "student") indexStudent = res;
	else if(classEntity == "instructor") indexInstructor = res;
	else if(classEntity == "department") indexDtp = res;
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
			actualizarIndex(classEntity, getIndex(classEntity) + 1);
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
	        	console.log(data);
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
	        	console.log(data);
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
	        	console.log(data);
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
	        if(data) {
	        	console.log(data);
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
	        	console.log(data);
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
	        }
	        else {
	        	console.log('msg_internal_server_error');
	        }
	    }
	});
}