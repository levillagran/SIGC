package ec.com.sigc.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.CheckList;
import ec.com.sigc.entidad.DatoComun;
import ec.com.sigc.entidad.Entregable;
import ec.com.sigc.entidad.Informe;
import ec.com.sigc.entidad.Seccion;
import ec.com.sigc.entidad.SolicitudConsultoria;
//import ec.com.sigc.modelo.GeneratePdfCertificado;
//import ec.com.sigc.modelo.GeneratePdfReport;
import ec.com.sigc.repositorio.CheckListRepository;
import ec.com.sigc.repositorio.EntregableRepository;
import ec.com.sigc.repositorio.EstadoConRepository;
import ec.com.sigc.repositorio.InformeRepository;
import ec.com.sigc.repositorio.SectionRepository;
import ec.com.sigc.servicio.ReportGenerationService;

@Service("reportGenerationService")
public class ReportGenerationServiceImpl implements ReportGenerationService {

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Autowired
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;

	@Autowired
	@Qualifier("entregableRepository")
	private EntregableRepository entregableRepository;
	
	@Autowired
	@Qualifier("sectionRepository")
	private SectionRepository sectionRepository;
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("estadoConRepository")
	private EstadoConRepository estadoAuditRepository;

	/**
	 * @param informeId id informe
	 */	
	@Override
	public String reportGeneration(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		DatoComun dc = inf.getDatoComunId();
		dc.setCalificacionFinal(dc.getCalificacion());
		SolicitudConsultoria sa = dc.getSolicitudConsultoriaId();
		sa.setEstadoConsultoriaId(estadoAuditRepository.findById(5).get());
		dc.setSolicitudConsultoriaId(sa);
		inf.setDatoComunId(dc);
		
		List<CheckList> cl = checkListRepository
				.findAllBySolicitudConsultoriaId(inf.getDatoComunId().getSolicitudConsultoriaId());
		List<Seccion> secciones = sectionRepository.findAll();
		Entregable en = entregableRepository.findById(1).get();

		Entregable ent = new Entregable();
		//ent.setInforme(GeneratePdfReport.auditoriesReport(inf, cl, en, secciones));
		//ent.setCertificado(GeneratePdfCertificado.auditoriesCertificate(inf, en));
		entregableRepository.save(ent);

		inf.setEntregableId(ent);
		informeRepository.save(inf);

		return "Report Generate";
	}

	@Override
	public String reportGenerationPost(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		DatoComun dc = inf.getDatoComunId();
		SolicitudConsultoria sa = dc.getSolicitudConsultoriaId();
		sa.setEstadoConsultoriaId(estadoAuditRepository.findById(5).get());
		dc.setSolicitudConsultoriaId(sa);
		inf.setDatoComunId(dc);
		
		List<CheckList> cl = checkListRepository
				.findAllBySolicitudConsultoriaId(inf.getDatoComunId().getSolicitudConsultoriaId());
		List<Seccion> secciones = sectionRepository.findAll();
		Entregable en = entregableRepository.findById(1).get();

		Entregable ent = new Entregable();
		//ent.setInforme(GeneratePdfReport.auditoriesReport(inf, cl, en, secciones));
		//ent.setCertificado(GeneratePdfCertificado.auditoriesCertificate(inf, en));
		entregableRepository.save(ent);

		inf.setEntregableId(ent);
		informeRepository.save(inf);

		return "Report Generate";
	}

}
