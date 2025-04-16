package model.factoryEmerencias;

// Importación de clases necesarias desde el paquete utils
import utils.TipoEmergencia;
import utils.NivelGravedad;
import utils.Ubicacion;

// Clase que representa una emergencia del tipo "Accidente Vehicular"
public class AccidenteVehicular extends Emergencia {

    // Constructor de la clase que inicializa los atributos heredados de la clase Emergencia
    public AccidenteVehicular(TipoEmergencia tipo, Ubicacion ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta, String prioridad) {
        // Llamada al constructor de la clase base (Emergencia) con los parámetros proporcionados
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
        // TODO: Constructor generado automáticamente, se puede personalizar si es necesario
    }
}
