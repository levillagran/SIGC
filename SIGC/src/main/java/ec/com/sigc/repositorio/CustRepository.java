package ec.com.sigc.repositorio;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.sigc.entidad.Cliente;
import ec.com.sigc.entidad.User;

@Repository("custRepository")
public interface CustRepository extends JpaRepository<Cliente, Serializable>{
	public abstract Cliente findByUserId(User userId);
	
}
