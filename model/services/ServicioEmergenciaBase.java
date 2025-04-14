package model.services;

import model.factoryEmerencias.Emergencia;
import model.interfaces.IServicioEmergencia;

public abstract class ServicioEmergenciaBase implements IServicioEmergencia {

  private String id;
  private int personalDisponible;
  private int vehiculosDisponibles; // Atributo agregado para la cantidad de vehículos disponibles
  private double combustible;
  
  
  public ServicioEmergenciaBase(String id, int personalDisponible, int vehiculosDisponibles,double combustible) {
    this.id = id;
    this.personalDisponible = personalDisponible;
    this.vehiculosDisponibles = vehiculosDisponibles; // Inicialización del nuevo atributo
    this.combustible = combustible;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public int getPersonalDisponible() {
    return personalDisponible;
  }

  
  @Override
  public int getVehiculosDisponibles() {
    return vehiculosDisponibles;
  }

  @Override
  public double getCombustible() {
    return combustible;
  }

  @Override
  public boolean estaDisponible() {
    return personalDisponible > 0 && combustible > 0;
  }

  @Override
  public void asignarPersonal(int cantidad) {
    if(cantidad <= personalDisponible) {
        personalDisponible -= cantidad;
    }else {
        System.out.println("No hay suficiente personal disponible.");
    }
  }

  @Override
  public void liberarPersonal(int cantidad) {
    personalDisponible += cantidad;
  }

  @Override
  public void asignarVehiculo(int cantidad) {
    if(cantidad <= vehiculosDisponibles ) {
        vehiculosDisponibles -= cantidad;
    }else {
        System.out.println("No hay vehículos disponibles.");
    }
  }

  @Override
  public void liberarVehiculo(int cantidad){
    vehiculosDisponibles += cantidad;
  }

  @Override
  public void asignarCombustible(double cantidad) {
    combustible = Math.max(0, combustible - cantidad);
  }

  @Override
  public void tanquearCombustible(double cantidad) {
    combustible += cantidad;
  }

  @Override
  public abstract void atenderEmergencia(Emergencia emergencia);

  @Override
  public String toString() {
    return "ServicioEmergenciaBase [id=" + id + ", personalDisponible=" + personalDisponible + ", combustible="
            + combustible + "]";
  }

}
