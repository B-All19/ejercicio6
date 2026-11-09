/**
 * ProveedorLogistico.java
 * API de envios. Solo consulta estados (Consultable) y genera guias
 * de envio (Documentable). NO es Transaccional, por eso no hace reintegros.
 */
public class ProveedorLogistico extends IntegracionAPI implements Consultable, Documentable {

    private String cobertura;

    public ProveedorLogistico(String id, String nombre, String proveedor, String urlBase,
                              boolean conectada, double latenciaMs, String cobertura) {
        super(id, nombre, proveedor, urlBase, conectada, latenciaMs);
        this.cobertura = cobertura;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    @Override
    public String getTipo() {
        return "Proveedor logistico";
    }

    @Override
    public String consultarEstado(String referencia) {
        if (!isConectada()) {
            return mensajeDesconexion();
        }
        return getNombre() + ": el paquete " + referencia + " esta en transito.";
    }

    @Override
    public String generarDocumento(String referencia) {
        if (!isConectada()) {
            return mensajeDesconexion();
        }
        return "<guiaEnvio>\n"
             + "  <referencia>" + referencia + "</referencia>\n"
             + "  <transportista>" + getNombre() + "</transportista>\n"
             + "</guiaEnvio>";
    }

    @Override
    public String getParametros() {
        return super.getParametros() + "\n"
             + "Cobertura: " + cobertura;
    }
}
