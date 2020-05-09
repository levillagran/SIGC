package ec.com.sigc.preLoad;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ec.com.sigc.entidad.BackOffice;
import ec.com.sigc.entidad.Consultor;
import ec.com.sigc.entidad.RoleSys;
import ec.com.sigc.entidad.User;
import ec.com.sigc.repositorio.BackRepository;
import ec.com.sigc.repositorio.ConsultorRepository;
import ec.com.sigc.repositorio.RoleSysRepository;
import ec.com.sigc.repositorio.UserRepository;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, RoleSysRepository roleSysRepository, BackRepository backRepository, ConsultorRepository consRepo ) {
		return args -> {
			//roles
			roleSysRepository.save(new RoleSys(1, "ADMINISTRADOR"));
			roleSysRepository.save(new RoleSys(2, "BACK-OFFICE"));
			roleSysRepository.save(new RoleSys(3, "CONSULTOR"));
			roleSysRepository.save(new RoleSys(4, "CLIENTE"));
			//administradores
			userRepository.save(new User(1, "admin", "admin", "admin@sigc", Long.valueOf("9999999999"), "admin", "admin", "UCE", roleSysRepository.findById(1).get(), true));
			//back-office
			userRepository.save(new User(2, "back", "back", "back@sigc", Long.valueOf("9999999999"), "back", "back", "UCE", roleSysRepository.findById(2).get(), true));
			backRepository.save(new BackOffice(1, "1721", userRepository.findById(2).get()));
			//consultor
			userRepository.save(new User(3, "cons", "cons", "cons@sigc", Long.valueOf("9999999999"), "cons", "cons", "UCE", roleSysRepository.findById(3).get(), true));
			consRepo.save(new Consultor(1, null, userRepository.findById(3).get()));
		};
	}
}
