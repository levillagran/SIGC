package ec.com.sigc.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.sigc.entidad.BackOffice;
import ec.com.sigc.entidad.User;
import ec.com.sigc.servicio.BackOfficeService;
import ec.com.sigc.servicio.UserServicio;

@Controller
public class TableBackController {
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;
	
	@Autowired
	@Qualifier("backOfficeService")
	private BackOfficeService backOfficeService;

	@GetMapping("/tableBack")
	@ResponseBody
	public ModelAndView showForm(String username) {
		ModelAndView mav = new ModelAndView("tableBack");
		mav.addObject("contacts", backOfficeService.findAllBack());
		mav.addObject("username", username);
		return mav;
	}
	
	@GetMapping("/editBack")
	@ResponseBody
	public ModelAndView showEditAdminForm(Integer backId, String username) {
		ModelAndView mav = new ModelAndView("editBack");
		if (backId != null) {
			mav.addObject("back", backOfficeService.findBack(backId));	
		}
		mav.addObject("username", username);
		return mav;
	}
	
	@PostMapping("/saveBack")
	public ModelAndView saveAdmin(BackOffice back, String username) {
		ModelAndView mav = new ModelAndView("dashboardAdmin");
		User user = back.getUserId();
		user.setRoleId(userServicio.findRoleById(3));
		back.setUserId(user);
		backOfficeService.saveBack(back);
		mav.addObject("username", username);
		return mav;
	}

	@GetMapping("/findBack")
	@ResponseBody
	public User findOne(Integer id) {
	return userServicio.findAdmin(id);
	}
	
	@GetMapping("/cancelBack")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}
	
	@GetMapping("/deleteBack")
    public String deleteCountry(Integer adminId) {
		//userServicio.deletAdmin(userServicio.findAdmin(adminId));
        return "redirect:/dashboardAdmin";
    }


}
