 

/**
 * Representa a un operario encargado de fases de montaje.
 *
 * @author cmamani11
 */
public class Operario extends Trabajador
{
    private TipoTrabajador tipoTrabajador;
    private int piezasMontadas;

    /**
     * Crea un operario con su informacion laboral y experiencia.
     *
     * @param nombre nombre del operario.
     * @param apellidos apellidos del operario.
     * @param dni documento identificativo.
     * @param direccion direccion postal.
     * @param numeroSeguridadSocial numero de seguridad social.
     * @param salario salario asignado.
     * @param fechaIngreso fecha de ingreso.
     * @param tipoTrabajador clasificacion de rendimiento.
     * @param piezasMontadas piezas montadas anteriormente.
     */
    public Operario(String nombre, String apellidos, String dni,
                    String direccion, String numeroSeguridadSocial,
                    double salario, String fechaIngreso,
                    TipoTrabajador tipoTrabajador, int piezasMontadas)
    {
        super(nombre, apellidos, dni, direccion, numeroSeguridadSocial,
              salario, fechaIngreso, "Operario");
        this.tipoTrabajador = tipoTrabajador;
        this.piezasMontadas = piezasMontadas;
    }

    /**
     * Devuelve el tipo de trabajador del operario.
     *
     * @return tipo de trabajador.
     */
    public TipoTrabajador getTipoTrabajador()
    {
        return tipoTrabajador;
    }

    /**
     * Devuelve las piezas montadas por el operario.
     *
     * @return numero de piezas montadas.
     */
    public int getPiezasMontadas()
    {
        return piezasMontadas;
    }

    /**
     * Calcula el tiempo de montaje segun rendimiento y experiencia.
     *
     * @return segundos necesarios para montar una pieza.
     */
    public int calcularTiempoMontaje()
    {
        if (tipoTrabajador == TipoTrabajador.EFICIENTE || piezasMontadas > 10) {
            return 1;
        }
        return 3;
    }

    /**
     * Incrementa el contador de piezas montadas.
     */
    public void registrarPiezaMontada()
    {
        piezasMontadas++;
    }
}
