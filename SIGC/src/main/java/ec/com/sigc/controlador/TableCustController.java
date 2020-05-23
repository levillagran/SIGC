package ec.com.sigc.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.sigc.entidad.Cliente;
import ec.com.sigc.entidad.User;
import ec.com.sigc.repositorio.CustRepository;
import ec.com.sigc.servicio.CustService;
import ec.com.sigc.servicio.UserServicio;

@Controller
public class TableCustController {

	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;

	@Autowired
	@Qualifier("custService")
	private CustService custService;

	@Autowired
	@Qualifier("custRepository")
	private CustRepository custRepository;

	@GetMapping("/tableCust")
	@ResponseBody
	public ModelAndView showForm(String username) {
		ModelAndView mav = new ModelAndView("tableCust");
		mav.addObject("contacts", custRepository.findAll());
		mav.addObject("username", username);
		return mav;
	}

	@GetMapping("/editCust")
	public String showEditAdminForm() {
		return "editAdmin";
	}

	@PostMapping("/saveCust")
	public String saveAdmin(User admin) {
		userServicio.saveUser(admin);
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/findCust")
	@ResponseBody
	public Cliente findOne(Integer id) {
		return custService.findCustById(id);
	}

	@GetMapping("/cancelCust")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteCust")
	public String deleteCountry(Integer adminId) {
		// userServicio.deletAdmin(userServicio.findAdmin(adminId));
		return "redirect:/dashboardAdmin";
	}

}
