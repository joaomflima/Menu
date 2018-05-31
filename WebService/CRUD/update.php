<?php
	include "conexao.php";

	$id = $_POST['id'];
	$nome = $_POST['nome'];
	$telefone = $_POST['telefone'];
	$email = $_POST['email'];

	$sql_update = "UPDATE contatos SET nome=:nome, telefone=:telefone, email=:email WHERE id=:id";

	$stmt = $PDO->prepare($sql_update);

	$stmt->bindParam(':nome', $nome);
	$stmt->bindParam(':telefone', $telefone);
	$stmt->bindParam(':email', $email);
	$stmt->bindParam(':id', $id);

	if($stmt->execute()){

		$dados = array("UPDATE"=>"OK");

	} else {

		$dados = array("UPDATE"=>"ERRO");
	}

	echo json_encode($dados);
?>