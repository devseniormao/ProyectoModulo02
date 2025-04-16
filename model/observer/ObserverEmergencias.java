package model.observer;

import model.factoryEmerencias.Emergencia;

/**
 * Interfaz ObserverEmergencias que define los métodos para manejar eventos relacionados con emergencias.
 * Implementa el patrón Observer para notificar cambios en el estado de las emergencias.
 */
public interface ObserverEmergencias {

    /**
     * Método llamado cuando hay nuevas emergencias.
     * @param emergencia Objeto de tipo Emergencia que representa la nueva emergencia.
     */
    void onNuevasEmergencias(Emergencia emergencia);

    /**
     * Método llamado cuando una emergencia ha sido atendida.
     * @param emergencia Objeto de tipo Emergencia que representa la emergencia atendida.
     */
    void onEmergenciaAtendida(Emergencia emergencia);

    /**
     * Método llamado cuando una emergencia no ha sido atendida.
     * @param emergencia Objeto de tipo Emergencia que representa la emergencia no atendida.
     */
    void onEmergenciaNoAtendida(Emergencia emergencia);
}
