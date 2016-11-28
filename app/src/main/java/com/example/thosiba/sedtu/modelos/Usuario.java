package com.example.thosiba.sedtu.modelos;

/**
 * Created by thosiba on 09/11/2016.
 */

public class Usuario {

    private String email;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String calle;
    private String colonia;
    private String municipio;
    private String estado;
    private String password;
    private String new_password;
    private String old_password;
    private String hora;
    private String fecha;

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getNombre(){ return nombre; }

    public String getEmail() {
        return email;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }
}
