package com.hstreb;

import java.util.Date;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Executor executor;

	@RequestMapping(value = "/hello")
	public String  hello(@RequestParam String name) throws InterruptedException {
		logger.info("hello {}", name);
		iniciaApp(name);
		return "Running " + new Date();
	}
	
	private void iniciaApp(String name) throws InterruptedException {
		logger.info("start call in background");
		executor.execute(() -> {
			try {
				new HelloService().run(name);
			} catch (InterruptedException e) {
				logger.error("Error to call service", e);
			}
		});
		logger.info("end call in background");
	}
	
}
