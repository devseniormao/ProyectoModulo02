package model.factoryEmerencias;

import utils.TipoEmergencia;
import utils.NivelGravedad;
public class AccidenteVehicular extends Emergencia{

    public AccidenteVehicular(TipoEmergencia tipo, String ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta);
        //TODO Auto-generated constructor stub
    }

}
