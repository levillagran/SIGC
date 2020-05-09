package ec.com.sigc.servicio;

import java.util.List;

import ec.com.sigc.entidad.DatoComun;
import ec.com.sigc.entidad.TipoConsultoria;

public interface ConsService {
	public abstract List<TipoConsultoria> findAllTipoConsultoria();
	public abstract void solicitudConsultoria(DatoComun datoComun, SolucitudConsultoriaString sas, String usuario)throws Exception;
}
