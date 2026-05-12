 
/**
 * Clase base para los vehiculos fabricados en las cadenas de montaje.
 *
 * @author cmamani11
 */
public abstract class Vehiculo
{
    private String codigoVehiculo;
    private String tipoVehiculo;
    private EstadoVehiculo estadoMontaje;
    private Chasis chasis;
    private Motor motor;
    private Tapiceria tapiceria;
    private Rueda rueda;

    /**
     * Crea un vehiculo con codigo y tipo.
     *
     * @param codigoVehiculo identificador unico del vehiculo.
     * @param tipoVehiculo tipo comercial del vehiculo.
     */
    public Vehiculo(String codigoVehiculo, String tipoVehiculo)
    {
        this.codigoVehiculo = codigoVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.estadoMontaje = EstadoVehiculo.PENDIENTE;
    }

    /**
     * Devuelve el codigo del vehiculo.
     *
     * @return codigo del vehiculo.
     */
    public String getCodigoVehiculo()
    {
        return codigoVehiculo;
    }

    /**
     * Devuelve el tipo del vehiculo.
     *
     * @return tipo del vehiculo.
     */
    public String getTipoVehiculo()
    {
        return tipoVehiculo;
    }

    /**
     * Devuelve el estado actual de montaje.
     *
     * @return estado de montaje.
     */
    public EstadoVehiculo getEstadoMontaje()
    {
        return estadoMontaje;
    }

    /**
     * Devuelve una descripcion del estado de montaje.
     *
     * @return descripcion del estado.
     */
    public String getDescripcionEstadoMontaje()
    {
        return estadoMontaje.getDescripcion();
    }

    /**
     * Devuelve el chasis instalado.
     *
     * @return chasis instalado, o null si no existe.
     */
    public Chasis getChasis()
    {
        return chasis;
    }

    /**
     * Devuelve el motor instalado.
     *
     * @return motor instalado, o null si no existe.
     */
    public Motor getMotor()
    {
        return motor;
    }

    /**
     * Devuelve la tapiceria instalada.
     *
     * @return tapiceria instalada, o null si no existe.
     */
    public Tapiceria getTapiceria()
    {
        return tapiceria;
    }

    /**
     * Devuelve la rueda instalada.
     *
     * @return rueda instalada, o null si no existe.
     */
    public Rueda getRueda()
    {
        return rueda;
    }

    /**
     * Devuelve el color del vehiculo a partir de su chasis.
     *
     * @return color del vehiculo o cadena vacia si no hay chasis.
     */
    public String getColor()
    {
        if (chasis == null) {
            return "";
        }
        return chasis.getColor();
    }

    /**
     * Devuelve el numero de plazas definido por el chasis.
     *
     * @return numero de plazas, o cero si no hay chasis.
     */
    public int getNumeroPlazas()
    {
        if (chasis == null) {
            return 0;
        }
        return chasis.getNumeroPlazas();
    }

    /**
     * Devuelve la tara definida por el chasis.
     *
     * @return tara, o cero si no hay chasis.
     */
    public double getTara()
    {
        if (chasis == null) {
            return 0.0;
        }
        return chasis.getTara();
    }

    /**
     * Devuelve el peso maximo autorizado definido por el chasis.
     *
     * @return peso maximo autorizado, o cero si no hay chasis.
     */
    public double getPesoMaximoAutorizado()
    {
        if (chasis == null) {
            return 0.0;
        }
        return chasis.getPesoMaximoAutorizado();
    }

    /**
     * Instala el chasis y avanza el estado de montaje.
     *
     * @param chasis chasis que se instala.
     */
    public void instalarChasis(Chasis chasis)
    {
        this.chasis = chasis;
        this.estadoMontaje = EstadoVehiculo.CHASIS;
    }

    /**
     * Instala el motor y avanza el estado de montaje.
     *
     * @param motor motor que se instala.
     */
    public void instalarMotor(Motor motor)
    {
        this.motor = motor;
        this.estadoMontaje = EstadoVehiculo.MOTOR;
    }

    /**
     * Instala la tapiceria y avanza el estado de montaje.
     *
     * @param tapiceria tapiceria que se instala.
     */
    public void instalarTapiceria(Tapiceria tapiceria)
    {
        this.tapiceria = tapiceria;
        this.estadoMontaje = EstadoVehiculo.TAPICERIA;
    }

    /**
     * Instala las ruedas y avanza el estado de montaje.
     *
     * @param rueda rueda que se instala.
     */
    public void instalarRuedas(Rueda rueda)
    {
        this.rueda = rueda;
        this.estadoMontaje = EstadoVehiculo.RUEDAS;
    }

    /**
     * Sustituye la configuracion completa del vehiculo.
     *
     * @param chasis nuevo chasis.
     * @param motor nuevo motor.
     * @param tapiceria nueva tapiceria.
     * @param rueda nueva rueda.
     */
    public void actualizarConfiguracion(Chasis chasis, Motor motor,
                                        Tapiceria tapiceria, Rueda rueda)
    {
        instalarChasis(chasis);
        instalarMotor(motor);
        instalarTapiceria(tapiceria);
        instalarRuedas(rueda);
    }
}
