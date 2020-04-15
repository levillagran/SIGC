package ec.com.sigc.controlador;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showFormHome() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String showForm() {
		return "home";
	}

}
