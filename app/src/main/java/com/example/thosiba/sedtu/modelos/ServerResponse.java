package com.example.thosiba.sedtu.modelos;

/**
 * Created by thosiba on 09/11/2016.
 */

public class ServerResponse {
    private String result;
    private String message;
    private Usuario usuario;

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }

    public Usuario getUsuario(){
        return usuario;
    }
}
