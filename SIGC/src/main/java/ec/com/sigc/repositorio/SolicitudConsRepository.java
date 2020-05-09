package ec.com.sigc.repositorio;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.sigc.entidad.SolicitudConsultoria;

@Repository("solicitudConRepository")
public interface SolicitudConsRepository extends JpaRepository<SolicitudConsultoria, Serializable>{
	//public abstract List findByUserId(User userId);
	
}
