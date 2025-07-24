package org.example.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.example.controller.MenuController;
import org.example.controller.VisitaController;
import org.example.controller.VisitaMedicamentoController;
import org.example.model.Visita;

public class VisitaView {
    private final VisitaController visitaController = new VisitaController();
    private final MenuController menuController = new MenuController();
    private final Scanner scanner;

    public VisitaView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        this.menuController.mostrarMenu("Visita", this::listarVisitas, this::insertarVisita, this::editarVisita, this::eliminarVisita, this::asignarMedicamentoAVisita, this::generarReporte);
    }

    private void listarVisitas() {
        List<Visita> visitas = this.visitaController.obtenerVisitas();
        System.out.println("\n\ud83d\udccb Lista de Visitas:");
        if (visitas.isEmpty()) {
            System.out.println("No hay visitas registradas.");
        } else {
            PrintStream var10001 = System.out;
            Objects.requireNonNull(var10001);
            visitas.forEach(var10001::println);
        }

    }

    private void insertarVisita() {
        System.out.println("\n➕ Insertar Visita");

        try {
            System.out.print("Fecha (yyyy-MM-dd): ");
            LocalDate fecha = LocalDate.parse(this.scanner.nextLine());
            System.out.print("Hora entrada (HH:mm, ej. 07:00): ");
            LocalTime horaEntrada = LocalTime.parse(this.scanner.nextLine());
            System.out.print("Hora salida (HH:mm, ej. 17:00): ");
            LocalTime horaSalida = LocalTime.parse(this.scanner.nextLine());
            System.out.print("Diagnóstico: ");
            String diagnostico = this.scanner.nextLine();
            System.out.print("ID Cliente: ");
            int idCliente = Integer.parseInt(this.scanner.nextLine());
            System.out.print("ID Especialista: ");
            int idEspecialista = Integer.parseInt(this.scanner.nextLine());
            LocalDateTime entrada = LocalDateTime.of(fecha, horaEntrada);
            LocalDateTime salida = LocalDateTime.of(fecha, horaSalida);
            Visita visita = new Visita(fecha, entrada, salida, diagnostico, idCliente, idEspecialista);
            boolean exito = this.visitaController.insertarVisita(visita);
            if (exito) {
                System.out.println("✅ Visita insertada correctamente.");
            } else {
                System.out.println("❌ No se pudo insertar la visita.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error en el formato de entrada: " + e.getMessage());
        }

    }

    private void editarVisita() {
        System.out.println("\n✏️ Editar Visita");
        System.out.print("ID de la visita a editar: ");
        int id = Integer.parseInt(this.scanner.nextLine());
        Visita visita = this.visitaController.obtenerPorId(id);
        if (visita == null) {
            System.out.println("❌ No se encontró la visita con ID: " + id);
        } else {
            try {
                System.out.print("Nueva fecha (" + String.valueOf(visita.getFecha()) + "): ");
                String input = this.scanner.nextLine();
                if (!input.isEmpty()) {
                    visita.setFecha(LocalDate.parse(input));
                }

                System.out.print("Nueva hora entrada (HH:mm) [" + String.valueOf(visita.getHoraEntrada().toLocalTime()) + "]: ");
                input = this.scanner.nextLine();
                if (!input.isEmpty()) {
                    LocalTime nuevaEntrada = LocalTime.parse(input);
                    visita.setHoraEntrada(LocalDateTime.of(visita.getFecha(), nuevaEntrada));
                }

                System.out.print("Nueva hora salida (HH:mm) [" + String.valueOf(visita.getHoraSalida().toLocalTime()) + "]: ");
                input = this.scanner.nextLine();
                if (!input.isEmpty()) {
                    LocalTime nuevaSalida = LocalTime.parse(input);
                    visita.setHoraSalida(LocalDateTime.of(visita.getFecha(), nuevaSalida));
                }

                System.out.print("Nuevo diagnóstico (" + visita.getDiagnostico() + "): ");
                input = this.scanner.nextLine();
                if (!input.isEmpty()) {
                    visita.setDiagnostico(input);
                }

                System.out.print("Nuevo ID Cliente (" + visita.getIdCliente() + "): ");
                input = this.scanner.nextLine();
                if (!input.isEmpty()) {
                    visita.setIdCliente(Integer.parseInt(input));
                }

                System.out.print("Nuevo ID Especialista (" + visita.getIdEspecialista() + "): ");
                input = this.scanner.nextLine();
                if (!input.isEmpty()) {
                    visita.setIdEspecialista(Integer.parseInt(input));
                }

                boolean exito = this.visitaController.actualizarVisita(visita);
                if (exito) {
                    System.out.println("✅ Visita actualizada correctamente.");
                } else {
                    System.out.println("❌ No se pudo actualizar la visita.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error en la edición: " + e.getMessage());
            }

        }
    }

    private void eliminarVisita() {
        System.out.println("\n\ud83d\uddd1️ Eliminar Visita");
        System.out.print("ID de la visita a eliminar: ");
        int id = Integer.parseInt(this.scanner.nextLine());
        boolean exito = this.visitaController.eliminarVisita(id);
        if (exito) {
            System.out.println("✅ Visita eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar la visita.");
        }

    }

    public void asignarMedicamentoAVisita() {
        Scanner scanner = new Scanner(System.in);
        VisitaMedicamentoController controller = new VisitaMedicamentoController();
        System.out.println("\n\ud83d\udc8a Asignar medicamento a visita");
        System.out.print("ID de la visita: ");
        int idVisita = Integer.parseInt(scanner.nextLine());
        System.out.print("ID del medicamento: ");
        int idMedicamento = Integer.parseInt(scanner.nextLine());
        controller.asociarMedicamentoVisita(idVisita, idMedicamento);
        System.out.println("✅ Medicamento asignado a la visita correctamente.");
    }

    private void generarReporte() {
        System.out.println("\n\ud83d\udcdd Generar Reporte de Visita");
        System.out.print("ID de la visita: ");
        int id = Integer.parseInt(this.scanner.nextLine());
        Visita visita = this.visitaController.obtenerPorId(id);
        if (visita == null) {
            System.out.println("❌ No se encontró la visita con ID: " + id);
        } else {
            String nombreCliente = this.visitaController.getNombreCliente(visita.getIdCliente());
            String nombreEspecialista = this.visitaController.getNombreEspecialista(visita.getIdEspecialista());
            List<String> medicamentos = this.visitaController.getMedicamentosPorVisita(visita.getId());
            String medicamentosTexto = medicamentos.isEmpty() ? "Ninguno" : String.join(", ", medicamentos);
            String nombreArchivo = "reporte_visita_" + id + ".txt";
            DateTimeFormatter horaFormato = DateTimeFormatter.ofPattern("h:mm a");
            String contenido = String.format("Reporte de cita de cliente %s\nFecha: %s\nHora de Entrada: %s\nHora de Salida: %s\nNombre de profesional: %s\nNombre de Cliente: %s\nMedicamentos asignados: %s\nDiagnóstico: %s\n", nombreCliente, visita.getFecha().format(DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy")), visita.getHoraEntrada().format(horaFormato), visita.getHoraSalida().format(horaFormato), nombreEspecialista, nombreCliente, medicamentosTexto, visita.getDiagnostico());

            try (FileWriter writer = new FileWriter(nombreArchivo)) {
                writer.write(contenido);
                System.out.println("✅ Reporte generado correctamente: " + nombreArchivo);
            } catch (IOException e) {
                System.out.println("❌ Error al generar el archivo: " + e.getMessage());
            }

        }
    }
}