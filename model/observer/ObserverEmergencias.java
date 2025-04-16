package model.observer;

import model.factoryEmerencias.Emergencia;

public interface ObserverEmergencias {
    void onNuevasEmergencias(Emergencia emergencia);
    void onEmergenciaAtendida(Emergencia emergencia);
    void onEmergenciaNoAtendida(Emergencia emergencia);
}
