package model.factoryEmerencias;

import utils.NivelGravedad;
import utils.TipoEmergencia;
import utils.Ubicacion;

// Clase que representa una emergencia con atributos como tipo, ubicación, nivel de gravedad, etc.
public class Emergencia {
    private TipoEmergencia tipo; // Tipo de emergencia (e.g., incendio, accidente)
    private Ubicacion ubicacion; // Ubicación de la emergencia
    private NivelGravedad nivelGravedad; // Nivel de gravedad de la emergencia
    private int tiempoRespuesta; // Tiempo estimado de respuesta en minutos
    private boolean atendida; // Indica si la emergencia ha sido atendida
    private long tiempoInicioAtencion; // Marca de tiempo cuando se inicia la atención
    private long tiempoFinalAtencion; // Marca de tiempo cuando se finaliza la atención
    private String prioridad; // Prioridad de la emergencia (e.g., alta, media, baja)
    
    // Constructor para inicializar una emergencia con sus atributos
    public Emergencia(TipoEmergencia tipo, Ubicacion ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta, String prioridad) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.nivelGravedad = nivelGravedad; 
        this.tiempoRespuesta = tiempoRespuesta;
        this.atendida = false; // Por defecto, la emergencia no está atendida
        this.prioridad = prioridad;
    }

    // Métodos getter y setter para acceder y modificar los atributos
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

    // Método para iniciar la atención de la emergencia, registrando el tiempo actual
    public void iniciarAtencion() {
        this.tiempoInicioAtencion = System.currentTimeMillis();
    }

    // Método para finalizar la atención de la emergencia, registrando el tiempo actual y marcándola como atendida
    public void finalizarAtencion() {
        this.tiempoFinalAtencion = System.currentTimeMillis();
        this.atendida = true;
    }

    // Método para calcular el tiempo total de atención en milisegundos
    public long calcularTiempoAtencion() {
        return this.tiempoFinalAtencion - this.tiempoInicioAtencion;
    }

    // Método para representar la emergencia como una cadena de texto
    @Override
    public String toString() {
        return "Emergencia [tipo=" + tipo + ", ubicacion=" + ubicacion + ", nivelGravedad=" + nivelGravedad
                + ", tiempoRespuesta=" + tiempoRespuesta + ", atendida=" + atendida + ", tiempoInicioAtencion="
                + tiempoInicioAtencion + ", tiempoFinalAtencion=" + tiempoFinalAtencion + ", Prioridad=" + prioridad + "]";
    }
}
