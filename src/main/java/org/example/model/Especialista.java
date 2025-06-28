package org.example.model;

import java.util.Date;

public class Especialista extends Persona {
    private String Especialista;

    public Especialista(int id, String nombreCompleto, String cedula, String correo, int telefono, String direccion, Date fechaNacimiento, String genero) {
        super(id, nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
    }

    public Especialista(String nombreCompleto, String cedula, String correo, int telefono, String direccion, Date fechaNacimiento, String genero) {
        super(nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
    }

    public String getEspecialista() {
        return Especialista;
    }

    public void setEspecialista(String especialista) {
        Especialista = especialista;
    }
}
