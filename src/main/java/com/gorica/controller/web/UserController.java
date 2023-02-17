package com.gorica.controller.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gorica.SessionData;
import com.gorica.dao.model.Role;
import com.gorica.dao.model.User;
import com.gorica.service.UserDetailsService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private SessionData sessionobj;

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/createUser")
	public String createUser(ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				User user = new User();
				List<Role> rolesList = userService.getAllRoles();
				model.put("rolesList", rolesList);
				model.put("user", user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "createUser";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				
				String userName= user.getUserName();
				User existUser = userService.getUserByUserName(userName);
				
				if(existUser!=null) {
					user.setErrorMsg(" is already Exist");
					map.put("user", user);
					return "createUser";
				}
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setCreatedDate(new Date());
				System.out.println("user{}=" + user);
				userService.saveUser(user);
				return "redirect:/user/allUsers";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("editUser/{id}")
	public String editUser(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				User user = userService.getUserById(id);
				List<Role> rolesList = userService.getAllRoles();
				model.put("rolesList", rolesList);
				model.put("user", user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "editUserPage";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute User user, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				user.setCreatedDate(new Date());
				userService.updateUser(user);
				return "redirect:/user/allUsers";

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/allUsers")
	public String allUsers(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<User> usersList = userService.getAllUsers();
				map.put("usersList", usersList);
				map.put("title", "User List");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "usersList";
		} else
			return "redirect:/";
	}

	@GetMapping("/activeUsers")
	public String activeUsers(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<User> usersList = userService.getActiveUsers();
				map.put("usersList", usersList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "activeUsers";
		} else
			return "redirect:/";
	}

	@GetMapping("/inActiveUsers")
	public String inActiveUsers(ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				List<User> usersList = userService.getInActiveUsers();
				map.put("usersList", usersList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "inActiveUsers";
		} else
			return "redirect:/";
	}

	@GetMapping("deactivateUser/{id}")
	public String deactivateUser(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				userService.deActivateUserById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/user/activeUsers";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("activateUser/{id}")
	public String activateUser(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				userService.activateUserById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/user/inActiveUsers";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				userService.deleteUserById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/user/allUsers";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("changePassword/{id}")
	public String changePassword(@PathVariable Long id, ModelMap model) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {
				User user = userService.getUserById(id);
				model.put("user", user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "changePassword";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/updatePassword")
	public String updatePassword(@ModelAttribute User user, BindingResult result, ModelMap map) {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {
			try {

				User existedUser = userService.getUserById(user.getId());
				existedUser.setPassword(passwordEncoder.encode(user.getPassword()));
				userService.updateUser(existedUser);

				return "redirect:/user/passwordSuccess";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/";
			}
		} else
			return "redirect:/";
	}

	@GetMapping("/passwordSuccess")
	public String passwordSuccess() {
		if (sessionobj != null && sessionobj.getIsValidLogin()) {

			return "passwordSuccess";
		} else {
			return "redirect:/";
		}
	}

}
