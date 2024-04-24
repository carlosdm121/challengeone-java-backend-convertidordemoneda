import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorTasas conversor = new ConversorTasas("bf87f2355c6bf59806e87582");

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    conversor.convertirMoneda("USD", "ARS");
                    break;
                case 2:
                    conversor.convertirMoneda("ARS", "USD");
                    break;
                case 3:
                    conversor.convertirMoneda("USD", "BRL");
                    break;
                case 4:
                    conversor.convertirMoneda("BRL", "USD");
                    break;
                case 5:
                    conversor.convertirMoneda("USD", "COP");
                    break;
                case 6:
                    conversor.convertirMoneda("COP", "USD");
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 1 al 7.");
            }
        }
        System.out.println("Gracias por utilizar nuestro conversor de divisas. ¡Hasta luego!");
    }

    private static void mostrarMenu() {
        System.out.println("=== Conversor de Monedas ===");
        System.out.println("1. Convertir USD a ARS");
        System.out.println("2. Convertir ARS a USD");
        System.out.println("3. Convertir USD a BRL");
        System.out.println("4. Convertir BRL a USD");
        System.out.println("5. Convertir USD a COP");
        System.out.println("6. Convertir COP a USD");
        System.out.println("7. Salir");
        System.out.print("Por favor, elige una opción: ");
    }
}
