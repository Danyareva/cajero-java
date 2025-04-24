import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CajeroAutomatico {
    private static final String ARCHIVO_SALDO = "saldo.dat";
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Verificar si el archivo de saldo existe, si no, crearlo con un saldo inicial
        inicializarArchivo();
        
        boolean continuar = true;
        
        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcion();
            
            switch (opcion) {
                case 1:
                    consultarSaldo();
                    esperarEnter();
                    break;
                case 2:
                    retirarDinero();
                    // No es necesario llamar a esperarEnter() aquí porque ya se llama dentro de retirarDinero()
                    break;
                case 3:
                    System.out.println("Gracias por utilizar nuestro cajero automático. ¡Hasta pronto!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
                    esperarEnter();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Inicializa el archivo de saldo con un valor predeterminado si no existe
     */
    private static void inicializarArchivo() {
        File archivo = new File(ARCHIVO_SALDO);
        
        if (!archivo.exists()) {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo))) {
                // Inicializar con un saldo de 1000.0
                dos.writeDouble(1000.0);
                System.out.println("Archivo de saldo inicializado con 1000.0");
            } catch (IOException e) {
                System.err.println("Error al inicializar el archivo de saldo: " + e.getMessage());
                System.exit(1);
            }
        }
    }
    
    /**
     * Muestra el menú de opciones al usuario
     */
    private static void mostrarMenu() {
        System.out.println("\n=== CAJERO AUTOMÁTICO ===");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Retirar dinero");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Obtiene la opción seleccionada por el usuario
     * @return La opción seleccionada
     */
    private static int obtenerOpcion() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar buffer
            return -1; // Valor inválido
        }
    }
    
    /**
     * Método para esperar a que el usuario presione Enter para continuar
     */
    private static void esperarEnter() {
        scanner.nextLine(); // Consumir cualquier entrada pendiente
        System.out.println("Presione Enter para ir al menú...");
        scanner.nextLine();
    }
    
    /**
     * Consulta el saldo actual del usuario
     */
    private static void consultarSaldo() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ARCHIVO_SALDO))) {
            double saldo = dis.readDouble();
            System.out.printf("Su saldo actual es: $%.2f\n", saldo);
        } catch (IOException e) {
            System.err.println("Error al consultar el saldo: " + e.getMessage());
        }
    }
    
    /**
     * Realiza un retiro de dinero si hay saldo suficiente
     */
    private static void retirarDinero() {
        double saldoActual = obtenerSaldoActual();
        
        System.out.print("Ingrese la cantidad a retirar: $");
        try {
            double cantidadRetiro = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea
            
            if (cantidadRetiro <= 0) {
                System.out.println("La cantidad a retirar debe ser mayor que cero.");
                esperarEnter();
                return;
            }
            
            if (cantidadRetiro > saldoActual) {
                System.out.println("Saldo insuficiente para realizar el retiro.");
                System.out.printf("Su saldo actual es: $%.2f\n", saldoActual);
                esperarEnter();
                return;
            }
            
            // Actualizar el saldo
            double nuevoSaldo = saldoActual - cantidadRetiro;
            actualizarSaldo(nuevoSaldo);
            
            // Mostrar un resumen de la operación
            System.out.println("\n=== RESUMEN DE LA OPERACIÓN ===");
            System.out.printf("Monto retirado: $%.2f\n", cantidadRetiro);
            System.out.printf("Saldo anterior: $%.2f\n", saldoActual);
            System.out.printf("Saldo actual: $%.2f\n", nuevoSaldo);
            
            esperarEnter();
            
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar buffer
            System.out.println("Valor inválido. Ingrese un número válido.");
            esperarEnter();
        }
    }
    
    /**
     * Obtiene el saldo actual desde el archivo
     * @return El saldo actual
     */
    private static double obtenerSaldoActual() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ARCHIVO_SALDO))) {
            return dis.readDouble();
        } catch (IOException e) {
            System.err.println("Error al leer el saldo: " + e.getMessage());
            return 0.0;
        }
    }
    
    /**
     * Actualiza el saldo en el archivo
     * @param nuevoSaldo El nuevo saldo a guardar
     */
    private static void actualizarSaldo(double nuevoSaldo) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ARCHIVO_SALDO))) {
            dos.writeDouble(nuevoSaldo);
        } catch (IOException e) {
            System.err.println("Error al actualizar el saldo: " + e.getMessage());
        }
    }
}