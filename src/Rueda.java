 

/**
 * Representa una rueda disponible para el montaje de vehiculos.
 *
 * @author cmamani11
 */
public class Rueda extends Componente
{
    private TipoRueda tipoRueda;
    private float anchoMm;
    private float diametroLlanta;
    private float indiceCargaKg;
    private float codigoVelocidadKmH;

    /**
     * Crea una rueda con sus caracteristicas tecnicas y stock.
     *
     * @param codigo codigo del componente.
     * @param tipoRueda tipo de rueda.
     * @param anchoMm ancho en milimetros.
     * @param diametroLlanta diametro de la llanta.
     * @param indiceCargaKg indice de carga en kilogramos.
     * @param codigoVelocidadKmH velocidad maxima asociada.
     * @param unidadesDisponibles unidades disponibles.
     */
    public Rueda(String codigo, TipoRueda tipoRueda, float anchoMm,
                 float diametroLlanta, float indiceCargaKg,
                 float codigoVelocidadKmH, int unidadesDisponibles)
    {
        super(codigo, "Rueda", unidadesDisponibles);
        this.tipoRueda = tipoRueda;
        this.anchoMm = anchoMm;
        this.diametroLlanta = diametroLlanta;
        this.indiceCargaKg = indiceCargaKg;
        this.codigoVelocidadKmH = codigoVelocidadKmH;
    }

    /**
     * Devuelve el tipo de rueda.
     *
     * @return tipo de rueda.
     */
    public TipoRueda getTipoRueda()
    {
        return tipoRueda;
    }

    /**
     * Devuelve el ancho de la rueda.
     *
     * @return ancho en milimetros.
     */
    public float getAnchoMm()
    {
        return anchoMm;
    }

    /**
     * Devuelve el diametro de la llanta.
     *
     * @return diametro de llanta.
     */
    public float getDiametroLlanta()
    {
        return diametroLlanta;
    }

    /**
     * Devuelve el indice de carga.
     *
     * @return indice de carga en kilogramos.
     */
    public float getIndiceCargaKg()
    {
        return indiceCargaKg;
    }

    /**
     * Devuelve la velocidad maxima asociada.
     *
     * @return velocidad maxima en km/h.
     */
    public float getCodigoVelocidadKmH()
    {
        return codigoVelocidadKmH;
    }

    /**
     * Crea una unidad independiente de esta rueda para montaje.
     *
     * @return nueva unidad de rueda.
     */
    protected Componente crearUnidadIndependiente()
    {
        return new Rueda(getCodigo(), tipoRueda, anchoMm, diametroLlanta,
                         indiceCargaKg, codigoVelocidadKmH, 1);
    }
}
