package model.services;

// Importación de la clase Emergencia desde el paquete correspondiente
import model.factoryEmerencias.Emergencia;

// Clase Bomberos que extiende de ServicioEmergenciaBase
public class Bomberos extends ServicioEmergenciaBase {

    // Constructor de la clase Bomberos que inicializa los atributos heredados
    public Bomberos(String id, int personalDisponible, int vehiculosDisponibles, double combustible) {
        super(id, personalDisponible, vehiculosDisponibles, combustible);
    }

    // Implementación del método abstracto atenderEmergencia
    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        // Mensaje indicando que los bomberos están atendiendo la emergencia
        System.out.println("Bomberos atendiendo emergencia.");
        // Imprime el ID de los bomberos y los detalles de la emergencia
        System.out.println("Bomberos: " + getId() + "\n" + emergencia.toString());
    
        // Asigna recursos para atender la emergencia
        asignarPersonal(20); // Asigna 20 unidades de personal
        asignarVehiculo(4); // Asigna 4 vehículos
        asignarCombustible(100.0); // Asigna 100 unidades de combustible
    }
}
