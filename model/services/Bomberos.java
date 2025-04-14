package model.services;

import model.factoryEmerencias.Emergencia;

public class Bomberos extends ServicioEmergenciaBase {

    public Bomberos(String id, int personalDisponible, int vehiculosDisponibles, double combustible) {
        super(id, personalDisponible, vehiculosDisponibles, combustible);

    }

    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        System.out.println("Bomberos atendiendo emergencia.");
        System.out.println("Bomberos: " + getId() + "\n" +  emergencia.toString());
    
        asignarPersonal(20);
        asignarVehiculo(4);
        asignarCombustible(100.0);
    }

}
