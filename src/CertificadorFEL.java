import java.util.ArrayList;

/**
 * CertificadorFEL.java
 * API de Factura Electronica en Linea. Genera el XML de la factura
 * (Documentable), consulta su estado (Consultable) y guarda historial (Auditable).
 */
public class CertificadorFEL extends IntegracionAPI implements Consultable, Documentable, Auditable {

    private String ambiente;

    public CertificadorFEL(String id, String nombre, String proveedor, String urlBase,
                           boolean conectada, double latenciaMs, String ambiente) {
        super(id, nombre, proveedor, urlBase, conectada, latenciaMs);
        this.ambiente = ambiente;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    @Override
    public String getTipo() {
        return "Certificador FEL";
    }

    @Override
    public String consultarEstado(String referencia) {
        if (!isConectada()) {
            return mensajeDesconexion();
        }
        return getNombre() + ": la factura " + referencia + " esta certificada.";
    }

    @Override
    public String generarDocumento(String referencia) {
        if (!isConectada()) {
            return mensajeDesconexion();
        }
        registrarEvento("Factura generada: " + referencia);
        return "<dte>\n"
             + "  <numero>" + referencia + "</numero>\n"
             + "  <certificador>" + getNombre() + "</certificador>\n"
             + "  <ambiente>" + ambiente + "</ambiente>\n"
             + "</dte>";
    }

    @Override
    public ArrayList<String> obtenerHistorial() {
        return getHistorial();
    }

    @Override
    public String getParametros() {
        return super.getParametros() + "\n"
             + "Ambiente: " + ambiente;
    }
}
