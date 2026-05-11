 public class CadenaMontajeFurgoneta extends CadenaMontaje
{
    public CadenaMontajeFurgoneta(String codigo)
    {
        super(codigo, "Furgoneta");
    }

    protected Vehiculo crearVehiculoBase(String codigoVehiculo)
    {
        return new Furgoneta(codigoVehiculo);
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
