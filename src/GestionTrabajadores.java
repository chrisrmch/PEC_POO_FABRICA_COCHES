 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionTrabajadores
{
    private List<Trabajador> trabajadores;

    public GestionTrabajadores()
    {
        this.trabajadores = new ArrayList<Trabajador>();
    }

    public boolean darAltaTrabajador(Trabajador trabajador)
    {
        if (trabajador == null || buscarPorDni(trabajador.getDni()) != null) {
            return false;
        }
        trabajadores.add(trabajador);
        return true;
    }

    public Trabajador buscarPorDni(String dni)
    {
        for (int i = 0; i < trabajadores.size(); i++) {
            Trabajador trabajador = trabajadores.get(i);
            if (trabajador.getDni().equalsIgnoreCase(dni)) {
                return trabajador;
            }
        }
        return null;
    }

    public boolean actualizarDireccion(String dni, String nuevaDireccion)
    {
        Trabajador trabajador = buscarPorDni(dni);
        if (trabajador == null) {
            return false;
        }
        trabajador.setDireccion(nuevaDireccion);
        return true;
    }

    public boolean actualizarSalario(String dni, double nuevoSalario)
    {
        Trabajador trabajador = buscarPorDni(dni);
        if (trabajador == null) {
            return false;
        }
        trabajador.setSalario(nuevoSalario);
        return true;
    }

    public List<Trabajador> getTrabajadores()
    {
        return Collections.unmodifiableList(trabajadores);
    }

    public List<Trabajador> buscarPorNombreOApellido(String texto)
    {
        List<Trabajador> coincidencias = new ArrayList<Trabajador>();
        String textoNormalizado = texto.toLowerCase();

        for (int i = 0; i < trabajadores.size(); i++) {
            Trabajador trabajador = trabajadores.get(i);
            String nombreCompleto = trabajador.getNombreCompleto().toLowerCase();
            if (nombreCompleto.contains(textoNormalizado)) {
                coincidencias.add(trabajador);
            }
        }

        return coincidencias;
    }

    public List<Trabajador> buscarPorPuesto(String puesto)
    {
        List<Trabajador> coincidencias = new ArrayList<Trabajador>();
        String puestoNormalizado = puesto.toLowerCase();

        for (int i = 0; i < trabajadores.size(); i++) {
            Trabajador trabajador = trabajadores.get(i);
            if (trabajador.getPuesto().toLowerCase().contains(puestoNormalizado)) {
                coincidencias.add(trabajador);
            }
        }

        return coincidencias;
    }

    public List<Operario> getOperarios()
    {
        List<Operario> operarios = new ArrayList<Operario>();

        for (int i = 0; i < trabajadores.size(); i++) {
            Trabajador trabajador = trabajadores.get(i);
            if (trabajador instanceof Operario) {
                operarios.add((Operario) trabajador);
            }
        }

        return operarios;
    }

    public int getNumeroTrabajadores()
    {
        return trabajadores.size();
    }
}
