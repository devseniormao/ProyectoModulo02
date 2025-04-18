package view;

// Importaciones necesarias
import controller.SistemaEmergencias;
import java.util.List;
import java.util.Scanner;
import model.factoryEmerencias.Emergencia;
import model.factoryEmerencias.FactoryEmergencias;
import model.services.Ambulancia;
import model.services.Bomberos;
import model.services.Policia;
import utils.NivelGravedad;
import utils.TipoEmergencia;
import utils.Ubicacion;

public class Main {

    public static void main(String[] args) {
        // Singleton para obtener la instancia del sistema de emergencias
        SistemaEmergencias sistema = SistemaEmergencias.getInstance();

        // Inicializar recursos de demostración
        inicializarRecursosDemo(sistema);
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        // Bucle principal del menú
        while (!salir) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE EMERGENCIAS ===");
            System.out.println("1. Registrar una nueva emergencia");
            System.out.println("2. Ver estado de recursos disponibles");
            System.out.println("3. Atender una emergencia");
            System.out.println("4. Reasignar recursos a una emergencia");
            System.out.println("5. Mostrar estadísticas del día");
            System.out.println("6. Finalizar jornada (cerrar sistema)");
            System.out.println("7. Agencias");
          
            // Verificar si hay emergencias pendientes
            sistema.verificarEmergenciasPendientes();
          
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            try {
                // Leer la opción seleccionada por el usuario
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            // Manejar las opciones del menú
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
                    reasignarRecursosMenu(sistema, sc);
                    break;
                case 5:
                    sistema.mostrarEstadisticas();
                    break;
                case 6:
                    System.out.println("Finalizando jornada...");
                    sistema.finalizarJornada();
                    salir = true;
                    break;
                case 7:
                    mostrarSubmenuAgencias(sistema, sc);
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        sc.close();
    }

    // Método para inicializar recursos de demostración
    private static void inicializarRecursosDemo(SistemaEmergencias sistema) {
        // Registrar recursos de bomberos
        sistema.registrarRecurso(new Bomberos("Unidad-B1", 5, 1,25));
        sistema.registrarRecurso(new Bomberos("Unidad-B2", 5, 1,25));
        sistema.registrarRecurso(new Bomberos("Unidad-B3", 5, 1,25));
        sistema.registrarRecurso(new Bomberos("Unidad-B4", 5, 1,25));
        // Registrar recursos de ambulancias
        sistema.registrarRecurso(new Ambulancia("Unidad-A1", 2, 1, 10));
        sistema.registrarRecurso(new Ambulancia("Unidad-A2", 2, 1, 10));
        sistema.registrarRecurso(new Ambulancia("Unidad-A3", 2, 1, 10));
        sistema.registrarRecurso(new Ambulancia("Unidad-A4", 2, 1, 10));
        sistema.registrarRecurso(new Ambulancia("Unidad-A5", 2, 1, 10));
        // Registrar recursos de policía
        sistema.registrarRecurso(new Policia("Unidad-P1", 2,1, 10));
        sistema.registrarRecurso(new Policia("Unidad-P2", 2, 1,10));
        sistema.registrarRecurso(new Policia("Unidad-P3", 2, 1,10));
        sistema.registrarRecurso(new Policia("Unidad-P4", 2, 1,10));
        sistema.registrarRecurso(new Policia("Unidad-P5", 2, 1,10));
        sistema.registrarRecurso(new Policia("Unidad-P6", 2, 1,10));
        sistema.registrarRecurso(new Policia("Unidad-P7", 2, 1,10));
        sistema.registrarRecurso(new Policia("Unidad-P8", 2, 1,10));
        sistema.registrarRecurso(new Policia("Unidad-P9", 2, 1,10));
        sistema.registrarRecurso(new Policia("Unidad-P10", 2, 1,10));
    }
    
    // Menú para registrar una nueva emergencia
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

        // Seleccionar ubicación
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

        // Seleccionar nivel de gravedad
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

        // Ingresar tiempo estimado de atención
        System.out.print("Ingrese tiempo estimado de atención (minutos): ");
        int tiempoEstimado = Integer.parseInt(sc.nextLine());

        // Crear una emergencia temporal para calcular la prioridad
        Emergencia tempEmergencia = new Emergencia(tipo, ubicacion, nivelGravedad, tiempoEstimado, "");
        double prioridadCalculada = sistema.prioridad(tempEmergencia);

        // Convertir la prioridad a un formato de cadena
        String prioridad = String.format("%.1f", prioridadCalculada)+"%";

        // Crear y registrar la emergencia
        Emergencia nueva = FactoryEmergencias.crearEmergencia(tipo, ubicacion, nivelGravedad, tiempoEstimado, prioridad);
        if (nueva == null) {
            System.out.println("Tipo de emergencia inválido.");
            return;
        }
        
        sistema.registrarNuevaEmergencia(nueva);
        System.out.println("Emergencia registrada: " + nueva);
    }
    
    // Menú para atender una emergencia
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

        // Asignar recursos y atender la emergencia seleccionada
        Emergencia emergencia = pendientes.get(indice);
        sistema.asignarRecursosAEmergencia(emergencia);
        sistema.atenderEmergencia(emergencia);
    }

    private static void reasignarRecursosMenu(SistemaEmergencias sistema, Scanner sc) {
        List<Emergencia> enCurso = sistema.getEmergenciasEnCurso();
        if (enCurso.isEmpty()) {
            System.out.println("No hay emergencias en curso para reasignar recursos.");
            return;
        }
    
        System.out.println("\n=== REASIGNAR RECURSOS ===");
        for (int i = 0; i < enCurso.size(); i++) {
            System.out.println((i + 1) + ". " + enCurso.get(i).toString());
        }
        System.out.print("Seleccione el número de la emergencia para reasignar recursos: ");
        int indice = Integer.parseInt(sc.nextLine()) - 1;
        if (indice < 0 || indice >= enCurso.size()) {
            System.out.println("Índice inválido.");
            return;
        }
    
        Emergencia emergencia = enCurso.get(indice);
        sistema.reasignarRecursosAEmergencia(emergencia);
    }


    // Submenú para mostrar emergencias por agencia
    private static void mostrarSubmenuAgencias(SistemaEmergencias sistema, Scanner sc) {
        System.out.println("\n=== AGENCIAS ===");
        System.out.println("1. Ambulancia");
        System.out.println("2. Policía");
        System.out.println("3. Bomberos");
        System.out.print("Seleccione una opción: ");

        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                sistema.mostrarEmergenciasPorAgencia("Ambulancia");
                break;
            case 2:
                sistema.mostrarEmergenciasPorAgencia("Policía");
                break;
            case 3:
                sistema.mostrarEmergenciasPorAgencia("Bomberos");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
}