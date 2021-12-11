package com.lti.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.exception.ResourceNotFoundException;
import com.lti.model.Customer;
import com.lti.repository.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bank")
public class ProducerController {

	 private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);
		
	@Autowired
	CustomerRepository custrep;
	
	 @PostMapping("/customers")
	 public Customer postCustomer( @RequestBody Customer customer) {
	        return   custrep.save(customer);
	      
	    }
	 
	 @GetMapping("/customers")
	 @HystrixCommand(fallbackMethod = "getDataFallBack")
	 public List<Customer> getCustomers(){
		
		 return  custrep.findAll();
	 }
	 
	 @SuppressWarnings("unchecked")
	public List<Customer> getDataFallBack() {
			
		 Customer cust = new Customer();
			cust.setName("fallback-name");
			cust.setAge(20);
			cust.setTypeofAccount("fallback-savings");

			return (List<Customer>) cust; 
		}
	 
	 @DeleteMapping("/customers/{id}")
	 public String deleteCust(@PathVariable(value = "id") int custid) throws ResourceNotFoundException {
		 Customer cust=custrep.findById(custid)
					.orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + custid));
			
		 custrep.delete(cust);
		 return "deleted" + custid;
	 }
	  
	 @PutMapping("/customers/{id}")
		public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") int custid,
				 @RequestBody Customer custDetails) throws ResourceNotFoundException  {
		
			logger.info("****************Controller Class :update customers****************");
			Customer cust = custrep.findById(custid)
					.orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + custid));
			cust.setName(custDetails.getName());
			cust.setAddress(custDetails.getAddress());
			cust.setAge(custDetails.getAge());
			cust.setTypeofAccount(custDetails.getTypeofAccount());
			final Customer updatedCustomer = custrep.save(cust);

			return ResponseEntity.ok(updatedCustomer);
		}
}
