 

public interface Sujeto
{
    void registrarObservador(Observador observador);

    void eliminarObservador(Observador observador);

    void notificarObservadores();
}
