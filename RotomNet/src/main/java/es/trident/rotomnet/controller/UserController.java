/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.service.UserService;

/**
 * UserController Controlador dedicado a la gestión de peticiones de
 * páginas relacionadas con la creación, modificación y borrado de
 * usuarios.
 */
@Controller
public class UserController {
	
	private static final Path IMAGE_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	private UserService userService;	
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String create(Model model) {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "logout";
	}
	
	@GetMapping("/register")
	public String login(Model model){			
		model.addAttribute("duplicatedUsername", false);		
		model.addAttribute("emptyMail",false);
		return "register";
	}
	
	@PostMapping("/registered")
	public String register(Model model, @RequestParam String username, @RequestParam String pwd,
			@RequestParam String mail, @RequestParam MultipartFile image) {
		User user;
		try {
			userService.findUserByUsername(username);
			model.addAttribute("duplicatedUsername", true);
			model.addAttribute("emptyMail",false);
			return "register";
		}	catch(UsernameNotFoundException userNotFound) {
			
			if(mail.equals("")) {
				model.addAttribute("duplicatedUsername", false);
				model.addAttribute("emptyMail",true);
				return "register";
			}
			user = userService.saveNewUser(username, pwd, mail, image);	
			model.addAttribute("user", user);
			return "registered";
		}		
	}
	
	@PostMapping("/modified_user/{username}")
	public String modifyUser(Model model, @RequestParam String pwd, @RequestParam String mail, 
			@RequestParam MultipartFile image, @PathVariable String username) throws IOException {
			userService.modifyUser(username, pwd, mail, image);
			return "redirect:/";
	}
	
	@GetMapping("/users")
	public String allUsers(Model model){		
		model.addAttribute("users", userService.getAllUsers());		
		return "users";	
	}
	
	@PostMapping("/{id}/delete")
	public String deleteSelectedUser(Model model, @PathVariable long id) {		
		userService.deleteUser(id);		
		return "redirect:/users";
	}
	
	@GetMapping("/modify/{username}")
	public String modifySelectedUser(Model model, @PathVariable String username) {
		User user = userService.findUserByUsername(username);		
		model.addAttribute("user", user);		
		return "modify";
	}
	
	
	/**
	 * Descarga la imagen de usuario si la tiene. En caso contrario, devuelve una imagen por 
	 * defecto
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws MalformedURLException
	 */
	@GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadUserImage(@PathVariable long id) throws SQLException, MalformedURLException{
		User user = userService.findUserById(id);
		
		if(user.getImage()) {
			Resource file = new InputStreamResource(user.getImageFile().getBinaryStream());
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(user.getImageFile().length())
					.body(file);
		}else {
			Path default_path = IMAGE_FOLDER.resolve("default.jpg");
			Resource file = new UrlResource(default_path.toUri());
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(file);
		}		
	}
	
	/**
	 * Devuelve el recurso gráfico del logo utilizado en la cabecera
	 * @param model
	 * @return
	 * @throws MalformedURLException
	 */
	@GetMapping("/image/rotom")
	public ResponseEntity<Object> downloadRotomImage (Model model) throws MalformedURLException{		
		Path image_path = IMAGE_FOLDER.resolve("rotom.png");
		Resource image = new UrlResource(image_path.toUri());
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "rotom/png").body(image);
	}
	
}

