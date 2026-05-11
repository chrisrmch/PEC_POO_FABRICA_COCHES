 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardEstado implements Observador
{
    private List<String> eventos;

    public DashboardEstado()
    {
        this.eventos = new ArrayList<String>();
    }

    public void actualizar(Sujeto sujeto)
    {
        if (sujeto instanceof CadenaMontaje) {
            registrarEventoCadena((CadenaMontaje) sujeto);
        }
        else if (sujeto instanceof Componente) {
            registrarEventoComponente((Componente) sujeto);
        }
    }

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

    private void registrarEventoComponente(Componente componente)
    {
        String evento = "Componente " + componente.getNombreComponente()
                        + " [" + componente.getCodigo() + "]"
                        + " - estado " + componente.getDescripcionEstado()
                        + " - referencia " + componente.getReferenciaEstado()
                        + " - stock " + componente.getUnidadesDisponibles();
        eventos.add(evento);
    }

    public List<String> getEventos()
    {
        return Collections.unmodifiableList(eventos);
    }

    public String getUltimoEvento()
    {
        if (eventos.isEmpty()) {
            return "Sin eventos registrados.";
        }
        return eventos.get(eventos.size() - 1);
    }
}
