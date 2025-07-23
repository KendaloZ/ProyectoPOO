package view;

import java.util.Scanner;

public class View {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;

        do {
            System.out.println("\n Gestión Operativa Hospital Sanzón");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Especialistas");
            System.out.println("2. Clientes");
            System.out.println("3. Gestionar Medicamentos");
            System.out.println("4. Gestionar Citas");
            System.out.println("5. Gestionar Visitas");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> System.out.println("1");
                case 2 -> System.out.println("2");
                case 3 -> new MedicamentoView().mostrarMenu();
                case 4 -> new CitaView().mostrarMenu();
                case 5 -> new VisitaView().mostrarMenu();
                case 6 -> System.out.println("👋 Gracias por usar el programa. ¡Hasta luego!");
                default -> System.out.println("❌ Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }
}

