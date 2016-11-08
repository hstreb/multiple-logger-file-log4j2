package com.hstreb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run(String name) throws InterruptedException {
        MDC.put("FILENAME", name);
        logger.info("service process started: {}", name);
        Thread t = new Thread(() -> {
            try {
                new LoopService().run(name);
            } catch (InterruptedException e) {
                logger.error("Error to call service", e);
            }
        });
        t.start();
        logger.info("service process ended: {}", name);
        MDC.remove("FILENAME");
    }
}
