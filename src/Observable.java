public interface Observable
{
    void registrarObservador(Observer observer);

    void eliminarObservador(Observer observer);

    void notificarObservadores();
}
