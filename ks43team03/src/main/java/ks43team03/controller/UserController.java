package ks43team03.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks43team03.dto.User;
import ks43team03.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final UserService userService; 
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		
		List<User> userList = userService.getUserList();
		log.info("userList : {}", userList);
		
		model.addAttribute("userList", userList);
		
		return "user/userList";
	}
}
