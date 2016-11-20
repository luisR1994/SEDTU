package com.example.thosiba.sedtu.modelos;

/**
 * Created by thosiba on 09/11/2016.
 */

public class ServerRequest {
    private String operation;
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
