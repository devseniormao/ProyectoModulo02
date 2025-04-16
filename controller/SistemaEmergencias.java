package controller;

// Importaciones necesarias para el funcionamiento del sistema
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.factoryEmerencias.AccidenteVehicular;
import model.factoryEmerencias.Emergencia;
import model.factoryEmerencias.Incendio;
import model.factoryEmerencias.Robo;
import model.interfaces.IServicioEmergencia;
import model.observer.ObserverEmergencias;
import model.observer.SujetoEmergencias;
import model.services.Ambulancia;
import model.services.Bomberos;
import model.services.Policia;
import model.strategy.CalcularPrioridad;
import model.strategy.IPrioridad;
import model.strategy.StrategyPrioridadCercania;
import model.strategy.StrategyPrioridadGravedad;

// Clase principal que implementa el patrón Singleton y gestiona emergencias
public class SistemaEmergencias implements SujetoEmergencias {

    // Atributos principales del sistema
    private static SistemaEmergencias instance; // Instancia única del sistema
    private List<Emergencia> listaEmergencias; // Lista de emergencias registradas
    private List<IServicioEmergencia> listaRecursos; // Lista de recursos disponibles
    private List<ObserverEmergencias> observadores; // Observadores para notificaciones
    private List<Emergencia> emergenciasEnCurso; // Emergencias en curso
    private IPrioridad strategyPrioridad; // Estrategia para calcular prioridad
    private int emergenciasAtendidas; // Contador de emergencias atendidas
    private long tiempoTotalAtencion; // Tiempo total de atención acumulado

    // Constructor privado para implementar el patrón Singleton
    private SistemaEmergencias() {
        strategyPrioridad = new StrategyPrioridadGravedad(); // Estrategia por defecto
        listaEmergencias = new ArrayList<>();
        listaRecursos = new ArrayList<>();
        observadores = new ArrayList<>();
        emergenciasAtendidas = 0;
        tiempoTotalAtencion = 0;
    }

    // Método para obtener la instancia única del sistema
    public static SistemaEmergencias getInstance() {
        if (instance == null) {
            instance = new SistemaEmergencias();
        }
        return instance;
    }

    // Métodos para gestionar observadores (patrón Observer)
    @Override
    public void agregarObserver(ObserverEmergencias observerEmergencias) {
        observadores.add(observerEmergencias);
    }

    @Override
    public void eliminarObserver(ObserverEmergencias observerEmergencias) {
        observadores.remove(observerEmergencias);
    }

    @Override
    public void notificarEmergencias(Emergencia emergencia) {
        // Notifica a los observadores sobre el estado de una emergencia
        for (ObserverEmergencias observer : observadores) {
            if (emergencia.isAtendida()) {
                observer.onEmergenciaAtendida(emergencia);
            } else {
                observer.onEmergenciaNoAtendida(emergencia);
            }
        }
    }

    // Registra un nuevo recurso en el sistema
    public void registrarRecurso(IServicioEmergencia recurso) {
        listaRecursos.add(recurso);
    }

    // Muestra el estado actual de los recursos
    public void mostrarEstadoRecursos() {
        System.out.println("\n=== ESTADO ACTUAL DE RECURSOS ===");
        for (IServicioEmergencia r : listaRecursos) {
            System.out.println(r.toString());
        }
    }

    // Filtra y devuelve los recursos disponibles usando Streams
    public List<IServicioEmergencia> filtrarRecursosDisponibles() {
        return listaRecursos.stream()
                .filter(r -> r.estaDisponible())
                .collect(Collectors.toList());
    }

    // Registra una nueva emergencia y notifica a los observadores
    public void registrarNuevaEmergencia(Emergencia e) {
        listaEmergencias.add(e);
        notificarEmergencias(e);
    }

    // Obtiene la lista de emergencias pendientes
    public List<Emergencia> getEmergenciasPendientes() {
        return listaEmergencias.stream()
                .filter(e -> !e.isAtendida())
                .collect(Collectors.toList());
    }

    // Obtiene la lista de emergencias en curso
    public List<Emergencia> getEmergenciasEnCurso() {
        return listaEmergencias.stream()
                .filter(e -> e.isAtendida())
                .collect(Collectors.toList());
    }

    // Asigna recursos a una emergencia según su tipo
    public void asignarRecursosAEmergencia(Emergencia emergencia) {
        List<IServicioEmergencia> disponibles = filtrarRecursosDisponibles();
        if (disponibles.isEmpty()) {
            System.out.println("No hay recursos disponibles para esta emergencia.");
            return;
        }
        System.out.println("-> Asignando recursos automáticamente...");

        // Asignación específica según el tipo de emergencia
        if (emergencia instanceof Incendio) {
            for (IServicioEmergencia r : disponibles) {
                if (r instanceof Bomberos) {
                    r.atenderEmergencia(emergencia);
                    break;
                }
            }
        } else if (emergencia instanceof AccidenteVehicular) {
            for (IServicioEmergencia r : disponibles) {
                if (r instanceof Ambulancia) {
                    r.atenderEmergencia(emergencia);
                    break;
                }
            }
        } else if (emergencia instanceof Robo) {
            for (IServicioEmergencia r : disponibles) {
                if (r instanceof Policia) {
                    r.atenderEmergencia(emergencia);
                    break;
                }
            }
        }
    }

    // Simula la atención de una emergencia
    public void atenderEmergencia(Emergencia e) {
        e.iniciarAtencion();
        System.out.println("Atendiendo emergencia: " + e.toString());

        int totalTiempo = 4000; // Tiempo total simulado
        int pasos = 10; // Número de pasos para simular
        int tiempoPorPaso = totalTiempo / pasos;

        for (int i = 1; i <= pasos; i++) {
            try {
                Thread.sleep(tiempoPorPaso);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            int porcentage = (i * 100) / pasos;
            System.out.println("Avance Atención de emergencia: " + porcentage + "% completado.");
        }

        e.finalizarAtencion();
        System.out.println("Emergencia atendida: " + e.toString());

        emergenciasAtendidas++;
        tiempoTotalAtencion += e.getTiempoRespuesta();
    }

    // Muestra estadísticas del día
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DEL DÍA ===");
        System.out.println("Emergencias atendidas: " + emergenciasAtendidas);

        long promedioMs = 0;
        if (emergenciasAtendidas > 0) {
            promedioMs = tiempoTotalAtencion / emergenciasAtendidas;
        }
        double promedioSeg = promedioMs / 1000.0;
        System.out.println("Tiempo promedio de respuesta: " + promedioSeg + " seg.");

        long noAtendidas = listaEmergencias.stream()
                .filter(e -> !e.isAtendida())
                .count();
        System.out.println("Emergencias no atendidas: " + noAtendidas);
    }

    // Verifica emergencias pendientes y en curso
    public void verificarEmergenciasPendientes() {
        List<Emergencia> pendientes = getEmergenciasPendientes();
        List<Emergencia> enCurso = getEmergenciasEnCurso();
        LocalDateTime ahora = LocalDateTime.now();

        // Revisar emergencias pendientes
        for (Emergencia e : pendientes) {
            System.out.println("ALARMA: Emergencia pendiente de atencion -> " + e);
        }

        // Revisar emergencias en curso
        for (Emergencia e : enCurso) {
            if (e.isAtendida()) {
                continue;
            }

            System.out.println("ALARMA: Emergencia en curso -> " + e);
            if (e.getTiempoInicioAtencion() > 0) {
                Instant tiempoInicioInstant = Instant.ofEpochMilli(e.getTiempoInicioAtencion());
                Instant ahoraInstant = ahora.atZone(ZoneId.systemDefault()).toInstant();

                Duration duracion = Duration.between(tiempoInicioInstant, ahoraInstant);
                long minutosTranscurridos = duracion.toMinutes();
                if (minutosTranscurridos > e.getTiempoRespuesta()) {
                    System.out.println("ALARMA: Emergencia en curso ha excedido el tiempo estimado de atención.");
                }
            } else {
                System.out.println("ALARMA: Emergencia en curso no tiene tiempo de inicio registrado.");
            }
        }
    }

    // Finaliza la jornada mostrando estadísticas y simulando el guardado de datos
    public void finalizarJornada() {
        mostrarEstadisticas();
        System.out.println("Guardando registro del día (simulado)...");
        System.out.println("Sistema preparado para siguiente ciclo.");
    }

    // Cambia la estrategia de prioridad
    public void setEstrategiaPrioridad(IPrioridad nuevaEstrategia) {
        strategyPrioridad = nuevaEstrategia;
    }

    // Calcula la prioridad de una emergencia usando diferentes estrategias
    public double prioridad(Emergencia emergencia) {
        CalcularPrioridad distancia = new CalcularPrioridad(new StrategyPrioridadCercania());
        CalcularPrioridad gravedad = new CalcularPrioridad(new StrategyPrioridadGravedad());

        double prioridad = ((gravedad.calcularPrioridad(emergencia) / 3.0) * 70)
                         + ((distancia.calcularPrioridad(emergencia) / 10.0) * 30);
        return prioridad;
    }

    // Muestra emergencias filtradas por agencia
    public void mostrarEmergenciasPorAgencia(String agencia) {
        System.out.println("\n=== EMERGENCIAS PARA " + agencia.toUpperCase() + " ===");
        List<Emergencia> atendidas = listaEmergencias.stream()
                .filter(e -> e.isAtendida() && obtenerTipoRecurso(e).equalsIgnoreCase(agencia))
                .collect(Collectors.toList());
        List<Emergencia> noAtendidas = listaEmergencias.stream()
                .filter(e -> !e.isAtendida() && obtenerTipoRecurso(e).equalsIgnoreCase(agencia))
                .collect(Collectors.toList());

        System.out.println("Emergencias atendidas:");
        atendidas.forEach(e -> System.out.println(e.toString()));

        System.out.println("\nEmergencias no atendidas:");
        noAtendidas.forEach(e -> System.out.println(e.toString()));
    }

    // Obtiene el tipo de recurso asociado a una emergencia
    private String obtenerTipoRecurso(Emergencia emergencia) {
        if (emergencia instanceof AccidenteVehicular) {
            return "Ambulancia";
        } else if (emergencia instanceof Robo) {
            return "Policía";
        } else if (emergencia instanceof Incendio) {
            return "Bomberos";
        }
        return "Desconocido";
    }
}