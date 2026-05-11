
public class ProcesoProduccionSimple
{
    private static final String[] FASES = {"Chasis", "Motor", "Tapiceria", "Ruedas"};

    private CadenaMontaje cadena;
    private LoteMontaje loteMontaje;
    private Operario[] operariosPorFase;
    private Vehiculo vehiculo;
    private int indiceFaseActual;
    private int segundosRestantesFase;
    private boolean finalizado;

    public ProcesoProduccionSimple(CadenaMontaje cadena, LoteMontaje loteMontaje,
                                   Operario[] operariosPorFase)
    {
        this.cadena = cadena;
        this.loteMontaje = loteMontaje;
        this.operariosPorFase = operariosPorFase;
        this.indiceFaseActual = 0;
        this.segundosRestantesFase = operariosPorFase[0].calcularTiempoMontaje();
        this.finalizado = false;
    }

    public CadenaMontaje getCadena()
    {
        return cadena;
    }

    public Vehiculo getVehiculo()
    {
        return vehiculo;
    }

    public Operario getOperarioActual()
    {
        return operariosPorFase[indiceFaseActual];
    }

    public String getNombreFaseActual()
    {
        return FASES[indiceFaseActual];
    }

    public int getSegundosRestantesFase()
    {
        return segundosRestantesFase;
    }

    public boolean estaFinalizado()
    {
        return finalizado;
    }

    public boolean consumirSegundoDeTrabajo()
    {
        segundosRestantesFase--;
        return segundosRestantesFase <= 0;
    }

    public void ejecutarFaseActual()
    {
        Operario operario = getOperarioActual();

        if (indiceFaseActual == 0) {
            vehiculo = cadena.iniciarMontaje(loteMontaje.getChasis());
        }
        else if (indiceFaseActual == 1) {
            cadena.montarMotorEnVehiculo(vehiculo, loteMontaje.getMotor());
        }
        else if (indiceFaseActual == 2) {
            cadena.montarTapiceriaEnVehiculo(vehiculo, loteMontaje.getTapiceria());
        }
        else if (indiceFaseActual == 3) {
            cadena.montarRuedasEnVehiculo(vehiculo, loteMontaje.getRueda());
        }

        operario.registrarPiezaMontada();

        if (indiceFaseActual == FASES.length - 1) {
            finalizado = true;
        }
        else {
            indiceFaseActual++;
            segundosRestantesFase = operariosPorFase[indiceFaseActual].calcularTiempoMontaje();
        }
    }
}
