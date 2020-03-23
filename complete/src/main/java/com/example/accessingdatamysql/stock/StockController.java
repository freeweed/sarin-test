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
@RequestMapping(path="/stock") // This means URL's start with /demo (after Application path)
public class StockController {

	@Autowired
	private StockRepository stockRepository;


	@CrossOrigin("*")
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewStock (@RequestBody String json) {
		Stock stock = this.factory(json, new Stock());
		stockRepository.save(stock);
		return "Saved";
    }
    


	@CrossOrigin("*")
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Stock> getAllStock() {
		// This returns a JSON or XML with the Stocks
		return stockRepository.findAll();
	}

	@CrossOrigin("*")
	@PatchMapping(path="/edit")
	public @ResponseBody String editStock(@RequestParam("id") Integer stock_id, @RequestBody String json) {
		Stock stock = stockRepository.findById(stock_id).get();
		stock = this.factory(json, stock);
		stockRepository.save(stock);
		return "Success";
	}

	@CrossOrigin("*")
	@DeleteMapping(path="/delete")
	public @ResponseBody String deleteStock(@RequestParam("id") Integer stock_id){
		Stock stock = stockRepository.findById(stock_id).get();
		stockRepository.delete(stock);
		return "Success";
	}

	private Stock factory(String json, Stock stock){
		JSONObject jsonObj = new JSONObject(json);
        // stock.setName(jsonObj.getString("stock_name"));
        stock.setPbId(jsonObj.getInt("sh_pb_id"));
        stock.setAmount(jsonObj.getInt("sh_amount"));
        stock.setUserId(jsonObj.getInt("sh_user_id"));
        stock.setOperator(0);
		return stock;
	}
}
