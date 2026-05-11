

public class LoteMontaje
{
    private Chasis chasis;
    private Motor motor;
    private Tapiceria tapiceria;
    private Rueda rueda;

    public LoteMontaje(Chasis chasis, Motor motor, Tapiceria tapiceria, Rueda rueda)
    {
        this.chasis = chasis;
        this.motor = motor;
        this.tapiceria = tapiceria;
        this.rueda = rueda;
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
}
