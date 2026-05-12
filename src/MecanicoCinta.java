 

/**
 * Representa a un mecanico especializado en reparaciones de cinta.
 *
 * @author cmamani11
 */
public class MecanicoCinta extends Trabajador
{
    private TipoTrabajador tipoTrabajador;
    private int reparacionesRealizadas;

    /**
     * Crea un mecanico de cinta con su informacion laboral y experiencia.
     *
     * @param nombre nombre del mecanico.
     * @param apellidos apellidos del mecanico.
     * @param dni documento identificativo.
     * @param direccion direccion postal.
     * @param numeroSeguridadSocial numero de seguridad social.
     * @param salario salario asignado.
     * @param fechaIngreso fecha de ingreso.
     * @param tipoTrabajador clasificacion de rendimiento.
     * @param reparacionesRealizadas reparaciones realizadas previamente.
     */
    public MecanicoCinta(String nombre, String apellidos, String dni,
                         String direccion, String numeroSeguridadSocial,
                         double salario, String fechaIngreso,
                         TipoTrabajador tipoTrabajador, int reparacionesRealizadas)
    {
        super(nombre, apellidos, dni, direccion, numeroSeguridadSocial,
              salario, fechaIngreso, "Mecanico de cinta");
        this.tipoTrabajador = tipoTrabajador;
        this.reparacionesRealizadas = reparacionesRealizadas;
    }

    /**
     * Devuelve el tipo de trabajador del mecanico.
     *
     * @return tipo de trabajador.
     */
    public TipoTrabajador getTipoTrabajador()
    {
        return tipoTrabajador;
    }

    /**
     * Devuelve el numero de reparaciones realizadas.
     *
     * @return reparaciones realizadas.
     */
    public int getReparacionesRealizadas()
    {
        return reparacionesRealizadas;
    }

    /**
     * Calcula el tiempo de reparacion segun rendimiento y experiencia.
     *
     * @return segundos necesarios para reparar.
     */
    public int calcularTiempoReparacion()
    {
        if (tipoTrabajador == TipoTrabajador.EFICIENTE || reparacionesRealizadas > 20) {
            return 1;
        }
        return 3;
    }
}
