 

public class Chasis extends Componente
{
    private String tipoVehiculoCompatible;
    private String color;
    private int numeroPlazas;
    private double tara;
    private double pesoMaximoAutorizado;

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

    public String getTipoVehiculoCompatible()
    {
        return tipoVehiculoCompatible;
    }

    public String getColor()
    {
        return color;
    }

    public int getNumeroPlazas()
    {
        return numeroPlazas;
    }

    public double getTara()
    {
        return tara;
    }

    public double getPesoMaximoAutorizado()
    {
        return pesoMaximoAutorizado;
    }

    public boolean esCompatibleCon(String tipoVehiculo)
    {
        return tipoVehiculoCompatible.equalsIgnoreCase(tipoVehiculo);
    }

    protected Componente crearUnidadIndependiente()
    {
        return new Chasis(getCodigo(), tipoVehiculoCompatible, color, numeroPlazas,
                          tara, pesoMaximoAutorizado, 1);
    }
}
