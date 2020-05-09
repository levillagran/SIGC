package ec.com.sigc.repositorio;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ec.com.sigc.entidad.Preguntas;
import ec.com.sigc.entidad.TipoConsultoria;


@Repository("preguntasRepository")
public interface PreguntasRepository extends JpaRepository<Preguntas, Serializable>{
	public abstract List<Preguntas> findByTipoConsultoriaId(TipoConsultoria tipoConsultoriaId);
}
