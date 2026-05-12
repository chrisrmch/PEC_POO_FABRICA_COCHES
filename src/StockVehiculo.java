/**
 * Controla el stock disponible para un tipo concreto de vehiculo terminado.
 *
 * @author cmamani11
 */
public class StockVehiculo
{
    private String tipoVehiculo;
    private int unidadesDisponibles;

    /**
     * Crea el registro de stock para un tipo de vehiculo.
     *
     * @param tipoVehiculo tipo de vehiculo controlado.
     * @param unidadesDisponibles unidades iniciales disponibles.
     */
    public StockVehiculo(String tipoVehiculo, int unidadesDisponibles)
    {
        this.tipoVehiculo = tipoVehiculo;
        actualizarStock(unidadesDisponibles);
    }

    /**
     * Devuelve el tipo de vehiculo asociado al stock.
     *
     * @return tipo de vehiculo.
     */
    public String getTipoVehiculo()
    {
        return tipoVehiculo;
    }

    /**
     * Devuelve las unidades disponibles.
     *
     * @return numero de unidades disponibles.
     */
    public int getUnidadesDisponibles()
    {
        return unidadesDisponibles;
    }

    /**
     * Sustituye el stock actual por un nuevo valor no negativo.
     *
     * @param nuevasUnidades nuevo numero de unidades.
     */
    public void actualizarStock(int nuevasUnidades)
    {
        if (nuevasUnidades >= 0) {
            this.unidadesDisponibles = nuevasUnidades;
        }
    }

    /**
     * Anade unidades al stock actual.
     *
     * @param unidades unidades que entran en almacen.
     */
    public void registrarEntrada(int unidades)
    {
        if (unidades > 0) {
            unidadesDisponibles += unidades;
        }
    }
}
