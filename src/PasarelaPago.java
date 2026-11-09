import java.util.ArrayList;

/**
 * PasarelaPago.java
 * Clase abstracta para las APIs de pago. Cobran y reintegran dinero
 * (Transaccional) y guardan historial (Auditable).
 */
public abstract class PasarelaPago extends IntegracionAPI implements Transaccional, Auditable {

    private double comisionPorcentaje;

    public PasarelaPago(String id, String nombre, String proveedor, String urlBase,
                        boolean conectada, double latenciaMs, double comisionPorcentaje) {
        super(id, nombre, proveedor, urlBase, conectada, latenciaMs);
        this.comisionPorcentaje = comisionPorcentaje;
    }

    public double getComisionPorcentaje() {
        return comisionPorcentaje;
    }

    public void setComisionPorcentaje(double comisionPorcentaje) {
        this.comisionPorcentaje = comisionPorcentaje;
    }

    @Override
    public String procesarCobro(double monto) {
        if (!isConectada()) {
            return mensajeDesconexion();
        }
        double comision = monto * comisionPorcentaje / 100;
        registrarEvento("Cobro de Q" + String.format("%.2f", monto));
        return getNombre() + ": cobro aprobado por Q" + String.format("%.2f", monto)
                + " (comision Q" + String.format("%.2f", comision) + ")";
    }

    @Override
    public String procesarReintegro(double monto) {
        if (!isConectada()) {
            return mensajeDesconexion();
        }
        registrarEvento("Reintegro de Q" + String.format("%.2f", monto));
        return getNombre() + ": reintegro realizado por Q" + String.format("%.2f", monto);
    }

    @Override
    public ArrayList<String> obtenerHistorial() {
        return getHistorial();
    }

    @Override
    public String getParametros() {
        return super.getParametros() + "\n"
             + "Comision: " + comisionPorcentaje + " %";
    }
}
