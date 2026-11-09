/**
 * BilleteraDigital.java
 * Pasarela de pago con billeteras digitales.
 */
public class BilleteraDigital extends PasarelaPago {

    private String plataforma;

    public BilleteraDigital(String id, String nombre, String proveedor, String urlBase,
                            boolean conectada, double latenciaMs, double comision,
                            String plataforma) {
        super(id, nombre, proveedor, urlBase, conectada, latenciaMs, comision);
        this.plataforma = plataforma;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String getTipo() {
        return "Billetera digital";
    }

    @Override
    public String getParametros() {
        return super.getParametros() + "\n"
             + "Plataforma: " + plataforma;
    }
}
