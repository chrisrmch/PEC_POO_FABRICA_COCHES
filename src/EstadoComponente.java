 

public enum EstadoComponente
{
    EN_ALMACEN("En almacen"),
    RESERVADO("Reservado para montaje"),
    MONTADO("Montado en vehiculo"),
    SIN_STOCK("Sin stock");

    private String descripcion;

    private EstadoComponente(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
}
