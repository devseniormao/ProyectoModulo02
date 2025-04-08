package model.services;

import model.factoryEmerencias.Emergencia;
import model.interfaces.IServicioEmergencia;

public abstract class ServicioEmergenciaBase implements IServicioEmergencia {

  private String id;
  private int personalDisponible;
  private double combustible;
  private boolean atendiendoEmergencia;

  public ServicioEmergenciaBase(String id, int personalDisponible, double combustible) {
    this.id = id;
    this.personalDisponible = personalDisponible;
    this.combustible = combustible;
    this.atendiendoEmergencia = false;
  }

  public String getId() {
    return id;
  }

  public int getPersonalDisponible() {
    return personalDisponible;
  }

  public double getCombustible() {
    return combustible;
  }

  public boolean isAtendiendoEmergencia() {
    return atendiendoEmergencia;
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
  public void asignarCombustible(double cantidad) {
    combustible = Math.max(0, combustible - cantidad);
  }

  @Override
  public void tanquearCombustible(double cantidad) {
    combustible += cantidad;
  }

  public abstract void atenderEmergencia(Emergencia emergencia);

  @Override
  public String toString() {
    return "ServicioEmergenciaBase [id=" + id + ", personalDisponible=" + personalDisponible + ", combustible="
            + combustible + "]";
  }

  

}
