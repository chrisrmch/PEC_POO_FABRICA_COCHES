import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalFabrica
{
    private FabricaCoches fabrica;
    private Scanner scanner;

    public TerminalFabrica(FabricaCoches fabrica, Scanner scanner)
    {
        this.fabrica = fabrica;
        this.scanner = scanner;
    }

    public void iniciar()
    {
        System.out.println("Sistema de gestion de fabrica de vehiculos");
        System.out.println(fabrica.describirOrganizacion());
        mostrarMenuPrincipal();
    }

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

    private void ejecutarPlanificadorSimple()
    {
        List<PedidoProduccionSimple> pedidos = new ArrayList<PedidoProduccionSimple>();
        boolean agregarMas = true;

        while (agregarMas) {
            CadenaMontaje cadena = seleccionarCadena();
            System.out.println("Configurando pedido para " + cadena.getCodigo() + " (" + cadena.getEspecialidad() + ")");
            String codigoChasis = leerTexto("Codigo de chasis: ");
            String codigoMotor = leerTexto("Codigo de motor: ");
            String codigoTapiceria = leerTexto("Codigo de tapiceria: ");
            String codigoRueda = leerTexto("Codigo de rueda: ");
            int unidades = leerEntero("Numero de unidades: ");

            pedidos.add(new PedidoProduccionSimple(
                cadena, codigoChasis, codigoMotor, codigoTapiceria, codigoRueda, unidades
            ));

            String respuesta = leerTexto("Desea anadir otro pedido? (s/n): ");
            agregarMas = respuesta.equalsIgnoreCase("s");
        }

        PlanificadorSimple planificador = new PlanificadorSimple(fabrica);
        List<String> bitacora = planificador.ejecutar(pedidos);

        System.out.println();
        System.out.println("Ejecucion del planificador simple");
        for (int i = 0; i < bitacora.size(); i++) {
            System.out.println(bitacora.get(i));
        }
    }

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
            String codigo = leerTexto("Codigo: ");
            String tipoVehiculo = leerTexto("Tipo de vehiculo compatible: ");
            String color = leerTexto("Color: ");
            int plazas = leerEntero("Numero de plazas: ");
            double tara = leerDouble("Tara: ");
            double pesoMaximo = leerDouble("Peso maximo autorizado: ");
            int unidades = leerEntero("Unidades disponibles: ");
            componente = new Chasis(codigo, tipoVehiculo, color, plazas, tara, pesoMaximo, unidades);
        }
        else if (opcion == 2) {
            String codigo = leerTexto("Codigo: ");
            TipoMotor tipoMotor = seleccionarTipoMotor();
            int cilindrada = leerEntero("Cilindrada: ");
            int potencia = leerEntero("Potencia: ");
            short cilindros = (short) leerEntero("Numero de cilindros: ");
            int unidades = leerEntero("Unidades disponibles: ");
            componente = new Motor(codigo, tipoMotor, cilindrada, potencia, cilindros, unidades);
        }
        else if (opcion == 3) {
            String codigo = leerTexto("Codigo: ");
            TipoTapiceria tipoTapiceria = seleccionarTipoTapiceria();
            String color = leerTexto("Color: ");
            float metros = leerFloat("Metros cuadrados: ");
            int unidades = leerEntero("Unidades disponibles: ");
            componente = new Tapiceria(codigo, tipoTapiceria, color, metros, unidades);
        }
        else if (opcion == 4) {
            String codigo = leerTexto("Codigo: ");
            TipoRueda tipoRueda = seleccionarTipoRueda();
            float ancho = leerFloat("Ancho mm: ");
            float diametro = leerFloat("Diametro de llanta: ");
            float carga = leerFloat("Indice de carga kg: ");
            float velocidad = leerFloat("Codigo de velocidad km/h: ");
            int unidades = leerEntero("Unidades disponibles: ");
            componente = new Rueda(codigo, tipoRueda, ancho, diametro, carga, velocidad, unidades);
        }

        if (componente == null) {
            System.out.println("Tipo de componente no valido.");
            return;
        }

        fabrica.registrarComponenteEnAlmacen(componente);
        System.out.println("Componente registrado correctamente.");
    }

    private void buscarComponente()
    {
        String codigo = leerTexto("Codigo del componente: ");
        Componente componente = fabrica.buscarComponentePorCodigo(codigo);

        if (componente == null) {
            System.out.println("No existe un componente con ese codigo.");
            return;
        }

        System.out.println(describirComponente(componente));
    }

    private void actualizarStockComponente()
    {
        String codigo = leerTexto("Codigo del componente: ");
        int unidades = leerEntero("Nuevo stock: ");

        if (fabrica.actualizarStockComponente(codigo, unidades)) {
            System.out.println("Stock actualizado correctamente.");
        }
        else {
            System.out.println("No existe un componente con ese codigo.");
        }
    }

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

    private void altaTrabajador()
    {
        System.out.println("Tipo de trabajador");
        System.out.println("1. Operario");
        System.out.println("2. Gestor de planta");
        System.out.println("3. Administrador del sistema");
        System.out.println("4. Mecanico de cinta");

        int opcion = leerEntero("Seleccione el tipo: ");
        String nombre = leerTexto("Nombre: ");
        String apellidos = leerTexto("Apellidos: ");
        String dni = leerTexto("DNI: ");
        String direccion = leerTexto("Direccion: ");
        String numeroSeguridadSocial = leerTexto("Numero de seguridad social: ");
        double salario = leerDouble("Salario: ");
        String fechaIngreso = leerTexto("Fecha de ingreso: ");
        Trabajador trabajador = null;

        if (opcion == 1) {
            TipoTrabajador tipo = seleccionarTipoTrabajador();
            int piezasMontadas = leerEntero("Piezas montadas: ");
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
            int reparaciones = leerEntero("Reparaciones realizadas: ");
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

    private void buscarTrabajadorPorDni()
    {
        String dni = leerTexto("DNI del trabajador: ");
        Trabajador trabajador = fabrica.identificarTrabajadorPorDni(dni);

        if (trabajador == null) {
            System.out.println("No existe un trabajador con ese DNI.");
            return;
        }

        System.out.println(describirTrabajador(trabajador));
    }

    private void buscarTrabajadorPorNombre()
    {
        String texto = leerTexto("Texto a buscar en nombre o apellidos: ");
        listarTrabajadores(fabrica.buscarTrabajadoresPorNombreOApellido(texto));
    }

    private void buscarTrabajadorPorPuesto()
    {
        String texto = leerTexto("Puesto a buscar: ");
        listarTrabajadores(fabrica.buscarTrabajadoresPorPuesto(texto));
    }

    private void actualizarDireccionTrabajador()
    {
        String dni = leerTexto("DNI del trabajador: ");
        String direccion = leerTexto("Nueva direccion: ");

        if (fabrica.actualizarDireccionTrabajador(dni, direccion)) {
            System.out.println("Direccion actualizada correctamente.");
        }
        else {
            System.out.println("No existe un trabajador con ese DNI.");
        }
    }

    private void actualizarSalarioTrabajador()
    {
        String dni = leerTexto("DNI del trabajador: ");
        double salario = leerDouble("Nuevo salario: ");

        if (fabrica.actualizarSalarioTrabajador(dni, salario)) {
            System.out.println("Salario actualizado correctamente.");
        }
        else {
            System.out.println("No existe un trabajador con ese DNI.");
        }
    }

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

    private void buscarVehiculoPorCodigo()
    {
        String codigo = leerTexto("Codigo del vehiculo: ");
        Vehiculo vehiculo = fabrica.buscarVehiculoPorCodigo(codigo);

        if (vehiculo == null) {
            System.out.println("No existe un vehiculo con ese codigo.");
            return;
        }

        System.out.println(describirVehiculo(vehiculo));
    }

    private void buscarVehiculosPorTipo()
    {
        String tipo = leerTexto("Tipo de vehiculo: ");
        listarVehiculos(fabrica.buscarVehiculosPorTipo(tipo));
    }

    private void actualizarVehiculoDesdeStock()
    {
        String codigoVehiculo = leerTexto("Codigo del vehiculo a actualizar: ");
        String codigoChasis = leerTexto("Nuevo codigo de chasis: ");
        String codigoMotor = leerTexto("Nuevo codigo de motor: ");
        String codigoTapiceria = leerTexto("Nuevo codigo de tapiceria: ");
        String codigoRueda = leerTexto("Nuevo codigo de rueda: ");

        if (fabrica.actualizarVehiculoRegistradoDesdeStock(
            codigoVehiculo, codigoChasis, codigoMotor, codigoTapiceria, codigoRueda
        )) {
            System.out.println("Vehiculo actualizado correctamente.");
        }
        else {
            System.out.println("No se ha podido actualizar el vehiculo. Revise codigo y stock.");
        }
    }

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

    private void actualizarStockVehiculo()
    {
        String tipoVehiculo = seleccionarTipoVehiculo();
        int unidades = leerEntero("Nuevo stock de vehiculos: ");

        if (fabrica.actualizarStockVehiculo(tipoVehiculo, unidades)) {
            System.out.println("Stock de vehiculos actualizado correctamente.");
        }
        else {
            System.out.println("No se ha podido actualizar el stock de vehiculos.");
        }
    }

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

    private void altaPedidoProduccion()
    {
        String codigoPedido = leerTexto("Codigo del pedido: ");
        PedidoProduccionSimple pedido = leerDatosPedidoProduccion(codigoPedido);

        if (fabrica.registrarPedidoProduccion(pedido)) {
            System.out.println("Pedido de produccion registrado correctamente.");
            avisarSiNoHayStockSuficiente(pedido);
        }
        else {
            System.out.println("No se ha podido registrar el pedido. Revise codigo, componentes y compatibilidad.");
        }
    }

    private void buscarPedidoProduccion()
    {
        String codigoPedido = leerTexto("Codigo del pedido: ");
        PedidoProduccionSimple pedido = fabrica.buscarPedidoProduccionPorCodigo(codigoPedido);

        if (pedido == null) {
            System.out.println("No existe un pedido con ese codigo.");
            return;
        }

        System.out.println(describirPedidoProduccion(pedido));
    }

    private void actualizarPedidoProduccion()
    {
        String codigoPedido = leerTexto("Codigo del pedido a actualizar: ");
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

    private PedidoProduccionSimple leerDatosPedidoProduccion(String codigoPedido)
    {
        CadenaMontaje cadena = seleccionarCadena();
        System.out.println("Configurando pedido para " + cadena.getCodigo() + " (" + cadena.getEspecialidad() + ")");
        String codigoChasis = leerTexto("Codigo de chasis: ");
        String codigoMotor = leerTexto("Codigo de motor: ");
        String codigoTapiceria = leerTexto("Codigo de tapiceria: ");
        String codigoRueda = leerTexto("Codigo de rueda: ");
        int unidades = leerEntero("Unidades a producir: ");

        return new PedidoProduccionSimple(codigoPedido, cadena, codigoChasis, codigoMotor,
                                          codigoTapiceria, codigoRueda, unidades);
    }

    private void avisarSiNoHayStockSuficiente(PedidoProduccionSimple pedido)
    {
        if (!fabrica.hayStockParaPedidoProduccion(pedido)) {
            System.out.println("Aviso: el pedido queda registrado, pero el stock actual no cubre todas las unidades.");
        }
    }

    private void ejecutarPedidosProduccionAlmacenados()
    {
        List<PedidoProduccionSimple> pedidos = new ArrayList<PedidoProduccionSimple>(fabrica.getPedidosProduccion());

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados para ejecutar.");
            return;
        }

        PlanificadorSimple planificador = new PlanificadorSimple(fabrica);
        List<String> bitacora = planificador.ejecutar(pedidos);

        System.out.println();
        System.out.println("Ejecucion de pedidos almacenados");
        for (int i = 0; i < bitacora.size(); i++) {
            System.out.println(bitacora.get(i));
        }
    }

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

    private int leerEntero(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();

            try {
                return Integer.parseInt(linea);
            }
            catch (NumberFormatException e) {
                System.out.println("Debe introducir un numero entero.");
            }
        }
    }

    private double leerDouble(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();

            try {
                return Double.parseDouble(linea);
            }
            catch (NumberFormatException e) {
                System.out.println("Debe introducir un numero valido.");
            }
        }
    }

    private float leerFloat(String mensaje)
    {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();

            try {
                return Float.parseFloat(linea);
            }
            catch (NumberFormatException e) {
                System.out.println("Debe introducir un numero valido.");
            }
        }
    }

    private String leerTexto(String mensaje)
    {
        System.out.print(mensaje);
        return leerTextoNoVacio();
    }

    private String leerTextoNoVacio()
    {
        String texto = scanner.nextLine().trim();

        while (texto.length() == 0) {
            System.out.print("El valor no puede estar vacio. Introduzca de nuevo: ");
            texto = scanner.nextLine().trim();
        }

        return texto;
    }

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

    private String describirVehiculo(Vehiculo vehiculo)
    {
        return vehiculo.getCodigoVehiculo()
               + " | tipo=" + vehiculo.getTipoVehiculo()
               + " | color=" + vehiculo.getColor()
               + " | plazas=" + vehiculo.getNumeroPlazas()
               + " | estado=" + vehiculo.getDescripcionEstadoMontaje();
    }

    private String describirStockVehiculo(StockVehiculo stock)
    {
        return stock.getTipoVehiculo()
               + " | unidades disponibles=" + stock.getUnidadesDisponibles();
    }

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
