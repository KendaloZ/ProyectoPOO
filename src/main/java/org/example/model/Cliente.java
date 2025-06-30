package org.example.model;

import java.util.Date;

public class Cliente extends Persona {
    private String padecimiento;

    public Cliente(int id, String nombreCompleto, String cedula, String correo, int telefono, String direccion, Date fechaNacimiento, String genero, String padecimiento) {
        super(id, nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
        this.padecimiento = padecimiento;
    }

    public Cliente(String nombreCompleto, String cedula, String correo, int telefono, String direccion, Date fechaNacimiento, String genero, String padecimiento) {
        super(nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
        this.padecimiento = padecimiento;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }
}
