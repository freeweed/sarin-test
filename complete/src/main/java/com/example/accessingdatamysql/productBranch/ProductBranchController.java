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
@RequestMapping(path="/product_branch") // This means URL's start with /demo (after Application path)
public class ProductBranchController {

	@Autowired
	private ProductBranchRepository productBranchRepository;


	@CrossOrigin("*")
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewProductBranch (@RequestBody String json) {
		ProductBranch productBranch = this.factory(json, new ProductBranch());
		productBranchRepository.save(productBranch);
		return "Saved";
	}

	@CrossOrigin("*")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<ProductBranch> getAllProductBranch() {
		// This returns a JSON or XML with the users
		return productBranchRepository.findAll();
	}

	@CrossOrigin("*")
	@PatchMapping(path="/edit")
	public @ResponseBody String editProduct(@RequestParam("id") Integer pb_id, @RequestBody String json) {
		ProductBranch productBranch = productBranchRepository.findById(pb_id).get();
		productBranch = this.factory(json, productBranch);
		productBranchRepository.save(productBranch);
		return "Success";
	}

	@CrossOrigin("*")
	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteProduct(@RequestParam("id") Integer pb_id){
		ProductBranch productBranch = productBranchRepository.findById(pb_id).get();
		productBranchRepository.delete(productBranch);
		return "Success";
	}

	private ProductBranch factory(String json, ProductBranch productBranch){
		JSONObject jsonObj = new JSONObject(json);
		productBranch.setPbBranchId(jsonObj.getInt("pb_branch_id"));
		productBranch.setPbProductId(jsonObj.getInt("pb_product_id"));
		productBranch.setPbStock(jsonObj.getInt("pb_stock"));
		return productBranch;
	}
}
