package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Preguntas;
import ec.com.siga.entity.Seccion;
import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.repository.QuestJpaRepository;
import ec.com.siga.repository.SectionRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.service.QuestService;

@Service("questServicio")
public class QuestServiceImpl implements QuestService {

	@Autowired
	@Qualifier("questRepository")
	private QuestJpaRepository questRepository;

	@Autowired
	@Qualifier("tipoAudiRepository")
	private TipoConsRepository tipoAudiRepository;

	@Autowired
	@Qualifier("sectionRepository")
	private SectionRepository sectionRepository;

	@Override
	public List<Preguntas> findAllQuest() {
		// int tipoAuditoriaId=1;
		// questRepository.findAllByTipoAuditoriaId(tipoAudiRepository.findById(tipoAuditoriaId).get())
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
	public List<Preguntas> findAllByTipoAuditoriaAndSeccion(Integer tipoAuditoria, Integer seccion) {
		TipoAuditoria tAu = tipoAudiRepository.findById(tipoAuditoria).get();
		Seccion secc = sectionRepository.findById(seccion).get();
		return questRepository.findAllByTipoAuditoriaIdAndSeccionId(tAu, secc);
	}

	@Override
	public void saveQuest(String pregunta, String taId, String sect) {
		TipoAuditoria ta = tipoAudiRepository.findById(Integer.valueOf(taId)).get();
		Seccion sec = sectionRepository.findById(Integer.valueOf(sect)).get();
		Preguntas pre = new Preguntas();
		pre.setTipoAuditoriaId(ta);
		pre.setSeccionId(sec);
		pre.setPreguntas(pregunta);
		questRepository.save(pre);
	}

}
