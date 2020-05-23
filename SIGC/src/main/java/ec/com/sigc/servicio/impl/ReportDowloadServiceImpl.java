package ec.com.sigc.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.CheckList;
import ec.com.sigc.entidad.Informe;
import ec.com.sigc.repositorio.CheckListRepository;
import ec.com.sigc.repositorio.EntregableRepository;
import ec.com.sigc.repositorio.InformeRepository;
import ec.com.sigc.servicio.ReportDowloadService;

@Service("reportDowloadService")
public class ReportDowloadServiceImpl implements ReportDowloadService {

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Autowired
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;

	@Autowired
	@Qualifier("entregableRepository")
	private EntregableRepository entregableRepository;

	@Override
	public byte[] reportDowloar(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		return inf.getEntregableId().getInforme();
	}
	
	@Override
	public byte[] reportDowloarCertificate(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		return inf.getEntregableId().getCertificado();
	}

	@Override
	public byte[] fileDowloarToCheck(Integer id) {
		CheckList cl = checkListRepository.findById(id).get();
		return cl.getDatoEspecificoId().getArchivoId().getArchivo();
	}

}
