package com.example.accessingdatamysql;

import java.util.Optional;

import com.example.accessingdatamysql.Branch;
import com.example.accessingdatamysql.ProductBranch;
import com.example.accessingdatamysql.Product;
import com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.BranchRepository;
import com.example.accessingdatamysql.ProductBranchRepository;
import com.example.accessingdatamysql.ProductRepository;
import com.example.accessingdatamysql.UserRepository;

import org.json.JSONObject;
import org.json.JSONArray;
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
@RequestMapping(path="/report") // This means URL's start with /demo (after Application path)
public class ReportController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductBranchRepository productBranchRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private UserRepository userRepository;


    
    @CrossOrigin("*")
	@GetMapping(path="/in_stock")
	public @ResponseBody String inStock() {
        Iterable<ProductBranch> pb = this.productBranchRepository.findAll();
        long size = pb.spliterator().getExactSizeIfKnown();
        String result = "";
        try{    
            JSONArray jArr = new JSONArray(); 
            for(ProductBranch row : pb) {
                int id = row.getPbId();
                int product_id = row.getPbProductId();
                int branch_id = row.getPbBranchId();
                int pb_stock = row.getPbStock();
                
                Branch branch = this.branchRepository.findById(branch_id).get();   
                String branch_name = branch.getName();
                
                Product product = this.productRepository.findById(product_id).get();
                String product_name = product.getName();
                Integer product_price = product.getProductPrice();
                
                
                JSONObject obj = new JSONObject();
                obj.put("pb_id",id);
                obj.put("branch_id",branch_id);
                obj.put("branch_name",branch_name);
                obj.put("product_id",product_id);
                obj.put("product_name",product_name);
                obj.put("pb_stock",pb_stock);
                obj.put("product_price",product_price);
                jArr.put(obj);
            }    
            result = jArr.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
	}
}