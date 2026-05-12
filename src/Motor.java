 

/**
 * Representa un motor utilizado en el montaje de vehiculos.
 *
 * @author cmamani11
 */
public class Motor extends Componente
{
    private TipoMotor tipoMotor;
    private int cilindrada;
    private int potencia;
    private short numeroCilindros;

    /**
     * Crea un motor con sus caracteristicas tecnicas y stock.
     *
     * @param codigo codigo del componente.
     * @param tipoMotor tipo de motor.
     * @param cilindrada cilindrada del motor.
     * @param potencia potencia del motor.
     * @param numeroCilindros numero de cilindros.
     * @param unidadesDisponibles unidades disponibles.
     */
    public Motor(String codigo, TipoMotor tipoMotor, int cilindrada,
                 int potencia, short numeroCilindros, int unidadesDisponibles)
    {
        super(codigo, "Motor", unidadesDisponibles);
        this.tipoMotor = tipoMotor;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.numeroCilindros = numeroCilindros;
    }

    /**
     * Devuelve el tipo de motor.
     *
     * @return tipo de motor.
     */
    public TipoMotor getTipoMotor()
    {
        return tipoMotor;
    }

    /**
     * Devuelve la cilindrada.
     *
     * @return cilindrada del motor.
     */
    public int getCilindrada()
    {
        return cilindrada;
    }

    /**
     * Devuelve la potencia.
     *
     * @return potencia del motor.
     */
    public int getPotencia()
    {
        return potencia;
    }

    /**
     * Devuelve el numero de cilindros.
     *
     * @return numero de cilindros.
     */
    public short getNumeroCilindros()
    {
        return numeroCilindros;
    }

    /**
     * Crea una unidad independiente de este motor para montaje.
     *
     * @return nueva unidad de motor.
     */
    protected Componente crearUnidadIndependiente()
    {
        return new Motor(getCodigo(), tipoMotor, cilindrada, potencia,
                         numeroCilindros, 1);
    }
}
