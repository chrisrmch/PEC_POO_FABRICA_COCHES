
/**
 * Representa un pedido de produccion con componentes y unidades pendientes.
 *
 * @author cmamani11
 */
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

    /**
     * Crea un pedido sin codigo explicito.
     *
     * @param cadena cadena donde se producira el vehiculo.
     * @param codigoChasis codigo del chasis requerido.
     * @param codigoMotor codigo del motor requerido.
     * @param codigoTapiceria codigo de la tapiceria requerida.
     * @param codigoRueda codigo de la rueda requerida.
     * @param unidadesPendientes unidades pendientes iniciales.
     */
    public PedidoProduccionSimple(CadenaMontaje cadena, String codigoChasis, String codigoMotor,
                                  String codigoTapiceria, String codigoRueda, int unidadesPendientes)
    {
        this("", cadena, codigoChasis, codigoMotor, codigoTapiceria, codigoRueda,
             unidadesPendientes);
    }

    /**
     * Crea un pedido con codigo y configuracion completa.
     *
     * @param codigoPedido identificador del pedido.
     * @param cadena cadena donde se producira el vehiculo.
     * @param codigoChasis codigo del chasis requerido.
     * @param codigoMotor codigo del motor requerido.
     * @param codigoTapiceria codigo de la tapiceria requerida.
     * @param codigoRueda codigo de la rueda requerida.
     * @param unidadesPendientes unidades solicitadas.
     */
    public PedidoProduccionSimple(String codigoPedido, CadenaMontaje cadena, String codigoChasis,
                                  String codigoMotor, String codigoTapiceria, String codigoRueda,
                                  int unidadesPendientes)
    {
        this.cadena = cadena;
        this.codigoPedido = codigoPedido;
        actualizarConfiguracion(cadena, codigoChasis, codigoMotor, codigoTapiceria,
                                codigoRueda, unidadesPendientes);
    }

    /**
     * Devuelve el codigo del pedido.
     *
     * @return codigo del pedido.
     */
    public String getCodigoPedido()
    {
        return codigoPedido;
    }

    /**
     * Devuelve la cadena asociada al pedido.
     *
     * @return cadena de montaje.
     */
    public CadenaMontaje getCadena()
    {
        return cadena;
    }

    /**
     * Devuelve el codigo de chasis requerido.
     *
     * @return codigo de chasis.
     */
    public String getCodigoChasis()
    {
        return codigoChasis;
    }

    /**
     * Devuelve el codigo de motor requerido.
     *
     * @return codigo de motor.
     */
    public String getCodigoMotor()
    {
        return codigoMotor;
    }

    /**
     * Devuelve el codigo de tapiceria requerido.
     *
     * @return codigo de tapiceria.
     */
    public String getCodigoTapiceria()
    {
        return codigoTapiceria;
    }

    /**
     * Devuelve el codigo de rueda requerido.
     *
     * @return codigo de rueda.
     */
    public String getCodigoRueda()
    {
        return codigoRueda;
    }

    /**
     * Devuelve las unidades pendientes de fabricar.
     *
     * @return unidades pendientes.
     */
    public int getUnidadesPendientes()
    {
        return unidadesPendientes;
    }

    /**
     * Devuelve las unidades solicitadas originalmente.
     *
     * @return unidades solicitadas.
     */
    public int getUnidadesSolicitadas()
    {
        return unidadesSolicitadas;
    }

    /**
     * Sustituye la configuracion completa del pedido.
     *
     * @param cadena cadena donde se producira el vehiculo.
     * @param codigoChasis codigo del chasis requerido.
     * @param codigoMotor codigo del motor requerido.
     * @param codigoTapiceria codigo de la tapiceria requerida.
     * @param codigoRueda codigo de la rueda requerida.
     * @param unidades unidades solicitadas.
     */
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

    /**
     * Indica si todavia quedan unidades por fabricar.
     *
     * @return true si existen unidades pendientes.
     */
    public boolean tieneUnidadesPendientes()
    {
        return unidadesPendientes > 0;
    }

    /**
     * Descuenta una unidad pendiente tras iniciar su fabricacion.
     */
    public void consumirUnidadPendiente()
    {
        if (unidadesPendientes > 0) {
            unidadesPendientes--;
        }
    }

    /**
     * Cancela todas las unidades pendientes del pedido.
     */
    public void cancelarPendientes()
    {
        unidadesPendientes = 0;
    }

    /**
     * Genera una descripcion textual del pedido.
     *
     * @return descripcion del pedido.
     */
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
