package org.example.model;

import java.util.Date;

public class Especialista extends Persona {
    private String especialidad;

    public Especialista(int id, String nombreCompleto, String cedula, String correo, int telefono, String direccion, Date fechaNacimiento, String genero, String especialidad) {
        super(id, nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
        this.especialidad = especialidad;
    }

    public Especialista(String nombreCompleto, String cedula, String correo, int telefono, String direccion, Date fechaNacimiento, String genero, String especialidad) {
        super(nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String toString() {
        int var10000 = this.getId();
        return "Especialista{id=" + var10000 + ", nombreCompleto='" + this.getNombreCompleto() + "', cedula='" + this.getCedula() + "', correo='" + this.getCorreo() + "', telefono=" + this.getTelefono() + ", direccion='" + this.getDireccion() + "', fechaNacimiento=" + String.valueOf(this.getFechaNacimiento()) + ", genero='" + this.getGenero() + "', especialidad='" + this.especialidad + "'}";
    }
}
