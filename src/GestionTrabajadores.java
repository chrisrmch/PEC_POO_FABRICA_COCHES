 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Gestiona altas, consultas y actualizaciones de trabajadores.
 *
 * @author cmamani11
 */
public class GestionTrabajadores
{
    private List<Trabajador> trabajadores;

    /**
     * Crea una gestion de trabajadores vacia.
     */
    public GestionTrabajadores()
    {
        this.trabajadores = new ArrayList<Trabajador>();
    }

    /**
     * Da de alta un trabajador si su DNI no existe previamente.
     *
     * @param trabajador trabajador que se desea registrar.
     * @return true si el alta se completa correctamente.
     */
    public boolean darAltaTrabajador(Trabajador trabajador)
    {
        if (trabajador == null || buscarPorDni(trabajador.getDni()) != null) {
            return false;
        }
        trabajadores.add(trabajador);
        return true;
    }

    /**
     * Busca un trabajador por DNI.
     *
     * @param dni DNI que se desea localizar.
     * @return trabajador encontrado, o null si no existe.
     */
    public Trabajador buscarPorDni(String dni)
    {
        Iterator<Trabajador> iterador = trabajadores.iterator();
        while (iterador.hasNext()) {
            Trabajador trabajador = iterador.next();
            if (trabajador.getDni().equalsIgnoreCase(dni)) {
                return trabajador;
            }
        }
        return null;
    }

    /**
     * Actualiza la direccion de un trabajador.
     *
     * @param dni DNI del trabajador.
     * @param nuevaDireccion nueva direccion postal.
     * @return true si se encuentra y actualiza.
     */
    public boolean actualizarDireccion(String dni, String nuevaDireccion)
    {
        Trabajador trabajador = buscarPorDni(dni);
        if (trabajador == null) {
            return false;
        }
        trabajador.setDireccion(nuevaDireccion);
        return true;
    }

    /**
     * Actualiza el salario de un trabajador.
     *
     * @param dni DNI del trabajador.
     * @param nuevoSalario nuevo salario.
     * @return true si se encuentra y actualiza.
     */
    public boolean actualizarSalario(String dni, double nuevoSalario)
    {
        Trabajador trabajador = buscarPorDni(dni);
        if (trabajador == null) {
            return false;
        }
        trabajador.setSalario(nuevoSalario);
        return true;
    }

    /**
     * Devuelve todos los trabajadores registrados.
     *
     * @return lista no modificable de trabajadores.
     */
    public List<Trabajador> getTrabajadores()
    {
        return Collections.unmodifiableList(trabajadores);
    }

    /**
     * Busca trabajadores cuyo nombre completo contiene el texto indicado.
     *
     * @param texto texto que se desea buscar.
     * @return lista de trabajadores coincidentes.
     */
    public List<Trabajador> buscarPorNombreOApellido(String texto)
    {
        List<Trabajador> coincidencias = new ArrayList<Trabajador>();
        String textoNormalizado = texto.toLowerCase();

        Iterator<Trabajador> iterador = trabajadores.iterator();
        while (iterador.hasNext()) {
            Trabajador trabajador = iterador.next();
            String nombreCompleto = trabajador.getNombreCompleto().toLowerCase();
            if (nombreCompleto.contains(textoNormalizado)) {
                coincidencias.add(trabajador);
            }
        }

        return coincidencias;
    }

    /**
     * Busca trabajadores por coincidencia parcial de puesto.
     *
     * @param puesto texto del puesto que se desea buscar.
     * @return lista de trabajadores coincidentes.
     */
    public List<Trabajador> buscarPorPuesto(String puesto)
    {
        List<Trabajador> coincidencias = new ArrayList<Trabajador>();
        String puestoNormalizado = puesto.toLowerCase();

        Iterator<Trabajador> iterador = trabajadores.iterator();
        while (iterador.hasNext()) {
            Trabajador trabajador = iterador.next();
            if (trabajador.getPuesto().toLowerCase().contains(puestoNormalizado)) {
                coincidencias.add(trabajador);
            }
        }

        return coincidencias;
    }

    /**
     * Devuelve los trabajadores que son operarios.
     *
     * @return lista de operarios registrados.
     */
    public List<Operario> getOperarios()
    {
        List<Operario> operarios = new ArrayList<Operario>();

        Iterator<Trabajador> iterador = trabajadores.iterator();
        while (iterador.hasNext()) {
            Trabajador trabajador = iterador.next();
            if (trabajador instanceof Operario) {
                operarios.add((Operario) trabajador);
            }
        }

        return operarios;
    }

    /**
     * Devuelve el numero total de trabajadores registrados.
     *
     * @return numero de trabajadores.
     */
    public int getNumeroTrabajadores()
    {
        return trabajadores.size();
    }
}
