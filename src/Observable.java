/**
 * Define el contrato de los objetos que pueden publicar cambios de estado.
 *
 * @author cmamani11
 */
public interface Observable
{
    /**
     * Registra un observador para futuras notificaciones.
     *
     * @param observer observador que se desea registrar.
     */
    void registrarObservador(Observer observer);

    /**
     * Elimina un observador previamente registrado.
     *
     * @param observer observador que se desea eliminar.
     */
    void eliminarObservador(Observer observer);

    /**
     * Notifica el cambio de estado a todos los observadores registrados.
     */
    void notificarObservadores();
}
