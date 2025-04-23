package model.services;

import java.util.List;
import java.util.stream.Collectors;

// Importación de la clase Emergencia desde el paquete correspondiente
import model.factoryEmerencias.Emergencia;
import model.observer.ObserverEmergencias;
import utils.Agencias;

// Clase Policia que extiende de ServicioEmergenciaBase
public class Policia extends ServicioEmergenciaBase implements ObserverEmergencias{

    private Agencias nombre;

    // Constructor de la clase Policia que inicializa los atributos heredados
    public Policia(String id, int personalDisponible, int vehiculosDisponibles, double combustible) {
        super(id, personalDisponible, vehiculosDisponibles, combustible);
    }
    // Constructor de la clase Policia que inicializa un atributo propio de la clase
    public Policia(Agencias nombre){
        super(null,0,0,0.0);
        this.nombre = nombre;
    }

    // Implementación del método abstracto atenderEmergencia
    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        // Mensaje indicando que la policía está atendiendo la emergencia
        System.out.println("Policia atendiendo emergencia.");
        // Imprime el ID de la policía y los detalles de la emergencia
        System.out.println("Policia: " + getId() + "\n" + emergencia.toString());
        
        // Asigna recursos para atender la emergencia
        asignarPersonal(20); // Asigna 20 unidades de personal
        asignarVehiculo(10); // Asigna 10 vehículos
        asignarCombustible(100.0); // Asigna 100 unidades de combustible
    }

    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Policia that = (Policia) obj;
    return this.nombre.equals(that.nombre); // Compara el atributo relevante
}

    @Override
    public int hashCode() {
        return nombre.hashCode(); // Genera un hash basado en el atributo relevante
    }

    @Override
    public void onEmergenciaAtendida(List<Emergencia> emergencias) {
        
        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias atendidas.");
            return;
        }
        
        List<Emergencia> emergenciasAtendidas = emergencias.stream()
        .filter(e -> e.isAtendida()).collect(Collectors.toList());

        System.out.println("\n=== NOTIFICACIONES AGENCIA: POLICIA ===");
        System.out.println("Emergencias atendidas:");
        System.out.println(emergenciasAtendidas.toString());
    }

    @Override
    public void onEmergenciaNoAtendida(List<Emergencia> emergencias) {
        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias no atendidas.");
            return;
        }
        List<Emergencia> emergenciasNoAtendidas = emergencias.stream()
        .filter(e -> !e.isAtendida()).collect(Collectors.toList());
        
        System.out.println("\nEmergencias no atendidas:");
        System.out.println(emergenciasNoAtendidas.toString());
    }
}
