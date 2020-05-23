package ec.com.sigc.servicio;

import java.util.List;

import ec.com.sigc.entidad.TipoConsultoria;

public interface TypeQuestionnaireService {
	public abstract List<TipoConsultoria> findAllTypeQuestionnaire();
	public abstract TipoConsultoria findTypeQuestionnaire(Integer idTypeQuestionnaire);
	public abstract void saveTypeQuestionnaire(TipoConsultoria typeQuestionnaire);
	public abstract void deleteTypeQuestionnaire(Integer idTypeQuestionnaire);
}
