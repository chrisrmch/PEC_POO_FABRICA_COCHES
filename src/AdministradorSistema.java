 

/**
 * Representa al administrador encargado de reactivar la gestion y las cadenas.
 *
 * @author cmamani11
 */
public class AdministradorSistema extends Trabajador
{
    private static final int SEGUNDOS_REACTIVAR_GESTION = 2;
    private static final int SEGUNDOS_REACTIVAR_CADENAS = 3;

    /**
     * Crea un administrador del sistema con sus datos laborales.
     *
     * @param nombre nombre del administrador.
     * @param apellidos apellidos del administrador.
     * @param dni documento identificativo.
     * @param direccion direccion postal.
     * @param numeroSeguridadSocial numero de seguridad social.
     * @param salario salario asignado.
     * @param fechaIngreso fecha de ingreso.
     */
    public AdministradorSistema(String nombre, String apellidos, String dni,
                                String direccion, String numeroSeguridadSocial,
                                double salario, String fechaIngreso)
    {
        super(nombre, apellidos, dni, direccion, numeroSeguridadSocial,
              salario, fechaIngreso, "Administrador del sistema");
    }

    /**
     * Devuelve el tiempo necesario para reactivar la gestion.
     *
     * @return segundos de reactivacion de gestion.
     */
    public int getSegundosReactivarGestion()
    {
        return SEGUNDOS_REACTIVAR_GESTION;
    }

    /**
     * Devuelve el tiempo necesario para reactivar las cadenas.
     *
     * @return segundos de reactivacion de cadenas.
     */
    public int getSegundosReactivarCadenas()
    {
        return SEGUNDOS_REACTIVAR_CADENAS;
    }
}
