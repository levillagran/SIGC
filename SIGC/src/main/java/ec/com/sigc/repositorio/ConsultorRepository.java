package ec.com.sigc.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import ec.com.sigc.entidad.User;
import ec.com.sigc.entidad.Consultor;

@Repository("ConsultorRepository")
public interface ConsultorRepository extends JpaRepository<Consultor, Serializable>{
	public abstract Consultor findByUserId(User userId);
	
}
