package ec.com.sigc.servicio;

import java.util.List;

import ec.com.sigc.entidad.Preguntas;

public interface QuestService {
	public abstract List<Preguntas> findAllQuest();
	public abstract List<Preguntas> findAllByTipoConsultoriaAndSeccion(Integer tipoConsultoria, Integer seccion);
	public abstract Preguntas findQuestion(int idQuestion);
	public abstract void saveQuest(String pregunta, String taId, String sect);
	public abstract void deleteQuestion(int idQuestion);
}
