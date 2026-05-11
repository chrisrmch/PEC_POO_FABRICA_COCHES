 
public class CadenaMontajeDeportivo extends CadenaMontaje
{
    public CadenaMontajeDeportivo(String codigo)
    {
        super(codigo, "Biplaza deportivo");
    }

    protected Vehiculo crearVehiculoBase(String codigoVehiculo)
    {
        return new BiplazaDeportivo(codigoVehiculo);
    }

    protected void montarChasisRobotizado(Vehiculo vehiculo, Chasis chasis)
    {
        chasis.marcarMontadoEnVehiculo(vehiculo.getCodigoVehiculo());
        vehiculo.instalarChasis(chasis);
    }

    protected void montarMotorRobotizado(Vehiculo vehiculo, Motor motor)
    {
        motor.marcarMontadoEnVehiculo(vehiculo.getCodigoVehiculo());
        vehiculo.instalarMotor(motor);
    }

    protected void montarTapiceriaRobotizada(Vehiculo vehiculo, Tapiceria tapiceria)
    {
        tapiceria.marcarMontadoEnVehiculo(vehiculo.getCodigoVehiculo());
        vehiculo.instalarTapiceria(tapiceria);
    }

    protected void montarRuedasRobotizadas(Vehiculo vehiculo, Rueda rueda)
    {
        rueda.marcarMontadoEnVehiculo(vehiculo.getCodigoVehiculo());
        vehiculo.instalarRuedas(rueda);
    }
}
