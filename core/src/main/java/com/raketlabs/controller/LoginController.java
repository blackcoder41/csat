package com.raketlabs.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.raketlabs.form.UserForm;

@Controller
//@RequestMapping(value = "/")
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login () {
		return "forms/login";
	}
	
	@RequestMapping(value = "/authFailed", method = RequestMethod.GET)
    public String authFailed (RedirectAttributes redirectAttributes) {
	    
	    redirectAttributes.addFlashAttribute("authFailed", "authFailed");
        return "redirect:/login";
    }

	@RequestMapping(value = {"/home" }, method = RequestMethod.GET)
	public String home(
	        Authentication authentication, 
	        RedirectAttributes redirectAttributes,
	        Model model) {
		
		
		model.addAttribute("authentication",  authentication);

		return "home/home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login";
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}
}