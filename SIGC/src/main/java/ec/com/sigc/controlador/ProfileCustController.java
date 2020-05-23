package ec.com.sigc.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.sigc.entidad.Cliente;
import ec.com.sigc.modelo.Usuario;
import ec.com.sigc.repositorio.CustRepository;
import ec.com.sigc.repositorio.UserRepository;
import ec.com.sigc.servicio.CustService;

@Controller
public class ProfileCustController {
	
	@Autowired
	@Qualifier("custRepository")
	private CustRepository custRepository;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@GetMapping("/profileCust")
	@ResponseBody
	public ModelAndView showForm(String usuario) {
		ModelAndView mav = new ModelAndView("profileCust");
		Cliente cli = custRepository.findByUserId(userRepository.findByUsuario(usuario));
		mav.addObject("cliente", cli);
		mav.addObject("usuario", usuario);
		return mav;
	}
	
}
