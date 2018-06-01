package com.una.menu.model;

public class Produto {

    // Atributos
    private String id_produto;
    private String nome;
    private String descricao;
    private String preco;

    // Construtor 01
    public Produto(String nome, String descricao, String preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Contrutor 02
    public Produto(){

    }
    // Contrutor 03
    public Produto(String nome) {
        this.nome = nome;
        this.descricao = "Descrição do Produto";
        this.preco = "R$00,00";
    }
    // Contrutor 04
    public Produto(String nome, String id) {
        this.nome = nome;
        this.id_produto = id;
    }

    // Getters and Setters


    public String getId_produto() {
        return id_produto;
    }

    public void setId_produto(String id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
