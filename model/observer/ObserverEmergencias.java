package model.observer;

import java.util.List;

import model.factoryEmerencias.Emergencia;

/**
 * Interfaz ObserverEmergencias que define los métodos para manejar eventos relacionados con emergencias.
 * Implementa el patrón Observer para notificar cambios en el estado de las emergencias.
 */
public interface ObserverEmergencias {

    /**
     * Método llamado cuando una emergencia ha sido atendida.
     * @param emergencia Objeto de tipo Emergencia que representa la emergencia atendida.
     */
    void onEmergenciaAtendida(List<Emergencia> emergencias);

    /**
     * Método llamado cuando una emergencia no ha sido atendida.
     * @param emergencia Objeto de tipo Emergencia que representa la emergencia no atendida.
     */
    void onEmergenciaNoAtendida(List<Emergencia> emergencias);
}
