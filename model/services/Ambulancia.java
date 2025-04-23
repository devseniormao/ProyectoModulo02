package model.services;

import java.util.List;
import java.util.stream.Collectors;

// Importación de la clase Emergencia desde el paquete correspondiente
import model.factoryEmerencias.Emergencia;
import model.observer.ObserverEmergencias;
import utils.Agencias;

// Clase Ambulancia que extiende de ServicioEmergenciaBase
public class Ambulancia extends ServicioEmergenciaBase implements ObserverEmergencias {
    private Agencias nombre;

    // Constructor de la clase Ambulancia que inicializa los atributos heredados
    public Ambulancia(String id, int personalDisponible, int vehiculosDisponibles, double combustible) {
        super(id, personalDisponible, vehiculosDisponibles, combustible);
    }

    // Constructor de la clase Ambulancia que inicializa un atributo propio de la clase
    public Ambulancia(Agencias nombre) {
        super(null, 0, 0, 0.0); // Llama a un constructor válido de ServicioEmergenciaBase
        this.nombre = nombre;
    }

    // Implementación del método abstracto atenderEmergencia
    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        // Mensaje indicando que la ambulancia está atendiendo la emergencia
        System.out.println("Ambulancia atendiendo emergencia.");
        // Imprime el ID de la ambulancia y los detalles de la emergencia
        System.out.println("Ambulancia: " + getId() + "\n" + emergencia.toString());
        
        // Asigna recursos para atender la emergencia
        asignarPersonal(10); // Asigna 10 unidades de personal
        asignarVehiculo(5); // Asigna 5 vehículos
        asignarCombustible(50.0); // Asigna 50 unidades de combustible
    }

    // Método que se ejecuta cuando hay emergencias atendidas
    @Override
    public void onEmergenciaAtendida(List<Emergencia> emergencias) {
        // Verifica si la lista de emergencias está vacía
        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias atendidas.");
            return;
        }
        
        // Filtra las emergencias que han sido atendidas
        List<Emergencia> emergenciasAtendidas = emergencias.stream()
            .filter(e -> e.isAtendida()).collect(Collectors.toList());
        
        // Muestra las notificaciones de emergencias atendidas
        System.out.println("\n=== NOTIFICACIONES AGENCIA: AMBULANCIA ===");
        System.out.println("Emergencias atendidas:");
        System.out.println(emergenciasAtendidas.toString());
    }

    // Método que se ejecuta cuando hay emergencias no atendidas
    @Override
    public void onEmergenciaNoAtendida(List<Emergencia> emergencias) {
        // Verifica si la lista de emergencias está vacía
        if (emergencias.isEmpty()) {
            System.out.println("No hay emergencias no atendidas.");
            return;
        }
        
        // Filtra las emergencias que no han sido atendidas
        List<Emergencia> emergenciasNoAtendidas = emergencias.stream()
            .filter(e -> !e.isAtendida()).collect(Collectors.toList());
        
        // Muestra las notificaciones de emergencias no atendidas
        System.out.println("\nEmergencias no atendidas:");
        System.out.println(emergenciasNoAtendidas.toString());
    }

    // Método para comparar objetos de tipo Ambulancia
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifica si son el mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false; // Verifica si son de la misma clase
        Ambulancia that = (Ambulancia) obj;
        return this.nombre.equals(that.nombre); // Compara el atributo relevante
    }

    // Método para generar un hash basado en el atributo relevante
    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
