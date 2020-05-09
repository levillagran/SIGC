package ec.com.sigc.servicio;


import java.util.List;

import ec.com.sigc.entidad.BackOffice;
import ec.com.sigc.entidad.CheckList;
import ec.com.sigc.entidad.Consultor;
import ec.com.sigc.entidad.SolicitudConsultoria;

public interface BackOfficeService {
	public abstract List<Consultor> findAllAudit();
	public abstract List<BackOffice> findAllBack();
	//public abstract List<CheckList> findAllCheckList(SolicitudConsultoria sa);
	//public abstract void saveInforme(int informeId, String auditorId, SolucitudAuditoriaString sa)throws Exception;
	public abstract void saveBack(BackOffice back);
	public abstract BackOffice findBack(Integer id);
	
}
