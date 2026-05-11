 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AlmacenFabrica
{
    private List<Componente> componentes;
    private List<Vehiculo> vehiculos;
    private List<StockVehiculo> stockVehiculos;
    private List<PedidoProduccionSimple> pedidosProduccion;

    public AlmacenFabrica()
    {
        this.componentes = new ArrayList<Componente>();
        this.vehiculos = new ArrayList<Vehiculo>();
        this.stockVehiculos = new ArrayList<StockVehiculo>();
        this.pedidosProduccion = new ArrayList<PedidoProduccionSimple>();
    }

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

    public void registrarVehiculo(Vehiculo vehiculo)
    {
        if (vehiculo == null || buscarVehiculoPorCodigo(vehiculo.getCodigoVehiculo()) != null) {
            return;
        }
        vehiculos.add(vehiculo);
        registrarEntradaStockVehiculo(vehiculo.getTipoVehiculo(), 1);
    }

    public Componente buscarComponentePorCodigo(String codigo)
    {
        for (int i = 0; i < componentes.size(); i++) {
            Componente componente = componentes.get(i);
            if (componente.getCodigo().equalsIgnoreCase(codigo)) {
                return componente;
            }
        }
        return null;
    }

    public Vehiculo buscarVehiculoPorCodigo(String codigoVehiculo)
    {
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo vehiculo = vehiculos.get(i);
            if (vehiculo.getCodigoVehiculo().equalsIgnoreCase(codigoVehiculo)) {
                return vehiculo;
            }
        }
        return null;
    }

    public boolean actualizarStockComponente(String codigo, int nuevasUnidades)
    {
        Componente componente = buscarComponentePorCodigo(codigo);
        if (componente == null) {
            return false;
        }
        componente.actualizarStock(nuevasUnidades);
        return true;
    }

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

    public boolean hayStockParaFabricacion(String codigoChasis, String codigoMotor,
                                           String codigoTapiceria, String codigoRueda)
    {
        return tieneDisponible(codigoChasis)
               && tieneDisponible(codigoMotor)
               && tieneDisponible(codigoTapiceria)
               && tieneDisponible(codigoRueda);
    }

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

    private boolean tieneDisponible(String codigoComponente)
    {
        Componente componente = buscarComponentePorCodigo(codigoComponente);
        return componente != null && componente.hayStockDisponible();
    }

    private boolean tieneUnidadesDisponibles(String codigoComponente, int unidades)
    {
        Componente componente = buscarComponentePorCodigo(codigoComponente);
        return componente != null && componente.getUnidadesDisponibles() >= unidades;
    }

    private void devolverComponenteMontado(Componente componente)
    {
        if (componente != null) {
            registrarComponente(componente);
        }
    }

    public Chasis retirarChasis(String codigoChasis)
    {
        Componente componente = buscarComponentePorCodigo(codigoChasis);
        if (componente instanceof Chasis) {
            return (Chasis) componente.extraerUnidadDeStock();
        }
        return null;
    }

    public Motor retirarMotor(String codigoMotor)
    {
        Componente componente = buscarComponentePorCodigo(codigoMotor);
        if (componente instanceof Motor) {
            return (Motor) componente.extraerUnidadDeStock();
        }
        return null;
    }

    public Tapiceria retirarTapiceria(String codigoTapiceria)
    {
        Componente componente = buscarComponentePorCodigo(codigoTapiceria);
        if (componente instanceof Tapiceria) {
            return (Tapiceria) componente.extraerUnidadDeStock();
        }
        return null;
    }

    public Rueda retirarRueda(String codigoRueda)
    {
        Componente componente = buscarComponentePorCodigo(codigoRueda);
        if (componente instanceof Rueda) {
            return (Rueda) componente.extraerUnidadDeStock();
        }
        return null;
    }

    public List<Componente> getComponentes()
    {
        return Collections.unmodifiableList(componentes);
    }

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

    public PedidoProduccionSimple buscarPedidoProduccionPorCodigo(String codigoPedido)
    {
        if (codigoPedido == null) {
            return null;
        }

        for (int i = 0; i < pedidosProduccion.size(); i++) {
            PedidoProduccionSimple pedido = pedidosProduccion.get(i);
            if (pedido.getCodigoPedido().equalsIgnoreCase(codigoPedido)) {
                return pedido;
            }
        }
        return null;
    }

    public List<PedidoProduccionSimple> getPedidosProduccion()
    {
        return Collections.unmodifiableList(pedidosProduccion);
    }

    public List<Componente> buscarComponentesPorNombre(String nombreComponente)
    {
        List<Componente> coincidencias = new ArrayList<Componente>();
        String nombreNormalizado = nombreComponente.toLowerCase();

        for (int i = 0; i < componentes.size(); i++) {
            Componente componente = componentes.get(i);
            if (componente.getNombreComponente().toLowerCase().contains(nombreNormalizado)) {
                coincidencias.add(componente);
            }
        }

        return coincidencias;
    }

    public List<Vehiculo> getVehiculos()
    {
        return Collections.unmodifiableList(vehiculos);
    }

    public StockVehiculo buscarStockVehiculoPorTipo(String tipoVehiculo)
    {
        if (tipoVehiculo == null) {
            return null;
        }

        for (int i = 0; i < stockVehiculos.size(); i++) {
            StockVehiculo stock = stockVehiculos.get(i);
            if (stock.getTipoVehiculo().equalsIgnoreCase(tipoVehiculo)) {
                return stock;
            }
        }
        return null;
    }

    public List<StockVehiculo> getStockVehiculos()
    {
        return Collections.unmodifiableList(stockVehiculos);
    }

    public List<Vehiculo> buscarVehiculosPorTipo(String tipoVehiculo)
    {
        List<Vehiculo> coincidencias = new ArrayList<Vehiculo>();
        String tipoNormalizado = tipoVehiculo.toLowerCase();

        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo vehiculo = vehiculos.get(i);
            if (vehiculo.getTipoVehiculo().toLowerCase().contains(tipoNormalizado)) {
                coincidencias.add(vehiculo);
            }
        }

        return coincidencias;
    }

    public int getNumeroComponentes()
    {
        return componentes.size();
    }

    public int getNumeroVehiculos()
    {
        return vehiculos.size();
    }

    public int getTotalStockVehiculos()
    {
        int total = 0;
        for (int i = 0; i < stockVehiculos.size(); i++) {
            total += stockVehiculos.get(i).getUnidadesDisponibles();
        }
        return total;
    }
}
