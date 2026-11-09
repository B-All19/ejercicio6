/**
 * Transaccional.java
 * Contrato para las APIs que mueven dinero: cobros y reintegros.
 */
public interface Transaccional {

    /**
     * Cobra un monto al cliente.
     * @param monto cantidad a cobrar en quetzales
     * @return mensaje con el resultado
     */
    String procesarCobro(double monto);

    /**
     * Devuelve un monto al cliente.
     * @param monto cantidad a devolver en quetzales
     * @return mensaje con el resultado
     */
    String procesarReintegro(double monto);
}
