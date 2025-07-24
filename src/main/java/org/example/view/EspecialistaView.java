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
import org.example.controller.EspecialistaController;
import org.example.controller.MenuController;
import org.example.model.Especialista;

public class EspecialistaView {
    private final EspecialistaController especialistaController = new EspecialistaController();
    private final MenuController menuController = new MenuController();
    private final Scanner scanner;

    public EspecialistaView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        this.menuController.mostrarMenu("Especialista", this::listarEspecialistas, this::insertarEspecialista, this::editarEspecialista, this::eliminarEspecialista);
    }

    private void listarEspecialistas() {
        List<Especialista> especialistas = this.especialistaController.obtenerEspecialistas();
        System.out.println("\n\ud83d\udccb Lista de Especialistas:");
        if (especialistas.isEmpty()) {
            System.out.println("No hay especialistas registrados.");
        } else {
            PrintStream var10001 = System.out;
            Objects.requireNonNull(var10001);
            especialistas.forEach(var10001::println);
        }

    }

    private void insertarEspecialista() {
        System.out.println("\n➕ Insertar Especialista");

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
            System.out.print("Especialidad: ");
            String especialidad = this.scanner.nextLine();
            Especialista especialista = new Especialista(nombre, cedula, correo, telefono, direccion, fecha, genero, especialidad);
            boolean exito = this.especialistaController.insertarEspecialista(especialista);
            if (exito) {
                System.out.println("✅ Especialista insertado correctamente.");
            } else {
                System.out.println("❌ No se pudo insertar el especialista.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error en el formato de entrada: " + e.getMessage());
        }

    }

    private void editarEspecialista() {
        System.out.println("\n✏️ Editar Especialista");
        System.out.print("ID del especialista a editar: ");
        int id = Integer.parseInt(this.scanner.nextLine());
        Especialista especialista = this.especialistaController.obtenerEspecialistaXID(id);
        if (especialista == null) {
            System.out.println("❌ No se encontró el especialista con ID: " + id);
        } else {
            try {
                System.out.print("Nuevo nombre completo: (" + especialista.getNombreCompleto() + "): ");
                String nombreCompleto = this.scanner.nextLine();
                if (!nombreCompleto.isEmpty()) {
                    especialista.setNombreCompleto(nombreCompleto);
                }

                System.out.print("Nueva cedula (" + especialista.getCedula() + "): ");
                String cedula = this.scanner.nextLine();
                if (!cedula.isEmpty()) {
                    especialista.setCedula(cedula);
                }

                System.out.println("Nuevo correo (" + especialista.getCorreo() + "): ");
                String correo = this.scanner.nextLine();
                if (!correo.isEmpty()) {
                    especialista.setCorreo(correo);
                }

                System.out.println("Nuevo telefono (" + especialista.getTelefono() + "): ");
                String inputTelefono = this.scanner.nextLine();
                if (!inputTelefono.isEmpty()) {
                    int telefono = Integer.parseInt(inputTelefono);
                    especialista.setTelefono(telefono);
                }

                System.out.println("Nueva Dirección (" + especialista.getDireccion() + "): ");
                String direccion = this.scanner.nextLine();
                if (!direccion.isEmpty()) {
                    especialista.setDireccion(direccion);
                }

                System.out.print("Nueva fecha de nacimiento (" + String.valueOf(especialista.getFechaNacimiento()) + "): ");
                String input = this.scanner.nextLine();
                if (!input.isEmpty()) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fecha = sdf.parse(input);
                        especialista.setFechaNacimiento(fecha);
                    } catch (ParseException var12) {
                        System.out.println("Formato de fecha inválido. Use el formato yyyy-MM-dd.");
                    }
                }

                System.out.print("Nuevo genero: (" + especialista.getGenero() + "): ");
                String genero = this.scanner.nextLine();
                if (!genero.isEmpty()) {
                    especialista.setGenero(genero);
                }

                System.out.print("Nueva especialidad: (" + especialista.getEspecialidad() + "): ");
                String especialidad = this.scanner.nextLine();
                if (!especialidad.isEmpty()) {
                    especialista.setEspecialidad(especialidad);
                }

                boolean exito = this.especialistaController.actualizarEspecialista(especialista);
                if (exito) {
                    System.out.println("✅ Especialista actualizado correctamente.");
                } else {
                    System.out.println("❌ No se pudo actualizar el especialista.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error en la edición: " + e.getMessage());
            }

        }
    }

    private void eliminarEspecialista() {
        System.out.println("\n\ud83d\uddd1️ Eliminar Especialista");
        System.out.print("ID del especialista a eliminar: ");
        int id = Integer.parseInt(this.scanner.nextLine());
        boolean exito = this.especialistaController.eliminarEspecialista(id);
        if (exito) {
            System.out.println("✅ Especialista eliminado correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar el especialista.");
        }

    }
}
