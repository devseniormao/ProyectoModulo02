package model.observer;

import model.factoryEmerencias.Emergencia;

public interface SujetoEmergencias {
    
    void agregarObserver(ObserverEmergencias observerEmergencias);
    void eliminarObserver(ObserverEmergencias observerEmergencias);
    void notificarEmergencias(Emergencia emergencia);

}
