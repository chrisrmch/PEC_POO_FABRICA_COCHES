/**
 * Cadena de montaje especializada en furgonetas.
 *
 * @author cmamani11
 */
public class CadenaMontajeFurgoneta extends CadenaMontaje
{
    /**
     * Crea la cadena de furgoneta con su codigo identificador.
     *
     * @param codigo codigo de la cadena.
     */
    public CadenaMontajeFurgoneta(String codigo)
    {
        super(codigo, "Furgoneta");
    }

    /**
     * Crea el vehiculo base de tipo furgoneta.
     *
     * @param codigoVehiculo codigo generado para el vehiculo.
     * @return furgoneta creada.
     */
    protected Vehiculo crearVehiculoBase(String codigoVehiculo)
    {
        return new Furgoneta(codigoVehiculo);
    }

    /**
     * Monta el chasis en la furgoneta.
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
     * Monta el motor en la furgoneta.
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
     * Monta la tapiceria en la furgoneta.
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
     * Monta las ruedas en la furgoneta.
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
