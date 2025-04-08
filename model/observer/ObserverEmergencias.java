package model.observer;

import model.factoryEmerencias.Emergencia;

public interface ObserverEmergencias {
    void onNuevasEmergencias(Emergencia emergencia);
}
