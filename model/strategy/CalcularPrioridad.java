package model.strategy;

import model.factoryEmerencias.Emergencia;

// Clase que utiliza una estrategia para calcular la prioridad de una emergencia
public class CalcularPrioridad {
    private IPrioridad prioridad; // Estrategia de prioridad

    public CalcularPrioridad(IPrioridad prioridad) {
        this.prioridad = prioridad;
    }
    
    public int calcularPrioridad(Emergencia emergencia) {
        // Calcular la prioridad utilizando la estrategia definida
        return prioridad.calcularPrioridad(emergencia);
    }
}