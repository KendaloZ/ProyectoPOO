package view;

import org.example.misc.controller.CitaController;
import org.example.misc.controller.MenuController;
import org.example.misc.model.Cita;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


public class CitaView {

    private final CitaController citaController = new CitaController();
    private final MenuController menuController = new MenuController();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        menuController.mostrarMenu(
                "Cita",
                this::listarCitas,
                this::insertarCita,
                this::editarCita,
                this::eliminarCita
        );
    }

    private void listarCitas() {
        List<Cita> citas = citaController.obtenerCitas();
        System.out.println("\n📅 Lista de Citas:");
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            citas.forEach(System.out::println);
        }
    }

    private void insertarCita() {
        System.out.println("\n➕ Insertar Cita");

        try {
            System.out.print("Fecha (yyyy-MM-dd): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            System.out.print("Hora (HH:mm): ");
            LocalTime hora = LocalTime.parse(scanner.nextLine());

            System.out.print("ID Cliente: ");
            int idCliente = Integer.parseInt(scanner.nextLine());

            System.out.print("ID Especialista: ");
            int idEspecialista = Integer.parseInt(scanner.nextLine());

            System.out.print("Motivo: ");
            String motivo = scanner.nextLine();

            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            Cita cita = new Cita(fecha, fechaHora, idCliente, idEspecialista, motivo);
            boolean exito = citaController.insertarCita(cita);

            if (exito) {
                System.out.println("✅ Cita insertada correctamente.");
            } else {
                System.out.println("❌ No se pudo insertar la cita.");
            }

        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("❌ Error en el formato de entrada: " + e.getMessage());
        }
    }

    private void editarCita() {
        System.out.println("\n✏️ Editar Cita");
        System.out.print("ID de la cita a editar: ");
        String idInput = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: debe ingresar un número válido.");
            return;
        }

        Cita citaExistente = citaController.obtenerPorId(id);
        if (citaExistente == null) {
            System.out.println("❌ No se encontró la cita con ID: " + id);
            return;
        }

        try {
            System.out.print("Nueva fecha (" + citaExistente.getFecha() + "): ");
            String fechaInput = scanner.nextLine();
            if (!fechaInput.isEmpty()) {
                citaExistente.setFecha(LocalDate.parse(fechaInput));
            }

            System.out.print("Nueva hora (HH:mm) [" + citaExistente.getHora().toLocalTime() + "]: ");
            String horaInput = scanner.nextLine();
            if (!horaInput.isEmpty()) {
                LocalTime nuevaHora = LocalTime.parse(horaInput);
                citaExistente.setHora(LocalDateTime.of(citaExistente.getFecha(), nuevaHora));
            }

            System.out.print("Nuevo ID Cliente (" + citaExistente.getIdCliente() + "): ");
            String idClienteInput = scanner.nextLine();
            if (!idClienteInput.isEmpty()) {
                citaExistente.setIdCliente(Integer.parseInt(idClienteInput));
            }

            System.out.print("Nuevo ID Especialista (" + citaExistente.getIdEspecialista() + "): ");
            String idEspecialistaInput = scanner.nextLine();
            if (!idEspecialistaInput.isEmpty()) {
                citaExistente.setIdEspecialista(Integer.parseInt(idEspecialistaInput));
            }

            System.out.print("Nuevo motivo (" + citaExistente.getMotivo() + "): ");
            String motivoInput = scanner.nextLine();
            if (!motivoInput.isEmpty()) {
                citaExistente.setMotivo(motivoInput);
            }

            boolean exito = citaController.actualizarCita(citaExistente);
            if (exito) {
                System.out.println("✅ Cita actualizada correctamente.");
            } else {
                System.out.println("❌ No se pudo actualizar la cita.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar: " + e.getMessage());
        }
    }

    private void eliminarCita() {
        System.out.println("\n🗑️ Eliminar Cita");
        System.out.print("ID de la cita a eliminar: ");
        String idInput = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: debe ingresar un número válido.");
            return;
        }

        boolean exito = citaController.eliminarCita(id);
        if (exito) {
            System.out.println("✅ Cita eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar la cita.");
        }
    }
}
