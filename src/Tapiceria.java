 

/**
 * Representa la tapiceria que se instala en un vehiculo.
 *
 * @author cmamani11
 */
public class Tapiceria extends Componente
{
    private TipoTapiceria tipoTapiceria;
    private String color;
    private float metrosCuadrados;

    /**
     * Crea una tapiceria con tipo, color, superficie y stock.
     *
     * @param codigo codigo del componente.
     * @param tipoTapiceria tipo de tapiceria.
     * @param color color de la tapiceria.
     * @param metrosCuadrados superficie necesaria.
     * @param unidadesDisponibles unidades disponibles.
     */
    public Tapiceria(String codigo, TipoTapiceria tipoTapiceria, String color,
                     float metrosCuadrados, int unidadesDisponibles)
    {
        super(codigo, "Tapiceria", unidadesDisponibles);
        this.tipoTapiceria = tipoTapiceria;
        this.color = color;
        this.metrosCuadrados = metrosCuadrados;
    }

    /**
     * Devuelve el tipo de tapiceria.
     *
     * @return tipo de tapiceria.
     */
    public TipoTapiceria getTipoTapiceria()
    {
        return tipoTapiceria;
    }

    /**
     * Devuelve el color.
     *
     * @return color de la tapiceria.
     */
    public String getColor()
    {
        return color;
    }

    /**
     * Devuelve los metros cuadrados necesarios.
     *
     * @return metros cuadrados de tapiceria.
     */
    public float getMetrosCuadrados()
    {
        return metrosCuadrados;
    }

    /**
     * Crea una unidad independiente de esta tapiceria para montaje.
     *
     * @return nueva unidad de tapiceria.
     */
    protected Componente crearUnidadIndependiente()
    {
        return new Tapiceria(getCodigo(), tipoTapiceria, color, metrosCuadrados, 1);
    }
}
