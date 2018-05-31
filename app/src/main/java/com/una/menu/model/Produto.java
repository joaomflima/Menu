package com.una.menu.model;

public class Produto {

    // Atributos
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

    // Getters and Setters

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
