package model.strategy;

import model.factoryEmerencias.Emergencia;

public class CalcularPrioridad {
    private IPrioridad prioridad;

    public CalcularPrioridad(IPrioridad prioridad) {
        this.prioridad = prioridad;
    }
    
    public int calcularPrioridad(Emergencia emergencia) {
        return prioridad.calcularPrioridad(emergencia);
    }

}