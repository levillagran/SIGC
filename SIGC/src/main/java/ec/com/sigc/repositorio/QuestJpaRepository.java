package ec.com.sigc.repositorio;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.sigc.entidad.Preguntas;
import ec.com.sigc.entidad.Seccion;
import ec.com.sigc.entidad.TipoConsultoria;

@Repository("questRepository")
public interface QuestJpaRepository extends JpaRepository<Preguntas, Serializable>{
	
	public abstract List<Preguntas> findAllByTipoConsultoriaId(TipoConsultoria tipoConsultoriaId);
	public abstract List<Preguntas> findAllByTipoConsultoriaIdAndSeccionId(TipoConsultoria tipoConsultoriaId, Seccion seccion);
	public abstract List<Preguntas> findBySeccionId(Seccion seccionId);
}
