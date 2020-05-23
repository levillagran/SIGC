package ec.com.sigc.repositorio;
import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.sigc.entidad.TipoConsultoria;

@Repository("tipoConsRepository")
public interface TipoConsRepository extends JpaRepository<TipoConsultoria, Serializable>{
	public abstract TipoConsultoria findByTipoConsultoria(String tipoConsul);
}
