 

public enum EstadoVehiculo
{
    PENDIENTE("Pendiente"),
    CHASIS("Chasis"),
    MOTOR("Motor"),
    TAPICERIA("Tapiceria"),
    RUEDAS("Ruedas");

    private String descripcion;

    private EstadoVehiculo(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
}
