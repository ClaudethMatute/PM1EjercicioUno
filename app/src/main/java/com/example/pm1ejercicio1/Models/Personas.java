package com.example.pm1ejercicio1.Models;

public class Personas {
    private Integer id;
    private String nombre;
    private String apellidos;
    private Integer edad;
    private String correo;
    private String direccion;


    public Personas(Integer id, String nombre, String apellidos, Integer edad, String correo, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.direccion = direccion;

    }

    public Personas() {

    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getApellidos() {

        return apellidos;
    }

    public void setApellidos(String apellidos) {

        this.apellidos = apellidos;
    }

    public Integer getEdad() {

        return edad;
    }

    public void setEdad(Integer edad) {

        this.edad = edad;
    }

    public String getCorreo() {

        return correo;
    }

    public void setCorreo(String correo) {

        correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}