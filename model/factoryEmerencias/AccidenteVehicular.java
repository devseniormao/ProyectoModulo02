package model.factoryEmerencias;

import utils.TipoEmergencia;
import utils.NivelGravedad;
import utils.Ubicacion;

public class AccidenteVehicular extends Emergencia{

    public AccidenteVehicular(TipoEmergencia tipo, Ubicacion ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta, String prioridad) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
        //TODO Auto-generated constructor stub
    }

}
