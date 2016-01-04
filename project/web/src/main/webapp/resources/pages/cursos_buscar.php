<?php
	error_reporting(E_ALL ^ E_DEPRECATED);
	$con = mysql_connect("localhost","root","EduardoBasurco");

	if(!$con){
		die("error: ".mysql_error());
	}
	mysql_select_db("proyecto",$con);
	$result = mysql_query("SELECT * FROM course");
	
?>

<html>
<link rel="stylesheet" href="css/stiles.css">
<link rel="stylesheet" href="css/jquery-ui.css">


<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.10/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>

    <table id="cur_tab" class="display" width="100%">
		<tbody>
			<?php
			while($row = mysql_fetch_array($result)){
				?>
			<tr>
				<td><?=$row['course_id']?></td>
				<td><?=$row['title']?></td>
				<td><?=$row['dept_name']?></td>
				<td><?=$row['credits']?></td>
			</tr>
			<?php
			}
			?>
	</table>
	</html>
<script>
    $('#cur_tab').DataTable({
        columns: [
            { title: "Curso_id" },
            { title: "Titulo" },
			{ title: "Nombre departamento" },
            { title: "Creditos" }

        ]
    });
</script>