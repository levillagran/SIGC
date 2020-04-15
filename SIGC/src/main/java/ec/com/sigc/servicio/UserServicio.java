package ec.com.sigc.servicio;

import java.util.List;

import ec.com.sigc.entidad.RoleSys;
import ec.com.sigc.entidad.User;
import ec.com.sigc.modelo.Usuario;

public interface UserServicio {
	public abstract User autenticacion(Usuario usuario);
	public abstract List<User> findAll();
	public abstract User findAdmin(int adminId);
	public abstract User findUserRole(String username);
	public abstract void saveUser(User admin);
	public abstract void deletAdmin(int adminId);
	public abstract List<User> findAllAdmin();
	public abstract List<User> findAllConsul();
	public abstract List<User> findAllCust();
	public abstract List<RoleSys> findAllRole();
	public abstract RoleSys findRoleById(int roleId);
}
