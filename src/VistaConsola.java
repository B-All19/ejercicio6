import java.util.List;
import java.util.Scanner;

/**
 * VistaConsola.java
 * Vista del MVC. Solo muestra informacion y lee lo que escribe el usuario.
 */
public class VistaConsola {

    private Scanner scanner;

    public VistaConsola() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n===== INTEGRACIONES DE APIs =====");
        System.out.println("1. Listar integraciones");
        System.out.println("2. Buscar integracion por ID");
        System.out.println("3. Ordenar por latencia");
        System.out.println("4. Probar una operacion");
        System.out.println("0. Salir");
        return pedirEntero("Opcion: ");
    }

    public int mostrarMenuOperaciones() {
        System.out.println("\n--- Operaciones ---");
        System.out.println("1. Cobro");
        System.out.println("2. Reintegro");
        System.out.println("3. Generar documento");
        System.out.println("4. Consultar estado");
        System.out.println("5. Ver historial de auditoria");
        return pedirEntero("Operacion: ");
    }

    public void mostrarLista(List<IntegracionAPI> lista, String titulo) {
        System.out.println("\n" + titulo);
        for (IntegracionAPI api : lista) {
            System.out.println(api);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int pedirEntero(String mensaje) {
        try {
            return Integer.parseInt(pedirTexto(mensaje));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public double pedirDecimal(String mensaje) {
        try {
            return Double.parseDouble(pedirTexto(mensaje));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
