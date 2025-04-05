package model.services;

import model.factoryEmerencias.Emergencia;

public class Ambulancia extends ServicioEmergenciaBase{

    public Ambulancia(String id, int personalDisponible, double combustible) {
        super(id, personalDisponible, combustible);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        System.out.println("Ambulancia atendiendo emergencia.");
        System.out.println("Ambulancia: " + getId() + "\n" + emergencia.toString());
        
        asignarPersonal(3);
        asignarCombustible(5.0);
    }

}
