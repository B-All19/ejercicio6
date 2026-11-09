import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CatalogoIntegraciones.java
 * Modelo que guarda la UNICA lista polimorfica del sistema.
 * Aqui entran todas las integraciones sin importar su tipo.
 */
public class CatalogoIntegraciones {

    private ArrayList<IntegracionAPI> integraciones;

    public CatalogoIntegraciones() {
        integraciones = new ArrayList<>();
    }

    /**
     * Agrega una integracion al catalogo. No permite ids repetidos.
     * @return true si se agrego
     */
    public boolean agregar(IntegracionAPI api) {
        if (integraciones.contains(api)) {
            return false;
        }
        integraciones.add(api);
        return true;
    }

    /** Busca por id. Devuelve null si no existe. */
    public IntegracionAPI buscarPorId(String id) {
        for (IntegracionAPI api : integraciones) {
            if (api.getId().equalsIgnoreCase(id)) {
                return api;
            }
        }
        return null;
    }

    /** Ordena la lista por latencia usando compareTo. */
    public void ordenarPorLatencia() {
        Collections.sort(integraciones);
    }

    public List<IntegracionAPI> getIntegraciones() {
        return Collections.unmodifiableList(integraciones);
    }
}
