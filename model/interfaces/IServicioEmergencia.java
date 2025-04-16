package model.interfaces;

import model.factoryEmerencias.Emergencia;

/**
 * Interfaz IServicioEmergencia que define los métodos para gestionar un servicio de emergencias.
 * Proporciona funcionalidades para manejar personal, vehículos y combustible, así como para atender emergencias.
 */
public interface IServicioEmergencia {
    
    /**
     * Obtiene el identificador único del servicio de emergencia.
     * @return String con el ID del servicio.
     */
    String getId();
    
    /**
     * Obtiene la cantidad de personal disponible en el servicio.
     * @return Número de personal disponible.
     */
    int getPersonalDisponible();

    /**
     * Obtiene la cantidad de vehículos disponibles en el servicio.
     * @return Número de vehículos disponibles.
     */
    int getVehiculosDisponibles(); // Método agregado para obtener la cantidad de vehículos disponibles
    
    /**
     * Obtiene la cantidad de combustible disponible en el servicio.
     * @return Cantidad de combustible disponible.
     */
    double getCombustible();

    /**
     * Verifica si el servicio está disponible para atender emergencias.
     * @return true si está disponible, false en caso contrario.
     */
    boolean estaDisponible();

    /**
     * Asigna una cantidad específica de personal al servicio.
     * @param cantidad Número de personal a asignar.
     */
    void asignarPersonal(int cantidad);

    /**
     * Libera una cantidad específica de personal del servicio.
     * @param cantidad Número de personal a liberar.
     */
    void liberarPersonal(int cantidad);

    /**
     * Asigna una cantidad específica de vehículos al servicio.
     * @param cantidad Número de vehículos a asignar.
     */
    void asignarVehiculo(int cantidad); // Método agregado para asignar vehículos

    /**
     * Libera una cantidad específica de vehículos del servicio.
     * @param cantidad Número de vehículos a liberar.
     */
    void liberarVehiculo(int cantidad); // Método agregado para liberar vehículos

    /**
     * Asigna una cantidad específica de combustible al servicio.
     * @param cantidad Cantidad de combustible a asignar.
     */
    void asignarCombustible(double cantidad);

    /**
     * Tanquea una cantidad específica de combustible al servicio.
     * @param cantidad Cantidad de combustible a tanquear.
     */
    void tanquearCombustible(double cantidad);

    /**
     * Atiende una emergencia específica utilizando los recursos del servicio.
     * @param emergencia Objeto de tipo Emergencia que representa la emergencia a atender.
     */
    void atenderEmergencia(Emergencia emergencia);
}
