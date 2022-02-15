package es.trident.rotomnet.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.trident.rotomnet.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Path IMAGE_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	
	
	@GetMapping("/register")
	public String login(Model model){			
		return "register";
	}
	
	@PostMapping("/registered")
	public String register(Model model, @RequestParam String username, @RequestParam String pwd) {
		
		//Access the UserService and save the new user into the database
		userService.saveNewUser(username, pwd);
		model.addAttribute("users", userService.getAllUsers());
		return "registered";
	}
	
	
	@GetMapping("/image/rotom")
	public ResponseEntity<Object> downloadImage (Model model) throws MalformedURLException{
		
		Path image_path = IMAGE_FOLDER.resolve("rotom.png");
		Resource image = new UrlResource(image_path.toUri());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "rotom/png").body(image);
	}
}
