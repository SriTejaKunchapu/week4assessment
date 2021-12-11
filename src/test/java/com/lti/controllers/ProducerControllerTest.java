package com.lti.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.lti.model.Customer;
import com.lti.repository.CustomerRepository;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProducerController.class)
class ProducerControllerTest {

	@Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerRepository customerRepository;
	
	
	@Test
	 void testGetCustomers() throws Exception{
        String URI = "/bank/customers";
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("Suresh");
        customer1.setAge(20);
        customer1.setAddress("Pune");
        customer1.setTypeofAccount("savings");

        Customer customer2 = new Customer();
        customer2.setId(3);
        customer2.setName("ramesh");
        customer2.setAge(20);
        customer2.setAddress("Hydrebad");
        customer2.setTypeofAccount("salary");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        String jsonInput = this.converttoJson(customerList);

        Mockito.when(customerRepository.findAll()).thenReturn(customerList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	
	
	@Test
     void testPostCustomer() throws Exception{
		String URI = "/bank/customers";
        Customer customer = new Customer();
        customer.setId(2);
        customer.setName("ramu");
        customer.setAge(50);
        customer.setAddress("Hydrebad");
        customer.setTypeofAccount("current");
        String jsonInput = this.converttoJson(customer);

        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }
	
	
	@Test
    void testDeleteCust() throws Exception{
		String URI = "/bank/customers/{id}";
       Customer customer = new Customer();
       customer.setId(2);
       customer.setName("ramu");
       customer.setAge(50);
       customer.setAddress("Hydrebad");
       customer.setTypeofAccount("current");
      // Mockito.when(customerRepository.findById(2)).thenReturn(customer);
      // Mockito.when(customerRepository.delete(Mockito.any())).thenReturn("deleted");
       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 2).accept(MediaType.
       		APPLICATION_JSON)).andReturn();
       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
      Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

   }

	
   @Test
    void testUpdateCustomer() throws Exception{

       String URI = "/bank/customers/{id}";
       Customer customer2 = new Customer();
       customer2.setId(3);
       customer2.setName("krish");
       customer2.setAge(20);
       customer2.setAddress("bangalore");
       customer2.setTypeofAccount("salary");
       String jsonInput = this.converttoJson(customer2);
 //      Mockito.when(customerRepository.findById(3)).thenReturn("find");
       Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer2);
       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,3).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
               .andReturn();
       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
       String jsonOutput = mockHttpServletResponse.getContentAsString();
       assertThat(jsonInput).isEqualTo(jsonOutput);
   }

    @SuppressWarnings("unchecked")
	@Test
	 void testGetDataFallBack() throws Exception{
        String URI = "/bank/customers";
        Customer customer = new Customer();
        customer.setName("Suresh");
        customer.setAge(20);
        customer.setAddress("Pune");
        customer.setTypeofAccount("savings");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        String jsonInput = this.converttoJson(customerList);

        Mockito.when(customerRepository.findAll()).thenReturn(customerList );
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
 
        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
        
	    private String converttoJson(Object customer) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(customer);
    }

}