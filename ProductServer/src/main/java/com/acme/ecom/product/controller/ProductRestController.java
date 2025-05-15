package com.hong.msv.controller;

import com.hong.msv.repository.ProductRepository;
import com.hong.msv.repository.ProductCategoryRepository;
import com.hong.msv.model.Product;
import com.hong.msv.model.ProductOR;
import com.hong.msv.model.ProductCategory;
import com.hong.msv.model.ProductCategoryOR;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
//import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ProductRestController {

	@Autowired
	private ProductRepository productRepository;

    @Autowired
	private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    //------------------- Retreive a Product --------------------------------------------------------
    //@ApiOperation(value="Find a product info by its id", response = Product.class)
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {

    	LOGGER.info("Start");
    	LOGGER.debug("Fetching Product with id: {}", id);

        ProductOR productOR = productRepository.findById(Long.parseLong(id)).get();
        if (productOR == null) {
    		LOGGER.debug("Product with id: {} not found", id);
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    	LOGGER.info("Ending");
        return new ResponseEntity<Product>(productMapper.entityToApi(productOR), HttpStatus.OK);
    }

    //------------------- Create a Product --------------------------------------------------------
    //@ApiOperation(value="Save new product", response = Product.class)
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Product> postProduct(@RequestBody Product product) {

    	LOGGER.info("Start");
    	LOGGER.debug("Creating Product with code: {}", product.getCode());

        List<ProductOR> productORs = productRepository.findByCode(product.getCode());
        if (productORs.size() > 0) {
    		LOGGER.debug("A Product with code {} already exist", product.getCode());
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }

        ProductOR newProductOR = productRepository.save(productMapper.apiToEntity(product));
    	LOGGER.info("Ending");
        return new ResponseEntity<Product>(productMapper.entityToApi(newProductOR), HttpStatus.OK);
    }

    //------------------- Update a Product --------------------------------------------------------
    //@ApiOperation(value="Update a product with specific id", response = Product.class)
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {

    	LOGGER.info("Start");
    	LOGGER.debug("Updating Product with id: {}", id);

        ProductOR currentProductOR = productRepository.findById(Long.parseLong(id)).get();

        if (currentProductOR == null) {
    		LOGGER.debug("Product with id: {} not found", id);
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        currentProductOR = productMapper.apiToEntity(product);
        ProductOR newProductOR = productRepository.save(currentProductOR);
    	LOGGER.info("Ending");
        return new ResponseEntity<Product>(productMapper.entityToApi(newProductOR), HttpStatus.OK);
    }

    //------------------- Retreive all Products --------------------------------------------------------
    //@ApiOperation(value="View a list of all products", response = Product.class, responseContainer = "List")
    @RequestMapping(value = "/products", method = RequestMethod.GET ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getAllProducts() {

    	LOGGER.info("Start");
        //List<Product> products = productRepository.findAll();
        Iterable<ProductOR> iterable = productRepository.findAll();

        List<Product> list = new ArrayList<Product> ();
        for(ProductOR productOR:iterable){
            list.add(productMapper.entityToApi(productOR));
        }
        if(list.size() == 0){
    		LOGGER.debug("No products retreived from repository");
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }       
        list.forEach(item->LOGGER.debug(item.toString()));
    	LOGGER.info("Ending");
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    //------------------- Retreive Products by Category --------------------------------------------------------
    @RequestMapping(value = "/productsbycat/{productcategoryname}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("productcategoryname") String productCategoryName) {

    	LOGGER.info("Start");
        List<ProductOR> productORs = productRepository.findByCategory(productCategoryName);

        if(productORs.isEmpty()){
    		LOGGER.debug("No products retreived from repository for category {}", productCategoryName);
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        
        List<Product> list = new ArrayList<Product> ();
        for(ProductOR productOR:productORs){
            list.add(productMapper.entityToApi(productOR));
        }
        
        list.forEach(item->LOGGER.debug(item.toString()));
    	LOGGER.info("Ending");
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    //------------------- Retreive Category of a Product --------------------------------------------------------
    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCategory> getCategory(@PathVariable("category") String category) {

    	LOGGER.info("Start");
    	LOGGER.debug("Fetching Category of product with category name: {}", category);

        List<ProductCategoryOR> productCategoryORs = productCategoryRepository.findByName(category);
         if(productCategoryORs.isEmpty()){
    		LOGGER.debug("Category of product with category name: {} not found", category);
            return new ResponseEntity<ProductCategory>(HttpStatus.NOT_FOUND);
        }
        ProductCategoryOR firstProductCategoryOR = productCategoryORs.iterator().next();
    	LOGGER.info("Ending");
        return new ResponseEntity<ProductCategory>(productCategoryMapper.entityToApi(firstProductCategoryOR), HttpStatus.OK);
    }

    //------------------- Delete a Product --------------------------------------------------------
    //@ApiOperation(value="Delete product with specific id", response = String.class)
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {

    	LOGGER.info("Start");
    	LOGGER.debug("Fetching & Deleting Product with id: {}", id);
        ProductOR productOR = productRepository.findById(Long.parseLong(id)).get();
        if (productOR == null) {
    		LOGGER.debug("Product with id: {} not found, hence not deleted", id);
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        productRepository.delete(productOR);
    	LOGGER.info("Ending");
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    //------------------- Delete All Products --------------------------------------------------------
    //@ApiOperation(value="Delete all products", response = String.class)
    @RequestMapping(value = "/products", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteAllProducts() {

    	LOGGER.info("Start");
        long count = productRepository.count();
        LOGGER.debug("Deleting {} products", count);
        productRepository.deleteAll();
    	LOGGER.info("Ending");
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

}
