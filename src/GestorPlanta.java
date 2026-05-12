 

/**
 * Representa al responsable de la gestion general de la planta.
 *
 * @author cmamani11
 */
public class GestorPlanta extends Trabajador
{
    /**
     * Crea un gestor de planta con sus datos laborales.
     *
     * @param nombre nombre del gestor.
     * @param apellidos apellidos del gestor.
     * @param dni documento identificativo.
     * @param direccion direccion postal.
     * @param numeroSeguridadSocial numero de seguridad social.
     * @param salario salario asignado.
     * @param fechaIngreso fecha de ingreso.
     */
    public GestorPlanta(String nombre, String apellidos, String dni,
                        String direccion, String numeroSeguridadSocial,
                        double salario, String fechaIngreso)
    {
        super(nombre, apellidos, dni, direccion, numeroSeguridadSocial,
              salario, fechaIngreso, "Gestor de planta");
    }
}
