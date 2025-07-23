package org.example.misc.controller;

import java.util.Scanner;

public class MenuController {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu(String nombreEntidad, Runnable listar, Runnable insertar, Runnable editar, Runnable eliminar) {
        mostrarMenu(nombreEntidad, listar, insertar, editar, eliminar, null);
    }

    public void mostrarMenu(String nombreEntidad, Runnable listar, Runnable insertar, Runnable editar, Runnable eliminar, Runnable extra) {
        int opcion = -1;
        boolean tieneExtra = extra != null;

        do {
            System.out.println("\n--- Gestión de " + nombreEntidad + "s ---");
            System.out.println("1. Listar " + nombreEntidad.toLowerCase() + "s");
            System.out.println("2. Insertar " + nombreEntidad.toLowerCase());
            System.out.println("3. Editar " + nombreEntidad.toLowerCase());
            System.out.println("4. Eliminar " + nombreEntidad.toLowerCase());
            if (tieneExtra) {
                System.out.println("5. Generar reporte");
                System.out.println("6. Salir");
            } else {
                System.out.println("5. Salir");
            }

            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Ingrese un número.");
                continue;
            }

            if (!tieneExtra) {
                switch (opcion) {
                    case 1 -> listar.run();
                    case 2 -> insertar.run();
                    case 3 -> editar.run();
                    case 4 -> eliminar.run();
                    case 5 -> System.out.println("Saliendo del menú de " + nombreEntidad.toLowerCase() + "s...");
                    default -> System.out.println("❌ Opción no válida.");
                }
            } else {
                switch (opcion) {
                    case 1 -> listar.run();
                    case 2 -> insertar.run();
                    case 3 -> editar.run();
                    case 4 -> eliminar.run();
                    case 5 -> extra.run();
                    case 6 -> System.out.println("Saliendo del menú de " + nombreEntidad.toLowerCase() + "s...");
                    default -> System.out.println("❌ Opción no válida.");
                }
            }
        } while ((tieneExtra && opcion != 6) || (!tieneExtra && opcion != 5));
    }
}