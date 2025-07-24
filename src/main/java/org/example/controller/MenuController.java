//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.controller;

import java.util.Scanner;

public class MenuController {
    private final Scanner scanner;

    public MenuController() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(String nombreEntidad, Runnable listar, Runnable insertar, Runnable editar, Runnable eliminar) {
        this.mostrarMenu(nombreEntidad, listar, insertar, editar, eliminar, (Runnable)null, (Runnable)null);
    }

    public void mostrarMenu(String nombreEntidad, Runnable listar, Runnable insertar, Runnable editar, Runnable eliminar, Runnable extra1) {
        this.mostrarMenu(nombreEntidad, listar, insertar, editar, eliminar, extra1, (Runnable)null);
    }

    public void mostrarMenu(String nombreEntidad, Runnable listar, Runnable insertar, Runnable editar, Runnable eliminar, Runnable extra1, Runnable extra2) {
        int opcion = -1;
        boolean tieneExtra1 = extra1 != null;
        boolean tieneExtra2 = extra2 != null;

        do {
            System.out.println("\n--- Gestión de " + nombreEntidad + "s ---");
            System.out.println("1. Listar " + nombreEntidad.toLowerCase() + "s");
            System.out.println("2. Insertar " + nombreEntidad.toLowerCase());
            System.out.println("3. Editar " + nombreEntidad.toLowerCase());
            System.out.println("4. Eliminar " + nombreEntidad.toLowerCase());
            int opcionSalida = 5;
            if (tieneExtra1) {
                System.out.println("5. Asignar medicamento");
                ++opcionSalida;
            }

            if (tieneExtra2) {
                System.out.println(opcionSalida + ". Generar reporte");
                ++opcionSalida;
            }

            System.out.println(opcionSalida + ". Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException var13) {
                System.out.println("❌ Entrada inválida. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    listar.run();
                    break;
                case 2:
                    insertar.run();
                    break;
                case 3:
                    editar.run();
                    break;
                case 4:
                    eliminar.run();
                    break;
                case 5:
                    if (tieneExtra1) {
                        extra1.run();
                    } else {
                        System.out.println("Saliendo del menú de " + nombreEntidad.toLowerCase() + "s...");
                        opcion = opcionSalida;
                    }
                    break;
                case 6:
                    if (tieneExtra1 && tieneExtra2) {
                        extra2.run();
                        break;
                    }

                    System.out.println("Saliendo del menú de " + nombreEntidad.toLowerCase() + "s...");
                    opcion = opcionSalida;
                    break;
                case 7:
                    if (tieneExtra1 && tieneExtra2) {
                        System.out.println("Saliendo del menú de " + nombreEntidad.toLowerCase() + "s...");
                        break;
                    }

                    System.out.println("❌ Opción no válida.");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while(tieneExtra2 && opcion != 7 || tieneExtra1 && !tieneExtra2 && opcion != 6 || !tieneExtra1 && opcion != 5);

    }
}
