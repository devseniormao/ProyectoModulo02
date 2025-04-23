package model.observer;

import java.util.List;

import model.factoryEmerencias.Emergencia;

// Importa la clase Emergencia desde el paquete factoryEmerencias


// Interfaz que define el comportamiento de un sujeto en el patrón Observer
public interface SujetoEmergencias {
    
    // Método para agregar un observador al sujeto
    void agregarObserver(ObserverEmergencias observerEmergencias);
    
    // Método para eliminar un observador del sujeto
    void eliminarObserver(ObserverEmergencias observerEmergencias);
    
    // Método para notificar a todos los observadores sobre una emergencia
    void notificarEmergencias(List<Emergencia> emergencias);

}
