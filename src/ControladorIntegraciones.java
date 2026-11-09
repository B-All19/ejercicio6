import java.util.ArrayList;

/**
 * ControladorIntegraciones.java
 * Controlador del MVC. Conecta la vista con el modelo.
 * Los metodos ejecutarCobro, ejecutarReintegro, emitirDocumento,
 * consultarEstado y revisarAuditoria estan sobrecargados:
 * hay una version para el contrato (interfaz) y otra para IntegracionAPI.
 * La version general revisa si la API cumple el contrato; si no lo cumple,
 * ignora la peticion sin lanzar excepciones.
 */
public class ControladorIntegraciones {

    private CatalogoIntegraciones catalogo;
    private VistaConsola vista;

    public ControladorIntegraciones(CatalogoIntegraciones catalogo, VistaConsola vista) {
        this.catalogo = catalogo;
        this.vista = vista;
    }

    /** Carga las integraciones y corre el menu. */
    public void iniciar() {
        cargarDatosIniciales();
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    vista.mostrarLista(catalogo.getIntegraciones(), "Integraciones registradas:");
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    catalogo.ordenarPorLatencia();
                    vista.mostrarLista(catalogo.getIntegraciones(),
                            "Ordenadas por latencia (las mas lentas quedan al final):");
                    break;
                case 4:
                    probarOperacion();
                    break;
                case 0:
                    vista.mostrarMensaje("Hasta luego.");
                    break;
                default:
                    vista.mostrarMensaje("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private void buscar() {
        String id = vista.pedirTexto("ID a buscar: ");
        IntegracionAPI api = catalogo.buscarPorId(id);
        if (api == null) {
            vista.mostrarMensaje("No existe una integracion con ese ID.");
        } else {
            vista.mostrarMensaje("\n" + api.getNombre() + " (" + api.getTipo() + ")");
            vista.mostrarMensaje(api.getParametros());
        }
    }

    private void probarOperacion() {
        String id = vista.pedirTexto("ID de la integracion: ");
        IntegracionAPI api = catalogo.buscarPorId(id);
        if (api == null) {
            vista.mostrarMensaje("No existe una integracion con ese ID.");
            return;
        }
        int operacion = vista.mostrarMenuOperaciones();
        String resultado;
        switch (operacion) {
            case 1:
                resultado = ejecutarCobro(api, vista.pedirDecimal("Monto en Q: "));
                break;
            case 2:
                resultado = ejecutarReintegro(api, vista.pedirDecimal("Monto en Q: "));
                break;
            case 3:
                resultado = emitirDocumento(api, vista.pedirTexto("Referencia: "));
                break;
            case 4:
                resultado = consultarEstado(api, vista.pedirTexto("Referencia: "));
                break;
            case 5:
                resultado = revisarAuditoria(api);
                break;
            default:
                resultado = "Operacion no valida.";
        }
        vista.mostrarMensaje("\n" + resultado);
    }

    // ---------- Cobro ----------
    public String ejecutarCobro(Transaccional t, double monto) {
        return t.procesarCobro(monto);
    }

    public String ejecutarCobro(IntegracionAPI api, double monto) {
        if (api instanceof Transaccional) {
            return ejecutarCobro((Transaccional) api, monto);
        }
        return api.getNombre() + " no maneja cobros. Peticion ignorada.";
    }

    // ---------- Reintegro ----------
    public String ejecutarReintegro(Transaccional t, double monto) {
        return t.procesarReintegro(monto);
    }

    public String ejecutarReintegro(IntegracionAPI api, double monto) {
        if (api instanceof Transaccional) {
            return ejecutarReintegro((Transaccional) api, monto);
        }
        return api.getNombre() + " no maneja reintegros. Peticion ignorada.";
    }

    // ---------- Documentos ----------
    public String emitirDocumento(Documentable d, String referencia) {
        return d.generarDocumento(referencia);
    }

    public String emitirDocumento(IntegracionAPI api, String referencia) {
        if (api instanceof Documentable) {
            return emitirDocumento((Documentable) api, referencia);
        }
        return api.getNombre() + " no genera documentos. Peticion ignorada.";
    }

    // ---------- Consultas ----------
    public String consultarEstado(Consultable c, String referencia) {
        return c.consultarEstado(referencia);
    }

    public String consultarEstado(IntegracionAPI api, String referencia) {
        if (api instanceof Consultable) {
            return consultarEstado((Consultable) api, referencia);
        }
        return api.getNombre() + " no permite consultas de estado. Peticion ignorada.";
    }

    // ---------- Auditoria ----------
    public String revisarAuditoria(Auditable a) {
        ArrayList<String> historial = a.obtenerHistorial();
        if (historial.isEmpty()) {
            return "El historial esta vacio.";
        }
        String texto = "Historial:";
        for (String evento : historial) {
            texto += "\n- " + evento;
        }
        return texto;
    }

    public String revisarAuditoria(IntegracionAPI api) {
        if (api instanceof Auditable) {
            return revisarAuditoria((Auditable) api);
        }
        return api.getNombre() + " no tiene auditoria. Peticion ignorada.";
    }

    // ---------- Carga inicial ----------
    private void cargarDatosIniciales() {
        catalogo.agregar(new PasarelaTarjeta("PAY-01", "Visanet Guatemala", "Visanet",
                "https://api.visanet.example/v2", true, 180, 3.5, "Visa y Mastercard"));
        catalogo.agregar(new PasarelaTarjeta("PAY-02", "Credomatic", "BAC",
                "https://api.credomatic.example/v1", true, 220, 3.2, "Visa, Mastercard y Amex"));
        catalogo.agregar(new TransferenciaBancaria("PAY-03", "ACH Banco Industrial", "Banco Industrial",
                "https://api.bi.example/ach", true, 350, 1.0, "Banco Industrial"));
        catalogo.agregar(new TransferenciaBancaria("PAY-04", "Transferencia Banrural", "Banrural",
                "https://api.banrural.example/v1", false, 410, 1.2, "Banrural"));
        catalogo.agregar(new BilleteraDigital("PAY-05", "Tigo Money", "Tigo",
                "https://api.tigomoney.example/v3", true, 140, 2.0, "Tigo Money"));
        catalogo.agregar(new BilleteraDigital("PAY-06", "PayPal", "PayPal",
                "https://api.paypal.example/v2", true, 260, 2.9, "PayPal"));
        catalogo.agregar(new ProveedorLogistico("LOG-01", "Cargo Expreso", "Cargo Expreso",
                "https://api.cargoexpreso.example/v1", true, 300, "Nacional"));
        catalogo.agregar(new ProveedorLogistico("LOG-02", "Guatex", "Guatex",
                "https://api.guatex.example/v2", true, 280, "Nacional y Centroamerica"));
        catalogo.agregar(new ProveedorLogistico("LOG-03", "Forza Delivery", "Forza",
                "https://api.forza.example/v1", true, 120, "Ciudad de Guatemala"));
        catalogo.agregar(new CertificadorFEL("FEL-01", "Infile", "Infile",
                "https://api.infile.example/fel", true, 450, "Produccion"));
        catalogo.agregar(new CertificadorFEL("FEL-02", "Megaprint", "Megaprint",
                "https://api.megaprint.example/fel", true, 520, "Produccion"));
        catalogo.agregar(new CertificadorFEL("FEL-03", "Digifact", "Digifact",
                "https://api.digifact.example/fel", false, 600, "Pruebas"));
    }
}
