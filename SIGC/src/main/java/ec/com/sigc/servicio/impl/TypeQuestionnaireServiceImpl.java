package ec.com.sigc.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.TipoConsultoria;
import ec.com.sigc.repositorio.QuestJpaRepository;
import ec.com.sigc.repositorio.TipoConsRepository;
import ec.com.sigc.servicio.TypeQuestionnaireService;

@Service("typeQuestionnaireService")
public class TypeQuestionnaireServiceImpl implements TypeQuestionnaireService {

	@Autowired
	@Qualifier("questRepository")
	private QuestJpaRepository questRepository;

	@Autowired
	@Qualifier("tipoConsRepository")
	private TipoConsRepository tipoAudiRepository;

	@Override
	public List<TipoConsultoria> findAllTypeQuestionnaire() {
		return tipoAudiRepository.findAll();
	}

	@Override
	public TipoConsultoria findTypeQuestionnaire(Integer id) {
		return tipoAudiRepository.findById(id).get();
	}

	@Override
	public void deleteTypeQuestionnaire(Integer id) {
		tipoAudiRepository.deleteById(id);
	}

	@Override
	public void saveTypeQuestionnaire(TipoConsultoria typeQuestionnaire) {
		tipoAudiRepository.save(typeQuestionnaire);
		
	}

	

}
