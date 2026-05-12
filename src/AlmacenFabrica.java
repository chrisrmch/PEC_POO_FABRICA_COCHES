 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Gestiona el almacen de componentes, vehiculos terminados y pedidos.
 *
 * @author cmamani11
 */
public class AlmacenFabrica
{
    private List<Componente> componentes;
    private List<Vehiculo> vehiculos;
    private List<StockVehiculo> stockVehiculos;
    private List<PedidoProduccionSimple> pedidosProduccion;

    /**
     * Crea un almacen vacio para componentes, vehiculos y pedidos.
     */
    public AlmacenFabrica()
    {
        this.componentes = new ArrayList<Componente>();
        this.vehiculos = new ArrayList<Vehiculo>();
        this.stockVehiculos = new ArrayList<StockVehiculo>();
        this.pedidosProduccion = new ArrayList<PedidoProduccionSimple>();
    }

    /**
     * Registra un componente nuevo o suma unidades si ya existe.
     *
     * @param componente componente que entra en almacen.
     */
    public void registrarComponente(Componente componente)
    {
        if (componente == null) {
            return;
        }

        Componente existente = buscarComponentePorCodigo(componente.getCodigo());
        if (existente == null) {
            componentes.add(componente);
        }
        else {
            existente.registrarEntradaStock(componente.getUnidadesDisponibles());
        }
    }

    /**
     * Registra un vehiculo terminado y actualiza el stock por tipo.
     *
     * @param vehiculo vehiculo que se incorpora al almacen.
     */
    public void registrarVehiculo(Vehiculo vehiculo)
    {
        if (vehiculo == null || buscarVehiculoPorCodigo(vehiculo.getCodigoVehiculo()) != null) {
            return;
        }
        vehiculos.add(vehiculo);
        registrarEntradaStockVehiculo(vehiculo.getTipoVehiculo(), 1);
    }

    /**
     * Busca un componente por su codigo.
     *
     * @param codigo codigo del componente.
     * @return componente encontrado, o null si no existe.
     */
    public Componente buscarComponentePorCodigo(String codigo)
    {
        Iterator<Componente> iterador = componentes.iterator();
        while (iterador.hasNext()) {
            Componente componente = iterador.next();
            if (componente.getCodigo().equalsIgnoreCase(codigo)) {
                return componente;
            }
        }
        return null;
    }

    /**
     * Busca un vehiculo por su codigo.
     *
     * @param codigoVehiculo codigo del vehiculo.
     * @return vehiculo encontrado, o null si no existe.
     */
    public Vehiculo buscarVehiculoPorCodigo(String codigoVehiculo)
    {
        Iterator<Vehiculo> iterador = vehiculos.iterator();
        while (iterador.hasNext()) {
            Vehiculo vehiculo = iterador.next();
            if (vehiculo.getCodigoVehiculo().equalsIgnoreCase(codigoVehiculo)) {
                return vehiculo;
            }
        }
        return null;
    }

    /**
     * Actualiza el stock de un componente existente.
     *
     * @param codigo codigo del componente.
     * @param nuevasUnidades nuevas unidades disponibles.
     * @return true si el componente existe y se actualiza.
     */
    public boolean actualizarStockComponente(String codigo, int nuevasUnidades)
    {
        Componente componente = buscarComponentePorCodigo(codigo);
        if (componente == null) {
            return false;
        }
        componente.actualizarStock(nuevasUnidades);
        return true;
    }

    /**
     * Actualiza o crea el stock agregado para un tipo de vehiculo.
     *
     * @param tipoVehiculo tipo de vehiculo.
     * @param nuevasUnidades nuevas unidades disponibles.
     * @return true si los datos son validos.
     */
    public boolean actualizarStockVehiculo(String tipoVehiculo, int nuevasUnidades)
    {
        if (tipoVehiculo == null || tipoVehiculo.trim().length() == 0 || nuevasUnidades < 0) {
            return false;
        }

        StockVehiculo stock = buscarStockVehiculoPorTipo(tipoVehiculo);
        if (stock == null) {
            stockVehiculos.add(new StockVehiculo(tipoVehiculo, nuevasUnidades));
        }
        else {
            stock.actualizarStock(nuevasUnidades);
        }
        return true;
    }

    /**
     * Incrementa el stock de vehiculos terminados por tipo.
     *
     * @param tipoVehiculo tipo de vehiculo.
     * @param unidades unidades que entran.
     */
    private void registrarEntradaStockVehiculo(String tipoVehiculo, int unidades)
    {
        if (tipoVehiculo == null || unidades <= 0) {
            return;
        }

        StockVehiculo stock = buscarStockVehiculoPorTipo(tipoVehiculo);
        if (stock == null) {
            stockVehiculos.add(new StockVehiculo(tipoVehiculo, unidades));
        }
        else {
            stock.registrarEntrada(unidades);
        }
    }

    /**
     * Actualiza la configuracion de un vehiculo registrado.
     *
     * @param codigoVehiculo codigo del vehiculo.
     * @param chasis nuevo chasis.
     * @param motor nuevo motor.
     * @param tapiceria nueva tapiceria.
     * @param rueda nueva rueda.
     * @return true si el vehiculo existe y se actualiza.
     */
    public boolean actualizarVehiculo(String codigoVehiculo, Chasis chasis,
                                      Motor motor, Tapiceria tapiceria, Rueda rueda)
    {
        Vehiculo vehiculo = buscarVehiculoPorCodigo(codigoVehiculo);
        if (vehiculo == null) {
            return false;
        }

        devolverComponenteMontado(vehiculo.getChasis());
        devolverComponenteMontado(vehiculo.getMotor());
        devolverComponenteMontado(vehiculo.getTapiceria());
        devolverComponenteMontado(vehiculo.getRueda());

        vehiculo.actualizarConfiguracion(chasis, motor, tapiceria, rueda);
        return true;
    }

    /**
     * Comprueba si hay stock unitario para fabricar un vehiculo.
     *
     * @param codigoChasis codigo del chasis.
     * @param codigoMotor codigo del motor.
     * @param codigoTapiceria codigo de la tapiceria.
     * @param codigoRueda codigo de la rueda.
     * @return true si todos los componentes tienen stock.
     */
    public boolean hayStockParaFabricacion(String codigoChasis, String codigoMotor,
                                           String codigoTapiceria, String codigoRueda)
    {
        return tieneDisponible(codigoChasis)
               && tieneDisponible(codigoMotor)
               && tieneDisponible(codigoTapiceria)
               && tieneDisponible(codigoRueda);
    }

    /**
     * Comprueba si hay stock suficiente para varias unidades.
     *
     * @param codigoChasis codigo del chasis.
     * @param codigoMotor codigo del motor.
     * @param codigoTapiceria codigo de la tapiceria.
     * @param codigoRueda codigo de la rueda.
     * @param unidades unidades necesarias.
     * @return true si todos los componentes cubren las unidades.
     */
    public boolean hayStockParaFabricacion(String codigoChasis, String codigoMotor,
                                           String codigoTapiceria, String codigoRueda,
                                           int unidades)
    {
        if (unidades <= 0) {
            return false;
        }

        return tieneUnidadesDisponibles(codigoChasis, unidades)
               && tieneUnidadesDisponibles(codigoMotor, unidades)
               && tieneUnidadesDisponibles(codigoTapiceria, unidades)
               && tieneUnidadesDisponibles(codigoRueda, unidades);
    }

    /**
     * Comprueba si un componente existe y tiene al menos una unidad.
     *
     * @param codigoComponente codigo del componente.
     * @return true si hay stock disponible.
     */
    private boolean tieneDisponible(String codigoComponente)
    {
        Componente componente = buscarComponentePorCodigo(codigoComponente);
        return componente != null && componente.hayStockDisponible();
    }

    /**
     * Comprueba si un componente tiene las unidades solicitadas.
     *
     * @param codigoComponente codigo del componente.
     * @param unidades unidades necesarias.
     * @return true si hay unidades suficientes.
     */
    private boolean tieneUnidadesDisponibles(String codigoComponente, int unidades)
    {
        Componente componente = buscarComponentePorCodigo(codigoComponente);
        return componente != null && componente.getUnidadesDisponibles() >= unidades;
    }

    /**
     * Devuelve a almacen un componente que ya estaba montado.
     *
     * @param componente componente que vuelve a stock.
     */
    private void devolverComponenteMontado(Componente componente)
    {
        if (componente != null) {
            registrarComponente(componente);
        }
    }

    /**
     * Retira una unidad de chasis del almacen.
     *
     * @param codigoChasis codigo del chasis.
     * @return chasis retirado, o null si no esta disponible.
     */
    public Chasis retirarChasis(String codigoChasis)
    {
        Componente componente = buscarComponentePorCodigo(codigoChasis);
        if (componente instanceof Chasis) {
            return (Chasis) componente.extraerUnidadDeStock();
        }
        return null;
    }

    /**
     * Retira una unidad de motor del almacen.
     *
     * @param codigoMotor codigo del motor.
     * @return motor retirado, o null si no esta disponible.
     */
    public Motor retirarMotor(String codigoMotor)
    {
        Componente componente = buscarComponentePorCodigo(codigoMotor);
        if (componente instanceof Motor) {
            return (Motor) componente.extraerUnidadDeStock();
        }
        return null;
    }

    /**
     * Retira una unidad de tapiceria del almacen.
     *
     * @param codigoTapiceria codigo de la tapiceria.
     * @return tapiceria retirada, o null si no esta disponible.
     */
    public Tapiceria retirarTapiceria(String codigoTapiceria)
    {
        Componente componente = buscarComponentePorCodigo(codigoTapiceria);
        if (componente instanceof Tapiceria) {
            return (Tapiceria) componente.extraerUnidadDeStock();
        }
        return null;
    }

    /**
     * Retira una unidad de rueda del almacen.
     *
     * @param codigoRueda codigo de la rueda.
     * @return rueda retirada, o null si no esta disponible.
     */
    public Rueda retirarRueda(String codigoRueda)
    {
        Componente componente = buscarComponentePorCodigo(codigoRueda);
        if (componente instanceof Rueda) {
            return (Rueda) componente.extraerUnidadDeStock();
        }
        return null;
    }

    /**
     * Devuelve los componentes registrados en almacen.
     *
     * @return lista no modificable de componentes.
     */
    public List<Componente> getComponentes()
    {
        return Collections.unmodifiableList(componentes);
    }

    /**
     * Registra un pedido de produccion si su codigo no existe.
     *
     * @param pedido pedido que se desea guardar.
     * @return true si se registra correctamente.
     */
    public boolean registrarPedidoProduccion(PedidoProduccionSimple pedido)
    {
        if (pedido == null || pedido.getCodigoPedido() == null
            || pedido.getCodigoPedido().trim().length() == 0
            || buscarPedidoProduccionPorCodigo(pedido.getCodigoPedido()) != null) {
            return false;
        }

        pedidosProduccion.add(pedido);
        return true;
    }

    /**
     * Actualiza los datos de un pedido de produccion existente.
     *
     * @param codigoPedido codigo del pedido.
     * @param cadena cadena de montaje.
     * @param codigoChasis codigo del chasis.
     * @param codigoMotor codigo del motor.
     * @param codigoTapiceria codigo de la tapiceria.
     * @param codigoRueda codigo de la rueda.
     * @param unidades unidades solicitadas.
     * @return true si el pedido existe y se actualiza.
     */
    public boolean actualizarPedidoProduccion(String codigoPedido, CadenaMontaje cadena,
                                              String codigoChasis, String codigoMotor,
                                              String codigoTapiceria, String codigoRueda,
                                              int unidades)
    {
        PedidoProduccionSimple pedido = buscarPedidoProduccionPorCodigo(codigoPedido);
        if (pedido == null) {
            return false;
        }

        pedido.actualizarConfiguracion(cadena, codigoChasis, codigoMotor, codigoTapiceria,
                                       codigoRueda, unidades);
        return true;
    }

    /**
     * Busca un pedido de produccion por codigo.
     *
     * @param codigoPedido codigo del pedido.
     * @return pedido encontrado, o null si no existe.
     */
    public PedidoProduccionSimple buscarPedidoProduccionPorCodigo(String codigoPedido)
    {
        if (codigoPedido == null) {
            return null;
        }

        Iterator<PedidoProduccionSimple> iterador = pedidosProduccion.iterator();
        while (iterador.hasNext()) {
            PedidoProduccionSimple pedido = iterador.next();
            if (pedido.getCodigoPedido().equalsIgnoreCase(codigoPedido)) {
                return pedido;
            }
        }
        return null;
    }

    /**
     * Devuelve los pedidos de produccion registrados.
     *
     * @return lista no modificable de pedidos.
     */
    public List<PedidoProduccionSimple> getPedidosProduccion()
    {
        return Collections.unmodifiableList(pedidosProduccion);
    }

    /**
     * Busca componentes por coincidencia parcial de nombre.
     *
     * @param nombreComponente texto que se desea localizar.
     * @return lista de componentes coincidentes.
     */
    public List<Componente> buscarComponentesPorNombre(String nombreComponente)
    {
        List<Componente> coincidencias = new ArrayList<Componente>();
        String nombreNormalizado = nombreComponente.toLowerCase();

        Iterator<Componente> iterador = componentes.iterator();
        while (iterador.hasNext()) {
            Componente componente = iterador.next();
            if (componente.getNombreComponente().toLowerCase().contains(nombreNormalizado)) {
                coincidencias.add(componente);
            }
        }

        return coincidencias;
    }

    /**
     * Devuelve los vehiculos registrados.
     *
     * @return lista no modificable de vehiculos.
     */
    public List<Vehiculo> getVehiculos()
    {
        return Collections.unmodifiableList(vehiculos);
    }

    /**
     * Busca el stock agregado por tipo de vehiculo.
     *
     * @param tipoVehiculo tipo de vehiculo.
     * @return stock encontrado, o null si no existe.
     */
    public StockVehiculo buscarStockVehiculoPorTipo(String tipoVehiculo)
    {
        if (tipoVehiculo == null) {
            return null;
        }

        Iterator<StockVehiculo> iterador = stockVehiculos.iterator();
        while (iterador.hasNext()) {
            StockVehiculo stock = iterador.next();
            if (stock.getTipoVehiculo().equalsIgnoreCase(tipoVehiculo)) {
                return stock;
            }
        }
        return null;
    }

    /**
     * Devuelve los registros de stock de vehiculos.
     *
     * @return lista no modificable de stock.
     */
    public List<StockVehiculo> getStockVehiculos()
    {
        return Collections.unmodifiableList(stockVehiculos);
    }

    /**
     * Busca vehiculos por coincidencia parcial de tipo.
     *
     * @param tipoVehiculo texto del tipo de vehiculo.
     * @return lista de vehiculos coincidentes.
     */
    public List<Vehiculo> buscarVehiculosPorTipo(String tipoVehiculo)
    {
        List<Vehiculo> coincidencias = new ArrayList<Vehiculo>();
        String tipoNormalizado = tipoVehiculo.toLowerCase();

        Iterator<Vehiculo> iterador = vehiculos.iterator();
        while (iterador.hasNext()) {
            Vehiculo vehiculo = iterador.next();
            if (vehiculo.getTipoVehiculo().toLowerCase().contains(tipoNormalizado)) {
                coincidencias.add(vehiculo);
            }
        }

        return coincidencias;
    }

    /**
     * Devuelve el numero de componentes catalogados.
     *
     * @return numero de componentes.
     */
    public int getNumeroComponentes()
    {
        return componentes.size();
    }

    /**
     * Devuelve el numero de vehiculos registrados individualmente.
     *
     * @return numero de vehiculos.
     */
    public int getNumeroVehiculos()
    {
        return vehiculos.size();
    }

    /**
     * Suma el stock disponible de todos los tipos de vehiculo.
     *
     * @return total de vehiculos disponibles.
     */
    public int getTotalStockVehiculos()
    {
        int total = 0;
        Iterator<StockVehiculo> iterador = stockVehiculos.iterator();
        while (iterador.hasNext()) {
            total += iterador.next().getUnidadesDisponibles();
        }
        return total;
    }
}
