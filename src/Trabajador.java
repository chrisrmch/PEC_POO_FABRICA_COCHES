 

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

    public String getNombre()
    {
        return nombre;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public String getDni()
    {
        return dni;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public String getNumeroSeguridadSocial()
    {
        return numeroSeguridadSocial;
    }

    public double getSalario()
    {
        return salario;
    }

    public String getFechaIngreso()
    {
        return fechaIngreso;
    }

    public String getPuesto()
    {
        return puesto;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public void setSalario(double salario)
    {
        if (salario >= 0) {
            this.salario = salario;
        }
    }

    public String getNombreCompleto()
    {
        return nombre + " " + apellidos;
    }
}
