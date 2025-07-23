package view;

import org.example.misc.controller.MedicamentoController;
import org.example.misc.controller.MenuController;
import org.example.misc.model.Medicamento;



import java.util.List;
import java.util.Scanner;

public class MedicamentoView {

    private final MedicamentoController medicamentoController = new MedicamentoController();
    private final MenuController menuController = new MenuController();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        menuController.mostrarMenu(
                "Medicamento",
                this::listarMedicamentos,
                this::insertarMedicamento,
                this::editarMedicamento,
                this::eliminarMedicamento
        );
    }

    private void listarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoController.obtenerMedicamentos();
        System.out.println("\n💊 Lista de Medicamentos:");
        if (medicamentos.isEmpty()) {
            System.out.println("No hay medicamentos registrados.");
        } else {
            medicamentos.forEach(System.out::println);
        }
    }

    private void insertarMedicamento() {
        System.out.println("\n➕ Insertar Medicamento");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        boolean disponible;
        while (true) {
            System.out.print("¿Está disponible? (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                disponible = true;
                break;
            } else if (input.equals("false")) {
                disponible = false;
                break;
            } else {
                System.out.println("Por favor escriba solo 'true' o 'false'.");
            }
        }

        Medicamento medicamento = new Medicamento(nombre, disponible);
        boolean exito = medicamentoController.insertarMedicamento(medicamento);

        if (exito) {
            System.out.println("✅ Medicamento insertado correctamente.");
        } else {
            System.out.println("❌ No se pudo insertar el medicamento.");
        }
    }

    private void editarMedicamento() {
        System.out.println("\n✏️ Editar Medicamento");
        System.out.print("ID del medicamento a editar: ");
        String idInput = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: debe ingresar un número válido para el ID.");
            return;
        }

        Medicamento medicamentoExistente = medicamentoController.obtenerPorId(id);
        if (medicamentoExistente == null) {
            System.out.println("❌ No se encontró el medicamento con ID: " + id);
            return;
        }

        System.out.print("Nuevo nombre (" + medicamentoExistente.getNombre() + "): ");
        String nombre = scanner.nextLine();

        boolean disponible = medicamentoExistente.isDisponible();
        while (true) {
            System.out.print("¿Está disponible? (true/false) [" + disponible + "]: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                break;
            } else if (input.equals("true")) {
                disponible = true;
                break;
            } else if (input.equals("false")) {
                disponible = false;
                break;
            } else {
                System.out.println("Por favor escriba solo 'true' o 'false'.");
            }
        }

        medicamentoExistente.setNombre(nombre);
        medicamentoExistente.setDisponible(disponible);

        boolean exito = medicamentoController.actualizarMedicamento(medicamentoExistente);
        if (exito) {
            System.out.println("✅ Medicamento actualizado correctamente.");
        } else {
            System.out.println("❌ No se pudo actualizar el medicamento.");
        }
    }

    private void eliminarMedicamento() {
        System.out.println("\n🗑️ Eliminar Medicamento");
        System.out.print("ID del medicamento a eliminar: ");
        String idInput = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: debe ingresar un número válido.");
            return;
        }

        boolean exito = medicamentoController.eliminarMedicamento(id);
        if (exito) {
            System.out.println("✅ Medicamento eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar el medicamento.");
        }
    }
}
