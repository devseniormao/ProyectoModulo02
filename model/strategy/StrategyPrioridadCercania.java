package model.strategy;

import model.factoryEmerencias.Emergencia;

public class StrategyPrioridadCercania implements IPrioridad {

    private class MapaUrbano {
        public int calcularDistancia(String ubicacion) {
            switch(ubicacion.toLowerCase()){
                case "centro": return 2;//8 -> 1to Cercano
                case "este": return 5;//5   -> 2to Cercano
                case "oeste": return 6;//4  -> 3to Cercano
                case "norte": return 8;//2  -> 4to Cercano
                case "sur": return 10;//0   -> 5to Cercano
                default: return 0;
            }
        }    
    }
    
    private MapaUrbano mapaUrbano = new MapaUrbano();
    
    public int calcularPrioridad(Emergencia emergencia) {
        int calcularDistancia = mapaUrbano.calcularDistancia(emergencia.getUbicacion());
        return 10 - calcularDistancia;
    }

}
