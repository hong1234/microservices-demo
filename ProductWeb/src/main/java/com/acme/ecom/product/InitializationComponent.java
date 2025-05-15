package com.hong.msv;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InitializationComponent {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
    private void init(){

		logger.info("Start");

		logger.debug("Doing Nothing...");

		logger.info("End");
    }
}
