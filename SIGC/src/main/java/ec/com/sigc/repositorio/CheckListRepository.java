package ec.com.sigc.repositorio;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.sigc.entidad.CheckList;
import ec.com.sigc.entidad.SolicitudConsultoria;

@Repository("checkListRepository")
public interface CheckListRepository extends JpaRepository<CheckList, Serializable>{
	public abstract List<CheckList> findAllBySolicitudAuditoriaId(SolicitudConsultoria sa);
	public abstract CheckList findByCodigo(int codigo);
	
}
