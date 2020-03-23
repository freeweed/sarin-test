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
@RequestMapping(path="/branch") // This means URL's start with /demo (after Application path)
public class BranchController {

	@Autowired
	private BranchRepository branchRepository;


	@CrossOrigin("*")
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewBranch (@RequestBody String json) {
		Branch branch = this.factory(json, new Branch());
		branchRepository.save(branch);
		return "Saved";
	}

	@CrossOrigin("*")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Branch> getAllBranch() {
		// This returns a JSON or XML with the users
		return branchRepository.findAll();
	}

	@CrossOrigin("*")
	@PatchMapping(path="/edit")
	public @ResponseBody String editBranch(@RequestParam("id") Integer branch_id, @RequestBody String json) {
		Branch branch = branchRepository.findById(branch_id).get();
		branch = this.factory(json, branch);
		branchRepository.save(branch);
		return "Success";
	}

	@CrossOrigin("*")
	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteBranch(@RequestParam("id") Integer branch_id){
		Branch branch = branchRepository.findById(branch_id).get();
		branchRepository.delete(branch);
		return "Success";
	}

	private Branch factory(String json, Branch branch){
		JSONObject jsonObj = new JSONObject(json);
		branch.setName(jsonObj.getString("branch_name"));
		return branch;
	}
}
