 

public class MecanicoCinta extends Trabajador
{
    private TipoTrabajador tipoTrabajador;
    private int reparacionesRealizadas;

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

    public TipoTrabajador getTipoTrabajador()
    {
        return tipoTrabajador;
    }

    public int getReparacionesRealizadas()
    {
        return reparacionesRealizadas;
    }

    public int calcularTiempoReparacion()
    {
        if (tipoTrabajador == TipoTrabajador.EFICIENTE || reparacionesRealizadas > 20) {
            return 1;
        }
        return 3;
    }
}
