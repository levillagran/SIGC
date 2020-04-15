package ec.com.sigc.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ec.com.sigc.entidad.User;
import ec.com.sigc.modelo.Usuario;
import ec.com.sigc.servicio.UserServicio;

@RestController
public class LoginController {

	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;
	
	@GetMapping("/login")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@PostMapping("/checklogin")
	public ModelAndView saveAdmin(Usuario usu) {
		ModelAndView mav = new ModelAndView();
		System.out.println(usu.getUsuario());
		System.out.println(usu.getClave());
		User user = userServicio.autenticacion(usu);
		int role = user.getRoleId().getRoleId();

		switch (role) {
		case 1:
			mav.setViewName("dashboardAdmin");
			break;
		case 2:
			mav.setViewName("dashboardBack");
			break;
		case 3:
			mav.setViewName("dashboardAuditor");
			break;
		case 4:
			mav.setViewName("dashboardCust");
			break;
		default:
			mav.setViewName("login");
		}
		//mav.addObject("username", user.getUsuario());
		//mav.addObject("role", role);
		return mav;
	}

}
