package model.strategy;

import model.factoryEmerencias.Emergencia;
import utils.Ubicacion;

// Estrategia que calcula la prioridad de una emergencia basada en la cercanía
public class StrategyPrioridadCercania implements IPrioridad {

    // Clase interna que simula un mapa urbano para calcular distancias
    private class MapaUrbano {
        public int calcularDistancia(Ubicacion ubicacion) {
            // Retorna la distancia según la ubicación
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
    
    @Override
    public int calcularPrioridad(Emergencia emergencia) {
        // Calcular la prioridad basada en la distancia
        int calcularDistancia = mapaUrbano.calcularDistancia(emergencia.getUbicacion());
        return calcularDistancia;
    }
}
