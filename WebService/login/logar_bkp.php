<?php
	
	include_once 'conexao.php';

	$email = $_POST['email'];
	$senha = $_POST['senha'];

	$sql = $dbcon->query("SELECT * FROM tblogin WHERE email = '$email' AND senha = '$senha'");

	if(mysqli_num_rows($sql) > 0){
		echo "login_ok";
	} else {
		echo "login_erro";
	}

?>