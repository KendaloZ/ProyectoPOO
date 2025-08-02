package org.example.model;


import java.sql.Date;


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

    public Cliente() {
        super();
    }

    public String getPadecimiento() {
        return this.padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public String toString() {
        int var10000 = this.getId();
        return "Cliente{id=" + var10000 + ", nombreCompleto='" + this.getNombreCompleto() + "', cedula='" + this.getCedula() + "', correo='" + this.getCorreo() + "', telefono=" + this.getTelefono() + ", direccion='" + this.getDireccion() + "', fechaNacimiento=" + String.valueOf(this.getFechaNacimiento()) + ", genero='" + this.getGenero() + "', padecimiento='" + this.padecimiento + "'}";
    }
}

