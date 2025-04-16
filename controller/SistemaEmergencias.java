package controller;

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

public class SistemaEmergencias implements SujetoEmergencias{

    private static SistemaEmergencias instance;
    private List<Emergencia> listaEmergencias;
    private List<IServicioEmergencia> listaRecursos;
    private List<ObserverEmergencias> observadores; 
    private List<Emergencia> emergenciasEnCurso;   

    private IPrioridad strategyPrioridad;

    private int emergenciasAtendidas;
    private long tiempoTotalAtencion;

    private SistemaEmergencias() {
        strategyPrioridad = new StrategyPrioridadGravedad();
        listaEmergencias = new ArrayList<>();
        listaRecursos = new ArrayList<>();
        observadores = new ArrayList<>();
        emergenciasAtendidas = 0;
        tiempoTotalAtencion = 0;
    }

    public static SistemaEmergencias getInstance() {
        if (instance == null) {
            instance = new SistemaEmergencias();
        }
        return instance;
    }

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
        for(ObserverEmergencias observerEmergencias : observadores) {
            observerEmergencias.onNuevasEmergencias(emergencia);
        }
    }

    public void registrarRecurso(IServicioEmergencia recurso) {
        listaRecursos.add(recurso);
    }

    public void mostrarEstadoRecursos() {
        System.out.println("\n=== ESTADO ACTUAL DE RECURSOS ===");
        for (IServicioEmergencia r : listaRecursos) {
            System.out.println(r.toString());
        }
    }

    // Ejemplo de uso de Lambda para filtrar recursos disponibles
    public List<IServicioEmergencia> filtrarRecursosDisponibles() {
        return listaRecursos.stream()
                .filter(r -> r.estaDisponible())
                .collect(Collectors.toList());
    }    

    public void registrarNuevaEmergencia(Emergencia e) {
        listaEmergencias.add(e);
        notificarEmergencias(e);
    }    

    public List<Emergencia> getEmergenciasPendientes() {
        return listaEmergencias.stream()
                .filter(e -> !e.isAtendida())
                .collect(Collectors.toList());
    }

    public List<Emergencia> getEmergenciasEnCurso() {
        return listaEmergencias.stream()
                .filter(e -> e.isAtendida())
                .collect(Collectors.toList());
    }

     public void asignarRecursosAEmergencia(Emergencia emergencia) {
        // Buscamos recursos disponibles
        List<IServicioEmergencia> disponibles = filtrarRecursosDisponibles();
        if (disponibles.isEmpty()) {
            System.out.println("No hay recursos disponibles para esta emergencia.");
            return;
        }
        System.out.println("-> Asignando recursos automáticamente...");

        if (emergencia instanceof Incendio) {
            // Assign firefighters to fire emergencies
            for (IServicioEmergencia r : disponibles) {
                if (r instanceof Bomberos) {
                    r.atenderEmergencia(emergencia);
                    break;
                }
            }
        } else if (emergencia instanceof AccidenteVehicular) {
            // Assign ambulances to vehicle accidents
            for (IServicioEmergencia r : disponibles) {
                if (r instanceof Ambulancia) {
                    r.atenderEmergencia(emergencia);
                    break;
                }
            }
        } else if (emergencia instanceof Robo) {
            // Assign police to robbery emergencies
            for (IServicioEmergencia r : disponibles) {
                if (r instanceof Policia) {
                    r.atenderEmergencia(emergencia);
                    break;
                }
            }
        }
    }

    public void atenderEmergencia(Emergencia e) {

        e.iniciarAtencion();
        System.out.println("Atendiendo emergencia: " + e.toString());

        int totalTiempo = 4000; // tiempo total de atenciónencion de la emergencia simulado
        int pasos = 10; // número de pasos para simular la atenciónencion de la emergencia
        int tiempoPorPaso = totalTiempo / pasos; // tiempo por paso de atenciónencion de la emergencia

        for (int i = 1; i <= pasos; i++) {
            try {
                Thread.sleep(tiempoPorPaso);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            int porcentage = (i * 100) / pasos; // porcentaje de atenciónencion de la emergencia
            System.out.println("Avance Atención de emergencia: " + porcentage + "% completado.");
        }


        e.finalizarAtencion();
        System.out.println("Emergencia atendida: " + e.toString());

        emergenciasAtendidas++;
        tiempoTotalAtencion += e.getTiempoRespuesta();
    }
    
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

    public void verificarEmergenciasPendientes() {
        List<Emergencia> pendientes = getEmergenciasPendientes();
        List<Emergencia> enCurso = getEmergenciasEnCurso();
        LocalDateTime ahora = LocalDateTime.now();

        //revisar emergencias pendientes
        for (Emergencia e : pendientes) {
            System.out.println("ALARMA: Emergencia pendiente de atencion -> " + e);
        }

        //revisar las emergencias en curso y compararlas con el tiempo estimado
        for (Emergencia e : enCurso) {
            if (e.isAtendida()) {
                continue; // Si ya fue atendida, no la revisamos y no se genera la alarma
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

    public void finalizarJornada() {
        mostrarEstadisticas();
        System.out.println("Guardando registro del día (simulado)...");
        // Lógica para guardarlo en BD o archivo
        System.out.println("Sistema preparado para siguiente ciclo.");
    }

    public void setEstrategiaPrioridad(IPrioridad nuevaEstrategia) {
        strategyPrioridad = nuevaEstrategia;
    }
    
    //implementación del patrón strategy
    public double prioridad(Emergencia emergencia) {
        //instancia la clase calcular prioridad que a su vez construye un objeto con la instancia de los objetos que implementan
        // la interface IPrioridad
        CalcularPrioridad distancia = new CalcularPrioridad(new StrategyPrioridadCercania());
        CalcularPrioridad gravedad = new CalcularPrioridad(new StrategyPrioridadGravedad());

        // Realiza divisiones de punto flotante
        double prioridad = ((gravedad.calcularPrioridad(emergencia) / 3.0) * 70)
                         + ((distancia.calcularPrioridad(emergencia) / 10.0) * 30);
        return prioridad;
    }
}