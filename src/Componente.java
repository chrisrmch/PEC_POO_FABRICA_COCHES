 

import java.util.ArrayList;
import java.util.List;


/**
 * Clase base para los componentes que forman parte del montaje de vehiculos.
 *
 * @author cmamani11
 */
public abstract class Componente implements Observable
{
    private String codigo;
    private String nombreComponente;
    private int unidadesDisponibles;
    private EstadoComponente estado;
    private String referenciaEstado;
    private List<Observer> observadores;

    /**
     * Crea un componente con datos de identificacion y stock inicial.
     *
     * @param codigo identificador unico del componente.
     * @param nombreComponente nombre descriptivo del componente.
     * @param unidadesDisponibles unidades iniciales disponibles en almacen.
     */
    public Componente(String codigo, String nombreComponente, int unidadesDisponibles)
    {
        this.codigo = codigo;
        this.nombreComponente = nombreComponente;
        this.unidadesDisponibles = unidadesDisponibles;
        this.estado = unidadesDisponibles > 0
                      ? EstadoComponente.EN_ALMACEN
                      : EstadoComponente.SIN_STOCK;
        this.referenciaEstado = "Alta en almacen";
        this.observadores = new ArrayList<Observer>();
    }

    /**
     * Devuelve el codigo del componente.
     *
     * @return codigo del componente.
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Devuelve el nombre del componente.
     *
     * @return nombre del componente.
     */
    public String getNombreComponente()
    {
        return nombreComponente;
    }

    /**
     * Devuelve el numero de unidades disponibles.
     *
     * @return unidades disponibles en almacen.
     */
    public int getUnidadesDisponibles()
    {
        return unidadesDisponibles;
    }

    /**
     * Devuelve el estado actual del componente.
     *
     * @return estado del componente.
     */
    public EstadoComponente getEstado()
    {
        return estado;
    }

    /**
     * Devuelve la descripcion del estado actual.
     *
     * @return descripcion del estado.
     */
    public String getDescripcionEstado()
    {
        return estado.getDescripcion();
    }

    /**
     * Devuelve la referencia asociada al ultimo cambio de estado.
     *
     * @return referencia del estado.
     */
    public String getReferenciaEstado()
    {
        return referenciaEstado;
    }

    /**
     * Indica si existe al menos una unidad disponible.
     *
     * @return true si hay stock, false en caso contrario.
     */
    public boolean hayStockDisponible()
    {
        return unidadesDisponibles > 0;
    }

    /**
     * Sustituye el stock por una cantidad nueva y actualiza el estado.
     *
     * @param nuevasUnidades nueva cantidad de unidades.
     */
    public void actualizarStock(int nuevasUnidades)
    {
        if (nuevasUnidades < 0) {
            return;
        }

        this.unidadesDisponibles = nuevasUnidades;
        if (unidadesDisponibles == 0) {
            estado = EstadoComponente.SIN_STOCK;
            referenciaEstado = "Stock agotado";
        }
        else {
            estado = EstadoComponente.EN_ALMACEN;
            referenciaEstado = "Stock actualizado";
        }
        notificarObservadores();
    }

    /**
     * Registra una entrada de unidades en el almacen.
     *
     * @param nuevasUnidades unidades que se anaden al stock.
     */
    public void registrarEntradaStock(int nuevasUnidades)
    {
        if (nuevasUnidades <= 0) {
            return;
        }

        this.unidadesDisponibles += nuevasUnidades;
        this.estado = EstadoComponente.EN_ALMACEN;
        this.referenciaEstado = "Entrada de " + nuevasUnidades + " unidades";
        notificarObservadores();
    }

    /**
     * Extrae una unidad del stock y devuelve una instancia independiente.
     *
     * @return componente independiente retirado, o null si no hay stock.
     */
    public Componente extraerUnidadDeStock()
    {
        if (!hayStockDisponible()) {
            return null;
        }

        this.unidadesDisponibles--;
        if (unidadesDisponibles == 0) {
            this.estado = EstadoComponente.SIN_STOCK;
            this.referenciaEstado = "Salida de ultima unidad";
        }
        else {
            this.estado = EstadoComponente.EN_ALMACEN;
            this.referenciaEstado = "Salida de una unidad";
        }
        notificarObservadores();

        return crearUnidadIndependiente();
    }

    /**
     * Marca el componente como reservado para una cadena de montaje.
     *
     * @param referenciaMontaje codigo o descripcion de la reserva.
     */
    public void marcarReservadoParaMontaje(String referenciaMontaje)
    {
        this.estado = EstadoComponente.RESERVADO;
        this.referenciaEstado = referenciaMontaje;
        notificarObservadores();
    }

    /**
     * Marca el componente como montado en un vehiculo concreto.
     *
     * @param codigoVehiculo codigo del vehiculo donde se monta.
     */
    public void marcarMontadoEnVehiculo(String codigoVehiculo)
    {
        this.estado = EstadoComponente.MONTADO;
        this.referenciaEstado = codigoVehiculo;
        notificarObservadores();
    }

    /**
     * Registra un observador para recibir cambios del componente.
     *
     * @param observer observador que se desea registrar.
     */
    public void registrarObservador(Observer observer)
    {
        if (observer != null && !observadores.contains(observer)) {
            observadores.add(observer);
        }
    }

    /**
     * Elimina un observador del componente.
     *
     * @param observer observador que se desea eliminar.
     */
    public void eliminarObservador(Observer observer)
    {
        observadores.remove(observer);
    }

    /**
     * Notifica a todos los observadores registrados.
     */
    public void notificarObservadores()
    {
        for (int i = 0; i < observadores.size(); i++) {
            observadores.get(i).actualizar(this);
        }
    }

    /**
     * Crea una copia independiente de una unidad fisica del componente.
     *
     * @return componente independiente para montaje.
     */
    protected abstract Componente crearUnidadIndependiente();
}
