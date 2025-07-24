package org.example.view;


import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.example.controller.CitaController;
import org.example.controller.MenuController;
import org.example.model.Cita;

public class CitaView {
    private final CitaController citaController = new CitaController();
    private final MenuController menuController = new MenuController();
    private final Scanner scanner;

    public CitaView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        this.menuController.mostrarMenu("Cita", this::listarCitas, this::insertarCita, this::editarCita, this::eliminarCita);
    }

    private void listarCitas() {
        List<Cita> citas = this.citaController.obtenerCitas();
        System.out.println("\n\ud83d\udcc5 Lista de Citas:");
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            PrintStream var10001 = System.out;
            Objects.requireNonNull(var10001);
            citas.forEach(var10001::println);
        }

    }

    private void insertarCita() {
        System.out.println("\n➕ Insertar Cita");

        try {
            System.out.print("Fecha (yyyy-MM-dd): ");
            LocalDate fecha = LocalDate.parse(this.scanner.nextLine());
            System.out.print("Hora (HH:mm): ");
            LocalTime hora = LocalTime.parse(this.scanner.nextLine());
            System.out.print("ID Cliente: ");
            int idCliente = Integer.parseInt(this.scanner.nextLine());
            System.out.print("ID Especialista: ");
            int idEspecialista = Integer.parseInt(this.scanner.nextLine());
            System.out.print("Motivo: ");
            String motivo = this.scanner.nextLine();
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            Cita cita = new Cita(fecha, fechaHora, idCliente, idEspecialista, motivo);
            boolean exito = this.citaController.insertarCita(cita);
            if (exito) {
                System.out.println("✅ Cita insertada correctamente.");
            } else {
                System.out.println("❌ No se pudo insertar la cita.");
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println("❌ Error en el formato de entrada: " + ((RuntimeException)e).getMessage());
        }

    }

    private void editarCita() {
        System.out.println("\n✏️ Editar Cita");
        System.out.print("ID de la cita a editar: ");
        String idInput = this.scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException var11) {
            System.out.println("❌ Error: debe ingresar un número válido.");
            return;
        }

        Cita citaExistente = this.citaController.obtenerPorId(id);
        if (citaExistente == null) {
            System.out.println("❌ No se encontró la cita con ID: " + id);
        } else {
            try {
                System.out.print("Nueva fecha (" + String.valueOf(citaExistente.getFecha()) + "): ");
                String fechaInput = this.scanner.nextLine();
                if (!fechaInput.isEmpty()) {
                    citaExistente.setFecha(LocalDate.parse(fechaInput));
                }

                System.out.print("Nueva hora (HH:mm) [" + String.valueOf(citaExistente.getHora().toLocalTime()) + "]: ");
                String horaInput = this.scanner.nextLine();
                if (!horaInput.isEmpty()) {
                    LocalTime nuevaHora = LocalTime.parse(horaInput);
                    citaExistente.setHora(LocalDateTime.of(citaExistente.getFecha(), nuevaHora));
                }

                System.out.print("Nuevo ID Cliente (" + citaExistente.getIdCliente() + "): ");
                String idClienteInput = this.scanner.nextLine();
                if (!idClienteInput.isEmpty()) {
                    citaExistente.setIdCliente(Integer.parseInt(idClienteInput));
                }

                System.out.print("Nuevo ID Especialista (" + citaExistente.getIdEspecialista() + "): ");
                String idEspecialistaInput = this.scanner.nextLine();
                if (!idEspecialistaInput.isEmpty()) {
                    citaExistente.setIdEspecialista(Integer.parseInt(idEspecialistaInput));
                }

                System.out.print("Nuevo motivo (" + citaExistente.getMotivo() + "): ");
                String motivoInput = this.scanner.nextLine();
                if (!motivoInput.isEmpty()) {
                    citaExistente.setMotivo(motivoInput);
                }

                boolean exito = this.citaController.actualizarCita(citaExistente);
                if (exito) {
                    System.out.println("✅ Cita actualizada correctamente.");
                } else {
                    System.out.println("❌ No se pudo actualizar la cita.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error al actualizar: " + e.getMessage());
            }

        }
    }

    private void eliminarCita() {
        System.out.println("\n\ud83d\uddd1️ Eliminar Cita");
        System.out.print("ID de la cita a eliminar: ");
        String idInput = this.scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException var4) {
            System.out.println("❌ Error: debe ingresar un número válido.");
            return;
        }

        boolean exito = this.citaController.eliminarCita(id);
        if (exito) {
            System.out.println("✅ Cita eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar la cita.");
        }

    }
}
