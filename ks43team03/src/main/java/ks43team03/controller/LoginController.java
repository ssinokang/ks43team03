package ks43team03.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks43team03.dto.User;
import ks43team03.service.UserService;

@Controller
public class LoginController {
	
	private final UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	/**
	 * defaultValue = "1" request 데이터 타입 은 string 이니까 int 쓰면 자동으로 래퍼클래스 씌워서 타입 바꿔줌
	 * @param currentPage
	 * @return
	 */
	
	@GetMapping("/loginHistory")
	public String loginHistory(@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage
							  ,Model model) {
		
		Map<String, Object> resultMap = userService.getLoginHistory(currentPage);
		resultMap.get("lastPage");
		
		model.addAttribute("resultMap", 		resultMap);
		model.addAttribute("currentPage", 		currentPage);
		model.addAttribute("loginHistoryList", 	resultMap.get("loginHistoryList"));
		model.addAttribute("lastPage", 			resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 		resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 		resultMap.get("endPageNum"));
		System.out.println(model + "model!!!!!!!!!!");
		return "login/loginHistory";
	}
	 
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	public String login( @RequestParam(name = "userId", required = false) String userId
						,@RequestParam(name = "userPw", required = false) String userPw
						,HttpSession session) {
		
		User user = userService.getUserInfoById(userId);
		
		if(user != null) {
			String userPwCheck = user.getUserPw();
			if(user != null && userPwCheck.equals(userPw)) {
				session.setAttribute("SID"		, userId);
				session.setAttribute("SLEVEL"	, user.getUserLevel());
				session.setAttribute("SNAME"	, user.getUserName());
				return "redirect:/";
			}
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login/login";
	}
}
