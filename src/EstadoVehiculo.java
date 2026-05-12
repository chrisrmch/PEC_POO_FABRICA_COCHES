 

/**
 * Estados de avance de un vehiculo durante su montaje.
 *
 * @author cmamani11
 */
public enum EstadoVehiculo
{
    PENDIENTE("Pendiente"),
    CHASIS("Chasis"),
    MOTOR("Motor"),
    TAPICERIA("Tapiceria"),
    RUEDAS("Ruedas");

    private String descripcion;

    /**
     * Crea un estado de vehiculo con una descripcion legible.
     *
     * @param descripcion texto asociado al estado.
     */
    private EstadoVehiculo(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la descripcion asociada al estado de montaje.
     *
     * @return descripcion del estado.
     */
    public String getDescripcion()
    {
        return descripcion;
    }
}
