package com.skilldistillery.gearsilo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class UserController {

	@Autowired
	private UserService userSvc;

	@GetMapping("users")
	public List<User> findAll(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		;

		List<User> users = userSvc.findAll(principal.getName());

		if (users == null) {
			resp.setStatus(404);
		}
		if (users.size() == 0) {
			resp.setStatus(204);
		}

		return users;
	}

	@PutMapping("users/{id}")
	public User replaceExistingUser(@RequestBody User user, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			user = userSvc.updateUser(id, user);
			if (user == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}
		return user;
	}

	@GetMapping("users/{username}")
	public User replaceExistingUser(@PathVariable String username, HttpServletRequest req, Principal principal,
			HttpServletResponse resp) {
System.out.println(username);
		User user = null;

		try {
			user = userSvc.findUserByUsername(username);
			if (user == null) {
				resp.setStatus(404);
				return null;
			}
			System.out.println(user);
			resp.setStatus(202);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(user.getId());
//			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return user;
	}
}
