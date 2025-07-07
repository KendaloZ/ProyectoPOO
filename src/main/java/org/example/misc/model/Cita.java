package org.example.misc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cita {
    private int id;
    private LocalDate fecha;
    private LocalDateTime hora;
    private int idCliente;
    private int idEspecialista;
    private String motivo;

    public Cita() {}

    public Cita(int id, LocalDate fecha, LocalDateTime hora, int idCliente, int idEspecialista, String motivo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
        this.idEspecialista = idEspecialista;
        this.motivo = motivo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(int idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", idCliente=" + idCliente +
                ", idEspecialista=" + idEspecialista +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
