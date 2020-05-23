package ec.com.sigc.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.sigc.entidad.User;
import ec.com.sigc.servicio.UserServicio;
import ec.com.sigc.repositorio.ConsultorRepository;

@Controller
public class TableAudiController {
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;
	
	@Autowired
	@Qualifier("ConsultorRepository")
	private ConsultorRepository consultorRepository;

	@GetMapping("/tableAudi")
	@ResponseBody
	public ModelAndView showForm(String username) {
		ModelAndView mav = new ModelAndView("tableAudi");
		mav.addObject("contacts", consultorRepository.findAll());
		mav.addObject("username", username);
		return mav;
	}
	
	@GetMapping("/editAudi")
	@ResponseBody
	public String showEditAdminForm() {
		return "editAdmin";
	}
	
	@PostMapping("/saveAudi")
	public String saveAdmin(User admin) {
		userServicio.saveUser(admin);
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/findAudi")
	@ResponseBody
	public User findOne(Integer id) {
	return userServicio.findAdmin(id);
	}
	
	@GetMapping("/cancelAudi")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}
	
	@GetMapping("/deleteAudi")
    public String deleteCountry(Integer adminId) {
		//userServicio.deletAdmin(userServicio.findAdmin(adminId));
        return "redirect:/dashboardAdmin";
    }

}
