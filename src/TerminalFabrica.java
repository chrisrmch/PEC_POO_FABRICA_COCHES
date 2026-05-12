import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Interfaz de consola para operar la fabrica y sus opciones de gestion.
 *
 * @author cmamani11
 */
public class TerminalFabrica
{
    private FabricaCoches fabrica;
    private Scanner scanner;

    /**
     * Crea una terminal asociada a una fabrica y a un lector de entrada.
     *
     * @param fabrica fabrica que se gestionara desde la terminal.
     * @param scanner lector de entrada de usuario.
     */
    public TerminalFabrica(FabricaCoches fabrica, Scanner scanner)
    {
        this.fabrica = fabrica;
        this.scanner = scanner;
    }

    /**
     * Inicia la terminal y muestra el menu principal.
     */
    public void iniciar()
    {
        try {
            System.out.println("Sistema de gestion de fabrica de vehiculos");
            System.out.println(fabrica.describirOrganizacion());
            mostrarMenuPrincipal();
        }
        catch (IllegalStateException e) {
            System.out.println("La terminal se ha cerrado porque no hay entrada disponible: " + e.getMessage());
        }
    }

    /**
     * Muestra el menu principal y distribuye la navegacion.
     */
    private void mostrarMenuPrincipal()
    {
        boolean salir = false;

        while (!salir) {
            System.out.println();
            System.out.println("Menu principal");
            System.out.println("1. Dashboard");
            System.out.println("2. Gestion de almacen");
            System.out.println("3. Gestion de trabajadores");
            System.out.println("4. Planificador simple");
            System.out.println("5. Consultas de vehiculos");
            System.out.println("6. Resumen del sistema");
            System.out.println("7. Gestion de pedidos de produccion");
            System.out.println("0. Salir");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    mostrarDashboard();
                    break;
                case 2:
                    menuAlmacen();
                    break;
                case 3:
                    menuTrabajadores();
                    break;
                case 4:
                    ejecutarPlanificadorSimple();
                    break;
                case 5:
                    menuVehiculos();
                    break;
                case 6:
                    mostrarResumenSistema();
                    break;
                case 7:
                    menuPedidosProduccion();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }

    /**
     * Muestra los eventos registrados en el dashboard.
     */
    private void mostrarDashboard()
    {
        List<String> eventos = fabrica.getDashboard().getEventos();

        System.out.println();
        System.out.println("Dashboard");
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }

        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i));
        }
    }

    /**
     * Gestiona las opciones de almacen de componentes.
     */
    private void menuAlmacen()
    {
        boolean volver = false;

        while (!volver) {
            System.out.println();
            System.out.println("Gestion de almacen");
            System.out.println("1. Listar componentes");
            System.out.println("2. Alta de componente");
            System.out.println("3. Buscar componente por codigo");
            System.out.println("4. Actualizar stock de componente");
            System.out.println("0. Volver");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    listarComponentes();
                    break;
                case 2:
                    altaComponente();
                    break;
                case 3:
                    buscarComponente();
                    break;
                case 4:
                    actualizarStockComponente();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }

    /**
     * Gestiona las opciones de trabajadores.
     */
    private void menuTrabajadores()
    {
        boolean volver = false;

        while (!volver) {
            System.out.println();
            System.out.println("Gestion de trabajadores");
            System.out.println("1. Listar trabajadores");
            System.out.println("2. Alta de trabajador");
            System.out.println("3. Buscar trabajador por DNI");
            System.out.println("4. Buscar trabajador por nombre o apellidos");
            System.out.println("5. Buscar trabajador por puesto");
            System.out.println("6. Actualizar direccion");
            System.out.println("7. Actualizar salario");
            System.out.println("0. Volver");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    listarTrabajadores(fabrica.getTrabajadores());
                    break;
                case 2:
                    altaTrabajador();
                    break;
                case 3:
                    buscarTrabajadorPorDni();
                    break;
                case 4:
                    buscarTrabajadorPorNombre();
                    break;
                case 5:
                    buscarTrabajadorPorPuesto();
                    break;
                case 6:
                    actualizarDireccionTrabajador();
                    break;
                case 7:
                    actualizarSalarioTrabajador();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }

    /**
     * Gestiona las consultas y actualizaciones de vehiculos.
     */
    private void menuVehiculos()
    {
        boolean volver = false;

        while (!volver) {
            System.out.println();
            System.out.println("Consultas de vehiculos");
            System.out.println("1. Listar vehiculos registrados");
            System.out.println("2. Buscar vehiculo por codigo");
            System.out.println("3. Buscar vehiculos por tipo");
            System.out.println("4. Actualizar configuracion de vehiculo desde componentes");
            System.out.println("5. Listar stock de vehiculos");
            System.out.println("6. Buscar stock de vehiculo por tipo");
            System.out.println("7. Actualizar stock de vehiculo");
            System.out.println("0. Volver");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    listarVehiculos(fabrica.getVehiculos());
                    break;
                case 2:
                    buscarVehiculoPorCodigo();
                    break;
                case 3:
                    buscarVehiculosPorTipo();
                    break;
                case 4:
                    actualizarVehiculoDesdeStock();
                    break;
                case 5:
                    listarStockVehiculos();
                    break;
                case 6:
                    buscarStockVehiculoPorTipo();
                    break;
                case 7:
                    actualizarStockVehiculo();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }

    /**
     * Gestiona el alta, consulta y ejecucion de pedidos de produccion.
     */
    private void menuPedidosProduccion()
    {
        boolean volver = false;

        while (!volver) {
            System.out.println();
            System.out.println("Gestion de pedidos de produccion");
            System.out.println("1. Listar pedidos");
            System.out.println("2. Alta de pedido");
            System.out.println("3. Buscar pedido por codigo");
            System.out.println("4. Actualizar pedido");
            System.out.println("5. Ejecutar pedidos almacenados con planificador simple");
            System.out.println("0. Volver");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    listarPedidosProduccion();
                    break;
                case 2:
                    altaPedidoProduccion();
                    break;
                case 3:
                    buscarPedidoProduccion();
                    break;
                case 4:
                    actualizarPedidoProduccion();
                    break;
                case 5:
                    ejecutarPedidosProduccionAlmacenados();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }

    /**
     * Permite construir pedidos temporales y ejecutarlos con el planificador.
     */
    private void ejecutarPlanificadorSimple()
    {
        List<PedidoProduccionSimple> pedidos = new ArrayList<PedidoProduccionSimple>();
        boolean agregarMas = true;

        while (agregarMas) {
            CadenaMontaje cadena = seleccionarCadena();
            System.out.println("Configurando pedido para " + cadena.getCodigo() + " (" + cadena.getEspecialidad() + ")");
            String codigoChasis = leerCodigo("Codigo de chasis: ");
            String codigoMotor = leerCodigo("Codigo de motor: ");
            String codigoTapiceria = leerCodigo("Codigo de tapiceria: ");
            String codigoRueda = leerCodigo("Codigo de rueda: ");
            int unidades = leerEnteroPositivo("Numero de unidades: ");

            pedidos.add(new PedidoProduccionSimple(
                cadena, codigoChasis, codigoMotor, codigoTapiceria, codigoRueda, unidades
            ));

            String respuesta = leerSiNo("Desea anadir otro pedido? (s/n): ");
            agregarMas = respuesta.equalsIgnoreCase("s");
        }

        try {
            PlanificadorSimple planificador = new PlanificadorSimple(fabrica);
            List<String> bitacora = planificador.ejecutar(pedidos);

            System.out.println();
            System.out.println("Ejecucion del planificador simple");
            imprimirLineas(bitacora);
        }
        catch (RuntimeException e) {
            System.out.println("No se ha podido ejecutar el planificador simple: " + e.getMessage());
        }
    }

    /**
     * Muestra un resumen general del estado del sistema.
     */
    private void mostrarResumenSistema()
    {
        System.out.println();
        System.out.println("Resumen del sistema");
        System.out.println(fabrica.describirOrganizacion());
        System.out.println("Trabajadores: " + fabrica.getNumeroTrabajadores());
        System.out.println("Componentes en catalogo: " + fabrica.getNumeroComponentesEnAlmacen());
        System.out.println("Vehiculos en stock: " + fabrica.getNumeroVehiculosEnAlmacen());
        System.out.println("Vehiculos registrados: " + fabrica.getNumeroVehiculosRegistrados());
        System.out.println("Pedidos de produccion: " + fabrica.getPedidosProduccion().size());
        System.out.println("Ultimo evento dashboard: " + fabrica.getDashboard().getUltimoEvento());
    }

    /**
     * Lista todos los componentes del almacen.
     */
    private void listarComponentes()
    {
        List<Componente> componentes = fabrica.getComponentes();

        if (componentes.isEmpty()) {
            System.out.println("No hay componentes registrados.");
            return;
        }

        for (int i = 0; i < componentes.size(); i++) {
            System.out.println(describirComponente(componentes.get(i)));
        }
    }

    /**
     * Solicita datos y registra un componente nuevo.
     */
    private void altaComponente()
    {
        System.out.println("Tipo de componente");
        System.out.println("1. Chasis");
        System.out.println("2. Motor");
        System.out.println("3. Tapiceria");
        System.out.println("4. Rueda");

        int opcion = leerEntero("Seleccione el tipo: ");
        Componente componente = null;

        if (opcion == 1) {
            String codigo = leerCodigo("Codigo: ");
            String tipoVehiculo = seleccionarTipoVehiculo();
            String color = leerNombre("Color: ");
            int plazas = leerEnteroPositivo("Numero de plazas: ");
            double tara = leerDoublePositivo("Tara: ");
            double pesoMaximo = leerDoublePositivo("Peso maximo autorizado: ");
            int unidades = leerEnteroNoNegativo("Unidades disponibles: ");
            componente = new Chasis(codigo, tipoVehiculo, color, plazas, tara, pesoMaximo, unidades);
        }
        else if (opcion == 2) {
            String codigo = leerCodigo("Codigo: ");
            TipoMotor tipoMotor = seleccionarTipoMotor();
            int cilindrada = leerEnteroNoNegativo("Cilindrada: ");
            int potencia = leerEnteroPositivo("Potencia: ");
            short cilindros = (short) leerEnteroNoNegativo("Numero de cilindros: ");
            int unidades = leerEnteroNoNegativo("Unidades disponibles: ");
            componente = new Motor(codigo, tipoMotor, cilindrada, potencia, cilindros, unidades);
        }
        else if (opcion == 3) {
            String codigo = leerCodigo("Codigo: ");
            TipoTapiceria tipoTapiceria = seleccionarTipoTapiceria();
            String color = leerNombre("Color: ");
            float metros = leerFloatPositivo("Metros cuadrados: ");
            int unidades = leerEnteroNoNegativo("Unidades disponibles: ");
            componente = new Tapiceria(codigo, tipoTapiceria, color, metros, unidades);
        }
        else if (opcion == 4) {
            String codigo = leerCodigo("Codigo: ");
            TipoRueda tipoRueda = seleccionarTipoRueda();
            float ancho = leerFloatPositivo("Ancho mm: ");
            float diametro = leerFloatPositivo("Diametro de llanta: ");
            float carga = leerFloatPositivo("Indice de carga kg: ");
            float velocidad = leerFloatPositivo("Codigo de velocidad km/h: ");
            int unidades = leerEnteroNoNegativo("Unidades disponibles: ");
            componente = new Rueda(codigo, tipoRueda, ancho, diametro, carga, velocidad, unidades);
        }

        if (componente == null) {
            System.out.println("Tipo de componente no valido.");
            return;
        }

        fabrica.registrarComponenteEnAlmacen(componente);
        System.out.println("Componente registrado correctamente.");
    }

    /**
     * Busca y muestra un componente por codigo.
     */
    private void buscarComponente()
    {
        String codigo = leerCodigo("Codigo del componente: ");
        Componente componente = fabrica.buscarComponentePorCodigo(codigo);

        if (componente == null) {
            System.out.println("No existe un componente con ese codigo.");
            return;
        }

        System.out.println(describirComponente(componente));
    }

    /**
     * Solicita un nuevo valor de stock para un componente.
     */
    private void actualizarStockComponente()
    {
        String codigo = leerCodigo("Codigo del componente: ");
        int unidades = leerEnteroNoNegativo("Nuevo stock: ");

        if (fabrica.actualizarStockComponente(codigo, unidades)) {
            System.out.println("Stock actualizado correctamente.");
        }
        else {
            System.out.println("No existe un componente con ese codigo.");
        }
    }

    /**
     * Imprime una lista de trabajadores.
     *
     * @param trabajadores trabajadores que se desean mostrar.
     */
    private void listarTrabajadores(List<Trabajador> trabajadores)
    {
        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores para mostrar.");
            return;
        }

        for (int i = 0; i < trabajadores.size(); i++) {
            System.out.println(describirTrabajador(trabajadores.get(i)));
        }
    }

    /**
     * Solicita datos y registra un trabajador nuevo.
     */
    private void altaTrabajador()
    {
        System.out.println("Tipo de trabajador");
        System.out.println("1. Operario");
        System.out.println("2. Gestor de planta");
        System.out.println("3. Administrador del sistema");
        System.out.println("4. Mecanico de cinta");

        int opcion = leerEntero("Seleccione el tipo: ");
        String nombre = leerNombre("Nombre: ");
        String apellidos = leerNombre("Apellidos: ");
        String dni = leerDni("DNI: ");
        String direccion = leerTexto("Direccion: ");
        String numeroSeguridadSocial = leerCodigo("Numero de seguridad social: ");
        double salario = leerDoubleNoNegativo("Salario: ");
        String fechaIngreso = leerFecha("Fecha de ingreso (aaaa-mm-dd): ");
        Trabajador trabajador = null;

        if (opcion == 1) {
            TipoTrabajador tipo = seleccionarTipoTrabajador();
            int piezasMontadas = leerEnteroNoNegativo("Piezas montadas: ");
            trabajador = new Operario(nombre, apellidos, dni, direccion,
                                      numeroSeguridadSocial, salario, fechaIngreso,
                                      tipo, piezasMontadas);
        }
        else if (opcion == 2) {
            trabajador = new GestorPlanta(nombre, apellidos, dni, direccion,
                                          numeroSeguridadSocial, salario, fechaIngreso);
        }
        else if (opcion == 3) {
            trabajador = new AdministradorSistema(nombre, apellidos, dni, direccion,
                                                  numeroSeguridadSocial, salario, fechaIngreso);
        }
        else if (opcion == 4) {
            TipoTrabajador tipo = seleccionarTipoTrabajador();
            int reparaciones = leerEnteroNoNegativo("Reparaciones realizadas: ");
            trabajador = new MecanicoCinta(nombre, apellidos, dni, direccion,
                                           numeroSeguridadSocial, salario, fechaIngreso,
                                           tipo, reparaciones);
        }

        if (trabajador == null) {
            System.out.println("Tipo de trabajador no valido.");
            return;
        }

        if (fabrica.registrarTrabajador(trabajador)) {
            System.out.println("Trabajador dado de alta correctamente.");
        }
        else {
            System.out.println("No se ha podido dar de alta. Revise si el DNI ya existe.");
        }
    }

    /**
     * Busca y muestra un trabajador por DNI.
     */
    private void buscarTrabajadorPorDni()
    {
        String dni = leerDni("DNI del trabajador: ");
        Trabajador trabajador = fabrica.identificarTrabajadorPorDni(dni);

        if (trabajador == null) {
            System.out.println("No existe un trabajador con ese DNI.");
            return;
        }

        System.out.println(describirTrabajador(trabajador));
    }

    /**
     * Busca trabajadores por nombre o apellidos.
     */
    private void buscarTrabajadorPorNombre()
    {
        String texto = leerNombre("Texto a buscar en nombre o apellidos: ");
        listarTrabajadores(fabrica.buscarTrabajadoresPorNombreOApellido(texto));
    }

    /**
     * Busca trabajadores por puesto.
     */
    private void buscarTrabajadorPorPuesto()
    {
        String texto = leerNombre("Puesto a buscar: ");
        listarTrabajadores(fabrica.buscarTrabajadoresPorPuesto(texto));
    }

    /**
     * Solicita una nueva direccion para un trabajador.
     */
    private void actualizarDireccionTrabajador()
    {
        String dni = leerDni("DNI del trabajador: ");
        String direccion = leerTexto("Nueva direccion: ");

        if (fabrica.actualizarDireccionTrabajador(dni, direccion)) {
            System.out.println("Direccion actualizada correctamente.");
        }
        else {
            System.out.println("No existe un trabajador con ese DNI.");
        }
    }

    /**
     * Solicita un nuevo salario para un trabajador.
     */
    private void actualizarSalarioTrabajador()
    {
        String dni = leerDni("DNI del trabajador: ");
        double salario = leerDoubleNoNegativo("Nuevo salario: ");

        if (fabrica.actualizarSalarioTrabajador(dni, salario)) {
            System.out.println("Salario actualizado correctamente.");
        }
        else {
            System.out.println("No existe un trabajador con ese DNI.");
        }
    }

    /**
     * Imprime una lista de vehiculos.
     *
     * @param vehiculos vehiculos que se desean mostrar.
     */
    private void listarVehiculos(List<Vehiculo> vehiculos)
    {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
            return;
        }

        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.println(describirVehiculo(vehiculos.get(i)));
        }
    }

    /**
     * Busca y muestra un vehiculo por codigo.
     */
    private void buscarVehiculoPorCodigo()
    {
        String codigo = leerCodigo("Codigo del vehiculo: ");
        Vehiculo vehiculo = fabrica.buscarVehiculoPorCodigo(codigo);

        if (vehiculo == null) {
            System.out.println("No existe un vehiculo con ese codigo.");
            return;
        }

        System.out.println(describirVehiculo(vehiculo));
    }

    /**
     * Busca vehiculos por tipo seleccionado.
     */
    private void buscarVehiculosPorTipo()
    {
        String tipo = seleccionarTipoVehiculo();
        listarVehiculos(fabrica.buscarVehiculosPorTipo(tipo));
    }

    /**
     * Actualiza un vehiculo existente con componentes retirados de stock.
     */
    private void actualizarVehiculoDesdeStock()
    {
        String codigoVehiculo = leerCodigo("Codigo del vehiculo a actualizar: ");
        String codigoChasis = leerCodigo("Nuevo codigo de chasis: ");
        String codigoMotor = leerCodigo("Nuevo codigo de motor: ");
        String codigoTapiceria = leerCodigo("Nuevo codigo de tapiceria: ");
        String codigoRueda = leerCodigo("Nuevo codigo de rueda: ");

        if (fabrica.actualizarVehiculoRegistradoDesdeStock(
            codigoVehiculo, codigoChasis, codigoMotor, codigoTapiceria, codigoRueda
        )) {
            System.out.println("Vehiculo actualizado correctamente.");
        }
        else {
            System.out.println("No se ha podido actualizar el vehiculo. Revise codigo y stock.");
        }
    }

    /**
     * Lista los registros de stock de vehiculos terminados.
     */
    private void listarStockVehiculos()
    {
        List<StockVehiculo> stockVehiculos = fabrica.getStockVehiculos();

        if (stockVehiculos.isEmpty()) {
            System.out.println("No hay stock de vehiculos registrado.");
            return;
        }

        for (int i = 0; i < stockVehiculos.size(); i++) {
            System.out.println(describirStockVehiculo(stockVehiculos.get(i)));
        }
    }

    /**
     * Busca el stock disponible de un tipo de vehiculo.
     */
    private void buscarStockVehiculoPorTipo()
    {
        String tipoVehiculo = seleccionarTipoVehiculo();
        StockVehiculo stock = fabrica.buscarStockVehiculoPorTipo(tipoVehiculo);

        if (stock == null) {
            System.out.println("No hay stock registrado para ese tipo de vehiculo.");
            return;
        }

        System.out.println(describirStockVehiculo(stock));
    }

    /**
     * Actualiza el stock agregado de un tipo de vehiculo.
     */
    private void actualizarStockVehiculo()
    {
        String tipoVehiculo = seleccionarTipoVehiculo();
        int unidades = leerEnteroNoNegativo("Nuevo stock de vehiculos: ");

        if (fabrica.actualizarStockVehiculo(tipoVehiculo, unidades)) {
            System.out.println("Stock de vehiculos actualizado correctamente.");
        }
        else {
            System.out.println("No se ha podido actualizar el stock de vehiculos.");
        }
    }

    /**
     * Lista los pedidos de produccion registrados.
     */
    private void listarPedidosProduccion()
    {
        List<PedidoProduccionSimple> pedidos = fabrica.getPedidosProduccion();

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos de produccion registrados.");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println(describirPedidoProduccion(pedidos.get(i)));
        }
    }

    /**
     * Solicita datos y registra un pedido de produccion.
     */
    private void altaPedidoProduccion()
    {
        String codigoPedido = leerCodigo("Codigo del pedido: ");
        PedidoProduccionSimple pedido = leerDatosPedidoProduccion(codigoPedido);

        if (fabrica.registrarPedidoProduccion(pedido)) {
            System.out.println("Pedido de produccion registrado correctamente.");
            avisarSiNoHayStockSuficiente(pedido);
        }
        else {
            System.out.println("No se ha podido registrar el pedido. Revise codigo, componentes y compatibilidad.");
        }
    }

    /**
     * Busca y muestra un pedido de produccion por codigo.
     */
    private void buscarPedidoProduccion()
    {
        String codigoPedido = leerCodigo("Codigo del pedido: ");
        PedidoProduccionSimple pedido = fabrica.buscarPedidoProduccionPorCodigo(codigoPedido);

        if (pedido == null) {
            System.out.println("No existe un pedido con ese codigo.");
            return;
        }

        System.out.println(describirPedidoProduccion(pedido));
    }

    /**
     * Solicita datos y actualiza un pedido de produccion existente.
     */
    private void actualizarPedidoProduccion()
    {
        String codigoPedido = leerCodigo("Codigo del pedido a actualizar: ");
        PedidoProduccionSimple pedidoActualizado = leerDatosPedidoProduccion(codigoPedido);

        if (fabrica.actualizarPedidoProduccion(codigoPedido, pedidoActualizado.getCadena(),
                                               pedidoActualizado.getCodigoChasis(),
                                               pedidoActualizado.getCodigoMotor(),
                                               pedidoActualizado.getCodigoTapiceria(),
                                               pedidoActualizado.getCodigoRueda(),
                                               pedidoActualizado.getUnidadesSolicitadas())) {
            System.out.println("Pedido de produccion actualizado correctamente.");
            avisarSiNoHayStockSuficiente(pedidoActualizado);
        }
        else {
            System.out.println("No se ha podido actualizar el pedido. Revise codigo, componentes y compatibilidad.");
        }
    }

    /**
     * Lee desde consola la configuracion completa de un pedido.
     *
     * @param codigoPedido codigo que se asignara al pedido.
     * @return pedido construido con los datos introducidos.
     */
    private PedidoProduccionSimple leerDatosPedidoProduccion(String codigoPedido)
    {
        CadenaMontaje cadena = seleccionarCadena();
        System.out.println("Configurando pedido para " + cadena.getCodigo() + " (" + cadena.getEspecialidad() + ")");
        String codigoChasis = leerCodigo("Codigo de chasis: ");
        String codigoMotor = leerCodigo("Codigo de motor: ");
        String codigoTapiceria = leerCodigo("Codigo de tapiceria: ");
        String codigoRueda = leerCodigo("Codigo de rueda: ");
        int unidades = leerEnteroPositivo("Unidades a producir: ");

        return new PedidoProduccionSimple(codigoPedido, cadena, codigoChasis, codigoMotor,
                                          codigoTapiceria, codigoRueda, unidades);
    }

    /**
     * Muestra un aviso cuando el stock no cubre el pedido indicado.
     *
     * @param pedido pedido que se comprueba.
     */
    private void avisarSiNoHayStockSuficiente(PedidoProduccionSimple pedido)
    {
        if (!fabrica.hayStockParaPedidoProduccion(pedido)) {
            System.out.println("Aviso: el pedido queda registrado, pero el stock actual no cubre todas las unidades.");
        }
    }

    /**
     * Ejecuta los pedidos guardados usando el planificador simple.
     */
    private void ejecutarPedidosProduccionAlmacenados()
    {
        List<PedidoProduccionSimple> pedidos = new ArrayList<PedidoProduccionSimple>(fabrica.getPedidosProduccion());

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados para ejecutar.");
            return;
        }

        try {
            PlanificadorSimple planificador = new PlanificadorSimple(fabrica);
            List<String> bitacora = planificador.ejecutar(pedidos);

            System.out.println();
            System.out.println("Ejecucion de pedidos almacenados");
            imprimirLineas(bitacora);
        }
        catch (RuntimeException e) {
            System.out.println("No se han podido ejecutar los pedidos almacenados: " + e.getMessage());
        }
    }

    /**
     * Imprime una coleccion de lineas de texto.
     *
     * @param lineas lineas que se desean imprimir.
     */
    private void imprimirLineas(List<String> lineas)
    {
        Iterator<String> iterador = lineas.iterator();
        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }
    }

    /**
     * Solicita al usuario una cadena de montaje.
     *
     * @return cadena seleccionada.
     */
    private CadenaMontaje seleccionarCadena()
    {
        System.out.println("Seleccione la cadena");
        System.out.println("1. Biplaza deportivo");
        System.out.println("2. Turismo");
        System.out.println("3. Furgoneta");

        int opcion = leerEntero("Opcion: ");

        if (opcion == 1) {
            return fabrica.getCadenaDeportiva();
        }
        if (opcion == 2) {
            return fabrica.getCadenaTurismo();
        }
        return fabrica.getCadenaFurgoneta();
    }

    /**
     * Solicita al usuario un tipo de vehiculo.
     *
     * @return texto del tipo seleccionado.
     */
    private String seleccionarTipoVehiculo()
    {
        System.out.println("Tipo de vehiculo");
        System.out.println("1. Biplaza deportivo");
        System.out.println("2. Turismo");
        System.out.println("3. Furgoneta");

        int opcion = leerEntero("Opcion: ");

        if (opcion == 1) {
            return "Biplaza deportivo";
        }
        if (opcion == 2) {
            return "Turismo";
        }
        return "Furgoneta";
    }

    /**
     * Solicita al usuario un tipo de motor.
     *
     * @return tipo de motor seleccionado.
     */
    private TipoMotor seleccionarTipoMotor()
    {
        System.out.println("1. ELECTRICO");
        System.out.println("2. GASOLINA");
        System.out.println("3. HIBRIDO");
        int opcion = leerEntero("Tipo de motor: ");

        if (opcion == 1) {
            return TipoMotor.ELECTRICO;
        }
        if (opcion == 2) {
            return TipoMotor.GASOLINA;
        }
        return TipoMotor.HIBRIDO;
    }

    /**
     * Solicita al usuario un tipo de tapiceria.
     *
     * @return tipo de tapiceria seleccionado.
     */
    private TipoTapiceria seleccionarTipoTapiceria()
    {
        System.out.println("1. TELA");
        System.out.println("2. CUERO");
        System.out.println("3. ALCANTARA");
        int opcion = leerEntero("Tipo de tapiceria: ");

        if (opcion == 1) {
            return TipoTapiceria.TELA;
        }
        if (opcion == 2) {
            return TipoTapiceria.CUERO;
        }
        return TipoTapiceria.ALCANTARA;
    }

    /**
     * Solicita al usuario un tipo de rueda.
     *
     * @return tipo de rueda seleccionado.
     */
    private TipoRueda seleccionarTipoRueda()
    {
        System.out.println("1. NORMAL");
        System.out.println("2. DEPORTIVA");
        System.out.println("3. TODOTERRENO");
        int opcion = leerEntero("Tipo de rueda: ");

        if (opcion == 1) {
            return TipoRueda.NORMAL;
        }
        if (opcion == 2) {
            return TipoRueda.DEPORTIVA;
        }
        return TipoRueda.TODOTERRENO;
    }

    /**
     * Solicita al usuario un tipo de trabajador.
     *
     * @return tipo de trabajador seleccionado.
     */
    private TipoTrabajador seleccionarTipoTrabajador()
    {
        System.out.println("1. EFICIENTE");
        System.out.println("2. ESTANDAR");
        int opcion = leerEntero("Tipo de trabajador: ");

        if (opcion == 1) {
            return TipoTrabajador.EFICIENTE;
        }
        return TipoTrabajador.ESTANDAR;
    }

    /**
     * Lee un DNI valido desde consola.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return DNI validado.
     */
    private String leerDni(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String dni = leerLineaScanner().toUpperCase();

            if (esDniValido(dni)) {
                return dni;
            }
            System.out.println("Formato de DNI no valido. Use 8 numeros y una letra, por ejemplo 12345678A.");
        }
    }

    /**
     * Lee un texto de nombre validado.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return nombre validado.
     */
    private String leerNombre(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String nombre = normalizarEspacios(leerLineaScanner());

            if (esNombreValido(nombre)) {
                return nombre;
            }
            System.out.println("Formato no valido. Use solo letras, espacios o guiones.");
        }
    }

    /**
     * Lee un codigo sin espacios y con caracteres permitidos.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return codigo validado.
     */
    private String leerCodigo(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String codigo = leerLineaScanner().toUpperCase();

            if (esCodigoValido(codigo)) {
                return codigo;
            }
            System.out.println("Formato no valido. Use letras, numeros, guiones o guiones bajos, sin espacios.");
        }
    }

    /**
     * Lee una fecha con formato aaaa-mm-dd.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return fecha validada.
     */
    private String leerFecha(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String fecha = leerLineaScanner();

            if (esFechaValida(fecha)) {
                return fecha;
            }
            System.out.println("Formato de fecha no valido. Use aaaa-mm-dd.");
        }
    }

    /**
     * Lee una respuesta afirmativa o negativa.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return respuesta s o n.
     */
    private String leerSiNo(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String respuesta = leerLineaScanner().toLowerCase();

            if (respuesta.equals("s") || respuesta.equals("n")) {
                return respuesta;
            }
            System.out.println("Debe responder s o n.");
        }
    }

    /**
     * Lee un numero entero desde consola.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return entero introducido.
     */
    private int leerEntero(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String linea = leerLineaScanner();

            try {
                return Integer.parseInt(linea);
            }
            catch (NumberFormatException e) {
                System.out.println("Debe introducir un numero entero.");
            }
        }
    }

    /**
     * Lee un entero mayor o igual que cero.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return entero no negativo.
     */
    private int leerEnteroNoNegativo(String mensaje)
    {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Debe introducir un numero mayor o igual que cero.");
        }
    }

    /**
     * Lee un entero estrictamente positivo.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return entero positivo.
     */
    private int leerEnteroPositivo(String mensaje)
    {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor > 0) {
                return valor;
            }
            System.out.println("Debe introducir un numero mayor que cero.");
        }
    }

    /**
     * Lee un numero decimal double desde consola.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return valor decimal.
     */
    private double leerDouble(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String linea = leerLineaScanner();

            try {
                return Double.parseDouble(linea);
            }
            catch (NumberFormatException e) {
                System.out.println("Debe introducir un numero valido.");
            }
        }
    }

    /**
     * Lee un double mayor o igual que cero.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return double no negativo.
     */
    private double leerDoubleNoNegativo(String mensaje)
    {
        while (true) {
            double valor = leerDouble(mensaje);
            if (valor >= 0.0) {
                return valor;
            }
            System.out.println("Debe introducir un numero mayor o igual que cero.");
        }
    }

    /**
     * Lee un double estrictamente positivo.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return double positivo.
     */
    private double leerDoublePositivo(String mensaje)
    {
        while (true) {
            double valor = leerDouble(mensaje);
            if (valor > 0.0) {
                return valor;
            }
            System.out.println("Debe introducir un numero mayor que cero.");
        }
    }

    /**
     * Lee un numero decimal float desde consola.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return valor float.
     */
    private float leerFloat(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String linea = leerLineaScanner();

            try {
                return Float.parseFloat(linea);
            }
            catch (NumberFormatException e) {
                System.out.println("Debe introducir un numero valido.");
            }
        }
    }

    /**
     * Lee un float estrictamente positivo.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return float positivo.
     */
    private float leerFloatPositivo(String mensaje)
    {
        while (true) {
            float valor = leerFloat(mensaje);
            if (valor > 0.0f) {
                return valor;
            }
            System.out.println("Debe introducir un numero mayor que cero.");
        }
    }

    /**
     * Lee texto no vacio desde consola.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return texto introducido.
     */
    private String leerTexto(String mensaje)
    {
        System.out.print(mensaje);
        return leerTextoNoVacio();
    }

    /**
     * Repite la lectura hasta obtener texto no vacio.
     *
     * @return texto no vacio.
     */
    private String leerTextoNoVacio()
    {
        String texto = leerLineaScanner();

        while (texto.length() == 0) {
            System.out.print("El valor no puede estar vacio. Introduzca de nuevo: ");
            texto = leerLineaScanner();
        }

        return texto;
    }

    /**
     * Valida el formato basico de un DNI.
     *
     * @param dni DNI que se desea validar.
     * @return true si cumple el formato.
     */
    private boolean esDniValido(String dni)
    {
        if (dni == null || dni.length() != 9) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                return false;
            }
        }

        return Character.isLetter(dni.charAt(8));
    }

    /**
     * Valida que un texto contenga letras y caracteres de nombre permitidos.
     *
     * @param texto texto que se desea validar.
     * @return true si el nombre es valido.
     */
    private boolean esNombreValido(String texto)
    {
        if (texto == null || texto.length() == 0) {
            return false;
        }

        boolean contieneLetra = false;
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (Character.isLetter(caracter)) {
                contieneLetra = true;
            }
            else if (caracter != ' ' && caracter != '-') {
                return false;
            }
        }

        return contieneLetra;
    }

    /**
     * Valida que un codigo no este vacio y use caracteres permitidos.
     *
     * @param codigo codigo que se desea validar.
     * @return true si el codigo es valido.
     */
    private boolean esCodigoValido(String codigo)
    {
        if (codigo == null || codigo.length() == 0) {
            return false;
        }

        for (int i = 0; i < codigo.length(); i++) {
            char caracter = codigo.charAt(i);
            if (!Character.isLetterOrDigit(caracter) && caracter != '-' && caracter != '_') {
                return false;
            }
        }

        return true;
    }

    /**
     * Valida una fecha con formato aaaa-mm-dd y dia real de calendario.
     *
     * @param fecha fecha que se desea validar.
     * @return true si la fecha es valida.
     */
    private boolean esFechaValida(String fecha)
    {
        if (fecha == null || fecha.length() != 10
            || fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            return false;
        }

        for (int i = 0; i < fecha.length(); i++) {
            if (i != 4 && i != 7 && !Character.isDigit(fecha.charAt(i))) {
                return false;
            }
        }

        try {
            int anio = Integer.parseInt(fecha.substring(0, 4));
            int mes = Integer.parseInt(fecha.substring(5, 7));
            int dia = Integer.parseInt(fecha.substring(8, 10));

            if (anio < 1900 || mes < 1 || mes > 12 || dia < 1) {
                return false;
            }

            return dia <= diasDelMes(mes, anio);
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Calcula los dias de un mes considerando anos bisiestos.
     *
     * @param mes mes que se evalua.
     * @param anio ano que se evalua.
     * @return dias del mes.
     */
    private int diasDelMes(int mes, int anio)
    {
        if (mes == 2) {
            if (esBisiesto(anio)) {
                return 29;
            }
            return 28;
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        }
        return 31;
    }

    /**
     * Comprueba si un ano es bisiesto.
     *
     * @param anio ano que se evalua.
     * @return true si es bisiesto.
     */
    private boolean esBisiesto(int anio)
    {
        return (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
    }

    /**
     * Elimina espacios duplicados y recorta el texto.
     *
     * @param texto texto que se normaliza.
     * @return texto normalizado.
     */
    private String normalizarEspacios(String texto)
    {
        String normalizado = "";
        boolean espacioAnterior = false;

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (caracter == ' ') {
                if (!espacioAnterior && normalizado.length() > 0) {
                    normalizado += caracter;
                }
                espacioAnterior = true;
            }
            else {
                normalizado += caracter;
                espacioAnterior = false;
            }
        }

        return normalizado.trim();
    }

    /**
     * Lee una linea del scanner controlando cierres o fin de entrada.
     *
     * @return linea leida sin espacios extremos.
     */
    private String leerLineaScanner()
    {
        try {
            return scanner.nextLine().trim();
        }
        catch (NoSuchElementException e) {
            throw new IllegalStateException("no quedan lineas por leer", e);
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("el lector de entrada esta cerrado", e);
        }
    }

    /**
     * Construye una descripcion de un componente segun su subtipo.
     *
     * @param componente componente que se describe.
     * @return descripcion textual del componente.
     */
    private String describirComponente(Componente componente)
    {
        String descripcion = componente.getNombreComponente()
                             + " [" + componente.getCodigo() + "]"
                             + " stock=" + componente.getUnidadesDisponibles()
                             + " estado=" + componente.getDescripcionEstado();

        if (componente instanceof Chasis) {
            Chasis chasis = (Chasis) componente;
            descripcion += " tipo=" + chasis.getTipoVehiculoCompatible()
                           + " color=" + chasis.getColor()
                           + " plazas=" + chasis.getNumeroPlazas();
        }
        else if (componente instanceof Motor) {
            Motor motor = (Motor) componente;
            descripcion += " tipo=" + motor.getTipoMotor()
                           + " potencia=" + motor.getPotencia();
        }
        else if (componente instanceof Tapiceria) {
            Tapiceria tapiceria = (Tapiceria) componente;
            descripcion += " tipo=" + tapiceria.getTipoTapiceria()
                           + " color=" + tapiceria.getColor();
        }
        else if (componente instanceof Rueda) {
            Rueda rueda = (Rueda) componente;
            descripcion += " tipo=" + rueda.getTipoRueda()
                           + " llanta=" + rueda.getDiametroLlanta();
        }

        return descripcion;
    }

    /**
     * Construye una descripcion breve de un vehiculo.
     *
     * @param vehiculo vehiculo que se describe.
     * @return descripcion textual del vehiculo.
     */
    private String describirVehiculo(Vehiculo vehiculo)
    {
        return vehiculo.getCodigoVehiculo()
               + " | tipo=" + vehiculo.getTipoVehiculo()
               + " | color=" + vehiculo.getColor()
               + " | plazas=" + vehiculo.getNumeroPlazas()
               + " | estado=" + vehiculo.getDescripcionEstadoMontaje();
    }

    /**
     * Construye una descripcion del stock de vehiculos.
     *
     * @param stock registro de stock.
     * @return descripcion textual del stock.
     */
    private String describirStockVehiculo(StockVehiculo stock)
    {
        return stock.getTipoVehiculo()
               + " | unidades disponibles=" + stock.getUnidadesDisponibles();
    }

    /**
     * Construye una descripcion de pedido con informacion de stock.
     *
     * @param pedido pedido que se describe.
     * @return descripcion textual del pedido.
     */
    private String describirPedidoProduccion(PedidoProduccionSimple pedido)
    {
        String descripcion = pedido.getDescripcionPedido();
        if (fabrica.hayStockParaPedidoProduccion(pedido)) {
            descripcion += " | stock suficiente";
        }
        else {
            descripcion += " | stock insuficiente";
        }
        return descripcion;
    }

    /**
     * Construye una descripcion de trabajador segun su subtipo.
     *
     * @param trabajador trabajador que se describe.
     * @return descripcion textual del trabajador.
     */
    private String describirTrabajador(Trabajador trabajador)
    {
        String descripcion = trabajador.getNombreCompleto()
                             + " | DNI=" + trabajador.getDni()
                             + " | puesto=" + trabajador.getPuesto()
                             + " | salario=" + trabajador.getSalario();

        if (trabajador instanceof Operario) {
            Operario operario = (Operario) trabajador;
            descripcion += " | tipo=" + operario.getTipoTrabajador()
                           + " | piezas=" + operario.getPiezasMontadas();
        }
        else if (trabajador instanceof MecanicoCinta) {
            MecanicoCinta mecanico = (MecanicoCinta) trabajador;
            descripcion += " | tipo=" + mecanico.getTipoTrabajador()
                           + " | reparaciones=" + mecanico.getReparacionesRealizadas();
        }

        return descripcion;
    }
}
