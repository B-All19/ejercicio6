/**
 * Consultable.java
 * Contrato para las APIs que solo permiten consultar el estado de algo
 * (por ejemplo rastrear un paquete o ver el estado de una factura).
 */
public interface Consultable {

    /**
     * Consulta el estado de una referencia (guia, factura, etc.).
     * @param referencia codigo de lo que se quiere consultar
     * @return texto con el estado
     */
    String consultarEstado(String referencia);
}
