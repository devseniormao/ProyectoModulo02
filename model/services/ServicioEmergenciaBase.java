package model.services;

import model.factoryEmerencias.Emergencia;
import model.interfaces.IServicioEmergencia;

// Clase abstracta que implementa la interfaz IServicioEmergencia
public abstract class ServicioEmergenciaBase implements IServicioEmergencia {

  // Atributos de la clase
  private String id; // Identificador único del servicio
  private int personalDisponible; // Cantidad de personal disponible
  private int vehiculosDisponibles; // Cantidad de vehículos disponibles
  private double combustible; // Cantidad de combustible disponible
  
  // Constructor para inicializar los atributos
  public ServicioEmergenciaBase(String id, int personalDisponible, int vehiculosDisponibles, double combustible) {
    this.id = id;
    this.personalDisponible = personalDisponible;
    this.vehiculosDisponibles = vehiculosDisponibles;
    this.combustible = combustible;
  }

  // Métodos que implementan la interfaz IServicioEmergencia
  @Override
  public String getId() {
    return id; // Devuelve el identificador del servicio
  }

  @Override
  public int getPersonalDisponible() {
    return personalDisponible; // Devuelve la cantidad de personal disponible
  }

  @Override
  public int getVehiculosDisponibles() {
    return vehiculosDisponibles; // Devuelve la cantidad de vehículos disponibles
  }

  @Override
  public double getCombustible() {
    return combustible; // Devuelve la cantidad de combustible disponible
  }

  @Override
  public boolean estaDisponible() {
    // Verifica si el servicio está disponible (personal y combustible > 0)
    return personalDisponible > 0 && combustible > 0;
  }

  @Override
  public void asignarPersonal(int cantidad) {
    // Asigna personal si hay suficiente disponible
    if (cantidad <= personalDisponible) {
        personalDisponible -= cantidad;
    } else {
        System.out.println("No hay suficiente personal disponible.");
    }
  }

  @Override
  public void liberarPersonal(int cantidad) {
    // Libera personal, aumentando la cantidad disponible
    personalDisponible += cantidad;
  }

  @Override
  public void asignarVehiculo(int cantidad) {
    // Asigna vehículos si hay suficientes disponibles
    if (cantidad <= vehiculosDisponibles) {
        vehiculosDisponibles -= cantidad;
    } else {
        System.out.println("No hay vehículos disponibles.");
    }
  }

  @Override
  public void liberarVehiculo(int cantidad) {
    // Libera vehículos, aumentando la cantidad disponible
    vehiculosDisponibles += cantidad;
  }

  @Override
  public void asignarCombustible(double cantidad) {
    // Reduce la cantidad de combustible, asegurándose de que no sea negativa
    combustible = Math.max(0, combustible - cantidad);
  }

  @Override
  public void tanquearCombustible(double cantidad) {
    // Aumenta la cantidad de combustible
    combustible += cantidad;
  }

  @Override
  public abstract void atenderEmergencia(Emergencia emergencia);
  // Método abstracto que debe ser implementado por las clases hijas para atender emergencias

  @Override
  public String toString() {
    // Representación en forma de cadena del objeto
    return "ServicioEmergenciaBase [id=" + id + ", personalDisponible=" + personalDisponible + 
           ", combustible=" + combustible + "]";
  }
}
