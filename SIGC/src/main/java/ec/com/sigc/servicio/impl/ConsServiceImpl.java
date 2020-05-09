package ec.com.sigc.servicio.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.Cliente;
import ec.com.sigc.entidad.DatoComun;
import ec.com.sigc.entidad.Informe;
import ec.com.sigc.entidad.SolicitudConsultoria;
import ec.com.sigc.entidad.TipoConsultoria;
import ec.com.sigc.repositorio.CustRepository;
import ec.com.sigc.repositorio.DatoComunRepository;
import ec.com.sigc.repositorio.EstadoConRepository;
import ec.com.sigc.repositorio.InformeRepository;
import ec.com.sigc.repositorio.SolicitudConsRepository;
import ec.com.sigc.repositorio.TipoConsRepository;
import ec.com.sigc.repositorio.UserRepository;
import ec.com.sigc.servicio.ConsService;

@Service("consService")
public abstract class ConsServiceImpl implements ConsService {

	@Autowired
	@Qualifier("tipoConsRepository")
	private TipoConsRepository tipoConsRepository;

	@Autowired
	@Qualifier("solicitudConRepository")
	private SolicitudConsRepository solicitudConRepository;

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("estadoConRepository")
	private EstadoConRepository estadoConRepository;
	
	@Autowired
	@Qualifier("dComunRepository")
	private DatoComunRepository dComunRepository;
	
	@Autowired
	@Qualifier("custRepository")
	private CustRepository custRepository;
	
	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Override
	public List<TipoConsultoria> findAllTipoConsultoria() {
		return tipoConsRepository.findAll();
	}

	@Override
	public void solicitudConsultoria(DatoComun datoComun, SolucitudConsultoriaString sas, String usuario)
			throws Exception {
		Cliente cliente = new Cliente();
		SolicitudConsultoria soliAud = new SolicitudConsultoria();
		Informe informe = new Informe();

		soliAud.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse(sas.getAuxfechaInicio()));
		soliAud.setFechaFinal(new SimpleDateFormat("yyyy-MM-dd").parse(sas.getAuxfechaFinal()));
		soliAud.setTipoAuditoriaId(TipoConsRepository.findById(Integer.parseInt(sas.getAuxTipoAuditoriaId())).get());
		soliAud.setEstadoConsultoriaId(estadoConRepository.findById(1).get());
		solicitudConRepository.save(soliAud);
		System.out.println("id solicitud: "+soliAud.getSolicitudConsultoriaId());
		
		datoComun.setHoraInicio(new SimpleDateFormat("HH:mm").parse(sas.getAuxhoraInicio()));
		datoComun.setHoraFin(new SimpleDateFormat("HH:mm").parse(sas.getAuxhoraFin()));
		datoComun.setSolicitudConsultoriaId(solicitudConRepository.findById(soliAud.getSolicitudConsultoriaId()).get());
		dComunRepository.save(datoComun);
		System.out.println("id dato comun: "+datoComun.getDatoComunId());
		cliente=custRepository.findByUserId(userRepository.findByUsuario(usuario));
		custRepository.save(cliente);
		System.out.println("id cliente: "+cliente.getClienteId());
		
		informe.setDatoComunId(datoComun);
		informe.setClienteId(cliente);
		informeRepository.save(informe);
		System.out.println("id informe: "+informe.getInformeId());

	}

}
