package org.example.model;

import java.util.Date;

public class Cliente extends Persona {
    private String Padecimiento;
    private Medicamento medicamento;


    public Cliente(int id, String nombreCompleto, String cedula, String correo, int telefono, String direccion, Date fechaNacimiento, String genero) {
        super(id, nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
    }

    public Cliente(String nombreCompleto, String cedula, String correo, int telefono, String direccion, Date fechaNacimiento, String genero) {
        super(nombreCompleto, cedula, correo, telefono, direccion, fechaNacimiento, genero);
    }

    public String getPadecimiento() {
        return Padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        Padecimiento = padecimiento;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}
