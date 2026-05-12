
/**
 * Cadena de montaje especializada en turismos.
 *
 * @author cmamani11
 */
public class CadenaMontajeTurismo extends CadenaMontaje
{
    /**
     * Crea la cadena de turismo con su codigo identificador.
     *
     * @param codigo codigo de la cadena.
     */
    public CadenaMontajeTurismo(String codigo)
    {
        super(codigo, "Turismo");
    }

    /**
     * Crea el vehiculo base de tipo turismo.
     *
     * @param codigoVehiculo codigo generado para el vehiculo.
     * @return turismo creado.
     */
    protected Vehiculo crearVehiculoBase(String codigoVehiculo)
    {
        return new Turismo(codigoVehiculo);
    }

    /**
     * Monta el chasis en el turismo.
     *
     * @param vehiculo vehiculo en montaje.
     * @param chasis chasis que se instala.
     */
    protected void montarChasisRobotizado(Vehiculo vehiculo, Chasis chasis)
    {
        chasis.marcarMontadoEnVehiculo(vehiculo.getCodigoVehiculo());
        vehiculo.instalarChasis(chasis);
    }

    /**
     * Monta el motor en el turismo.
     *
     * @param vehiculo vehiculo en montaje.
     * @param motor motor que se instala.
     */
    protected void montarMotorRobotizado(Vehiculo vehiculo, Motor motor)
    {
        motor.marcarMontadoEnVehiculo(vehiculo.getCodigoVehiculo());
        vehiculo.instalarMotor(motor);
    }

    /**
     * Monta la tapiceria en el turismo.
     *
     * @param vehiculo vehiculo en montaje.
     * @param tapiceria tapiceria que se instala.
     */
    protected void montarTapiceriaRobotizada(Vehiculo vehiculo, Tapiceria tapiceria)
    {
        tapiceria.marcarMontadoEnVehiculo(vehiculo.getCodigoVehiculo());
        vehiculo.instalarTapiceria(tapiceria);
    }

    /**
     * Monta las ruedas en el turismo.
     *
     * @param vehiculo vehiculo en montaje.
     * @param rueda rueda que se instala.
     */
    protected void montarRuedasRobotizadas(Vehiculo vehiculo, Rueda rueda)
    {
        rueda.marcarMontadoEnVehiculo(vehiculo.getCodigoVehiculo());
        vehiculo.instalarRuedas(rueda);
    }
}
