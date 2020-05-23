package ec.com.sigc.servicio.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ec.com.sigc.entidad.CheckList;
import ec.com.sigc.entidad.DatoComun;
import ec.com.sigc.entidad.DatoEspecifico;
import ec.com.sigc.entidad.Archivo;
import ec.com.sigc.entidad.Informe;
import ec.com.sigc.entidad.Preguntas;
import ec.com.sigc.entidad.SolicitudConsultoria;
import ec.com.sigc.entidad.TipoConsultoria;
import ec.com.sigc.repositorio.ConsultorRepository;
import ec.com.sigc.repositorio.CheckListRepository;
import ec.com.sigc.repositorio.DatoComunRepository;
import ec.com.sigc.repositorio.DatoEspecificoRepository;
import ec.com.sigc.repositorio.EstadoConRepository;
import ec.com.sigc.repositorio.archivoRepository;
import ec.com.sigc.repositorio.InformeRepository;
import ec.com.sigc.repositorio.PreguntasRepository;
import ec.com.sigc.repositorio.SolicitudConsRepository;
import ec.com.sigc.repositorio.TipoConsRepository;
//import ec.com.sigc.repositorio.TipoCustRepository;
import ec.com.sigc.repositorio.UserRepository;
import ec.com.sigc.servicio.ConsService;
import ec.com.sigc.servicio.ConsultorService;

@Service("ConsultorService")
public class ConsultorServiceImpl implements ConsultorService {

	@Autowired
	@Qualifier("tipoConsRepository")
	private TipoConsRepository  tipoAudiRepository;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("tipoCustRepository") private TipoCustRepository
	 * tipoCustRepository;
	 */

	@Autowired
	@Qualifier("solicitudConRepository")
	private SolicitudConsRepository  solicitudConsultoriaRepository;

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("estadoConRepository")
	private EstadoConRepository estadoAuditRepository;

	@Autowired
	@Qualifier("dComunRepository")
	private DatoComunRepository dComunRepository;

	@Autowired
	@Qualifier("dEspecificoRepository")
	private DatoEspecificoRepository dEspecificoRepository;

	@Autowired
	@Qualifier("ConsultorRepository")
	private ConsultorRepository ConsultorRepository;

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Autowired
	@Qualifier("preguntasRepository")
	private PreguntasRepository preguntasRepository;

	@Autowired
	@Qualifier("archivoRepository")
	private archivoRepository ArchivoRepository;

	@Autowired
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;

	@Override
	public List<Informe> findAllAssignedAudits(String Consultor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByConsultorId(ConsultorRepository.findByUserId(userRepository.findByUsuario(Consultor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudConsultoriaId().getEstadoConsultoriaId().getEstadoConsultoriaId() == 2) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public void createCkeckList(int informeId) {
		Informe informe = informeRepository.findById(informeId).get();
		DatoComun dc = informe.getDatoComunId();
		SolicitudConsultoria sa = dc.getSolicitudConsultoriaId();
		String aId = String.valueOf(sa.getSolicitudConsultoriaId());
		String bId, res;
		TipoConsultoria ta = sa.getTipoConsultoriaId();
		List<Preguntas> pre = preguntasRepository.findByTipoConsultoriaId(ta);

		if (checkListRepository.findAllBySolicitudConsultoriaId(sa).isEmpty()) {
			int j = 0;
			for (Preguntas i : pre) {
				j = j + 1;
				CheckList cl = new CheckList();
				if (j > 9) {
					bId = String.valueOf(j);
				} else {
					bId = "0" + String.valueOf(j);
				}
				res = aId + bId;

				cl.setCodigo(Integer.parseInt(res));
				cl.setSolicitudConsultoriaId(sa);
				cl.setPreguntasId(i);
				checkListRepository.save(cl);
			}
		}

	}

	@Override
	public CheckList reply(int informeId) {
		Informe informe = informeRepository.findById(informeId).get();
		List<CheckList> preguntas = checkListRepository
				.findAllBySolicitudConsultoriaId(informe.getDatoComunId().getSolicitudConsultoriaId());
		return preguntas.get(0);
	}

	@Override
	public CheckList replyUploadFile(int informeId) {
		Informe informe = informeRepository.findById(informeId).get();
		List<CheckList> preguntas = checkListRepository
				.findAllBySolicitudConsultoriaId(informe.getDatoComunId().getSolicitudConsultoriaId());
		for (CheckList pre : preguntas) {
			if (pre.getDatoEspecificoId().isRespuesta() == false) {
				return pre;
			}
		}
		return null;
	}

	@Override
	public CheckList replyPost(int informeId, String codigo, String accion) {
		int cod = Integer.valueOf(codigo);

		if (accion.equals("+")) {
			return checkListRepository.findByCodigo(cod + 1);
		} else {
			return checkListRepository.findByCodigo(cod - 1);
		}
	}

	@Override
	public CheckList replyPostUploadFile(int informeId, String codigo, String accion) {
		int cod = Integer.valueOf(codigo);

		if (accion.equals("+")) {
			cod = cod + 1;
			while (checkListRepository.findByCodigo(cod).getDatoEspecificoId().isRespuesta() == true) {
				cod = cod + 1;
			}
			return checkListRepository.findByCodigo(cod);
		} else {
			cod = cod - 1;
			while (checkListRepository.findByCodigo(cod).getDatoEspecificoId().isRespuesta() == true) {
				cod = cod - 1;
			}
			return checkListRepository.findByCodigo(cod);
		}
	}

	@Override
	public void saveReply(MultipartFile f, String evidencia, boolean respuesta, String codigo) {
		System.out.println("entra en saveReply");
		System.out.println(codigo);
		System.out.println(f);
		System.out.println(evidencia);
		System.out.println(respuesta);
		
		Archivo Archivo = new Archivo();
		DatoEspecifico de = new DatoEspecifico();
		try {
			if (f.isEmpty()) {
				System.out.println("entra a no hacer nada");
			}else {
				System.out.println("entra a guardar Archivo");
				Archivo.setArchivo(f.getBytes());
				ArchivoRepository.save(Archivo);
				de.setArchivoId(Archivo);
			}
			
			// Archivo.setFileName(Base64.encodeBase64String(f.getBytes()));
		} catch (IOException e) {
			System.out.println("guardar catch");
		}
		System.out.println("guardar 1");
		de.setEvidencia(evidencia);
		System.out.println("guardar 2");
		de.setRespuesta(respuesta);
		System.out.println("guardar 3");
		dEspecificoRepository.save(de);
		System.out.println("guardar 4");
		CheckList cl = checkListRepository.findByCodigo(Integer.parseInt(codigo));
		System.out.println(cl.getCodigo());
		cl.setDatoEspecificoId(de);
		checkListRepository.save(cl);
	}

	@Override
	public List<Informe> findAllAuditsHistory(String Consultor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByConsultorId(ConsultorRepository.findByUserId(userRepository.findByUsuario(Consultor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudConsultoriaId().getEstadoConsultoriaId().getEstadoConsultoriaId() == 5) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public String sendNonConformities(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		DatoComun dc = inf.getDatoComunId();
		SolicitudConsultoria sa = dc.getSolicitudConsultoriaId();

		List<CheckList> listChck = checkListRepository
				.findAllBySolicitudConsultoriaId(inf.getDatoComunId().getSolicitudConsultoriaId());
		String finish = null;
		int finishAux = 0;
		for (CheckList lChck : listChck) {
			if (lChck.getDatoEspecificoId() == null) {
				finishAux = 1;
				System.out.println("si hay nulo" + finishAux);
			}
		}
		if (finishAux == 1) {
			finish = "Have questions whitout reply";
		} else {
			finish = "Request for evidence sent and first qualification sent";
			sa.setEstadoConsultoriaId(estadoAuditRepository.findById(3).get());
			dc.setSolicitudConsultoriaId(sa);
			dc.setCalificacion(firstQualification(inf));
			inf.setDatoComunId(dc);
			informeRepository.save(inf);
		}
		return finish;
	}

	@Override
	public String sendToCheck(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		DatoComun dc = inf.getDatoComunId();
		SolicitudConsultoria sa = dc.getSolicitudConsultoriaId();
		sa.setEstadoConsultoriaId(estadoAuditRepository.findById(4).get());
		dc.setSolicitudConsultoriaId(sa);
		inf.setDatoComunId(dc);
		informeRepository.save(inf);
		return "Request for evidence sent";
	}

	@Override
	public List<Informe> findAllAuditsSendNC(String Consultor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByConsultorId(ConsultorRepository.findByUserId(userRepository.findByUsuario(Consultor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudConsultoriaId().getEstadoConsultoriaId().getEstadoConsultoriaId() == 3) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public List<Informe> findAllAuditsToCheck(String Consultor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByConsultorId(ConsultorRepository.findByUserId(userRepository.findByUsuario(Consultor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudConsultoriaId().getEstadoConsultoriaId().getEstadoConsultoriaId() == 4) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public Integer firstQualification(Informe inf) {
		List<CheckList> listChck = checkListRepository
				.findAllBySolicitudConsultoriaId(inf.getDatoComunId().getSolicitudConsultoriaId());
		int numPreguntas = 0;
		int numSi = 0;
		for (CheckList cl : listChck) {
			numPreguntas = numPreguntas + 1;
			if (cl.getDatoEspecificoId().isRespuesta() == true) {
				numSi = numSi + 1;
			}
		}
		return (numSi * 100) / numPreguntas;
	}

	@Override
	public void finalQualification(Integer id) {
		Informe inf = informeRepository.findById(id).get();
		DatoComun dc = inf.getDatoComunId();
		
		List<CheckList> listChck = checkListRepository
				.findAllBySolicitudConsultoriaId(inf.getDatoComunId().getSolicitudConsultoriaId());
		int numPreguntas = 0;
		int numSi = 0;
		for (CheckList cl : listChck) {
			numPreguntas = numPreguntas + 1;
			if (cl.getDatoEspecificoId().isRespuesta() == true) {
				numSi = numSi + 1;
			}
		}
		dc.setCalificacionFinal((numSi * 100) / numPreguntas);
		inf.setDatoComunId(dc);
		informeRepository.save(inf);
	}

}
