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
import utils.TipoEmergencia;

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
    private int[] notificacionesPendientes; // 0 Policia, 1 Bomberos, 2 Ambulancias

    // Constructor privado para implementar el patrón Singleton
    private SistemaEmergencias() {
        strategyPrioridad = new StrategyPrioridadGravedad(); // Estrategia por defecto
        listaEmergencias = new ArrayList<>();
        listaRecursos = new ArrayList<>();
        observadores = new ArrayList<>();
        emergenciasAtendidas = 0;
        tiempoTotalAtencion = 0;
        notificacionesPendientes = new int[3];
    }

    public List<ObserverEmergencias> getObservadores() {
        return observadores;
    }

    public List<Emergencia> getListaEmergencias() {
        return listaEmergencias;
    }

    // Método para obtener la instancia única del sistema
    public static SistemaEmergencias getInstance() {
        if (instance == null) {
            instance = new SistemaEmergencias();
        }
        return instance;
    }

    public int[] getNotificacionesPendientes() {
        return notificacionesPendientes;
    }

    // Métodos para gestionar observadores (patrón Observer)
    @Override
    public void agregarObserver(ObserverEmergencias observerEmergencias) {
        if (observadores.contains(observerEmergencias)) {
            System.out.println("Ya existe una suscripción con este nombre");
        } else {
            observadores.add(observerEmergencias);
            System.out.println("Suscripción realizada exitosamente");
        }
    }

    @Override
    public void eliminarObserver(ObserverEmergencias observerEmergencias) {
        if (observadores.contains(observerEmergencias)) {
            observadores.remove(observerEmergencias);
            System.out.println("Suscripción eliminada exitosamente");
        } else {
            System.out.println("Actualmente no existe una suscripción para esta agencia");
        }
    }

    public List<Emergencia> getEmergenciasAmbulancia() {
        // Filtrar las emergencias para ambulancia
        List<Emergencia> emergenciasAmbulancia = listaEmergencias.stream()
                .filter(r -> r.getTipo().equals(TipoEmergencia.ACCIDENTE_VEHICULAR))
                .collect(Collectors.toList());
        return emergenciasAmbulancia;
    }

    public List<Emergencia> getEmergenciasPolicia() {
        // Filtrar las emergencias para policia
        List<Emergencia> emergenciasPolicia = listaEmergencias.stream()
                .filter(r -> r.getTipo().equals(TipoEmergencia.ROBO))
                .collect(Collectors.toList());
        return emergenciasPolicia;
    }

    public List<Emergencia> getEmergenciasBomberos() {
        // Filtrar las emergencias para bomberos
        List<Emergencia> emergenciasBomberos = listaEmergencias.stream()
                .filter(r -> r.getTipo().equals(TipoEmergencia.INCENDIO))
                .collect(Collectors.toList());
        return emergenciasBomberos;
    }

    @Override
    public void notificarEmergencias(List<Emergencia> emergencias) {

        // Notificaciones para los observadores emergencias atendidas
        if (observadores.isEmpty()) {
            System.out.println("No has realizado la suscripción de ninguna agencia");
            return;
        }

        for (ObserverEmergencias observerEmergencias : observadores) {
            observerEmergencias.onEmergenciaAtendida(emergencias);
        }

        // Notificaciones para los observadores emergencias no atendidas
        for (ObserverEmergencias observerEmergencias : observadores) {
            observerEmergencias.onEmergenciaNoAtendida(emergencias);
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

    // Registra una nueva emergencia y actualiza las notificaciones pendientes
    public void registrarNuevaEmergencia(Emergencia e) {
        listaEmergencias.add(e); // Agrega la emergencia a la lista
        // Incrementa el contador de notificaciones pendientes según el tipo de emergencia
        if (e.getTipo().equals(TipoEmergencia.ACCIDENTE_VEHICULAR)) {
            notificacionesPendientes[2]++; // Notificación para ambulancias
        } else if (e.getTipo().equals(TipoEmergencia.INCENDIO)) {
            notificacionesPendientes[1]++; // Notificación para bomberos
        } else {
            notificacionesPendientes[0]++; // Notificación para policía
        }
    }

    // Obtiene la lista de emergencias pendientes (no atendidas)
    public List<Emergencia> getEmergenciasPendientes() {
        return listaEmergencias.stream()
                .filter(e -> !e.isAtendida()) // Filtra las emergencias no atendidas
                .collect(Collectors.toList());
    }

    // Obtiene la lista de emergencias en curso (atendidas)
    public List<Emergencia> getEmergenciasEnCurso() {
        return listaEmergencias.stream()
                .filter(e -> e.isAtendida()) // Filtra las emergencias atendidas
                .collect(Collectors.toList());
    }

    // Asigna recursos a una emergencia según su tipo
    public void asignarRecursosAEmergencia(Emergencia emergencia) {
        // Filtra los recursos disponibles
        List<IServicioEmergencia> disponibles = filtrarRecursosDisponibles();
        if (disponibles.isEmpty()) {
            System.out.println("No hay recursos disponibles para esta emergencia.");
            return;
        }
        System.out.println("-> Asignando recursos automáticamente...");

        // Asignación específica según el tipo de emergencia
        if (emergencia instanceof Incendio) {
            // Asigna recursos de bomberos
            for (IServicioEmergencia r : disponibles) {
                if (r instanceof Bomberos) {
                    r.atenderEmergencia(emergencia);
                    break;
                }
            }
        } else if (emergencia instanceof AccidenteVehicular) {
            // Asigna recursos de ambulancias
            for (IServicioEmergencia r : disponibles) {
                if (r instanceof Ambulancia) {
                    r.atenderEmergencia(emergencia);
                    break;
                }
            }
        } else if (emergencia instanceof Robo) {
            // Asigna recursos de policía
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
}