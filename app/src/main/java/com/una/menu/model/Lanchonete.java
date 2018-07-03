package com.una.menu.model;

import java.io.Serializable;

public class Lanchonete implements Serializable {

    // Atributos
    private String id_lanchonete;
    private String nome;
    private String telefone;
    private String celular;
    private String cep;
    private String endereco;
    private String cidade;
    private String estado;
    private String id_pagamento;
    private String id_cliente;

    // Construtor
    public Lanchonete() {

    }

    // Getters and Setters
    public String getId_lanchonete() {
        return id_lanchonete;
    }

    public void setId_lanchonete(String id_lanchonete) {
        this.id_lanchonete = id_lanchonete;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(String id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
}
