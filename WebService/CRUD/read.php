<?php
	
	include "conexao.php";

	$sql_read = "SELECT * FROM contatos";
	$dados = $PDO->query($sql_read);

	$resultado = array();

	while($contato = $dados->fetch(PDO::FETCH_OBJ)) {

		$resultado[] = array("id"=>$contato->id, "nome"=>$contato->nome, "telefone"=>$contato->telefone, "email"=>$contato->email);
	}

	echo json_encode($resultado);
?>