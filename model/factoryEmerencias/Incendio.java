package model.factoryEmerencias;

// Importación de clases necesarias desde el paquete utils
import utils.TipoEmergencia;
import utils.Ubicacion;
import utils.NivelGravedad;

// Clase Incendio que extiende de la clase Emergencia
public class Incendio extends Emergencia {

    // Constructor de la clase Incendio
    // Recibe como parámetros el tipo de emergencia, ubicación, nivel de gravedad,
    // tiempo de respuesta y prioridad, y los pasa al constructor de la clase base (Emergencia)
    public Incendio(TipoEmergencia tipo, Ubicacion ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta, String prioridad) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad); // Llamada al constructor de la clase base
        // TODO Auto-generated constructor stub
    }

}
