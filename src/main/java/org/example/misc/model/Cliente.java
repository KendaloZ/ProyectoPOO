package org.example.misc.model;

import java.time.LocalDate;

public class Cliente extends Persona {
    private String padecimiento;

    public Cliente(int id, String nombreCompleto, String cedula, String correo, String telefono,
                   String direccion, LocalDate fechaNacimiento, String genero, String padecimiento) {
        super(id, nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
        this.padecimiento = padecimiento;
    }

    // Getters y setters
    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }
}
