package ca.mcgill.ecse321.grocerystoresystem;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class GroceryStoreSystemBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreSystemBackendApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting(){
		return "Hello world!";
	} 

	

}
