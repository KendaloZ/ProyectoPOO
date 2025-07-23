package org.example.misc.model;

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


    public Visita() {}

    public Visita(int id, LocalDate fecha, LocalDateTime horaEntrada, LocalDateTime horaSalida, String diagnostico, int idCliente, int idEspecialista) {
        this.id = id;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.diagnostico = diagnostico;
        this.idEspecialista = idEspecialista;
        this.idCliente = idCliente;
    }

    public Visita(LocalDate fecha, LocalDateTime horaEntrada, LocalDateTime horaSalida,
                  String diagnostico, int idCliente, int idEspecialista) {
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.diagnostico = diagnostico;
        this.idCliente = idCliente;
        this.idEspecialista = idEspecialista;
    }

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

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
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

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void mostrarVisita() {
        System.out.println("ID: " + id);
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora de entrada: " + horaEntrada);
        System.out.println("Hora de salida: " + horaSalida);
        System.out.println("Diagnóstico: " + diagnostico);
        System.out.println("ID Cliente: " + idCliente);
        System.out.println("ID Especialista: " + idEspecialista);
    }
}