package com.hong.msv;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hong.msv.model.ProductOR;
import com.hong.msv.model.ProductCategoryOR;
import com.hong.msv.repository.ProductRepository;
import com.hong.msv.repository.ProductCategoryRepository;

@Component
public class InitializationComponent {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
    private void init(){

		logger.info("Start...");

		//Deleting all existing data on start.....

		logger.debug("Deleting all existing data on start...");
		productCategoryRepository.deleteAll();
		productRepository.deleteAll();

    	logger.debug("Creating initial data on start...");

		ProductCategoryOR productCategoryOR = new ProductCategoryOR();
    	productCategoryOR.setName("Mobile");
    	productCategoryOR.setDescription("Mobile phones");
    	productCategoryOR.setTitle("Mobiles and Tablet");
    	productCategoryOR.setImgUrl("mobile.jpg");
    	productCategoryRepository.save(productCategoryOR);

		productCategoryOR = new ProductCategoryOR();
    	productCategoryOR.setName("Tablet");
    	productCategoryOR.setDescription("Tablet pads");
    	productCategoryOR.setTitle("Tablet like pads");
    	productCategoryOR.setImgUrl("tablet.jpg");
    	productCategoryRepository.save(productCategoryOR);

		ProductOR productOR = new ProductOR();
		productOR.setName("Samsung Mobile");
    	productOR.setCode("SAMSUNG-TRIOS");
    	productOR.setTitle("Samsung Trios 12 inch , black , 12 px");
    	productOR.setPrice(12000.00D);
		productOR.setCategory("Mobile");
    	productRepository.save(productOR);

    	productOR = new ProductOR();
		productOR.setName("Apple Tablet");
    	productOR.setCode("APPLE-PAD");
    	productOR.setTitle("Apple 11 inch , grey, 140px");
    	productOR.setPrice(19000.00D);
		productOR.setCategory("Tablet");
    	productRepository.save(productOR);

		logger.info("End");
    }
}
