package model.factoryEmerencias;

import utils.TipoEmergencia;
import utils.Ubicacion;
import utils.NivelGravedad;

public class Emergencia {
    private TipoEmergencia tipo;
    private Ubicacion ubicacion;
    private NivelGravedad nivelGravedad;
    private int tiempoRespuesta;// en minutos
    private boolean atendida;
    private long tiempoInicioAtencion;
    private long tiempoFinalAtencion;
    private String prioridad;
    
    public Emergencia(TipoEmergencia tipo, Ubicacion ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta, String prioridad) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.nivelGravedad = nivelGravedad;
        this.tiempoRespuesta = tiempoRespuesta;
        this.atendida = false;
        this.prioridad = prioridad;
    }

    public TipoEmergencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmergencia tipo) {
        this.tipo = tipo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public NivelGravedad getNivelGravedad() {
        return nivelGravedad;
    }

    public void setNivelGravedad(NivelGravedad nivelGravedad) {
        this.nivelGravedad = nivelGravedad;
    }

    public int getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(int tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }

    public long getTiempoInicioAtencion() {
        return tiempoInicioAtencion;
    }

    public void setTiempoInicioAtencion(long tiempoInicioAtencion) {
        this.tiempoInicioAtencion = tiempoInicioAtencion;
    }

    public long getTiempoFinalAtencion() {
        return tiempoFinalAtencion;
    }

    public void setTiempoFinalAtencion(long tiempoFinalAtencion) {
        this.tiempoFinalAtencion = tiempoFinalAtencion;
    }

    public void iniciarAtencion() {
        this.tiempoInicioAtencion = System.currentTimeMillis();
    }

    public void finalizarAtencion() {
        this.tiempoFinalAtencion = System.currentTimeMillis();
        this.atendida = true;
    }

    public long calcularTiempoAtencion() {
        return this.tiempoFinalAtencion - this.tiempoInicioAtencion;
    }

    @Override
    public String toString() {
        return "Emergencia [tipo=" + tipo + ", ubicacion=" + ubicacion + ", nivelGravedad=" + nivelGravedad
                + ", tiempoRespuesta=" + tiempoRespuesta + ", atendida=" + atendida + ", tiempoInicioAtencion="
                + tiempoInicioAtencion + ", tiempoFinalAtencion=" + tiempoFinalAtencion + ", Prioridad=" + prioridad + "]";
    }

}
