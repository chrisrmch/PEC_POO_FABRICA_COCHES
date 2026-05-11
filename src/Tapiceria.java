 

public class Tapiceria extends Componente
{
    private TipoTapiceria tipoTapiceria;
    private String color;
    private float metrosCuadrados;

    public Tapiceria(String codigo, TipoTapiceria tipoTapiceria, String color,
                     float metrosCuadrados, int unidadesDisponibles)
    {
        super(codigo, "Tapiceria", unidadesDisponibles);
        this.tipoTapiceria = tipoTapiceria;
        this.color = color;
        this.metrosCuadrados = metrosCuadrados;
    }

    public TipoTapiceria getTipoTapiceria()
    {
        return tipoTapiceria;
    }

    public String getColor()
    {
        return color;
    }

    public float getMetrosCuadrados()
    {
        return metrosCuadrados;
    }

    protected Componente crearUnidadIndependiente()
    {
        return new Tapiceria(getCodigo(), tipoTapiceria, color, metrosCuadrados, 1);
    }
}
