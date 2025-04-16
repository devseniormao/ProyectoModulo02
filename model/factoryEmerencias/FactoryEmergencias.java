package model.factoryEmerencias;

// Importación de utilidades necesarias para definir el tipo de emergencia, ubicación y nivel de gravedad.
import utils.NivelGravedad;
import utils.TipoEmergencia;
import utils.Ubicacion;

// Clase FactoryEmergencias que actúa como una fábrica para crear objetos de tipo Emergencia.
public class FactoryEmergencias {

    // Método estático que crea una instancia de Emergencia según el tipo especificado.
    public static Emergencia crearEmergencia(
        TipoEmergencia tipo, // Tipo de emergencia (ACCIDENTE_VEHICULAR, ROBO, INCENDIO, etc.).
        Ubicacion ubicacion, // Ubicación donde ocurre la emergencia.
        NivelGravedad nivelGravedad, // Nivel de gravedad de la emergencia.
        int tiempoRespuesta, // Tiempo estimado de respuesta para atender la emergencia.
        String prioridad // Prioridad asignada a la emergencia.
    ) {
        // Estructura switch para determinar qué tipo de emergencia crear.
        switch (tipo) {
            case ACCIDENTE_VEHICULAR:
                // Retorna una instancia de AccidenteVehicular.
                return new AccidenteVehicular(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
            case ROBO:
                // Retorna una instancia de Robo.
                return new Robo(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
            case INCENDIO:
                // Retorna una instancia de Incendio.
                return new Incendio(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
            default:
                // Retorna null si el tipo de emergencia no es reconocido.
                return null;
        }
    }
}
