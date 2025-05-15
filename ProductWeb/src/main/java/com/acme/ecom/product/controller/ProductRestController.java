package com.hong.msv.controller;

import com.hong.msv.model.Product;

import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
//import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@CrossOrigin
@RestController
public class ProductRestController{

	@Value("${acpm.PRODUCT_SERVICE_URL}")
	private String PRODUCT_SERVICE_URL;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    //------------------- Retreive all Products --------------------------------------------------------
    //@ApiOperation(value="View a list of all products", response = Product.class, responseContainer = "List")
	@RequestMapping(value = "/productsweb", method = RequestMethod.GET ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getAllProducts() {

        LOGGER.info("Start");

        ParameterizedTypeReference<List<Product>> responseTypeRef = new ParameterizedTypeReference<List<Product>>() {};
		ResponseEntity<List<Product>> entity = restTemplate.exchange(PRODUCT_SERVICE_URL, HttpMethod.GET,
				(HttpEntity<Product>) null, responseTypeRef);
		List<Product> productList = entity.getBody();

		LOGGER.info("Ending...");
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    //------------------- Retreive a Product --------------------------------------------------------
    //@ApiOperation(value="Find a product info by its id", response = Product.class)
    @RequestMapping(value = "/productsweb/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        
        LOGGER.info("Start");
		String uri = PRODUCT_SERVICE_URL + "/" + productId;
		Product product = restTemplate.getForObject(uri, Product.class);
		LOGGER.info("Ending...");
        return new ResponseEntity<Product>(product, HttpStatus.OK);

    }

    //------------------- Create a Product --------------------------------------------------------
    //@ApiOperation(value="Save new product", response = Product.class)
    @RequestMapping(value = "/productsweb", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        
        LOGGER.info("Start");
		Product productNew = restTemplate.postForObject( PRODUCT_SERVICE_URL, product, Product.class);
		LOGGER.info("Ending...");
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //------------------- Delete a Product --------------------------------------------------------
    //@ApiOperation(value="Delete product with specific id", response = String.class)
    @RequestMapping(value = "/productsweb/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId")String productId) {
    	 
        LOGGER.info("Start");
		restTemplate.delete(PRODUCT_SERVICE_URL + "/" + productId);
		LOGGER.info("Ending...");
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

    //------------------- Update a Product --------------------------------------------------------
    //@ApiOperation(value="Update a product with specific id", response = Product.class)
    @RequestMapping(value = "/productsweb/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@PathVariable("productId")String productId, @RequestBody Product product) {
    	 
        LOGGER.info("Start");
		String uri = PRODUCT_SERVICE_URL + "/" + productId;		
		LOGGER.debug("Attempting to update Product with ID : {} ...", productId);
		restTemplate.put(uri, product, Product.class);
		LOGGER.debug("Product with ID : {} updated. Retreiving updated product back...", productId);
		Product updatedProduct = restTemplate.getForObject(uri, Product.class);
		LOGGER.info("Ending...");
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}

}
