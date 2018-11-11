package com.una.menu.model;

public class Avaliacao {

    private String nota;
    private String comentario;
    private String cliente;

    public Avaliacao() {
    }

    //Construtor
    public Avaliacao(String nota, String comentario, String cliente) {
        this.nota = nota;
        this.comentario = comentario;
        this.cliente = cliente;
    }

    //Get and Setters
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = "Nota: " + nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = "Comentário: " + comentario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = "Autor: " + cliente;
    }


}
