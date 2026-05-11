
import java.util.Scanner;

public class FabricaMain
{
    public static void main(String[] args)
    {
        FabricaCoches fabrica = crearSistemaFabrica();
        Scanner scanner = new Scanner(System.in);
        TerminalFabrica terminal = new TerminalFabrica(fabrica, scanner);
        terminal.iniciar();
    }

    private static FabricaCoches crearSistemaFabrica()
    {
        CadenaMontaje deportiva = new CadenaMontajeDeportivo("CAD-DEP-01");
        CadenaMontaje turismo = new CadenaMontajeTurismo("CAD-TUR-01");
        CadenaMontaje furgoneta = new CadenaMontajeFurgoneta("CAD-FUR-01");

        GestorPlanta gestor = new GestorPlanta(
            "Laura", "Martin", "12345678A", "Calle Mayor 1",
            "SS-001", 28000.0, "2025-09-01"
        );
        AdministradorSistema administrador = new AdministradorSistema(
            "Javier", "Lopez", "87654321B", "Avenida Sur 5",
            "SS-002", 30000.0, "2025-09-10"
        );

        FabricaCoches fabrica = new FabricaCoches(
            deportiva, turismo, furgoneta, gestor, administrador
        );

        registrarTrabajadores(fabrica);
        registrarComponentes(fabrica);

        return fabrica;
    }

    private static void registrarTrabajadores(FabricaCoches fabrica)
    {
        fabrica.registrarTrabajador(
            new Operario("Mario", "Sanz", "11223344C", "Calle Norte 7",
                         "SS-003", 21000.0, "2025-09-15",
                         TipoTrabajador.EFICIENTE, 14)
        );
        fabrica.registrarTrabajador(
            new Operario("Elena", "Perez", "22334455E", "Calle Luna 3",
                         "SS-005", 20500.0, "2025-09-19",
                         TipoTrabajador.ESTANDAR, 4)
        );
        fabrica.registrarTrabajador(
            new Operario("David", "Ruiz", "33445566F", "Calle Sol 8",
                         "SS-006", 20800.0, "2025-09-20",
                         TipoTrabajador.EFICIENTE, 18)
        );
        fabrica.registrarTrabajador(
            new Operario("Sonia", "Vega", "55667788G", "Calle Mar 12",
                         "SS-007", 20200.0, "2025-09-21",
                         TipoTrabajador.ESTANDAR, 7)
        );
        fabrica.registrarTrabajador(
            new MecanicoCinta("Nuria", "Gil", "44556677D", "Calle Este 9",
                              "SS-004", 22000.0, "2025-09-18",
                              TipoTrabajador.ESTANDAR, 8)
        );
    }

    private static void registrarComponentes(FabricaCoches fabrica)
    {
        fabrica.registrarComponenteEnAlmacen(
            new Chasis("CHA-TUR-01", "Turismo", "Azul", 5, 1200.0, 1800.0, 3)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Chasis("CHA-DEP-01", "Biplaza deportivo", "Rojo", 2, 950.0, 1400.0, 2)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Chasis("CHA-FUR-01", "Furgoneta", "Blanco", 3, 1800.0, 3000.0, 2)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Motor("MOT-HIB-01", TipoMotor.HIBRIDO, 1600, 140, (short) 4, 4)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Motor("MOT-GAS-01", TipoMotor.GASOLINA, 2000, 180, (short) 4, 3)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Motor("MOT-ELE-01", TipoMotor.ELECTRICO, 0, 120, (short) 0, 2)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Tapiceria("TAP-TEL-01", TipoTapiceria.TELA, "Gris", 9.5f, 5)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Tapiceria("TAP-CUE-01", TipoTapiceria.CUERO, "Negro", 8.0f, 3)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Tapiceria("TAP-ALC-01", TipoTapiceria.ALCANTARA, "Beige", 7.5f, 2)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Rueda("RUE-NOR-01", TipoRueda.NORMAL, 205.0f, 16.0f, 615.0f, 210.0f, 5)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Rueda("RUE-DEP-01", TipoRueda.DEPORTIVA, 225.0f, 18.0f, 690.0f, 240.0f, 3)
        );
        fabrica.registrarComponenteEnAlmacen(
            new Rueda("RUE-TOD-01", TipoRueda.TODOTERRENO, 235.0f, 17.0f, 750.0f, 190.0f, 2)
        );
    }
}
