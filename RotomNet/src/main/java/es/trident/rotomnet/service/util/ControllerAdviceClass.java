/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.service.util;

import java.security.Principal;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ControllerAdviceClass Clase dedicada a configurar el lanzamiento de un error 404 ante una excepción NoSuchElementException y
 * añadir los atributos correspondientes al modelo de cada petición dependiendo de si se está o no logeado.
 */

@ControllerAdvice
public class ControllerAdviceClass {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public void notFoundLauncher() {}
	
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest req) {
		
		Principal principal = req.getUserPrincipal();
		
		if (principal != null) {
			model.addAttribute("logged", true);
			model.addAttribute("user_username", principal.getName());
			model.addAttribute("is_admin", req.isUserInRole("ADMIN"));
		}else {
			model.addAttribute("logged", false);
		}
		
	}
	
}
