package model.services;

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

    public Ambulancia(Agencias nombre){
        super(null, 0, 0, 0.0); // Call to a valid constructor of ServicioEmergenciaBase
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

    @Override
    public void onNuevasEmergencias(Emergencia emergencia) {
        System.out.printf("Tienes una nueva emergencia");
    }

    @Override
    public void onEmergenciaAtendida(Emergencia emergencia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onEmergenciaAtendida'");
    }

    @Override
    public void onEmergenciaNoAtendida(Emergencia emergencia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onEmergenciaNoAtendida'");
    }

    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Ambulancia that = (Ambulancia) obj;
    return this.nombre.equals(that.nombre); // Compara el atributo relevante
}

    @Override
    public int hashCode() {
        return nombre.hashCode(); // Genera un hash basado en el atributo relevante
    }
}
