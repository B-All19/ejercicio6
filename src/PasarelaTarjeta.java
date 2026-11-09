/**
 * PasarelaTarjeta.java
 * Pasarela de pago con tarjetas de credito.
 */
public class PasarelaTarjeta extends PasarelaPago {

    private String marcasAceptadas;

    public PasarelaTarjeta(String id, String nombre, String proveedor, String urlBase,
                           boolean conectada, double latenciaMs, double comision,
                           String marcasAceptadas) {
        super(id, nombre, proveedor, urlBase, conectada, latenciaMs, comision);
        this.marcasAceptadas = marcasAceptadas;
    }

    public String getMarcasAceptadas() {
        return marcasAceptadas;
    }

    public void setMarcasAceptadas(String marcasAceptadas) {
        this.marcasAceptadas = marcasAceptadas;
    }

    @Override
    public String getTipo() {
        return "Pasarela de tarjeta";
    }

    @Override
    public String getParametros() {
        return super.getParametros() + "\n"
             + "Tarjetas aceptadas: " + marcasAceptadas;
    }
}
