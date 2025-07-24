package org.example.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Visita {
    private int id;
    private LocalDate fecha;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private String diagnostico;
    private int idCliente;
    private int idEspecialista;
    private List<Medicamento> medicamentos;

    public Visita() {
    }

    public Visita(int id, LocalDate fecha, LocalDateTime horaEntrada, LocalDateTime horaSalida, String diagnostico, int idCliente, int idEspecialista) {
        this.id = id;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.diagnostico = diagnostico;
        this.idEspecialista = idEspecialista;
        this.idCliente = idCliente;
    }

    public Visita(LocalDate fecha, LocalDateTime horaEntrada, LocalDateTime horaSalida, String diagnostico, int idCliente, int idEspecialista) {
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.diagnostico = diagnostico;
        this.idCliente = idCliente;
        this.idEspecialista = idEspecialista;
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

    public LocalDateTime getHoraEntrada() {
        return this.horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return this.horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDiagnostico() {
        return this.diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
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

    public List<Medicamento> getMedicamentos() {
        return this.medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void mostrarVisita() {
        System.out.println("ID: " + this.id);
        System.out.println("Fecha: " + String.valueOf(this.fecha));
        System.out.println("Hora de entrada: " + String.valueOf(this.horaEntrada));
        System.out.println("Hora de salida: " + String.valueOf(this.horaSalida));
        System.out.println("Diagnóstico: " + this.diagnostico);
        System.out.println("ID Cliente: " + this.idCliente);
        System.out.println("ID Especialista: " + this.idEspecialista);
    }

    public String toString() {
        int var10000 = this.id;
        return "Visita{id=" + var10000 + ", fecha=" + String.valueOf(this.fecha) + ", hora entrada=" + String.valueOf(this.horaEntrada) + ", hora salida=" + String.valueOf(this.horaSalida) + ", idCliente=" + this.idCliente + ", idEspecialista=" + this.idEspecialista + ", diagnostico='" + this.diagnostico + "'}";
    }
}