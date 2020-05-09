package ec.com.sigc.servicio.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.Consultor;
import ec.com.sigc.entidad.BackOffice;
import ec.com.sigc.entidad.CheckList;
import ec.com.sigc.entidad.DatoComun;
import ec.com.sigc.entidad.Informe;
import ec.com.sigc.entidad.SolicitudConsultoria;
//import ec.com.sigc.model.SolucitudConsultoriaString;
import ec.com.sigc.repositorio.ConsultorRepository;
import ec.com.sigc.repositorio.BackRepository;
//import ec.com.sigc.repositorio.CheckListRepository;
import ec.com.sigc.repositorio.DatoComunRepository;
import ec.com.sigc.repositorio.EstadoConRepository;
import ec.com.sigc.repositorio.InformeRepository;
import ec.com.sigc.repositorio.RoleSysRepository;
import ec.com.sigc.repositorio.SolicitudConsRepository;
import ec.com.sigc.servicio.BackOfficeService;
//import ec.com.sigc.servicio.InformeService;

@Service("backOfficeService")
public class BackOfficeServiceImpl implements BackOfficeService {

	@Autowired
	@Qualifier("ConsultorRepository")
	private ConsultorRepository ConsultorRepository;

	@Autowired
	@Qualifier("backRepository")
	private BackRepository backRepository;

	@Autowired
	@Qualifier("roleSysRepository")
	private RoleSysRepository roleSysRepository;

	@Autowired

	/*
	 * @Qualifier("estadoAuditRepository") private EstadoConRepository
	 * estadoAuditRepository;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("informeRepository") private InformeRepository informeRepository;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("dComunRepository") private DatoComunRepository dComunRepository;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("solicitudConsultoriaRepository") private SolicitudConsRepository
	 * solicitudConsultoriaRepository;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("checkListRepository") private CheckListRepository
	 * checkListRepository;
	 */

	/*
	 * @Autowired
	 * 
	 * @Qualifier("informeServicio") private InformeService informeServicio;
	 */

	@Override
	public List<Consultor> findAllAudit() {
		return ConsultorRepository.findAll();
	}

	/*
	 * @Override public void saveInforme(int informeId, String ConsultorId,
	 * SolucitudConsultoriaString saAux) throws Exception { Informe informe =
	 * informeServicio.findReport(informeId); Consultor Consultor =
	 * ConsultorRepository.findById(Integer.parseInt(ConsultorId)).get(); DatoComun
	 * dc = informe.getDatoComunId(); SolicitudConsultoria sa =
	 * dc.getSolicitudConsultoriaId();
	 * sa.setEstadoConsultoriaId(estadoAuditRepository.findById(2).get());
	 * sa.setFechaInicio(new
	 * SimpleDateFormat("yyyy-MM-dd").parse(saAux.getAuxfechaInicio()));
	 * sa.setFechaFinal(new
	 * SimpleDateFormat("yyyy-MM-dd").parse(saAux.getAuxfechaFinal()));
	 * solicitudConsultoriaRepository.save(sa); dc.setHoraInicio(new
	 * SimpleDateFormat("HH:mm").parse(saAux.getAuxhoraInicio())); dc.setHoraFin(new
	 * SimpleDateFormat("HH:mm").parse(saAux.getAuxhoraFin()));
	 * dComunRepository.save(dc);
	 * 
	 * informe.setConsultorId(Consultor);
	 * 
	 * informeRepository.save(informe); }
	 * 
	 * @Override public List<CheckList> findAllCheckList(SolicitudConsultoria sa) {
	 * return checkListRepository.findAllBySolicitudConsultoriaId(sa); }
	 */

	@Override
	public List<BackOffice> findAllBack() {
		return backRepository.findAll();
	}

	@Override
	public void saveBack(BackOffice back) {
		backRepository.save(back);

	}

	@Override
	public BackOffice findBack(Integer id) {
		return backRepository.findById(id).get();
	}

}
