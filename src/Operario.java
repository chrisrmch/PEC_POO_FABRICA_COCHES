 

public class Operario extends Trabajador
{
    private TipoTrabajador tipoTrabajador;
    private int piezasMontadas;

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

    public TipoTrabajador getTipoTrabajador()
    {
        return tipoTrabajador;
    }

    public int getPiezasMontadas()
    {
        return piezasMontadas;
    }

    public int calcularTiempoMontaje()
    {
        if (tipoTrabajador == TipoTrabajador.EFICIENTE || piezasMontadas > 10) {
            return 1;
        }
        return 3;
    }

    public void registrarPiezaMontada()
    {
        piezasMontadas++;
    }
}
