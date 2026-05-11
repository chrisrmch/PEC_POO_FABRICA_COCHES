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

        List<ProcesoProduccionSimple> procesosActivos = new ArrayList<ProcesoProduccionSimple>();
        int segundoActual = 1;

        while (hayPedidosPendientes(pedidos) || !procesosActivos.isEmpty()) {
            iniciarProcesosDisponibles(pedidos, procesosActivos, operariosDisponibles, bitacora);
            if (procesosActivos.isEmpty()) {
                break;
            }

            bitacora.add("Segundo " + segundoActual + ":");

            List<ProcesoProduccionSimple> finalizados = new ArrayList<ProcesoProduccionSimple>();
            for (int i = 0; i < procesosActivos.size(); i++) {
                ProcesoProduccionSimple proceso = procesosActivos.get(i);
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
                                            List<ProcesoProduccionSimple> procesosActivos,
                                            List<Operario> operariosDisponibles,
                                            List<String> bitacora)
    {
        for (int i = 0; i < pedidos.size(); i++) {
            PedidoProduccionSimple pedido = pedidos.get(i);

            if (!pedido.tieneUnidadesPendientes() || existeProcesoActivoParaCadena(procesosActivos, pedido)) {
                continue;
            }

            LoteMontaje loteMontaje = fabrica.prepararLoteMontaje(
                pedido.getCadena(), pedido.getCodigoChasis(), pedido.getCodigoMotor(),
                pedido.getCodigoTapiceria(), pedido.getCodigoRueda()
            );

            if (loteMontaje == null) {
                bitacora.add("No se puede iniciar el pedido " + pedido.getDescripcionPedido()
                             + " por falta de stock o incompatibilidad.");
                pedido.cancelarPendientes();
                continue;
            }

            pedido.consumirUnidadPendiente();
            Operario[] operariosAsignados = seleccionarOperariosAleatorios(operariosDisponibles);
            procesosActivos.add(new ProcesoProduccionSimple(
                pedido.getCadena(), loteMontaje, operariosAsignados
            ));

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

    private boolean existeProcesoActivoParaCadena(List<ProcesoProduccionSimple> procesosActivos,
                                                  PedidoProduccionSimple pedido)
    {
        for (int i = 0; i < procesosActivos.size(); i++) {
            if (procesosActivos.get(i).getCadena().getCodigo().equals(pedido.getCadena().getCodigo())) {
                return true;
            }
        }
        return false;
    }

    private String describirTipoOperario(Operario operario)
    {
        if (operario.getTipoTrabajador() == TipoTrabajador.EFICIENTE || operario.getPiezasMontadas() > 10) {
            return "EFICIENTE";
        }
        return "ESTANDAR";
    }
}
