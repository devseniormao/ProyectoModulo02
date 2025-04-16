package model.services;

// Importación de la clase Emergencia desde el paquete correspondiente
import model.factoryEmerencias.Emergencia;

// Clase Ambulancia que extiende de ServicioEmergenciaBase
public class Ambulancia extends ServicioEmergenciaBase {

    // Constructor de la clase Ambulancia que inicializa los atributos heredados
    public Ambulancia(String id, int personalDisponible, int vehiculosDisponibles, double combustible) {
        super(id, personalDisponible, vehiculosDisponibles, combustible);
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
}
