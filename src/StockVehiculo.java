public class StockVehiculo
{
    private String tipoVehiculo;
    private int unidadesDisponibles;

    public StockVehiculo(String tipoVehiculo, int unidadesDisponibles)
    {
        this.tipoVehiculo = tipoVehiculo;
        actualizarStock(unidadesDisponibles);
    }

    public String getTipoVehiculo()
    {
        return tipoVehiculo;
    }

    public int getUnidadesDisponibles()
    {
        return unidadesDisponibles;
    }

    public void actualizarStock(int nuevasUnidades)
    {
        if (nuevasUnidades >= 0) {
            this.unidadesDisponibles = nuevasUnidades;
        }
    }

    public void registrarEntrada(int unidades)
    {
        if (unidades > 0) {
            unidadesDisponibles += unidades;
        }
    }
}
