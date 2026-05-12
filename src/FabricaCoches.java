

import java.util.List;

/**
 * Fachada principal que coordina cadenas, almacen, trabajadores y dashboard.
 *
 * @author cmamani11
 */
public class FabricaCoches
{
    private CadenaMontaje cadenaDeportiva;
    private CadenaMontaje cadenaTurismo;   private CadenaMontaje cadenaFurgoneta;
    private GestorPlanta gestorPlanta;
    private AdministradorSistema administradorSistema;
    private DashboardEstado dashboard;
    private AlmacenFabrica almacen;
    private GestionTrabajadores gestionTrabajadores;

    /**
     * Crea la fabrica con sus cadenas y responsables principales.
     *
     * @param cadenaDeportiva cadena especializada en deportivos.
     * @param cadenaTurismo cadena especializada en turismos.
     * @param cadenaFurgoneta cadena especializada en furgonetas.
     * @param gestorPlanta gestor responsable de la planta.
     * @param administradorSistema administrador del sistema.
     */
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

    /**
     * Suscribe el dashboard a las cadenas de montaje.
     */
    private void enlazarDashboard()
    {
        cadenaDeportiva.registrarObservador(dashboard);
        cadenaTurismo.registrarObservador(dashboard);
        cadenaFurgoneta.registrarObservador(dashboard);
    }

    /**
     * Registra un trabajador en la fabrica.
     *
     * @param trabajador trabajador que se desea dar de alta.
     * @return true si se registra correctamente.
     */
    public boolean registrarTrabajador(Trabajador trabajador)
    {
        return gestionTrabajadores.darAltaTrabajador(trabajador);
    }

    /**
     * Identifica un trabajador por DNI.
     *
     * @param dni DNI del trabajador.
     * @return trabajador encontrado, o null si no existe.
     */
    public Trabajador identificarTrabajadorPorDni(String dni)
    {
        return gestionTrabajadores.buscarPorDni(dni);
    }

    /**
     * Actualiza la direccion de un trabajador.
     *
     * @param dni DNI del trabajador.
     * @param nuevaDireccion nueva direccion postal.
     * @return true si se actualiza correctamente.
     */
    public boolean actualizarDireccionTrabajador(String dni, String nuevaDireccion)
    {
        return gestionTrabajadores.actualizarDireccion(dni, nuevaDireccion);
    }

    /**
     * Actualiza el salario de un trabajador.
     *
     * @param dni DNI del trabajador.
     * @param nuevoSalario nuevo salario.
     * @return true si se actualiza correctamente.
     */
    public boolean actualizarSalarioTrabajador(String dni, double nuevoSalario)
    {
        return gestionTrabajadores.actualizarSalario(dni, nuevoSalario);
    }

    /**
     * Registra un componente en almacen y lo conecta al dashboard.
     *
     * @param componente componente que se desea registrar.
     */
    public void registrarComponenteEnAlmacen(Componente componente)
    {
        if (componente == null) {
            return;
        }
        componente.registrarObservador(dashboard);
        almacen.registrarComponente(componente);
    }

    /**
     * Actualiza el stock de un componente del almacen.
     *
     * @param codigo codigo del componente.
     * @param nuevasUnidades nuevas unidades disponibles.
     * @return true si el componente existe.
     */
    public boolean actualizarStockComponente(String codigo, int nuevasUnidades)
    {
        return almacen.actualizarStockComponente(codigo, nuevasUnidades);
    }

    /**
     * Actualiza el stock agregado de un tipo de vehiculo.
     *
     * @param tipoVehiculo tipo de vehiculo.
     * @param nuevasUnidades nuevas unidades disponibles.
     * @return true si los datos son validos.
     */
    public boolean actualizarStockVehiculo(String tipoVehiculo, int nuevasUnidades)
    {
        return almacen.actualizarStockVehiculo(tipoVehiculo, nuevasUnidades);
    }

    /**
     * Registra un pedido de produccion tras validar su configuracion.
     *
     * @param pedido pedido que se desea registrar.
     * @return true si el pedido es valido y se registra.
     */
    public boolean registrarPedidoProduccion(PedidoProduccionSimple pedido)
    {
        if (!esPedidoProduccionValido(pedido)) {
            return false;
        }
        return almacen.registrarPedidoProduccion(pedido);
    }

    /**
     * Actualiza un pedido de produccion existente si los nuevos datos son validos.
     *
     * @param codigoPedido codigo del pedido.
     * @param cadena nueva cadena de montaje.
     * @param codigoChasis codigo del chasis.
     * @param codigoMotor codigo del motor.
     * @param codigoTapiceria codigo de la tapiceria.
     * @param codigoRueda codigo de la rueda.
     * @param unidades unidades solicitadas.
     * @return true si el pedido se actualiza.
     */
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

    /**
     * Valida la estructura, componentes y compatibilidad de un pedido.
     *
     * @param pedido pedido que se comprueba.
     * @return true si el pedido puede registrarse.
     */
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

    /**
     * Comprueba si el almacen cubre las unidades pendientes del pedido.
     *
     * @param pedido pedido que se desea fabricar.
     * @return true si hay stock suficiente.
     */
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

    /**
     * Comprueba si los componentes permiten preparar un montaje unitario.
     *
     * @param cadena cadena que realizara el montaje.
     * @param codigoChasis codigo del chasis.
     * @param codigoMotor codigo del motor.
     * @param codigoTapiceria codigo de la tapiceria.
     * @param codigoRueda codigo de la rueda.
     * @return true si hay stock y compatibilidad.
     */
    public boolean puedePrepararMontaje(CadenaMontaje cadena, String codigoChasis,
                                        String codigoMotor, String codigoTapiceria,
                                        String codigoRueda)
    {
        if (cadena == null || !almacen.hayStockParaFabricacion(codigoChasis, codigoMotor,
                                                               codigoTapiceria, codigoRueda)) {
            return false;
        }

        Componente componenteChasis = almacen.buscarComponentePorCodigo(codigoChasis);
        Componente componenteMotor = almacen.buscarComponentePorCodigo(codigoMotor);
        Componente componenteTapiceria = almacen.buscarComponentePorCodigo(codigoTapiceria);
        Componente componenteRueda = almacen.buscarComponentePorCodigo(codigoRueda);

        if (!(componenteChasis instanceof Chasis)
            || !(componenteMotor instanceof Motor)
            || !(componenteTapiceria instanceof Tapiceria)
            || !(componenteRueda instanceof Rueda)) {
            return false;
        }

        Chasis chasisStock = (Chasis) componenteChasis;
        return chasisStock.esCompatibleCon(cadena.getEspecialidad());
    }

    /**
     * Retira y reserva un chasis para montaje.
     *
     * @param codigoChasis codigo del chasis.
     * @param codigoCadena codigo de la cadena que reserva.
     * @return chasis reservado, o null si no existe stock.
     */
    public Chasis prepararChasisParaMontaje(String codigoChasis, String codigoCadena)
    {
        Chasis chasis = almacen.retirarChasis(codigoChasis);
        if (chasis == null) {
            return null;
        }
        prepararComponenteParaMontaje(chasis, codigoCadena);
        return chasis;
    }

    /**
     * Retira y reserva un motor para montaje.
     *
     * @param codigoMotor codigo del motor.
     * @param codigoCadena codigo de la cadena que reserva.
     * @return motor reservado, o null si no existe stock.
     */
    public Motor prepararMotorParaMontaje(String codigoMotor, String codigoCadena)
    {
        Motor motor = almacen.retirarMotor(codigoMotor);
        if (motor == null) {
            return null;
        }
        prepararComponenteParaMontaje(motor, codigoCadena);
        return motor;
    }

    /**
     * Retira y reserva una tapiceria para montaje.
     *
     * @param codigoTapiceria codigo de la tapiceria.
     * @param codigoCadena codigo de la cadena que reserva.
     * @return tapiceria reservada, o null si no existe stock.
     */
    public Tapiceria prepararTapiceriaParaMontaje(String codigoTapiceria, String codigoCadena)
    {
        Tapiceria tapiceria = almacen.retirarTapiceria(codigoTapiceria);
        if (tapiceria == null) {
            return null;
        }
        prepararComponenteParaMontaje(tapiceria, codigoCadena);
        return tapiceria;
    }

    /**
     * Retira y reserva una rueda para montaje.
     *
     * @param codigoRueda codigo de la rueda.
     * @param codigoCadena codigo de la cadena que reserva.
     * @return rueda reservada, o null si no existe stock.
     */
    public Rueda prepararRuedaParaMontaje(String codigoRueda, String codigoCadena)
    {
        Rueda rueda = almacen.retirarRueda(codigoRueda);
        if (rueda == null) {
            return null;
        }
        prepararComponenteParaMontaje(rueda, codigoCadena);
        return rueda;
    }

    /**
     * Fabrica un vehiculo completo tomando los componentes del almacen.
     *
     * @param cadena cadena de montaje que fabricara.
     * @param codigoChasis codigo del chasis.
     * @param codigoMotor codigo del motor.
     * @param codigoTapiceria codigo de la tapiceria.
     * @param codigoRueda codigo de la rueda.
     * @return vehiculo fabricado, o null si no puede prepararse.
     */
    public Vehiculo fabricarVehiculoDesdeAlmacen(CadenaMontaje cadena, String codigoChasis,
                                                 String codigoMotor, String codigoTapiceria,
                                                 String codigoRueda)
    {
        if (!puedePrepararMontaje(cadena, codigoChasis, codigoMotor, codigoTapiceria,
                                  codigoRueda)) {
            return null;
        }

        Chasis chasis = prepararChasisParaMontaje(codigoChasis, cadena.getCodigo());
        Motor motor = prepararMotorParaMontaje(codigoMotor, cadena.getCodigo());
        Tapiceria tapiceria = prepararTapiceriaParaMontaje(codigoTapiceria, cadena.getCodigo());
        Rueda rueda = prepararRuedaParaMontaje(codigoRueda, cadena.getCodigo());

        if (chasis == null || motor == null || tapiceria == null || rueda == null) {
            return null;
        }

        Vehiculo vehiculo = cadena.fabricaVehiculo(chasis, motor, tapiceria, rueda);
        if (vehiculo != null) {
            registrarVehiculoMontado(vehiculo);
        }
        return vehiculo;
    }

    /**
     * Conecta el componente al dashboard y lo marca como reservado.
     *
     * @param componente componente reservado.
     * @param codigoCadena codigo de la cadena que reserva.
     */
    private void prepararComponenteParaMontaje(Componente componente, String codigoCadena)
    {
        componente.registrarObservador(dashboard);
        componente.marcarReservadoParaMontaje(codigoCadena);
    }

    /**
     * Actualiza un vehiculo con componentes ya construidos.
     *
     * @param codigoVehiculo codigo del vehiculo.
     * @param chasis nuevo chasis.
     * @param motor nuevo motor.
     * @param tapiceria nueva tapiceria.
     * @param rueda nueva rueda.
     * @return true si el vehiculo existe.
     */
    public boolean actualizarVehiculoRegistrado(String codigoVehiculo, Chasis chasis,
                                                Motor motor, Tapiceria tapiceria,
                                                Rueda rueda)
    {
        return almacen.actualizarVehiculo(codigoVehiculo, chasis, motor, tapiceria, rueda);
    }

    /**
     * Actualiza un vehiculo tomando sus nuevos componentes desde stock.
     *
     * @param codigoVehiculo codigo del vehiculo.
     * @param codigoChasis codigo del nuevo chasis.
     * @param codigoMotor codigo del nuevo motor.
     * @param codigoTapiceria codigo de la nueva tapiceria.
     * @param codigoRueda codigo de la nueva rueda.
     * @return true si el cambio se completa.
     */
    public boolean actualizarVehiculoRegistradoDesdeStock(String codigoVehiculo, String codigoChasis,
                                                          String codigoMotor, String codigoTapiceria,
                                                          String codigoRueda)
    {
        Vehiculo vehiculo = buscarVehiculoPorCodigo(codigoVehiculo);
        if (vehiculo == null) {
            return false;
        }

        CadenaMontaje cadena = obtenerCadenaParaTipoVehiculo(vehiculo.getTipoVehiculo());
        if (!puedePrepararMontaje(cadena, codigoChasis, codigoMotor, codigoTapiceria,
                                  codigoRueda)) {
            return false;
        }

        Chasis chasis = prepararChasisParaMontaje(codigoChasis, cadena.getCodigo());
        Motor motor = prepararMotorParaMontaje(codigoMotor, cadena.getCodigo());
        Tapiceria tapiceria = prepararTapiceriaParaMontaje(codigoTapiceria, cadena.getCodigo());
        Rueda rueda = prepararRuedaParaMontaje(codigoRueda, cadena.getCodigo());

        if (chasis == null || motor == null || tapiceria == null || rueda == null) {
            return false;
        }

        return almacen.actualizarVehiculo(codigoVehiculo, chasis, motor, tapiceria, rueda);
    }

    /**
     * Registra en almacen un vehiculo terminado.
     *
     * @param vehiculo vehiculo terminado.
     */
    public void registrarVehiculoMontado(Vehiculo vehiculo)
    {
        almacen.registrarVehiculo(vehiculo);
    }

    /**
     * Devuelve los componentes del almacen.
     *
     * @return lista de componentes.
     */
    public List<Componente> getComponentes()
    {
        return almacen.getComponentes();
    }

    /**
     * Devuelve los vehiculos registrados.
     *
     * @return lista de vehiculos.
     */
    public List<Vehiculo> getVehiculos()
    {
        return almacen.getVehiculos();
    }

    /**
     * Devuelve los pedidos de produccion.
     *
     * @return lista de pedidos.
     */
    public List<PedidoProduccionSimple> getPedidosProduccion()
    {
        return almacen.getPedidosProduccion();
    }

    /**
     * Busca un pedido de produccion por codigo.
     *
     * @param codigoPedido codigo del pedido.
     * @return pedido encontrado, o null si no existe.
     */
    public PedidoProduccionSimple buscarPedidoProduccionPorCodigo(String codigoPedido)
    {
        return almacen.buscarPedidoProduccionPorCodigo(codigoPedido);
    }

    /**
     * Devuelve el stock agregado de vehiculos.
     *
     * @return lista de stock por tipo.
     */
    public List<StockVehiculo> getStockVehiculos()
    {
        return almacen.getStockVehiculos();
    }

    /**
     * Busca stock de vehiculos por tipo.
     *
     * @param tipoVehiculo tipo de vehiculo.
     * @return stock encontrado, o null si no existe.
     */
    public StockVehiculo buscarStockVehiculoPorTipo(String tipoVehiculo)
    {
        return almacen.buscarStockVehiculoPorTipo(tipoVehiculo);
    }

    /**
     * Busca un componente por codigo.
     *
     * @param codigo codigo del componente.
     * @return componente encontrado, o null si no existe.
     */
    public Componente buscarComponentePorCodigo(String codigo)
    {
        return almacen.buscarComponentePorCodigo(codigo);
    }

    /**
     * Busca un vehiculo por codigo.
     *
     * @param codigoVehiculo codigo del vehiculo.
     * @return vehiculo encontrado, o null si no existe.
     */
    public Vehiculo buscarVehiculoPorCodigo(String codigoVehiculo)
    {
        return almacen.buscarVehiculoPorCodigo(codigoVehiculo);
    }

    /**
     * Busca vehiculos por tipo.
     *
     * @param tipoVehiculo tipo o fragmento de tipo.
     * @return lista de vehiculos coincidentes.
     */
    public List<Vehiculo> buscarVehiculosPorTipo(String tipoVehiculo)
    {
        return almacen.buscarVehiculosPorTipo(tipoVehiculo);
    }

    /**
     * Devuelve todos los trabajadores.
     *
     * @return lista de trabajadores.
     */
    public List<Trabajador> getTrabajadores()
    {
        return gestionTrabajadores.getTrabajadores();
    }

    /**
     * Busca trabajadores por nombre o apellidos.
     *
     * @param texto texto que se desea buscar.
     * @return lista de trabajadores coincidentes.
     */
    public List<Trabajador> buscarTrabajadoresPorNombreOApellido(String texto)
    {
        return gestionTrabajadores.buscarPorNombreOApellido(texto);
    }

    /**
     * Busca trabajadores por puesto.
     *
     * @param puesto puesto o fragmento de puesto.
     * @return lista de trabajadores coincidentes.
     */
    public List<Trabajador> buscarTrabajadoresPorPuesto(String puesto)
    {
        return gestionTrabajadores.buscarPorPuesto(puesto);
    }

    /**
     * Devuelve los operarios disponibles.
     *
     * @return lista de operarios.
     */
    public List<Operario> getOperarios()
    {
        return gestionTrabajadores.getOperarios();
    }

    /**
     * Devuelve la cadena deportiva.
     *
     * @return cadena de deportivos.
     */
    public CadenaMontaje getCadenaDeportiva()
    {
        return cadenaDeportiva;
    }

    /**
     * Devuelve la cadena de turismos.
     *
     * @return cadena de turismos.
     */
    public CadenaMontaje getCadenaTurismo()
    {
        return cadenaTurismo;
    }

    /**
     * Devuelve la cadena de furgonetas.
     *
     * @return cadena de furgonetas.
     */
    public CadenaMontaje getCadenaFurgoneta()
    {
        return cadenaFurgoneta;
    }

    /**
     * Selecciona la cadena que corresponde a un tipo de vehiculo.
     *
     * @param tipoVehiculo tipo de vehiculo.
     * @return cadena compatible.
     */
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

    /**
     * Devuelve el dashboard de estado.
     *
     * @return dashboard asociado a la fabrica.
     */
    public DashboardEstado getDashboard()
    {
        return dashboard;
    }

    /**
     * Devuelve el numero de componentes catalogados.
     *
     * @return numero de componentes.
     */
    public int getNumeroComponentesEnAlmacen()
    {
        return almacen.getNumeroComponentes();
    }

    /**
     * Devuelve el total de vehiculos disponibles en stock.
     *
     * @return numero total de vehiculos en stock.
     */
    public int getNumeroVehiculosEnAlmacen()
    {
        return almacen.getTotalStockVehiculos();
    }

    /**
     * Devuelve el numero de vehiculos registrados individualmente.
     *
     * @return numero de vehiculos registrados.
     */
    public int getNumeroVehiculosRegistrados()
    {
        return almacen.getNumeroVehiculos();
    }

    /**
     * Devuelve el numero de trabajadores registrados.
     *
     * @return numero de trabajadores.
     */
    public int getNumeroTrabajadores()
    {
        return gestionTrabajadores.getNumeroTrabajadores();
    }

    /**
     * Genera un resumen textual de la organizacion de la fabrica.
     *
     * @return descripcion de responsables y cadenas.
     */
    public String describirOrganizacion()
    {
        return "Gestor: " + gestorPlanta.getNombreCompleto()
               + ", administrador: " + administradorSistema.getNombreCompleto()
               + ", cadenas: " + cadenaDeportiva.getCodigo() + ", "
               + cadenaTurismo.getCodigo() + ", "
               + cadenaFurgoneta.getCodigo();
    }
}
