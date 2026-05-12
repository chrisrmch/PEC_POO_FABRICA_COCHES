 

/**
 * Estados posibles de un componente durante su ciclo en almacen y montaje.
 *
 * @author cmamani11
 */
public enum EstadoComponente
{
    EN_ALMACEN("En almacen"),
    RESERVADO("Reservado para montaje"),
    MONTADO("Montado en vehiculo"),
    SIN_STOCK("Sin stock");

    private String descripcion;

    /**
     * Crea un estado de componente con una descripcion legible.
     *
     * @param descripcion texto asociado al estado.
     */
    private EstadoComponente(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la descripcion asociada al estado.
     *
     * @return descripcion del estado.
     */
    public String getDescripcion()
    {
        return descripcion;
    }
}
