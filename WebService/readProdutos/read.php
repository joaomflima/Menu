<?php
	
	include "conexao.php";

	$sql_read = "SELECT * FROM produto";
	$dados = $PDO->query($sql_read);

	$resultado = array();

	while($produto = $dados->fetch(PDO::FETCH_OBJ)) {

		/*$resultado[] = array("id_produto"=>$produto->ID_PRODUTO, "nome"=>$produto->NOME, 
			"descricao"=>$produto->DESCRICAO, "preco"=>$produto->PRECO, "id_categoria"=>$produto->ID_CATEGORIA, "id_lanchonete"=>$produto->ID_LANCHONETE);*/
		$resultado[] = array("nome"=>$produto->NOME);
	}

	echo json_encode($resultado);
?>