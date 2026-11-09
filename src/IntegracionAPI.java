import java.util.ArrayList;

/**
 * IntegracionAPI.java
 * Clase base (abstracta) de todas las conexiones a APIs externas.
 * Implementa Comparable para poder ordenar la lista por latencia.
 */
public abstract class IntegracionAPI implements Comparable<IntegracionAPI> {

    private String id;
    private String nombre;
    private String proveedor;
    private String urlBase;
    private boolean conectada;
    private double latenciaMs;
    private ArrayList<String> historial;

    public IntegracionAPI(String id, String nombre, String proveedor,
                          String urlBase, boolean conectada, double latenciaMs) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.urlBase = urlBase;
        this.conectada = conectada;
        this.latenciaMs = latenciaMs;
        this.historial = new ArrayList<>();
    }

    /** Cada clase hija dice de que tipo de integracion es. */
    public abstract String getTipo();

    /**
     * Parametros de configuracion. Las clases hijas lo sobrescriben
     * para agregar sus propios datos.
     */
    public String getParametros() {
        return "URL base: " + urlBase + "\n"
             + "Proveedor: " + proveedor + "\n"
             + "Latencia promedio: " + latenciaMs + " ms";
    }

    /** Guarda un evento en el historial interno. */
    protected void registrarEvento(String evento) {
        historial.add(evento);
    }

    /** Mensaje que se usa cuando la API esta desconectada. */
    protected String mensajeDesconexion() {
        return nombre + " esta desconectada. No se pudo realizar la operacion.";
    }

    protected ArrayList<String> getHistorial() {
        return historial;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public boolean isConectada() {
        return conectada;
    }

    public void setConectada(boolean conectada) {
        this.conectada = conectada;
    }

    public double getLatenciaMs() {
        return latenciaMs;
    }

    public void setLatenciaMs(double latenciaMs) {
        this.latenciaMs = latenciaMs;
    }

    /** Ordena de menor a mayor latencia. */
    @Override
    public int compareTo(IntegracionAPI otra) {
        return Double.compare(this.latenciaMs, otra.latenciaMs);
    }

    /** Dos integraciones son iguales si tienen el mismo id. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IntegracionAPI)) {
            return false;
        }
        IntegracionAPI otra = (IntegracionAPI) o;
        return id.equalsIgnoreCase(otra.id);
    }

    @Override
    public int hashCode() {
        return id.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        String estado = conectada ? "CONECTADA" : "DESCONECTADA";
        return String.format("[%s] %-22s %-23s %-13s %6.0f ms",
                id, nombre, getTipo(), estado, latenciaMs);
    }
}
