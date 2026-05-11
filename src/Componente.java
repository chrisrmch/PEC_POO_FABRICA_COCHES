 

import java.util.ArrayList;
import java.util.List;


public abstract class Componente implements Sujeto
{
    private String codigo;
    private String nombreComponente;
    private int unidadesDisponibles;
    private EstadoComponente estado;
    private String referenciaEstado;
    private List<Observador> observadores;

    public Componente(String codigo, String nombreComponente, int unidadesDisponibles)
    {
        this.codigo = codigo;
        this.nombreComponente = nombreComponente;
        this.unidadesDisponibles = unidadesDisponibles;
        this.estado = unidadesDisponibles > 0
                      ? EstadoComponente.EN_ALMACEN
                      : EstadoComponente.SIN_STOCK;
        this.referenciaEstado = "Alta en almacen";
        this.observadores = new ArrayList<Observador>();
    }

    public String getCodigo()
    {
        return codigo;
    }

    public String getNombreComponente()
    {
        return nombreComponente;
    }

    public int getUnidadesDisponibles()
    {
        return unidadesDisponibles;
    }

    public EstadoComponente getEstado()
    {
        return estado;
    }

    public String getDescripcionEstado()
    {
        return estado.getDescripcion();
    }

    public String getReferenciaEstado()
    {
        return referenciaEstado;
    }

    public boolean hayStockDisponible()
    {
        return unidadesDisponibles > 0;
    }

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

    public void marcarReservadoParaMontaje(String referenciaMontaje)
    {
        this.estado = EstadoComponente.RESERVADO;
        this.referenciaEstado = referenciaMontaje;
        notificarObservadores();
    }

    public void marcarMontadoEnVehiculo(String codigoVehiculo)
    {
        this.estado = EstadoComponente.MONTADO;
        this.referenciaEstado = codigoVehiculo;
        notificarObservadores();
    }

    public void registrarObservador(Observador observador)
    {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void eliminarObservador(Observador observador)
    {
        observadores.remove(observador);
    }

    public void notificarObservadores()
    {
        for (int i = 0; i < observadores.size(); i++) {
            observadores.get(i).actualizar(this);
        }
    }

    protected abstract Componente crearUnidadIndependiente();
}
