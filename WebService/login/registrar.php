<?php
	
	include_once 'conexao.php';

	$nome = $_POST['nome'];
	$email = $_POST['email'];
	$senha = $_POST['senha'];

	$sql1 = $dbcon->query("SELECT * FROM tblogin WHERE email = '$email'");

	if(mysqli_num_rows($sql1) > 0) {

		echo "email_erro";

	} else {

		$sql2 = $dbcon->query("INSERT INTO tblogin (nome, email, senha) VALUES ('$nome', '$email', '$senha')");

		if($sql2) {

			echo "registro_ok";

		} else {

			echo "registro_erro";
		}
	}

?>