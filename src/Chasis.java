 

/**
 * Representa el chasis de un vehiculo y su compatibilidad de montaje.
 *
 * @author cmamani11
 */
public class Chasis extends Componente
{
    private String tipoVehiculoCompatible;
    private String color;
    private int numeroPlazas;
    private double tara;
    private double pesoMaximoAutorizado;

    /**
     * Crea un chasis con sus caracteristicas tecnicas y stock.
     *
     * @param codigo codigo del componente.
     * @param tipoVehiculoCompatible tipo de vehiculo compatible.
     * @param color color del chasis.
     * @param numeroPlazas numero de plazas.
     * @param tara peso en vacio.
     * @param pesoMaximoAutorizado peso maximo autorizado.
     * @param unidadesDisponibles unidades disponibles.
     */
    public Chasis(String codigo, String tipoVehiculoCompatible, String color,
                  int numeroPlazas, double tara, double pesoMaximoAutorizado,
                  int unidadesDisponibles)
    {
        super(codigo, "Chasis", unidadesDisponibles);
        this.tipoVehiculoCompatible = tipoVehiculoCompatible;
        this.color = color;
        this.numeroPlazas = numeroPlazas;
        this.tara = tara;
        this.pesoMaximoAutorizado = pesoMaximoAutorizado;
    }

    /**
     * Devuelve el tipo de vehiculo compatible.
     *
     * @return tipo de vehiculo compatible.
     */
    public String getTipoVehiculoCompatible()
    {
        return tipoVehiculoCompatible;
    }

    /**
     * Devuelve el color del chasis.
     *
     * @return color del chasis.
     */
    public String getColor()
    {
        return color;
    }

    /**
     * Devuelve el numero de plazas.
     *
     * @return numero de plazas.
     */
    public int getNumeroPlazas()
    {
        return numeroPlazas;
    }

    /**
     * Devuelve la tara del vehiculo.
     *
     * @return tara del vehiculo.
     */
    public double getTara()
    {
        return tara;
    }

    /**
     * Devuelve el peso maximo autorizado.
     *
     * @return peso maximo autorizado.
     */
    public double getPesoMaximoAutorizado()
    {
        return pesoMaximoAutorizado;
    }

    /**
     * Comprueba si el chasis es compatible con el tipo de vehiculo.
     *
     * @param tipoVehiculo tipo de vehiculo que se desea montar.
     * @return true si el chasis es compatible.
     */
    public boolean esCompatibleCon(String tipoVehiculo)
    {
        return tipoVehiculoCompatible.equalsIgnoreCase(tipoVehiculo);
    }

    /**
     * Crea una unidad independiente de este chasis para montaje.
     *
     * @return nueva unidad de chasis.
     */
    protected Componente crearUnidadIndependiente()
    {
        return new Chasis(getCodigo(), tipoVehiculoCompatible, color, numeroPlazas,
                          tara, pesoMaximoAutorizado, 1);
    }
}
