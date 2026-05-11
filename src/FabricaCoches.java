

import java.util.List;

public class FabricaCoches
{
    private CadenaMontaje cadenaDeportiva;
    private CadenaMontaje cadenaTurismo;   private CadenaMontaje cadenaFurgoneta;
    private GestorPlanta gestorPlanta;
    private AdministradorSistema administradorSistema;
    private DashboardEstado dashboard;
    private AlmacenFabrica almacen;
    private GestionTrabajadores gestionTrabajadores;

    public FabricaCoches(CadenaMontaje cadenaDeportiva, CadenaMontaje cadenaTurismo,
                         CadenaMontaje cadenaFurgoneta, GestorPlanta gestorPlanta,
                         AdministradorSistema administradorSistema)
    {
        this.cadenaDeportiva = cadenaDeportiva;
        this.cadenaTurismo = cadenaTurismo;
        this.cadenaFurgoneta = cadenaFurgoneta;
        this.gestorPlanta = gestorPlanta;
        this.administradorSistema = administradorSistema;
        this.dashboard = new DashboardEstado();
        this.almacen = new AlmacenFabrica();
        this.gestionTrabajadores = new GestionTrabajadores();

        enlazarDashboard();
        registrarTrabajador(gestorPlanta);
        registrarTrabajador(administradorSistema);
    }

    private void enlazarDashboard()
    {
        cadenaDeportiva.registrarObservador(dashboard);
        cadenaTurismo.registrarObservador(dashboard);
        cadenaFurgoneta.registrarObservador(dashboard);
    }

    public boolean registrarTrabajador(Trabajador trabajador)
    {
        return gestionTrabajadores.darAltaTrabajador(trabajador);
    }

    public Trabajador identificarTrabajadorPorDni(String dni)
    {
        return gestionTrabajadores.buscarPorDni(dni);
    }

    public boolean actualizarDireccionTrabajador(String dni, String nuevaDireccion)
    {
        return gestionTrabajadores.actualizarDireccion(dni, nuevaDireccion);
    }

    public boolean actualizarSalarioTrabajador(String dni, double nuevoSalario)
    {
        return gestionTrabajadores.actualizarSalario(dni, nuevoSalario);
    }

    public void registrarComponenteEnAlmacen(Componente componente)
    {
        if (componente == null) {
            return;
        }
        componente.registrarObservador(dashboard);
        almacen.registrarComponente(componente);
    }

    public boolean actualizarStockComponente(String codigo, int nuevasUnidades)
    {
        return almacen.actualizarStockComponente(codigo, nuevasUnidades);
    }

    public boolean actualizarStockVehiculo(String tipoVehiculo, int nuevasUnidades)
    {
        return almacen.actualizarStockVehiculo(tipoVehiculo, nuevasUnidades);
    }

    public boolean registrarPedidoProduccion(PedidoProduccionSimple pedido)
    {
        if (!esPedidoProduccionValido(pedido)) {
            return false;
        }
        return almacen.registrarPedidoProduccion(pedido);
    }

    public boolean actualizarPedidoProduccion(String codigoPedido, CadenaMontaje cadena,
                                              String codigoChasis, String codigoMotor,
                                              String codigoTapiceria, String codigoRueda,
                                              int unidades)
    {
        PedidoProduccionSimple pedido = new PedidoProduccionSimple(
            codigoPedido, cadena, codigoChasis, codigoMotor, codigoTapiceria, codigoRueda, unidades
        );

        if (!esPedidoProduccionValido(pedido)) {
            return false;
        }
        return almacen.actualizarPedidoProduccion(codigoPedido, cadena, codigoChasis,
                                                  codigoMotor, codigoTapiceria, codigoRueda,
                                                  unidades);
    }

    private boolean esPedidoProduccionValido(PedidoProduccionSimple pedido)
    {
        if (pedido == null || pedido.getCadena() == null || pedido.getUnidadesSolicitadas() <= 0) {
            return false;
        }

        Componente componenteChasis = almacen.buscarComponentePorCodigo(pedido.getCodigoChasis());
        Componente componenteMotor = almacen.buscarComponentePorCodigo(pedido.getCodigoMotor());
        Componente componenteTapiceria = almacen.buscarComponentePorCodigo(pedido.getCodigoTapiceria());
        Componente componenteRueda = almacen.buscarComponentePorCodigo(pedido.getCodigoRueda());

        if (!(componenteChasis instanceof Chasis)
            || !(componenteMotor instanceof Motor)
            || !(componenteTapiceria instanceof Tapiceria)
            || !(componenteRueda instanceof Rueda)) {
            return false;
        }

        Chasis chasis = (Chasis) componenteChasis;
        return chasis.esCompatibleCon(pedido.getCadena().getEspecialidad());
    }

    public boolean hayStockParaPedidoProduccion(PedidoProduccionSimple pedido)
    {
        if (!esPedidoProduccionValido(pedido)) {
            return false;
        }
        if (pedido.getUnidadesPendientes() == 0) {
            return true;
        }

        return almacen.hayStockParaFabricacion(pedido.getCodigoChasis(), pedido.getCodigoMotor(),
                                               pedido.getCodigoTapiceria(), pedido.getCodigoRueda(),
                                               pedido.getUnidadesPendientes());
    }

    public LoteMontaje prepararLoteMontaje(CadenaMontaje cadena, String codigoChasis,
                                           String codigoMotor, String codigoTapiceria,
                                           String codigoRueda)
    {
        if (cadena == null || !almacen.hayStockParaFabricacion(codigoChasis, codigoMotor,
                                                               codigoTapiceria, codigoRueda)) {
            return null;
        }

        Componente componenteChasis = almacen.buscarComponentePorCodigo(codigoChasis);
        Componente componenteMotor = almacen.buscarComponentePorCodigo(codigoMotor);
        Componente componenteTapiceria = almacen.buscarComponentePorCodigo(codigoTapiceria);
        Componente componenteRueda = almacen.buscarComponentePorCodigo(codigoRueda);

        if (!(componenteChasis instanceof Chasis)
            || !(componenteMotor instanceof Motor)
            || !(componenteTapiceria instanceof Tapiceria)
            || !(componenteRueda instanceof Rueda)) {
            return null;
        }

        Chasis chasisStock = (Chasis) componenteChasis;
        if (!chasisStock.esCompatibleCon(cadena.getEspecialidad())) {
            return null;
        }

        Chasis chasis = almacen.retirarChasis(codigoChasis);
        Motor motor = almacen.retirarMotor(codigoMotor);
        Tapiceria tapiceria = almacen.retirarTapiceria(codigoTapiceria);
        Rueda rueda = almacen.retirarRueda(codigoRueda);

        if (chasis == null || motor == null || tapiceria == null || rueda == null) {
            return null;
        }

        prepararComponenteParaMontaje(chasis, cadena.getCodigo());
        prepararComponenteParaMontaje(motor, cadena.getCodigo());
        prepararComponenteParaMontaje(tapiceria, cadena.getCodigo());
        prepararComponenteParaMontaje(rueda, cadena.getCodigo());

        return new LoteMontaje(chasis, motor, tapiceria, rueda);
    }

    public Vehiculo fabricarVehiculoDesdeAlmacen(CadenaMontaje cadena, String codigoChasis,
                                                 String codigoMotor, String codigoTapiceria,
                                                 String codigoRueda)
    {
        LoteMontaje loteMontaje = prepararLoteMontaje(cadena, codigoChasis, codigoMotor,
                                                      codigoTapiceria, codigoRueda);
        if (loteMontaje == null) {
            return null;
        }

        Vehiculo vehiculo = cadena.fabricaVehiculo(loteMontaje.getChasis(), loteMontaje.getMotor(),
                                                   loteMontaje.getTapiceria(), loteMontaje.getRueda());
        if (vehiculo != null) {
            registrarVehiculoMontado(vehiculo);
        }
        return vehiculo;
    }

    private void prepararComponenteParaMontaje(Componente componente, String codigoCadena)
    {
        componente.registrarObservador(dashboard);
        componente.marcarReservadoParaMontaje(codigoCadena);
    }

    public boolean actualizarVehiculoRegistrado(String codigoVehiculo, Chasis chasis,
                                                Motor motor, Tapiceria tapiceria,
                                                Rueda rueda)
    {
        return almacen.actualizarVehiculo(codigoVehiculo, chasis, motor, tapiceria, rueda);
    }

    public boolean actualizarVehiculoRegistradoDesdeStock(String codigoVehiculo, String codigoChasis,
                                                          String codigoMotor, String codigoTapiceria,
                                                          String codigoRueda)
    {
        Vehiculo vehiculo = buscarVehiculoPorCodigo(codigoVehiculo);
        if (vehiculo == null) {
            return false;
        }

        CadenaMontaje cadena = obtenerCadenaParaTipoVehiculo(vehiculo.getTipoVehiculo());
        LoteMontaje loteMontaje = prepararLoteMontaje(cadena, codigoChasis, codigoMotor,
                                                      codigoTapiceria, codigoRueda);
        if (loteMontaje == null) {
            return false;
        }

        return almacen.actualizarVehiculo(codigoVehiculo, loteMontaje.getChasis(),
                                          loteMontaje.getMotor(), loteMontaje.getTapiceria(),
                                          loteMontaje.getRueda());
    }

    public void registrarVehiculoMontado(Vehiculo vehiculo)
    {
        almacen.registrarVehiculo(vehiculo);
    }

    public List<Componente> getComponentes()
    {
        return almacen.getComponentes();
    }

    public List<Vehiculo> getVehiculos()
    {
        return almacen.getVehiculos();
    }

    public List<PedidoProduccionSimple> getPedidosProduccion()
    {
        return almacen.getPedidosProduccion();
    }

    public PedidoProduccionSimple buscarPedidoProduccionPorCodigo(String codigoPedido)
    {
        return almacen.buscarPedidoProduccionPorCodigo(codigoPedido);
    }

    public List<StockVehiculo> getStockVehiculos()
    {
        return almacen.getStockVehiculos();
    }

    public StockVehiculo buscarStockVehiculoPorTipo(String tipoVehiculo)
    {
        return almacen.buscarStockVehiculoPorTipo(tipoVehiculo);
    }

    public Componente buscarComponentePorCodigo(String codigo)
    {
        return almacen.buscarComponentePorCodigo(codigo);
    }

    public Vehiculo buscarVehiculoPorCodigo(String codigoVehiculo)
    {
        return almacen.buscarVehiculoPorCodigo(codigoVehiculo);
    }

    public List<Vehiculo> buscarVehiculosPorTipo(String tipoVehiculo)
    {
        return almacen.buscarVehiculosPorTipo(tipoVehiculo);
    }

    public List<Trabajador> getTrabajadores()
    {
        return gestionTrabajadores.getTrabajadores();
    }

    public List<Trabajador> buscarTrabajadoresPorNombreOApellido(String texto)
    {
        return gestionTrabajadores.buscarPorNombreOApellido(texto);
    }

    public List<Trabajador> buscarTrabajadoresPorPuesto(String puesto)
    {
        return gestionTrabajadores.buscarPorPuesto(puesto);
    }

    public List<Operario> getOperarios()
    {
        return gestionTrabajadores.getOperarios();
    }

    public CadenaMontaje getCadenaDeportiva()
    {
        return cadenaDeportiva;
    }

    public CadenaMontaje getCadenaTurismo()
    {
        return cadenaTurismo;
    }

    public CadenaMontaje getCadenaFurgoneta()
    {
        return cadenaFurgoneta;
    }

    private CadenaMontaje obtenerCadenaParaTipoVehiculo(String tipoVehiculo)
    {
        if (cadenaDeportiva.getEspecialidad().equalsIgnoreCase(tipoVehiculo)) {
            return cadenaDeportiva;
        }
        if (cadenaTurismo.getEspecialidad().equalsIgnoreCase(tipoVehiculo)) {
            return cadenaTurismo;
        }
        return cadenaFurgoneta;
    }

    public DashboardEstado getDashboard()
    {
        return dashboard;
    }

    public int getNumeroComponentesEnAlmacen()
    {
        return almacen.getNumeroComponentes();
    }

    public int getNumeroVehiculosEnAlmacen()
    {
        return almacen.getTotalStockVehiculos();
    }

    public int getNumeroVehiculosRegistrados()
    {
        return almacen.getNumeroVehiculos();
    }

    public int getNumeroTrabajadores()
    {
        return gestionTrabajadores.getNumeroTrabajadores();
    }

    public String describirOrganizacion()
    {
        return "Gestor: " + gestorPlanta.getNombreCompleto()
               + ", administrador: " + administradorSistema.getNombreCompleto()
               + ", cadenas: " + cadenaDeportiva.getCodigo() + ", "
               + cadenaTurismo.getCodigo() + ", "
               + cadenaFurgoneta.getCodigo();
    }
}
