package model.strategy;

import model.factoryEmerencias.Emergencia;
import utils.Ubicacion;

public class StrategyPrioridadCercania implements IPrioridad {

    private class MapaUrbano {
        public int calcularDistancia(Ubicacion ubicacion) {
            switch(ubicacion.toString().toLowerCase()) {
                case "centro": return 2;
                case "este": return 5;
                case "oeste": return 6;
                case "norte": return 8;
                case "sur": return 10;
                default: return 0;
            }
        }    
    }
    
    private MapaUrbano mapaUrbano = new MapaUrbano();
    
    public int calcularPrioridad(Emergencia emergencia) {
        int calcularDistancia = mapaUrbano.calcularDistancia(emergencia.getUbicacion());
        return calcularDistancia;
    }

}
