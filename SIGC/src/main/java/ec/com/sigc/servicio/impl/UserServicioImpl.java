package ec.com.sigc.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.RoleSys;
import ec.com.sigc.entidad.User;
import ec.com.sigc.modelo.Usuario;
import ec.com.sigc.repositorio.RoleSysRepository;
import ec.com.sigc.repositorio.UserRepository;
import ec.com.sigc.servicio.EncryptKey;
import ec.com.sigc.servicio.UserServicio;

@Service("userServicio")
public class UserServicioImpl implements UserServicio {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("encryptKey")
	private EncryptKey encriptar;

	@Autowired
	@Qualifier("roleSysRepository")
	private RoleSysRepository roleSysRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findAdmin(int adminId) {
		return userRepository.findById(adminId).get();
	}

	@Override
	public void saveUser(User user) {
		user.setClave(encriptar.encryptKey(user.getClave()));
		userRepository.save(user);

	}

	@Override
	public void deletAdmin(int adminId) {
		userRepository.deleteById(adminId);

	}

	@Override
	public List<User> findAllAdmin() {
		return userRepository.findAllByRoleId(roleSysRepository.findById(1).get());
	}

	@Override
	public List<User> findAllConsul() {
		return userRepository.findAllByRoleId(roleSysRepository.findById(3).get());
	}

	@Override
	public List<User> findAllCust() {
		return userRepository.findAllByRoleId(roleSysRepository.findById(4).get());
	}

	@Override
	public List<RoleSys> findAllRole() {
		return roleSysRepository.findAll();
	}

	@Override
	public User findUserRole(String username) {
		return userRepository.findByUsuario(username);
	}

	@Override
	public RoleSys findRoleById(int roleId) {
		return roleSysRepository.findById(roleId).get();
	}

	@Override
	public User autenticacion(Usuario usuario) {
		return userRepository.findByUsuarioAndClave(usuario.getUsuario(), usuario.getClave());
	}

}
