package org.example.model;

public class Medicamento {
    private int id;
    private String nombre;
    private boolean disponible;

    public Medicamento() {
    }

    public Medicamento(int id, String nombre, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.disponible = disponible;
    }

    public Medicamento(String nombre, boolean disponible) {
        this.nombre = nombre;
        this.disponible = disponible;
    }

    public Medicamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return this.disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String toString() {
        return "Medicamento{id=" + this.id + ", nombre='" + this.nombre + "', disponible=" + this.disponible + "}";
    }
}
