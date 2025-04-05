package model.factoryEmerencias;

import utils.TipoEmergencia;
import utils.NivelGravedad;

public class Incendio extends Emergencia{

    public Incendio(TipoEmergencia tipo, String ubicacion, NivelGravedad nivelGravedad, int tiempoRespuesta) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta);
        //TODO Auto-generated constructor stub
    }

}
