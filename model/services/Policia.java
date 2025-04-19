package model.services;

// Importación de la clase Emergencia desde el paquete correspondiente
import model.factoryEmerencias.Emergencia;
import utils.Agencias;

// Clase Policia que extiende de ServicioEmergenciaBase
public class Policia extends ServicioEmergenciaBase {

    private Agencias nombre;

    // Constructor de la clase Policia que inicializa los atributos heredados
    public Policia(String id, int personalDisponible, int vehiculosDisponibles, double combustible) {
        super(id, personalDisponible, vehiculosDisponibles, combustible);
    }

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
}
