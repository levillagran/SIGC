package ec.com.sigc.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.Preguntas;
import ec.com.sigc.entidad.Seccion;
import ec.com.sigc.entidad.TipoConsultoria;
import ec.com.sigc.repositorio.QuestJpaRepository;
import ec.com.sigc.repositorio.SectionRepository;
import ec.com.sigc.repositorio.TipoConsRepository;
import ec.com.sigc.servicio.QuestService;

@Service("questServicio")
public class QuestServiceImpl implements QuestService {

	@Autowired
	@Qualifier("questRepository")
	private QuestJpaRepository questRepository;

	@Autowired
	@Qualifier("tipoConsRepository")
	private TipoConsRepository tipoAudiRepository;

	@Autowired
	@Qualifier("sectionRepository")
	private SectionRepository sectionRepository;

	@Override
	public List<Preguntas> findAllQuest() {
		// int tipoConsultoriaId=1;
		// questRepository.findAllByTipoConsultoriaId(tipoAudiRepository.findById(tipoConsultoriaId).get())
		return questRepository.findAll();
	}

	@Override
	public Preguntas findQuestion(int idQuestion) {
		return questRepository.findById(idQuestion).get();
	}

	@Override
	public void deleteQuestion(int idQuestion) {
		questRepository.deleteById(idQuestion);
	}

	@Override
	public List<Preguntas> findAllByTipoConsultoriaAndSeccion(Integer tipoConsultoria, Integer seccion) {
		TipoConsultoria tAu = tipoAudiRepository.findById(tipoConsultoria).get();
		Seccion secc = sectionRepository.findById(seccion).get();
		return questRepository.findAllByTipoConsultoriaIdAndSeccionId(tAu, secc);
	}

	@Override
	public void saveQuest(String pregunta, String taId, String sect) {
		TipoConsultoria ta = tipoAudiRepository.findById(Integer.valueOf(taId)).get();
		Seccion sec = sectionRepository.findById(Integer.valueOf(sect)).get();
		Preguntas pre = new Preguntas();
		pre.setTipoConsultoriaId(ta);
		pre.setSeccionId(sec);
		pre.setPreguntas(pregunta);
		questRepository.save(pre);
	}

}
