/**
 * TransferenciaBancaria.java
 * Pasarela de pago por transferencia entre bancos.
 */
public class TransferenciaBancaria extends PasarelaPago {

    private String banco;

    public TransferenciaBancaria(String id, String nombre, String proveedor, String urlBase,
                                 boolean conectada, double latenciaMs, double comision,
                                 String banco) {
        super(id, nombre, proveedor, urlBase, conectada, latenciaMs, comision);
        this.banco = banco;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String getTipo() {
        return "Transferencia bancaria";
    }

    /** En las transferencias el reintegro no es inmediato. */
    @Override
    public String procesarReintegro(double monto) {
        String resultado = super.procesarReintegro(monto);
        if (!isConectada()) {
            return resultado;
        }
        return resultado + " (se acredita en 2 dias habiles)";
    }

    @Override
    public String getParametros() {
        return super.getParametros() + "\n"
             + "Banco: " + banco;
    }
}
