import java.util.ArrayList;

/**
 * Auditable.java
 * Contrato para las APIs que guardan un historial de sus operaciones
 * para poder revisarlas despues.
 */
public interface Auditable {

    /**
     * Devuelve el historial de operaciones realizadas.
     * @return lista de eventos
     */
    ArrayList<String> obtenerHistorial();
}
