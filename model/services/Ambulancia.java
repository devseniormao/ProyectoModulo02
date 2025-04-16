package model.services;

import model.factoryEmerencias.Emergencia;

public class Ambulancia extends ServicioEmergenciaBase{

    public Ambulancia(String id, int personalDisponible, int vehiculosDisponibles,double combustible) {
        super(id, personalDisponible, vehiculosDisponibles,combustible);
        
    }

    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        System.out.println("Ambulancia atendiendo emergencia.");
        System.out.println("Ambulancia: " + getId() + "\n" + emergencia.toString());
        
        asignarPersonal(10);
        asignarVehiculo(5);
        asignarCombustible(50.0);
    }

}
