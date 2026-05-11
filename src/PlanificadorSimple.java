import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PlanificadorSimple
{
    private FabricaCoches fabrica;
    private Random random;

    public PlanificadorSimple(FabricaCoches fabrica)
    {
        this.fabrica = fabrica;
        this.random = new Random();
    }

    public List<String> ejecutar(List<PedidoProduccionSimple> pedidos)
    {
        List<String> bitacora = new ArrayList<String>();
        List<Operario> operariosDisponibles = fabrica.getOperarios();

        if (pedidos == null || pedidos.isEmpty()) {
            bitacora.add("No hay pedidos configurados para el planificador simple.");
            return bitacora;
        }
        if (operariosDisponibles.isEmpty()) {
            bitacora.add("No hay operarios disponibles para ejecutar el planificador simple.");
            return bitacora;
        }

        List<ProcesoActivo> procesosActivos = new ArrayList<ProcesoActivo>();
        int segundoActual = 1;

        while (hayPedidosPendientes(pedidos) || !procesosActivos.isEmpty()) {
            iniciarProcesosDisponibles(pedidos, procesosActivos, operariosDisponibles, bitacora);
            if (procesosActivos.isEmpty()) {
                break;
            }

            bitacora.add("Segundo " + segundoActual + ":");

            List<ProcesoActivo> finalizados = new ArrayList<ProcesoActivo>();
            for (int i = 0; i < procesosActivos.size(); i++) {
                ProcesoActivo proceso = procesosActivos.get(i);
                Operario operario = proceso.getOperarioActual();

                bitacora.add("  Cadena " + proceso.getCadena().getCodigo()
                             + " | fase " + proceso.getNombreFaseActual()
                             + " | operario " + operario.getNombreCompleto()
                             + " [" + describirTipoOperario(operario) + "]"
                             + " | restante " + proceso.getSegundosRestantesFase() + " s");

                boolean faseTerminada = proceso.consumirSegundoDeTrabajo();
                if (faseTerminada) {
                    proceso.ejecutarFaseActual();
                    Vehiculo vehiculo = proceso.getVehiculo();

                    bitacora.add("    Fase completada en " + proceso.getCadena().getCodigo()
                                 + " -> " + proceso.getCadena().getFaseActual());

                    if (proceso.estaFinalizado()) {
                        fabrica.registrarVehiculoMontado(vehiculo);
                        finalizados.add(proceso);
                        bitacora.add("    Vehiculo terminado: " + vehiculo.getCodigoVehiculo()
                                     + " (" + vehiculo.getTipoVehiculo() + ")");
                    }
                }
            }

            procesosActivos.removeAll(finalizados);
            segundoActual++;
        }

        bitacora.add("Planificador simple finalizado.");
        return bitacora;
    }

    private void iniciarProcesosDisponibles(List<PedidoProduccionSimple> pedidos,
                                            List<ProcesoActivo> procesosActivos,
                                            List<Operario> operariosDisponibles,
                                            List<String> bitacora)
    {
        for (int i = 0; i < pedidos.size(); i++) {
            PedidoProduccionSimple pedido = pedidos.get(i);

            if (!pedido.tieneUnidadesPendientes() || existeProcesoActivoParaCadena(procesosActivos, pedido)) {
                continue;
            }

            if (!fabrica.puedePrepararMontaje(pedido.getCadena(), pedido.getCodigoChasis(),
                                              pedido.getCodigoMotor(), pedido.getCodigoTapiceria(),
                                              pedido.getCodigoRueda())) {
                bitacora.add("No se puede iniciar el pedido " + pedido.getDescripcionPedido()
                             + " por falta de stock o incompatibilidad.");
                pedido.cancelarPendientes();
                continue;
            }

            String codigoCadena = pedido.getCadena().getCodigo();
            Chasis chasis = fabrica.prepararChasisParaMontaje(pedido.getCodigoChasis(), codigoCadena);
            Motor motor = fabrica.prepararMotorParaMontaje(pedido.getCodigoMotor(), codigoCadena);
            Tapiceria tapiceria = fabrica.prepararTapiceriaParaMontaje(pedido.getCodigoTapiceria(), codigoCadena);
            Rueda rueda = fabrica.prepararRuedaParaMontaje(pedido.getCodigoRueda(), codigoCadena);

            if (chasis == null || motor == null || tapiceria == null || rueda == null) {
                bitacora.add("No se puede iniciar el pedido " + pedido.getDescripcionPedido()
                             + " porque no se han podido reservar sus componentes.");
                pedido.cancelarPendientes();
                continue;
            }

            pedido.consumirUnidadPendiente();
            Operario[] operariosAsignados = seleccionarOperariosAleatorios(operariosDisponibles);
            procesosActivos.add(new ProcesoActivo(pedido.getCadena(), chasis, motor, tapiceria,
                                                  rueda, operariosAsignados));

            bitacora.add("Pedido iniciado en cadena " + pedido.getCadena().getCodigo()
                         + " con operarios aleatorios para las fases.");
        }
    }

    private Operario[] seleccionarOperariosAleatorios(List<Operario> operariosDisponibles)
    {
        Operario[] operarios = new Operario[4];

        for (int i = 0; i < operarios.length; i++) {
            int indice = random.nextInt(operariosDisponibles.size());
            operarios[i] = operariosDisponibles.get(indice);
        }

        return operarios;
    }

    private boolean hayPedidosPendientes(List<PedidoProduccionSimple> pedidos)
    {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).tieneUnidadesPendientes()) {
                return true;
            }
        }
        return false;
    }

    private boolean existeProcesoActivoParaCadena(List<ProcesoActivo> procesosActivos,
                                                  PedidoProduccionSimple pedido)
    {
        for (int i = 0; i < procesosActivos.size(); i++) {
            if (procesosActivos.get(i).getCadena().getCodigo().equals(pedido.getCadena().getCodigo())) {
                return true;
            }
        }
        return false;
    }

    private class ProcesoActivo
    {
        private static final String[] FASES = {"Chasis", "Motor", "Tapiceria", "Ruedas"};

        private CadenaMontaje cadena;
        private Chasis chasis;
        private Motor motor;
        private Tapiceria tapiceria;
        private Rueda rueda;
        private Operario[] operariosPorFase;
        private Vehiculo vehiculo;
        private int indiceFaseActual;
        private int segundosRestantesFase;
        private boolean finalizado;

        public ProcesoActivo(CadenaMontaje cadena, Chasis chasis, Motor motor,
                             Tapiceria tapiceria, Rueda rueda, Operario[] operariosPorFase)
        {
            this.cadena = cadena;
            this.chasis = chasis;
            this.motor = motor;
            this.tapiceria = tapiceria;
            this.rueda = rueda;
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
                vehiculo = cadena.iniciarMontaje(chasis);
            }
            else if (indiceFaseActual == 1) {
                cadena.montarMotorEnVehiculo(vehiculo, motor);
            }
            else if (indiceFaseActual == 2) {
                cadena.montarTapiceriaEnVehiculo(vehiculo, tapiceria);
            }
            else if (indiceFaseActual == 3) {
                cadena.montarRuedasEnVehiculo(vehiculo, rueda);
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

    private String describirTipoOperario(Operario operario)
    {
        if (operario.getTipoTrabajador() == TipoTrabajador.EFICIENTE || operario.getPiezasMontadas() > 10) {
            return "EFICIENTE";
        }
        return "ESTANDAR";
    }
}
