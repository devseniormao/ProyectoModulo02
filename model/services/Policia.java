package model.services;

import model.factoryEmerencias.Emergencia;

public class Policia extends ServicioEmergenciaBase{

    public Policia(String id, int personalDisponible, double combustible) {
        super(id, personalDisponible, combustible);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        System.out.println("Policia atendiendo emergencia.");
        System.out.println("Policia: " + getId() + "\n" + emergencia.toString());
        
        asignarPersonal(3);
        asignarCombustible(5.0);
    }
    

}
