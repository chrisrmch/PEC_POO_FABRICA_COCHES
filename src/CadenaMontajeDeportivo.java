 
/**
 * Cadena de montaje especializada en biplazas deportivos.
 *
 * @author cmamani11
 */
public class CadenaMontajeDeportivo extends CadenaMontaje
{
    /**
     * Crea la cadena deportiva con su codigo identificador.
     *
     * @param codigo codigo de la cadena.
     */
    public CadenaMontajeDeportivo(String codigo)
    {
        super(codigo, "Biplaza deportivo");
    }

    /**
     * Crea el vehiculo base deportivo.
     *
     * @param codigoVehiculo codigo generado para el vehiculo.
     * @return biplaza deportivo creado.
     */
    protected Vehiculo crearVehiculoBase(String codigoVehiculo)
    {
        return new BiplazaDeportivo(codigoVehiculo);
    }

    /**
     * Monta el chasis en el vehiculo deportivo.
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
     * Monta el motor en el vehiculo deportivo.
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
     * Monta la tapiceria en el vehiculo deportivo.
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
     * Monta las ruedas en el vehiculo deportivo.
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
