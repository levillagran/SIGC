package ec.com.sigc.repositorio;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.sigc.entidad.Consultor;
import ec.com.sigc.entidad.Cliente;
import ec.com.sigc.entidad.Informe;

@Repository("informeRepository")
public interface InformeRepository extends JpaRepository<Informe, Serializable> {
	
	public abstract List<Informe> findByClienteId(Cliente clienteId);
	//public abstract List<Informe> findByAuditorIdAndEstadoAuditoriaId(Auditor auditorId,EstadoAuditoria esAu);
	public abstract List<Informe> findByConsultorId(Consultor consultorId);

}
