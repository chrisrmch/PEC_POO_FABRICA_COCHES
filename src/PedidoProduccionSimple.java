
public class PedidoProduccionSimple
{
    private String codigoPedido;
    private CadenaMontaje cadena;
    private String codigoChasis;
    private String codigoMotor;
    private String codigoTapiceria;
    private String codigoRueda;
    private int unidadesSolicitadas;
    private int unidadesPendientes;

    public PedidoProduccionSimple(CadenaMontaje cadena, String codigoChasis, String codigoMotor,
                                  String codigoTapiceria, String codigoRueda, int unidadesPendientes)
    {
        this("", cadena, codigoChasis, codigoMotor, codigoTapiceria, codigoRueda,
             unidadesPendientes);
    }

    public PedidoProduccionSimple(String codigoPedido, CadenaMontaje cadena, String codigoChasis,
                                  String codigoMotor, String codigoTapiceria, String codigoRueda,
                                  int unidadesPendientes)
    {
        this.cadena = cadena;
        this.codigoPedido = codigoPedido;
        actualizarConfiguracion(cadena, codigoChasis, codigoMotor, codigoTapiceria,
                                codigoRueda, unidadesPendientes);
    }

    public String getCodigoPedido()
    {
        return codigoPedido;
    }

    public CadenaMontaje getCadena()
    {
        return cadena;
    }

    public String getCodigoChasis()
    {
        return codigoChasis;
    }

    public String getCodigoMotor()
    {
        return codigoMotor;
    }

    public String getCodigoTapiceria()
    {
        return codigoTapiceria;
    }

    public String getCodigoRueda()
    {
        return codigoRueda;
    }

    public int getUnidadesPendientes()
    {
        return unidadesPendientes;
    }

    public int getUnidadesSolicitadas()
    {
        return unidadesSolicitadas;
    }

    public void actualizarConfiguracion(CadenaMontaje cadena, String codigoChasis,
                                        String codigoMotor, String codigoTapiceria,
                                        String codigoRueda, int unidades)
    {
        this.cadena = cadena;
        this.codigoChasis = codigoChasis;
        this.codigoMotor = codigoMotor;
        this.codigoTapiceria = codigoTapiceria;
        this.codigoRueda = codigoRueda;

        if (unidades < 0) {
            unidades = 0;
        }
        this.unidadesSolicitadas = unidades;
        this.unidadesPendientes = unidades;
    }

    public boolean tieneUnidadesPendientes()
    {
        return unidadesPendientes > 0;
    }

    public void consumirUnidadPendiente()
    {
        if (unidadesPendientes > 0) {
            unidadesPendientes--;
        }
    }

    public void cancelarPendientes()
    {
        unidadesPendientes = 0;
    }

    public String getDescripcionPedido()
    {
        String descripcion = "";
        if (codigoPedido != null && codigoPedido.length() > 0) {
            descripcion = codigoPedido + " | ";
        }

        return descripcion + cadena.getCodigo() + " -> chasis " + codigoChasis
               + ", motor " + codigoMotor
               + ", tapiceria " + codigoTapiceria
               + ", rueda " + codigoRueda
               + ", unidades solicitadas " + unidadesSolicitadas
               + ", unidades pendientes " + unidadesPendientes;
    }
}
