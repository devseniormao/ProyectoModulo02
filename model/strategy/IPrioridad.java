package model.strategy;

import model.factoryEmerencias.Emergencia;

// Interfaz que define el método para calcular la prioridad de una emergencia
public interface IPrioridad {
    
    int calcularPrioridad(Emergencia emergencia); // Método para calcular la prioridad
}
