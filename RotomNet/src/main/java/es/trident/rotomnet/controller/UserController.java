package es.trident.rotomnet.controller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import javax.servlet.http.HttpSession;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	private static final Path IMAGE_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
	
	@GetMapping("/login")
	public String login(Model model, HttpSession s){			
		return "header";
	}
	
	@GetMapping("/image/rotom")
	public ResponseEntity<Object> downloadImage (Model model) throws MalformedURLException{
		
		Path image_path = IMAGE_FOLDER.resolve("rotom.png");
		Resource image = new UrlResource(image_path.toUri());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "rotom/png").body(image);
	}
}
