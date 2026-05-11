 

import java.util.ArrayList;
import java.util.List;


public abstract class CadenaMontaje implements Sujeto
{
    private String codigo;
    private String especialidad;
    private Vehiculo vehiculoActual;
    private String faseActual;
    private Componente componenteActual;
    private int contadorVehiculosFabricados;
    private List<Observador> observadores;

    public CadenaMontaje(String codigo, String especialidad)
    {
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.faseActual = "Inactiva";
        this.contadorVehiculosFabricados = 0;
        this.observadores = new ArrayList<Observador>();
    }

    public String getCodigo()
    {
        return codigo;
    }

    public String getEspecialidad()
    {
        return especialidad;
    }

    public Vehiculo getVehiculoActual()
    {
        return vehiculoActual;
    }

    public String getFaseActual()
    {
        return faseActual;
    }

    public Componente getComponenteActual()
    {
        return componenteActual;
    }

    protected void setVehiculoActual(Vehiculo vehiculoActual)
    {
        this.vehiculoActual = vehiculoActual;
    }

    public final Vehiculo fabricaVehiculo(Chasis chasis, Motor motor,
                                          Tapiceria tapiceria, Rueda rueda)
    {
        if (chasis == null || motor == null || tapiceria == null || rueda == null) {
            return null;
        }
        if (!chasis.esCompatibleCon(especialidad)) {
            return null;
        }

        Vehiculo vehiculo = iniciarMontaje(chasis);
        montarMotorEnVehiculo(vehiculo, motor);
        montarTapiceriaEnVehiculo(vehiculo, tapiceria);
        montarRuedasEnVehiculo(vehiculo, rueda);

        return vehiculo;
    }

    public Vehiculo iniciarMontaje(Chasis chasis)
    {
        if (chasis == null || !chasis.esCompatibleCon(especialidad)) {
            return null;
        }

        Vehiculo vehiculo = crearVehiculoBase(generarCodigoVehiculo());
        setVehiculoActual(vehiculo);
        montarChasisRobotizado(vehiculo, chasis);
        avanzarFase("Chasis", chasis);
        return vehiculo;
    }

    public void montarMotorEnVehiculo(Vehiculo vehiculo, Motor motor)
    {
        if (vehiculo == null || motor == null) {
            return;
        }
        montarMotorRobotizado(vehiculo, motor);
        avanzarFase("Motor", motor);
    }

    public void montarTapiceriaEnVehiculo(Vehiculo vehiculo, Tapiceria tapiceria)
    {
        if (vehiculo == null || tapiceria == null) {
            return;
        }
        montarTapiceriaRobotizada(vehiculo, tapiceria);
        avanzarFase("Tapiceria", tapiceria);
    }

    public void montarRuedasEnVehiculo(Vehiculo vehiculo, Rueda rueda)
    {
        if (vehiculo == null || rueda == null) {
            return;
        }
        montarRuedasRobotizadas(vehiculo, rueda);
        avanzarFase("Ruedas", rueda);
    }

    private String generarCodigoVehiculo()
    {
        contadorVehiculosFabricados++;
        return codigo + "-VEH-" + contadorVehiculosFabricados;
    }

    protected void avanzarFase(String faseActual, Componente componenteActual)
    {
        this.faseActual = faseActual;
        this.componenteActual = componenteActual;
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

    protected abstract Vehiculo crearVehiculoBase(String codigoVehiculo);

    protected abstract void montarChasisRobotizado(Vehiculo vehiculo, Chasis chasis);

    protected abstract void montarMotorRobotizado(Vehiculo vehiculo, Motor motor);

    protected abstract void montarTapiceriaRobotizada(Vehiculo vehiculo, Tapiceria tapiceria);

    protected abstract void montarRuedasRobotizadas(Vehiculo vehiculo, Rueda rueda);
}
