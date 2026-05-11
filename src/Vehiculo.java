 
public abstract class Vehiculo
{
    private String codigoVehiculo;
    private String tipoVehiculo;
    private EstadoVehiculo estadoMontaje;
    private Chasis chasis;
    private Motor motor;
    private Tapiceria tapiceria;
    private Rueda rueda;

    public Vehiculo(String codigoVehiculo, String tipoVehiculo)
    {
        this.codigoVehiculo = codigoVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.estadoMontaje = EstadoVehiculo.PENDIENTE;
    }

    public String getCodigoVehiculo()
    {
        return codigoVehiculo;
    }

    public String getTipoVehiculo()
    {
        return tipoVehiculo;
    }

    public EstadoVehiculo getEstadoMontaje()
    {
        return estadoMontaje;
    }

    public String getDescripcionEstadoMontaje()
    {
        return estadoMontaje.getDescripcion();
    }

    public Chasis getChasis()
    {
        return chasis;
    }

    public Motor getMotor()
    {
        return motor;
    }

    public Tapiceria getTapiceria()
    {
        return tapiceria;
    }

    public Rueda getRueda()
    {
        return rueda;
    }

    public String getColor()
    {
        if (chasis == null) {
            return "";
        }
        return chasis.getColor();
    }

    public int getNumeroPlazas()
    {
        if (chasis == null) {
            return 0;
        }
        return chasis.getNumeroPlazas();
    }

    public double getTara()
    {
        if (chasis == null) {
            return 0.0;
        }
        return chasis.getTara();
    }

    public double getPesoMaximoAutorizado()
    {
        if (chasis == null) {
            return 0.0;
        }
        return chasis.getPesoMaximoAutorizado();
    }

    public void instalarChasis(Chasis chasis)
    {
        this.chasis = chasis;
        this.estadoMontaje = EstadoVehiculo.CHASIS;
    }

    public void instalarMotor(Motor motor)
    {
        this.motor = motor;
        this.estadoMontaje = EstadoVehiculo.MOTOR;
    }

    public void instalarTapiceria(Tapiceria tapiceria)
    {
        this.tapiceria = tapiceria;
        this.estadoMontaje = EstadoVehiculo.TAPICERIA;
    }

    public void instalarRuedas(Rueda rueda)
    {
        this.rueda = rueda;
        this.estadoMontaje = EstadoVehiculo.RUEDAS;
    }

    public void actualizarConfiguracion(Chasis chasis, Motor motor,
                                        Tapiceria tapiceria, Rueda rueda)
    {
        instalarChasis(chasis);
        instalarMotor(motor);
        instalarTapiceria(tapiceria);
        instalarRuedas(rueda);
    }
}
