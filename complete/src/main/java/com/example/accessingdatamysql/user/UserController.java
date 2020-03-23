package com.example.accessingdatamysql;

import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {

	@Autowired
	private UserRepository userRepository;


	@CrossOrigin("*")
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestBody String json) {
		User user = this.factory(json, new User());
		userRepository.save(user);
		return "Saved";
	}

	@CrossOrigin("*")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUser() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@CrossOrigin("*")
	@PatchMapping(path="/edit")
	public @ResponseBody String editUser(@RequestParam("id") Integer user_id, @RequestBody String json) {
		User user = userRepository.findById(user_id).get();
		user = this.factory(json, user);
		userRepository.save(user);
		return "Success";
	}

	@CrossOrigin("*")
	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteUser(@RequestParam("id") Integer user_id){
		User user = userRepository.findById(user_id).get();
		userRepository.delete(user);
		return "Success";
	}

	private User factory(String json, User user){
		JSONObject jsonObj = new JSONObject(json);
		user.setName(jsonObj.getString("user_name"));
		return user;
	}
}
