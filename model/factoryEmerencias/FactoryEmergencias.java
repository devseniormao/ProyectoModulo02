package model.factoryEmerencias;

import utils.NivelGravedad;
import utils.TipoEmergencia;
import utils.Ubicacion;

public class FactoryEmergencias {
    public static Emergencia crearEmergencia(TipoEmergencia tipo, Ubicacion ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta, String prioridad) {
        switch (tipo) {
            case ACCIDENTE_VEHICULAR:
                return new AccidenteVehicular(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
            case ROBO:
                return new Robo(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
            case INCENDIO:
                return new Incendio(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
            default:
                return null;
        }
    }
}
