package model.services;

import model.factoryEmerencias.Emergencia;

public class Bomberos extends ServicioEmergenciaBase {

    public Bomberos(String id, int personalDisponible, double combustible, boolean atendiendoEmergencia) {
        super(id, personalDisponible, combustible, atendiendoEmergencia);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void atenderEmergencia(Emergencia emergencia) {
        System.out.println("Bomberos atendiendo emergencia.");
        System.out.println("Bomberos: " + getId() + "\n" +  emergencia.toString());
    
        asignarPersonal(3);
        asignarCombustible(5.0);
    }

}
