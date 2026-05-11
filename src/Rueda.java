 

public class Rueda extends Componente
{
    private TipoRueda tipoRueda;
    private float anchoMm;
    private float diametroLlanta;
    private float indiceCargaKg;
    private float codigoVelocidadKmH;

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

    public TipoRueda getTipoRueda()
    {
        return tipoRueda;
    }

    public float getAnchoMm()
    {
        return anchoMm;
    }

    public float getDiametroLlanta()
    {
        return diametroLlanta;
    }

    public float getIndiceCargaKg()
    {
        return indiceCargaKg;
    }

    public float getCodigoVelocidadKmH()
    {
        return codigoVelocidadKmH;
    }

    protected Componente crearUnidadIndependiente()
    {
        return new Rueda(getCodigo(), tipoRueda, anchoMm, diametroLlanta,
                         indiceCargaKg, codigoVelocidadKmH, 1);
    }
}
