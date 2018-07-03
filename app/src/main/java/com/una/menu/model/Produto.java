package com.una.menu.model;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import com.una.menu.R;
import com.una.menu.fragment.ProdutoFragment;

public class Produto implements Comparable<Produto> {

    // Atributos
    private String id_produto;
    private String nome;
    private String descricao;
    private String preco;
    private String avaliacao;
    private String imagem;

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

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem() {

        if(this.nome.contains("coxinha") || this.nome.contains("Coxinha")){
            this.imagem = "http://kitmix.com.br/wp-content/uploads/2016/03/COXINHA-2.png";
        }

        if(this.nome.contains("pão de queijo") || this.nome.contains("Pão de queijo")){
            this.imagem = "http://www.matulapaodequeijaria.com.br/wp-content/uploads/2017/10/comum-ok.png";
        }

        if(this.nome.contains("suco") || this.nome.contains("Suco")){
            this.imagem = "http://ruvolo.com.br/wp-content/uploads/2015/04/80703-1.jpg";
        }
    }

    @Override
    public int compareTo(@NonNull Produto o) {
        return 0;
    }


}
