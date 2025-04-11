package model.factoryEmerencias;

import utils.TipoEmergencia;
import utils.Ubicacion;
import utils.NivelGravedad;

public class Incendio extends Emergencia{

    public Incendio(TipoEmergencia tipo, Ubicacion ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta, String prioridad) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta, prioridad);
        //TODO Auto-generated constructor stub
    }

}
