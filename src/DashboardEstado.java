 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Registra eventos producidos por cadenas de montaje y componentes.
 *
 * @author cmamani11
 */
public class DashboardEstado implements Observer
{
    private List<String> eventos;

    /**
     * Crea un dashboard sin eventos iniciales.
     */
    public DashboardEstado()
    {
        this.eventos = new ArrayList<String>();
    }

    /**
     * Recibe cambios de objetos observables y los transforma en eventos.
     *
     * @param observable objeto que ha publicado el cambio.
     */
    public void actualizar(Observable observable)
    {
        if (observable instanceof CadenaMontaje) {
            registrarEventoCadena((CadenaMontaje) observable);
        }
        else if (observable instanceof Componente) {
            registrarEventoComponente((Componente) observable);
        }
    }

    /**
     * Registra un evento asociado a una cadena de montaje.
     *
     * @param cadena cadena que ha cambiado de fase.
     */
    private void registrarEventoCadena(CadenaMontaje cadena)
    {
        Vehiculo vehiculo = cadena.getVehiculoActual();
        String codigoVehiculo = "Sin vehiculo";
        if (vehiculo != null) {
            codigoVehiculo = vehiculo.getCodigoVehiculo();
        }

        String evento = "Cadena " + cadena.getCodigo()
                        + " - fase " + cadena.getFaseActual()
                        + " - vehiculo " + codigoVehiculo;
        eventos.add(evento);
    }

    /**
     * Registra un evento asociado al estado de un componente.
     *
     * @param componente componente que ha cambiado de estado.
     */
    private void registrarEventoComponente(Componente componente)
    {
        String evento = "Componente " + componente.getNombreComponente()
                        + " [" + componente.getCodigo() + "]"
                        + " - estado " + componente.getDescripcionEstado()
                        + " - referencia " + componente.getReferenciaEstado()
                        + " - stock " + componente.getUnidadesDisponibles();
        eventos.add(evento);
    }

    /**
     * Devuelve los eventos registrados.
     *
     * @return lista no modificable de eventos.
     */
    public List<String> getEventos()
    {
        return Collections.unmodifiableList(eventos);
    }

    /**
     * Devuelve el ultimo evento registrado.
     *
     * @return ultimo evento o mensaje por defecto.
     */
    public String getUltimoEvento()
    {
        if (eventos.isEmpty()) {
            return "Sin eventos registrados.";
        }
        return eventos.get(eventos.size() - 1);
    }
}
