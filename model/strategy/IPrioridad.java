package model.strategy;

import model.factoryEmerencias.Emergencia;

public interface IPrioridad {
    
    int calcularPrioridad(Emergencia emergencia);
    
}
