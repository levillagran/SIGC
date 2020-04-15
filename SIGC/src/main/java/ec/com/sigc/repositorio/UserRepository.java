package ec.com.sigc.repositorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.com.sigc.entidad.RoleSys;
import ec.com.sigc.entidad.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{
	
	public abstract User findByUsuario(String usuario); // usa spring security para devolver el usuario logiado
	public abstract List<User> findAllByRoleId(RoleSys roleId);
	public abstract User findByClave(String clave);
	
	public abstract User findByUsuarioAndClave(String usuario, String clave);
	
}
