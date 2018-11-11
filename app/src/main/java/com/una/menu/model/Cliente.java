package com.una.menu.model;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String id;
    private String nome;
    private String dataNascimento;
    private String sexo;
    private String email;

    public Cliente() {

    }

    public String getId_cliente() {
        return id;
    }

    public void setId_cliente(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
