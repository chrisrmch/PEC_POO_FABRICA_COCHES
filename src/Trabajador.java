 

/**
 * Modelo base de un trabajador de la fabrica.
 *
 * @author cmamani11
 */
public abstract class Trabajador
{
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String numeroSeguridadSocial;
    private double salario;
    private String fechaIngreso;
    private String puesto;

    /**
     * Inicializa los datos comunes de cualquier trabajador.
     *
     * @param nombre nombre del trabajador.
     * @param apellidos apellidos del trabajador.
     * @param dni documento identificativo.
     * @param direccion direccion postal.
     * @param numeroSeguridadSocial numero de seguridad social.
     * @param salario salario asignado.
     * @param fechaIngreso fecha de ingreso en la fabrica.
     * @param puesto puesto desempenado.
     */
    public Trabajador(String nombre, String apellidos, String dni, String direccion,
                      String numeroSeguridadSocial, double salario, String fechaIngreso,
                      String puesto)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.puesto = puesto;
    }

    /**
     * Devuelve el nombre del trabajador.
     *
     * @return nombre del trabajador.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Devuelve los apellidos del trabajador.
     *
     * @return apellidos del trabajador.
     */
    public String getApellidos()
    {
        return apellidos;
    }

    /**
     * Devuelve el DNI del trabajador.
     *
     * @return DNI del trabajador.
     */
    public String getDni()
    {
        return dni;
    }

    /**
     * Devuelve la direccion postal del trabajador.
     *
     * @return direccion del trabajador.
     */
    public String getDireccion()
    {
        return direccion;
    }

    /**
     * Devuelve el numero de seguridad social del trabajador.
     *
     * @return numero de seguridad social.
     */
    public String getNumeroSeguridadSocial()
    {
        return numeroSeguridadSocial;
    }

    /**
     * Devuelve el salario del trabajador.
     *
     * @return salario actual.
     */
    public double getSalario()
    {
        return salario;
    }

    /**
     * Devuelve la fecha de ingreso.
     *
     * @return fecha de ingreso.
     */
    public String getFechaIngreso()
    {
        return fechaIngreso;
    }

    /**
     * Devuelve el puesto del trabajador.
     *
     * @return puesto asignado.
     */
    public String getPuesto()
    {
        return puesto;
    }

    /**
     * Actualiza la direccion postal del trabajador.
     *
     * @param direccion nueva direccion postal.
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    /**
     * Actualiza el salario si el valor recibido es valido.
     *
     * @param salario nuevo salario.
     */
    public void setSalario(double salario)
    {
        if (salario >= 0) {
            this.salario = salario;
        }
    }

    /**
     * Devuelve el nombre completo del trabajador.
     *
     * @return nombre y apellidos concatenados.
     */
    public String getNombreCompleto()
    {
        return nombre + " " + apellidos;
    }
}
