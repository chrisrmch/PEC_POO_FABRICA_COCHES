 

import java.util.ArrayList;
import java.util.List;


/**
 * Clase base para una cadena de montaje robotizada.
 *
 * @author cmamani11
 */
public abstract class CadenaMontaje implements Observable
{
    private String codigo;
    private String especialidad;
    private Vehiculo vehiculoActual;
    private String faseActual;
    private Componente componenteActual;
    private int contadorVehiculosFabricados;
    private List<Observer> observadores;

    /**
     * Crea una cadena de montaje para una especialidad concreta.
     *
     * @param codigo identificador de la cadena.
     * @param especialidad tipo de vehiculo que puede fabricar.
     */
    public CadenaMontaje(String codigo, String especialidad)
    {
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.faseActual = "Inactiva";
        this.contadorVehiculosFabricados = 0;
        this.observadores = new ArrayList<Observer>();
    }

    /**
     * Devuelve el codigo de la cadena.
     *
     * @return codigo de la cadena.
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Devuelve la especialidad de la cadena.
     *
     * @return especialidad o tipo compatible.
     */
    public String getEspecialidad()
    {
        return especialidad;
    }

    /**
     * Devuelve el vehiculo actualmente en montaje.
     *
     * @return vehiculo actual, o null si no hay montaje activo.
     */
    public Vehiculo getVehiculoActual()
    {
        return vehiculoActual;
    }

    /**
     * Devuelve la fase actual de montaje.
     *
     * @return fase actual.
     */
    public String getFaseActual()
    {
        return faseActual;
    }

    /**
     * Devuelve el componente de la fase actual.
     *
     * @return componente actual.
     */
    public Componente getComponenteActual()
    {
        return componenteActual;
    }

    /**
     * Actualiza el vehiculo en montaje.
     *
     * @param vehiculoActual vehiculo que queda asociado a la cadena.
     */
    protected void setVehiculoActual(Vehiculo vehiculoActual)
    {
        this.vehiculoActual = vehiculoActual;
    }

    /**
     * Ejecuta el montaje completo de un vehiculo con los componentes recibidos.
     *
     * @param chasis chasis que se montara.
     * @param motor motor que se montara.
     * @param tapiceria tapiceria que se montara.
     * @param rueda rueda que se montara.
     * @return vehiculo fabricado, o null si los componentes no son validos.
     */
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

    /**
     * Inicia el montaje creando el vehiculo base e instalando el chasis.
     *
     * @param chasis chasis que define el vehiculo.
     * @return vehiculo iniciado, o null si el chasis no es valido.
     */
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

    /**
     * Monta el motor en un vehiculo.
     *
     * @param vehiculo vehiculo sobre el que se trabaja.
     * @param motor motor que se instala.
     */
    public void montarMotorEnVehiculo(Vehiculo vehiculo, Motor motor)
    {
        if (vehiculo == null || motor == null) {
            return;
        }
        montarMotorRobotizado(vehiculo, motor);
        avanzarFase("Motor", motor);
    }

    /**
     * Monta la tapiceria en un vehiculo.
     *
     * @param vehiculo vehiculo sobre el que se trabaja.
     * @param tapiceria tapiceria que se instala.
     */
    public void montarTapiceriaEnVehiculo(Vehiculo vehiculo, Tapiceria tapiceria)
    {
        if (vehiculo == null || tapiceria == null) {
            return;
        }
        montarTapiceriaRobotizada(vehiculo, tapiceria);
        avanzarFase("Tapiceria", tapiceria);
    }

    /**
     * Monta las ruedas en un vehiculo.
     *
     * @param vehiculo vehiculo sobre el que se trabaja.
     * @param rueda rueda que se instala.
     */
    public void montarRuedasEnVehiculo(Vehiculo vehiculo, Rueda rueda)
    {
        if (vehiculo == null || rueda == null) {
            return;
        }
        montarRuedasRobotizadas(vehiculo, rueda);
        avanzarFase("Ruedas", rueda);
    }

    /**
     * Genera el siguiente codigo de vehiculo para esta cadena.
     *
     * @return codigo de vehiculo generado.
     */
    private String generarCodigoVehiculo()
    {
        contadorVehiculosFabricados++;
        return codigo + "-VEH-" + contadorVehiculosFabricados;
    }

    /**
     * Cambia la fase de la cadena y avisa a los observadores.
     *
     * @param faseActual nombre de la nueva fase.
     * @param componenteActual componente tratado en la fase.
     */
    protected void avanzarFase(String faseActual, Componente componenteActual)
    {
        this.faseActual = faseActual;
        this.componenteActual = componenteActual;
        notificarObservadores();
    }

    /**
     * Registra un observador de la cadena.
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
     * Elimina un observador de la cadena.
     *
     * @param observer observador que se desea eliminar.
     */
    public void eliminarObservador(Observer observer)
    {
        observadores.remove(observer);
    }

    /**
     * Notifica el estado de la cadena a todos los observadores.
     */
    public void notificarObservadores()
    {
        for (int i = 0; i < observadores.size(); i++) {
            observadores.get(i).actualizar(this);
        }
    }

    /**
     * Crea la instancia concreta del vehiculo fabricado por la cadena.
     *
     * @param codigoVehiculo codigo generado para el vehiculo.
     * @return vehiculo base de la especialidad.
     */
    protected abstract Vehiculo crearVehiculoBase(String codigoVehiculo);

    /**
     * Realiza el montaje robotizado del chasis.
     *
     * @param vehiculo vehiculo sobre el que se monta.
     * @param chasis chasis que se instala.
     */
    protected abstract void montarChasisRobotizado(Vehiculo vehiculo, Chasis chasis);

    /**
     * Realiza el montaje robotizado del motor.
     *
     * @param vehiculo vehiculo sobre el que se monta.
     * @param motor motor que se instala.
     */
    protected abstract void montarMotorRobotizado(Vehiculo vehiculo, Motor motor);

    /**
     * Realiza el montaje robotizado de la tapiceria.
     *
     * @param vehiculo vehiculo sobre el que se monta.
     * @param tapiceria tapiceria que se instala.
     */
    protected abstract void montarTapiceriaRobotizada(Vehiculo vehiculo, Tapiceria tapiceria);

    /**
     * Realiza el montaje robotizado de las ruedas.
     *
     * @param vehiculo vehiculo sobre el que se monta.
     * @param rueda rueda que se instala.
     */
    protected abstract void montarRuedasRobotizadas(Vehiculo vehiculo, Rueda rueda);
}
