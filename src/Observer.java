/**
 * Define el contrato de los objetos que reciben cambios de estado.
 *
 * @author cmamani11
 */
public interface Observer
{
    /**
     * Recibe la notificacion enviada por un objeto observable.
     *
     * @param observable objeto que ha cambiado de estado.
     */
    void actualizar(Observable observable);
}
