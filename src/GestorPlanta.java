 

public class GestorPlanta extends Trabajador
{
    public GestorPlanta(String nombre, String apellidos, String dni,
                        String direccion, String numeroSeguridadSocial,
                        double salario, String fechaIngreso)
    {
        super(nombre, apellidos, dni, direccion, numeroSeguridadSocial,
              salario, fechaIngreso, "Gestor de planta");
    }
}
