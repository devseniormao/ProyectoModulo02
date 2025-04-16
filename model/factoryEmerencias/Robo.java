package model.factoryEmerencias;

import utils.TipoEmergencia;
import utils.Ubicacion;
import utils.NivelGravedad;

/**
 * Clase Robo que representa una emergencia de tipo robo.
 * Hereda de la clase Emergencia y utiliza sus propiedades y métodos.
 */
public class Robo extends Emergencia {

    /**
     * Constructor de la clase Robo.
     * Inicializa una emergencia de tipo robo con los parámetros proporcionados.
     * 
     * @param tipo Tipo de emergencia (debe ser TipoEmergencia.ROBO).
     * @param ubicacion Ubicación donde ocurre la emergencia.
     * @param nivelGravedad Nivel de gravedad de la emergencia.
     * @param tiempoRespuesta Tiempo estimado de respuesta para atender la emergencia.
     * @param prioridad Prioridad asignada a la emergencia.
     */
    public Robo(TipoEmergencia tipo, Ubicacion ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta, String prioridad) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
        // TODO Auto-generated constructor stub
    }

}
