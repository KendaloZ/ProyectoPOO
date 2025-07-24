package org.example.view;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.example.controller.MedicamentoController;
import org.example.controller.MenuController;
import org.example.model.Medicamento;

public class MedicamentoView {
    private final MedicamentoController medicamentoController = new MedicamentoController();
    private final MenuController menuController = new MenuController();
    private final Scanner scanner;

    public MedicamentoView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        this.menuController.mostrarMenu("Medicamento", this::listarMedicamentos, this::insertarMedicamento, this::editarMedicamento, this::eliminarMedicamento);
    }

    private void listarMedicamentos() {
        List<Medicamento> medicamentos = this.medicamentoController.obtenerMedicamentos();
        System.out.println("\n\ud83d\udc8a Lista de Medicamentos:");
        if (medicamentos.isEmpty()) {
            System.out.println("No hay medicamentos registrados.");
        } else {
            PrintStream var10001 = System.out;
            Objects.requireNonNull(var10001);
            medicamentos.forEach(var10001::println);
        }

    }

    private void insertarMedicamento() {
        System.out.println("\n➕ Insertar Medicamento");
        System.out.print("Nombre: ");
        String nombre = this.scanner.nextLine();

        boolean disponible;
        while(true) {
            System.out.print("¿Está disponible? (true/false): ");
            String input = this.scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                disponible = true;
                break;
            }

            if (input.equals("false")) {
                disponible = false;
                break;
            }

            System.out.println("Por favor escriba solo 'true' o 'false'.");
        }

        Medicamento medicamento = new Medicamento(nombre, disponible);
        boolean exito = this.medicamentoController.insertarMedicamento(medicamento);
        if (exito) {
            System.out.println("✅ Medicamento insertado correctamente.");
        } else {
            System.out.println("❌ No se pudo insertar el medicamento.");
        }

    }

    private void editarMedicamento() {
        System.out.println("\n✏️ Editar Medicamento");
        System.out.print("ID del medicamento a editar: ");
        String idInput = this.scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException var7) {
            System.out.println("❌ Error: debe ingresar un número válido para el ID.");
            return;
        }

        Medicamento medicamentoExistente = this.medicamentoController.obtenerPorId(id);
        if (medicamentoExistente == null) {
            System.out.println("❌ No se encontró el medicamento con ID: " + id);
        } else {
            System.out.print("Nuevo nombre (" + medicamentoExistente.getNombre() + "): ");
            String nombre = this.scanner.nextLine();
            boolean disponible = medicamentoExistente.isDisponible();

            while(true) {
                System.out.print("¿Está disponible? (true/false) [" + disponible + "]: ");
                String input = this.scanner.nextLine().trim().toLowerCase();
                if (input.isEmpty()) {
                    break;
                }

                if (input.equals("true")) {
                    disponible = true;
                    break;
                }

                if (input.equals("false")) {
                    disponible = false;
                    break;
                }

                System.out.println("Por favor escriba solo 'true' o 'false'.");
            }

            medicamentoExistente.setNombre(nombre);
            medicamentoExistente.setDisponible(disponible);
            boolean exito = this.medicamentoController.actualizarMedicamento(medicamentoExistente);
            if (exito) {
                System.out.println("✅ Medicamento actualizado correctamente.");
            } else {
                System.out.println("❌ No se pudo actualizar el medicamento.");
            }

        }
    }

    private void eliminarMedicamento() {
        System.out.println("\n\ud83d\uddd1️ Eliminar Medicamento");
        System.out.print("ID del medicamento a eliminar: ");
        String idInput = this.scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException var4) {
            System.out.println("❌ Error: debe ingresar un número válido.");
            return;
        }

        boolean exito = this.medicamentoController.eliminarMedicamento(id);
        if (exito) {
            System.out.println("✅ Medicamento eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar el medicamento.");
        }

    }
}
