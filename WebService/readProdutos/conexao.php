<?php
	
	$dns = "mysql:host=localhost;dbname=menu;charset=utf8";
	$usuario = "root";
	$senha = "";

	try {

		$PDO = new PDO($dns, $usuario, $senha);

		//echo "Conectado com sucesso";

	} catch(PDOException $erro) {

		echo "Ocorreu um erro; ";
		echo $erro->getMessage();

	}


?>