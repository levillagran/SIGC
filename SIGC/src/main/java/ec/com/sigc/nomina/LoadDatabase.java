package ec.com.sigc.nomina;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ec.com.sigc.entidad.RoleSys;
import ec.com.sigc.entidad.User;
import ec.com.sigc.repositorio.RoleSysRepository;
import ec.com.sigc.repositorio.UserRepository;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, RoleSysRepository roleSysRepository) {
		return args -> {
			System.out.println("Preloading " + roleSysRepository.save(new RoleSys(1, "ADMINISTRADOR")));
			roleSysRepository.save(new RoleSys(2, "BACK-OFFICE"));
			roleSysRepository.save(new RoleSys(3, "CONSULTOR"));
			roleSysRepository.save(new RoleSys(4, "CLIENTE"));


			userRepository.save(new User(1, "admin", "admin", "admin@sigc", Long.valueOf("9999999999"), "admin", "admin", "UCE", roleSysRepository.findById(1).get(), true));
		};
	}
}
