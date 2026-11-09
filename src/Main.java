/**
 * Main.java
 * Programa principal (driver). Crea el modelo, la vista y el controlador.
 * Ejercicio 6 - CC2008 Programacion Orientada a Objetos, UVG.
 */
public class Main {

    public static void main(String[] args) {
        CatalogoIntegraciones catalogo = new CatalogoIntegraciones();
        VistaConsola vista = new VistaConsola();
        ControladorIntegraciones controlador = new ControladorIntegraciones(catalogo, vista);
        controlador.iniciar();
    }
}
