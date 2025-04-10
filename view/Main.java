package view;

import controller.SistemaEmergencias;
import java.util.List;
import java.util.Scanner;
import model.factoryEmerencias.Emergencia;
import model.factoryEmerencias.FactoryEmergencias;
import model.services.Ambulancia;
import model.services.Bomberos;
import model.services.Policia;
import model.strategy.IPrioridad;
import utils.NivelGravedad;
import utils.TipoEmergencia;
import utils.Ubicacion;

public class Main {

    public static void main(String[] args) {
        // Singleton instance of the emergency system
        SistemaEmergencias sistema = SistemaEmergencias.getInstance();

        // Initialize demo resources
        inicializarRecursosDemo(sistema);
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        // Main menu loop
        while (!salir) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE EMERGENCIAS ===");
            System.out.println("1. Registrar una nueva emergencia");
            sistema.verificarEmergenciasPendientes();
            System.out.println("2. Ver estado de recursos disponibles");
            sistema.verificarEmergenciasPendientes();
            System.out.println("3. Atender una emergencia");
            sistema.verificarEmergenciasPendientes();
            System.out.println("4. Mostrar estadísticas del día");
            sistema.verificarEmergenciasPendientes();
            System.out.println("5. Finalizar jornada (cerrar sistema)");
            sistema.verificarEmergenciasPendientes();
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            // Handle menu options
            switch (opcion) {
                case 1:
                    registrarEmergenciaMenu(sistema, sc);
                    break;
                case 2:
                    sistema.mostrarEstadoRecursos();
                    break;
                case 3:
                    atenderEmergenciaMenu(sistema, sc);
                    break;
                case 4:
                    sistema.mostrarEstadisticas();
                    break;
                case 5:
                    System.out.println("Finalizando jornada...");
                    sistema.finalizarJornada();
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        sc.close();
    }

    // Initialize demo resources for testing
    private static void inicializarRecursosDemo(SistemaEmergencias sistema) {
        sistema.registrarRecurso(new Bomberos("Unidad-B1", 5, 100));
        sistema.registrarRecurso(new Bomberos("Unidad-B2", 3, 80));
        sistema.registrarRecurso(new Ambulancia("Unidad-A1", 2, 100));
        sistema.registrarRecurso(new Ambulancia("Unidad-A2", 2, 60));
        sistema.registrarRecurso(new Policia("Unidad-P1", 4, 100));
        sistema.registrarRecurso(new Policia("Unidad-P2", 2, 70));
    }
    
    // Menu for registering a new emergency
    private static void registrarEmergenciaMenu(SistemaEmergencias sistema, Scanner sc) {
        System.out.println("\n=== REGISTRAR NUEVA EMERGENCIA ===");
        System.out.println("1. Incendio");
        System.out.println("2. Accidente Vehicular");
        System.out.println("3. Robo");
        System.out.print("Seleccione el tipo: ");
        TipoEmergencia tipo = null;
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                tipo = TipoEmergencia.INCENDIO;
                break;
            case 2:
                tipo = TipoEmergencia.ACCIDENTE_VEHICULAR;
                break;
            case 3:
                tipo = TipoEmergencia.ROBO;
                break;
            default:
                System.out.println("Tipo de emergencia inválido.");
                return;
        }

        // Select location
        System.out.print("Ingrese ubicación (1. Norte - 2.Sur - 3.Centro - 4. Este - 5. Oeste): ");
        Ubicacion ubicacion = null;
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                ubicacion = Ubicacion.NORTE;
                break;
            case 2:
                ubicacion = Ubicacion.SUR;
                break;
            case 3:
                ubicacion = Ubicacion.CENTRO;
                break;
            case 4:
                ubicacion = Ubicacion.ESTE;
                break;
            case 5:
                ubicacion = Ubicacion.OESTE;
                break;
            default:
                System.out.println("Ubicación inválida.");
                return;
        }

        // Select severity level
        System.out.print("Ingrese nivel de gravedad (1. bajo, 2. medio, 3. alto): ");
        NivelGravedad nivelGravedad = null;
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                nivelGravedad = NivelGravedad.BAJO;
                break;
            case 2:
                nivelGravedad = NivelGravedad.MEDIO;
                break;
            case 3:
                nivelGravedad = NivelGravedad.ALTO;
                break;
            default:
            System.out.println("Nivel de gravedad inválido.");
                return;
        }

        // Input estimated response time
        System.out.print("Ingrese tiempo estimado de atención (minutos): ");
        int tiempoEstimado = Integer.parseInt(sc.nextLine());

        // Create a temporary emergency to calculate priority
        Emergencia tempEmergencia = new Emergencia(tipo, ubicacion, nivelGravedad, tiempoEstimado, "");
        double prioridadCalculada = sistema.prioridad(tempEmergencia);

        // Convert priority to a string
        String prioridad = String.format("%.1f", prioridadCalculada)+"%";

        // Create and register the emergency
        Emergencia nueva = FactoryEmergencias.crearEmergencia(tipo, ubicacion, nivelGravedad, tiempoEstimado, prioridad);
        if (nueva == null) {
            System.out.println("Tipo de emergencia inválido.");
            return;
        }
        
        sistema.registrarNuevaEmergencia(nueva);
        System.out.println("Emergencia registrada: " + nueva);
    }
    
    // Menu for attending an emergency
    private static void atenderEmergenciaMenu(SistemaEmergencias sistema, Scanner sc) {
        List<Emergencia> pendientes = sistema.getEmergenciasPendientes();
        if (pendientes.isEmpty()) {
            System.out.println("No hay emergencias pendientes.");
            return;
        }

        System.out.println("\n=== ATENDER EMERGENCIA ===");
        for (int i = 0; i < pendientes.size(); i++) {
            System.out.println((i + 1) + ". " + pendientes.get(i).toString());
        }
        System.out.print("Seleccione el número de la emergencia a atender: ");
        int indice = Integer.parseInt(sc.nextLine()) - 1;
        if (indice < 0 || indice >= pendientes.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        // Assign resources and attend the selected emergency
        Emergencia emergencia = pendientes.get(indice);
        sistema.asignarRecursosAEmergencia(emergencia);
        sistema.atenderEmergencia(emergencia);
    }

}