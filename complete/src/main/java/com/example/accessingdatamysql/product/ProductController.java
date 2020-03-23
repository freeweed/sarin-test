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
@RequestMapping(path="/product") // This means URL's start with /demo (after Application path)
public class ProductController {

	@Autowired
	private ProductRepository ProductRepository;


	@CrossOrigin("*")
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewProduct (@RequestBody String json) {
		Product pro = this.factory(json, new Product());
		ProductRepository.save(pro);
		return "Saved";
	}

	@CrossOrigin("*")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Product> getAllProduct() {
		// This returns a JSON or XML with the users
		return ProductRepository.findAll();
	}

	@CrossOrigin("*")
	@PatchMapping(path="/edit")
	public @ResponseBody String editProduct(@RequestParam("id") Integer Product_id, @RequestBody String json) {
		Product product = ProductRepository.findById(Product_id).get();
		product = this.factory(json, product);
		ProductRepository.save(product);
		return "Success";
	}

	@CrossOrigin("*")
	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteProduct(@RequestParam("id") Integer Product_id){
		Product product = ProductRepository.findById(Product_id).get();
		ProductRepository.delete(product);
		return "Success";
	}

	private Product factory(String json, Product product){
		JSONObject jsonObj = new JSONObject(json);
		product.setName(jsonObj.getString("product_name"));
		product.setDetail(jsonObj.getString("product_detail"));
		return product;
	}
}
