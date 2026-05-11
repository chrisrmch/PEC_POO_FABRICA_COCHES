 

public class Motor extends Componente
{
    private TipoMotor tipoMotor;
    private int cilindrada;
    private int potencia;
    private short numeroCilindros;

    public Motor(String codigo, TipoMotor tipoMotor, int cilindrada,
                 int potencia, short numeroCilindros, int unidadesDisponibles)
    {
        super(codigo, "Motor", unidadesDisponibles);
        this.tipoMotor = tipoMotor;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.numeroCilindros = numeroCilindros;
    }

    public TipoMotor getTipoMotor()
    {
        return tipoMotor;
    }

    public int getCilindrada()
    {
        return cilindrada;
    }

    public int getPotencia()
    {
        return potencia;
    }

    public short getNumeroCilindros()
    {
        return numeroCilindros;
    }

    protected Componente crearUnidadIndependiente()
    {
        return new Motor(getCodigo(), tipoMotor, cilindrada, potencia,
                         numeroCilindros, 1);
    }
}
