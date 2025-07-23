package org.example.misc.model;

import java.time.LocalDate;

public class Especialista extends Persona {
    private String especialidad;

    public Especialista(int id, String nombreCompleto, String cedula, String correo, String telefono,
                        String direccion, LocalDate fechaNacimiento, String genero, String especialidad) {
        super(id, nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
        this.especialidad = especialidad;
    }

    // Getters y setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}