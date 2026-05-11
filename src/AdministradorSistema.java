 

public class AdministradorSistema extends Trabajador
{
    private static final int SEGUNDOS_REACTIVAR_GESTION = 2;
    private static final int SEGUNDOS_REACTIVAR_CADENAS = 3;

    public AdministradorSistema(String nombre, String apellidos, String dni,
                                String direccion, String numeroSeguridadSocial,
                                double salario, String fechaIngreso)
    {
        super(nombre, apellidos, dni, direccion, numeroSeguridadSocial,
              salario, fechaIngreso, "Administrador del sistema");
    }

    public int getSegundosReactivarGestion()
    {
        return SEGUNDOS_REACTIVAR_GESTION;
    }

    public int getSegundosReactivarCadenas()
    {
        return SEGUNDOS_REACTIVAR_CADENAS;
    }
}
