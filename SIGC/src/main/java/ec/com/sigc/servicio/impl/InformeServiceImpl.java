package ec.com.sigc.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.Informe;
import ec.com.sigc.repositorio.CustRepository;
import ec.com.sigc.repositorio.InformeRepository;
import ec.com.sigc.repositorio.UserRepository;
import ec.com.sigc.servicio.InformeService;

@Service("informeServicio")
public class InformeServiceImpl implements InformeService {

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("custRepository")
	private CustRepository custRepository;

	@Override
	public List<Informe> findAllReport() {
		return informeRepository.findAll();
	}

	@Override
	public List<Informe> findAllReportRequests() {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findAll();
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudConsultoriaId().getEstadoConsultoriaId().getEstadoConsultoriaId() == 1) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public List<Informe> findAllReportProsesing() {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findAll();
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudConsultoriaId().getEstadoConsultoriaId().getEstadoConsultoriaId() > 1) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public Informe findReport(int idReport) {
		return informeRepository.findById(idReport).get();
	}

	@Override
	public void deleteReport(int idReport) {
		informeRepository.deleteById(idReport);

	}

	@Override
	public List<Informe> findAllCustAudits(String username) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByClienteId(custRepository.findByUserId(userRepository.findByUsuario(username)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudConsultoriaId().getEstadoConsultoriaId().getEstadoConsultoriaId() != 3) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public List<Informe> findAllCustAuditsNC(String username) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByClienteId(custRepository.findByUserId(userRepository.findByUsuario(username)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudConsultoriaId().getEstadoConsultoriaId().getEstadoConsultoriaId() == 3) {
				auxList.add(info);
			}
		}
		return auxList;
	}

}
