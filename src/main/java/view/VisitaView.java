package view;

import org.example.misc.controller.MenuController;
import org.example.misc.controller.VisitaController;
import org.example.misc.model.Visita;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VisitaView {

    private final VisitaController visitaController = new VisitaController();
    private final MenuController menuController = new MenuController();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        menuController.mostrarMenu(
                "Visita",
                this::listarVisitas,
                this::insertarVisita,
                this::editarVisita,
                this::eliminarVisita,
                this::generarReporte
        );
    }

    private void listarVisitas() {
        List<Visita> visitas = visitaController.obtenerVisitas();
        System.out.println("\n📋 Lista de Visitas:");
        if (visitas.isEmpty()) {
            System.out.println("No hay visitas registradas.");
        } else {
            visitas.forEach(System.out::println);
        }
    }

    private void insertarVisita() {
        System.out.println("\n➕ Insertar Visita");

        try {
            System.out.print("Fecha (yyyy-MM-dd): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            System.out.print("Hora entrada (HH:mm, ej. 07:00): ");
            LocalTime horaEntrada = LocalTime.parse(scanner.nextLine());

            System.out.print("Hora salida (HH:mm, ej. 17:00): ");
            LocalTime horaSalida = LocalTime.parse(scanner.nextLine());

            System.out.print("Diagnóstico: ");
            String diagnostico = scanner.nextLine();

            System.out.print("ID Cliente: ");
            int idCliente = Integer.parseInt(scanner.nextLine());

            System.out.print("ID Especialista: ");
            int idEspecialista = Integer.parseInt(scanner.nextLine());

            LocalDateTime entrada = LocalDateTime.of(fecha, horaEntrada);
            LocalDateTime salida = LocalDateTime.of(fecha, horaSalida);

            Visita visita = new Visita(fecha, entrada, salida, diagnostico, idCliente, idEspecialista);
            boolean exito = visitaController.insertarVisita(visita);

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
        int id = Integer.parseInt(scanner.nextLine());

        Visita visita = visitaController.obtenerPorId(id);
        if (visita == null) {
            System.out.println("❌ No se encontró la visita con ID: " + id);
            return;
        }

        try {
            System.out.print("Nueva fecha (" + visita.getFecha() + "): ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) visita.setFecha(LocalDate.parse(input));

            System.out.print("Nueva hora entrada (HH:mm) [" + visita.getHoraEntrada().toLocalTime() + "]: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                LocalTime nuevaEntrada = LocalTime.parse(input);
                visita.setHoraEntrada(LocalDateTime.of(visita.getFecha(), nuevaEntrada));
            }

            System.out.print("Nueva hora salida (HH:mm) [" + visita.getHoraSalida().toLocalTime() + "]: ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                LocalTime nuevaSalida = LocalTime.parse(input);
                visita.setHoraSalida(LocalDateTime.of(visita.getFecha(), nuevaSalida));
            }

            System.out.print("Nuevo diagnóstico (" + visita.getDiagnostico() + "): ");
            input = scanner.nextLine();
            if (!input.isEmpty()) visita.setDiagnostico(input);

            System.out.print("Nuevo ID Cliente (" + visita.getIdCliente() + "): ");
            input = scanner.nextLine();
            if (!input.isEmpty()) visita.setIdCliente(Integer.parseInt(input));

            System.out.print("Nuevo ID Especialista (" + visita.getIdEspecialista() + "): ");
            input = scanner.nextLine();
            if (!input.isEmpty()) visita.setIdEspecialista(Integer.parseInt(input));

            boolean exito = visitaController.actualizarVisita(visita);
            if (exito) {
                System.out.println("✅ Visita actualizada correctamente.");
            } else {
                System.out.println("❌ No se pudo actualizar la visita.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error en la edición: " + e.getMessage());
        }
    }

    private void eliminarVisita() {
        System.out.println("\n🗑️ Eliminar Visita");
        System.out.print("ID de la visita a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean exito = visitaController.eliminarVisita(id);
        if (exito) {
            System.out.println("✅ Visita eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar la visita.");
        }
    }

    private void generarReporte() {
        System.out.println("\n📝 Generar Reporte de Visita");
        System.out.print("ID de la visita: ");
        int id = Integer.parseInt(scanner.nextLine());

        Visita visita = visitaController.obtenerPorId(id);
        if (visita == null) {
            System.out.println("❌ No se encontró la visita con ID: " + id);
            return;
        }

        // Obtener datos relacionados desde el controller
        String nombreCliente = visitaController.getNombreCliente(visita.getIdCliente());
        String nombreEspecialista = visitaController.getNombreEspecialista(visita.getIdEspecialista());
        List<String> medicamentos = visitaController.getMedicamentosPorVisita(visita.getId());
        String medicamentosTexto = medicamentos.isEmpty() ? "Ninguno" : String.join(", ", medicamentos);

        // Formato y escritura del archivo
        String nombreArchivo = "reporte_visita_" + id + ".txt";

        DateTimeFormatter horaFormato = DateTimeFormatter.ofPattern("h:mm a");

        String contenido = String.format("""
                Reporte de cita de cliente %s
                Fecha: %s
                Hora de Entrada: %s
                Hora de Salida: %s
                Nombre de profesional: %s
                Nombre de Cliente: %s
                Medicamentos asignados: %s
                Diagnóstico: %s
                """,
                nombreCliente,
                visita.getFecha().format(DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy")),
                visita.getHoraEntrada().format(horaFormato),
                visita.getHoraSalida().format(horaFormato),
                nombreEspecialista,
                nombreCliente,
                medicamentosTexto,
                visita.getDiagnostico()
        );

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(contenido);
            System.out.println("✅ Reporte generado correctamente: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("❌ Error al generar el archivo: " + e.getMessage());
        }
    }
}
