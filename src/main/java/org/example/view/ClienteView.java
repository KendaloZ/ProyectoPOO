package org.example.view;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import org.example.controller.ClienteController;
import org.example.controller.MenuController;
import org.example.model.Cliente;

public class ClienteView {
    private final ClienteController clienteController = new ClienteController();
    private final MenuController menuController = new MenuController();
    private final Scanner scanner;

    public ClienteView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        this.menuController.mostrarMenu("Cliente", this::listarClientes, this::insertarCliente, this::editarCliente, this::eliminarCliente);
    }

    private void listarClientes() {
        List<Cliente> clientes = this.clienteController.obtenerClientes();
        System.out.println("\n\ud83d\udccb Lista de Clientes:");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            PrintStream var10001 = System.out;
            Objects.requireNonNull(var10001);
            clientes.forEach(var10001::println);
        }

    }

    private void insertarCliente() {
        System.out.println("\n➕ Insertar Cliente");

        try {
            System.out.print("Nombre completo: ");
            String nombre = this.scanner.nextLine();
            System.out.print("Cédula: ");
            String cedula = this.scanner.nextLine();
            System.out.print("Correo: ");
            String correo = this.scanner.nextLine();
            System.out.print("Telefono: ");
            int telefono = this.scanner.nextInt();
            this.scanner.nextLine();
            System.out.print("Direccion: ");
            String direccion = this.scanner.nextLine();
            System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
            LocalDate localDate = LocalDate.parse(this.scanner.nextLine());
            Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.print("Género: ");
            String genero = this.scanner.nextLine();
            System.out.print("Padecimiento: ");
            String padecimiento = this.scanner.nextLine();
            Cliente cliente = new Cliente(nombre, cedula, correo, telefono, direccion, fecha, genero, padecimiento);
            boolean exito = this.clienteController.insertarCliente(cliente);
            if (exito) {
                System.out.println("✅ Cliente insertado correctamente.");
            } else {
                System.out.println("❌ No se pudo insertar el cliente.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error en el formato de entrada: " + e.getMessage());
        }

    }

    private void editarCliente() {
        System.out.println("\n✏️ Editar Cliente");
        System.out.print("ID del cliente a editar: ");
        int id = Integer.parseInt(this.scanner.nextLine());
        Cliente cliente = this.clienteController.obtenerClienteXID(id);
        if (cliente == null) {
            System.out.println("❌ No se encontró el cliente con ID: " + id);
        } else {
            try {
                System.out.print("Nuevo nombre completo: (" + cliente.getNombreCompleto() + "): ");
                String nombreCompleto = this.scanner.nextLine();
                if (!nombreCompleto.isEmpty()) {
                    cliente.setNombreCompleto(nombreCompleto);
                }

                System.out.print("Nueva cedula (" + cliente.getCedula() + "): ");
                String cedula = this.scanner.nextLine();
                if (!cedula.isEmpty()) {
                    cliente.setCedula(cedula);
                }

                System.out.println("Nuevo correo (" + cliente.getCorreo() + "): ");
                String correo = this.scanner.nextLine();
                if (!correo.isEmpty()) {
                    cliente.setCorreo(correo);
                }

                System.out.println("Nuevo telefono (" + cliente.getTelefono() + "): ");
                String inputTelefono = this.scanner.nextLine();
                if (!inputTelefono.isEmpty()) {
                    int telefono = Integer.parseInt(inputTelefono);
                    cliente.setTelefono(telefono);
                }

                System.out.println("Nueva Dirección (" + cliente.getDireccion() + "): ");
                String direccion = this.scanner.nextLine();
                if (!direccion.isEmpty()) {
                    cliente.setDireccion(direccion);
                }

                System.out.print("Nueva fecha de nacimiento (" + String.valueOf(cliente.getFechaNacimiento()) + "): ");
                String input = this.scanner.nextLine();
                if (!input.isEmpty()) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fecha = sdf.parse(input);
                        cliente.setFechaNacimiento(fecha);
                    } catch (ParseException var12) {
                        System.out.println("Formato de fecha inválido. Use el formato yyyy-MM-dd.");
                    }
                }

                System.out.print("Nuevo genero: (" + cliente.getGenero() + "): ");
                String genero = this.scanner.nextLine();
                if (!genero.isEmpty()) {
                    cliente.setGenero(genero);
                }

                System.out.print("Nuevo padecimiento: (" + cliente.getPadecimiento() + "): ");
                String padecimiento = this.scanner.nextLine();
                if (!padecimiento.isEmpty()) {
                    cliente.setPadecimiento(padecimiento);
                }

                boolean exito = this.clienteController.actualizarCliente(cliente);
                if (exito) {
                    System.out.println("✅ Cliente actualizado correctamente.");
                } else {
                    System.out.println("❌ No se pudo actualizar el cliente.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error en la edición: " + e.getMessage());
            }

        }
    }

    private void eliminarCliente() {
        System.out.println("\n\ud83d\uddd1️ Eliminar Cliente");
        System.out.print("ID del cliente a eliminar: ");
        int id = Integer.parseInt(this.scanner.nextLine());
        boolean exito = this.clienteController.eliminarCliente(id);
        if (exito) {
            System.out.println("✅ Cliente eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar el cliente.");
        }

    }
}
