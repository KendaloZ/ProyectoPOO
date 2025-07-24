package org.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cita {
    private int id;
    private LocalDate fecha;
    private LocalDateTime hora;
    private int idCliente;
    private int idEspecialista;
    private String motivo;

    public Cita() {
    }

    public Cita(LocalDate fecha, LocalDateTime hora, int idCliente, int idEspecialista, String motivo) {
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
        this.idEspecialista = idEspecialista;
        this.motivo = motivo;
    }

    public Cita(int id, LocalDate fecha, LocalDateTime hora, int idCliente, int idEspecialista, String motivo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
        this.idEspecialista = idEspecialista;
        this.motivo = motivo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getHora() {
        return this.hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEspecialista() {
        return this.idEspecialista;
    }

    public void setIdEspecialista(int idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String toString() {
        int var10000 = this.id;
        return "Cita{id=" + var10000 + ", fecha=" + String.valueOf(this.fecha) + ", hora=" + String.valueOf(this.hora) + ", idCliente=" + this.idCliente + ", idEspecialista=" + this.idEspecialista + ", motivo='" + this.motivo + "'}";
    }
}
