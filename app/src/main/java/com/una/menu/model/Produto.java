package com.una.menu.model;

import android.support.annotation.NonNull;

public class Produto implements Comparable<Produto> {

    // Atributos
    private String id_produto;
    private String nome;
    private String descricao;
    private String preco;
    private String avaliacao;
    private String imagem;
    private String nomeLanchonete;
    private String endLanchonete;


    // Contrutor 02
    public Produto(){

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
        this.preco = "R$ "+preco;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem() {

        if(this.nome.contains("coxinha") || this.nome.contains("Coxinha")){
            this.imagem = "http://kitmix.com.br/wp-content/uploads/2016/03/COXINHA-2.png";
        }

        else if(this.nome.contains("pão de queijo") || this.nome.contains("Pão de queijo")){
            this.imagem = "http://www.matulapaodequeijaria.com.br/wp-content/uploads/2017/10/comum-ok.png";
        }

        else if(this.nome.contains("suco") || this.nome.contains("Suco")){
            this.imagem = "http://ruvolo.com.br/wp-content/uploads/2015/04/80703-1.jpg";
        }

        else if(this.nome.contains("Pastel") || this.nome.contains("pastel")) {
            this.imagem = "https://static.wixstatic.com/media/f88c19_01d9b393516442e1907c49e5a7f2bfdf.gif";
        }

        else if(this.nome.contains("Kibe") || this.nome.contains("kibe")) {
            this.imagem = "https://www.habibs.com.br/storage/products/images/5_interna.png";
        }
    }

    public String getNomeLanchonete() {
        return nomeLanchonete;
    }

    public void setNomeLanchonete(String nomeLanchonete) {
        this.nomeLanchonete = "Lanchonete: " + nomeLanchonete;
    }

    public String getEndLanchonete() {
        return endLanchonete;
    }

    public void setEndLanchonete(String endLanchonete) {
        this.endLanchonete = endLanchonete;
    }

    @Override
    public int compareTo(@NonNull Produto o) {
        return 0;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }
}
