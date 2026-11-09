/**
 * Documentable.java
 * Contrato para las APIs que generan documentos legales
 * (XML de facturas, guias de envio).
 */
public interface Documentable {

    /**
     * Genera el documento de una referencia.
     * @param referencia codigo de la compra o del envio
     * @return el documento en texto (XML simple)
     */
    String generarDocumento(String referencia);
}
