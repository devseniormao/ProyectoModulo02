package model.interfaces;

import model.factoryEmerencias.Emergencia;

public interface IServicioEmergencia {
    
    String getId();
    
    int getPersonalDisponible();

    int getVehiculosDisponibles(); // Método agregado para obtener la cantidad de vehículos disponibles
    
    double getCombustible();

    boolean estaDisponible();

    void asignarPersonal(int cantidad);

    void liberarPersonal(int cantidad);

    void asignarVehiculo(int cantidad); // Método agregado para asignar vehículos

    void liberarVehiculo(int cantidad); // Método agregado para liberar vehículos

    void asignarCombustible(double cantidad);

    void tanquearCombustible(double cantidad);

    void atenderEmergencia(Emergencia emergencia);
}
