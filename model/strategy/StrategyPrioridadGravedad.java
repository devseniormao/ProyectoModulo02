package model.strategy;

import model.factoryEmerencias.Emergencia;

// Estrategia que calcula la prioridad de una emergencia basada en su nivel de gravedad
public class StrategyPrioridadGravedad implements IPrioridad {

    @Override
    public int calcularPrioridad(Emergencia emergencia) {
        // Asignar prioridad según el nivel de gravedad
        switch (emergencia.getNivelGravedad()) {
            case BAJO:
                return 1; // Prioridad baja
            case MEDIO:
                return 2; // Prioridad media
            case ALTO:
                return 3; // Prioridad alta
            default:
                return 0; // Prioridad no definida
        }
    }
}