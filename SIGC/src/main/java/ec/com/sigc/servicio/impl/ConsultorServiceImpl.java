package ec.com.sigc.servicio.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.DatoEspecifico;
import ec.com.siga.entity.Foto;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.Preguntas;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.repository.AuditorRepository;
import ec.com.siga.repository.CheckListRepository;
import ec.com.siga.repository.DatoComunRepository;
import ec.com.siga.repository.DatoEspecificoRepository;
import ec.com.siga.repository.EstadoAuditRepository;
import ec.com.siga.repository.FotoRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.repository.PreguntasRepository;
import ec.com.siga.repository.SolicitudAuditoriaRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.repository.TipoCustRepository;
import ec.com.siga.repository.UserJpaRepository;
import ec.com.siga.service.AuditorService;

@Service("auditorService")
public class ConsultorServiceImpl implements ConsultorService {

	@Autowired
	@Qualifier("tipoAudiRepository")
	private TipoConsRepository tipoAudiRepository;

	@Autowired
	@Qualifier("tipoCustRepository")
	private TipoCustRepository tipoCustRepository;

	@Autowired
	@Qualifier("solicitudAuditoriaRepository")
	private SolicitudAuditoriaRepository solicitudAuditoriaRepository;

	@Autowired
	@Qualifier("userRepository")
	private UserJpaRepository userRepository;

	@Autowired
	@Qualifier("estadoAuditRepository")
	private EstadoAuditRepository estadoAuditRepository;

	@Autowired
	@Qualifier("dComunRepository")
	private DatoComunRepository dComunRepository;

	@Autowired
	@Qualifier("dEspecificoRepository")
	private DatoEspecificoRepository dEspecificoRepository;

	@Autowired
	@Qualifier("auditorRepository")
	private AuditorRepository auditorRepository;

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Autowired
	@Qualifier("preguntasRepository")
	private PreguntasRepository preguntasRepository;

	@Autowired
	@Qualifier("fotoRepository")
	private FotoRepository fotoRepository;

	@Autowired
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;

	@Override
	public List<Informe> findAllAssignedAudits(String auditor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByAuditorId(auditorRepository.findByUserId(userRepository.findByUsuario(auditor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudAuditoriaId().getEstadoAuditoriaId().getEstadoAuditoriaId() == 2) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public void createCkeckList(int informeId) {
		Informe informe = informeRepository.findById(informeId).get();
		DatoComun dc = informe.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		String aId = String.valueOf(sa.getSolicitudAuditoriaId());
		String bId, res;
		TipoAuditoria ta = sa.getTipoAuditoriaId();
		List<Preguntas> pre = preguntasRepository.findByTipoAuditoriaId(ta);

		if (checkListRepository.findAllBySolicitudAuditoriaId(sa).isEmpty()) {
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
				cl.setSolicitudAuditoriaId(sa);
				cl.setPreguntasId(i);
				checkListRepository.save(cl);
			}
		}

	}

	@Override
	public CheckList reply(int informeId) {
		Informe informe = informeRepository.findById(informeId).get();
		List<CheckList> preguntas = checkListRepository
				.findAllBySolicitudAuditoriaId(informe.getDatoComunId().getSolicitudAuditoriaId());
		return preguntas.get(0);
	}

	@Override
	public CheckList replyUploadFile(int informeId) {
		Informe informe = informeRepository.findById(informeId).get();
		List<CheckList> preguntas = checkListRepository
				.findAllBySolicitudAuditoriaId(informe.getDatoComunId().getSolicitudAuditoriaId());
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
		
		Foto foto = new Foto();
		DatoEspecifico de = new DatoEspecifico();
		try {
			if (f.isEmpty()) {
				System.out.println("entra a no hacer nada");
			}else {
				System.out.println("entra a guardar foto");
				foto.setFoto(f.getBytes());
				fotoRepository.save(foto);
				de.setFotoId(foto);
			}
			
			// foto.setFileName(Base64.encodeBase64String(f.getBytes()));
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
	public List<Informe> findAllAuditsHistory(String auditor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByAuditorId(auditorRepository.findByUserId(userRepository.findByUsuario(auditor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudAuditoriaId().getEstadoAuditoriaId().getEstadoAuditoriaId() == 5) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public String sendNonConformities(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		DatoComun dc = inf.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();

		List<CheckList> listChck = checkListRepository
				.findAllBySolicitudAuditoriaId(inf.getDatoComunId().getSolicitudAuditoriaId());
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
			sa.setEstadoAuditoriaId(estadoAuditRepository.findById(3).get());
			dc.setSolicitudAuditoriaId(sa);
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
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		sa.setEstadoAuditoriaId(estadoAuditRepository.findById(4).get());
		dc.setSolicitudAuditoriaId(sa);
		inf.setDatoComunId(dc);
		informeRepository.save(inf);
		return "Request for evidence sent";
	}

	@Override
	public List<Informe> findAllAuditsSendNC(String auditor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByAuditorId(auditorRepository.findByUserId(userRepository.findByUsuario(auditor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudAuditoriaId().getEstadoAuditoriaId().getEstadoAuditoriaId() == 3) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public List<Informe> findAllAuditsToCheck(String auditor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByAuditorId(auditorRepository.findByUserId(userRepository.findByUsuario(auditor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudAuditoriaId().getEstadoAuditoriaId().getEstadoAuditoriaId() == 4) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public Integer firstQualification(Informe inf) {
		List<CheckList> listChck = checkListRepository
				.findAllBySolicitudAuditoriaId(inf.getDatoComunId().getSolicitudAuditoriaId());
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
				.findAllBySolicitudAuditoriaId(inf.getDatoComunId().getSolicitudAuditoriaId());
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
