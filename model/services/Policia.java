package model.services;

import model.factoryEmerencias.Emergencia;

public class Policia extends ServicioEmergenciaBase{

    public Policia(String id, int personalDisponible, int vehiculosDisponibles, double combustible) {
        super(id, personalDisponible, vehiculosDisponibles, combustible);
        
    }

    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        System.out.println("Policia atendiendo emergencia.");
        System.out.println("Policia: " + getId() + "\n" + emergencia.toString());
        
        asignarPersonal(20);
        asignarVehiculo(10);
        asignarCombustible(100.0);
    }
    

}
