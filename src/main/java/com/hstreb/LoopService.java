package com.hstreb;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoopService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run(String name) throws InterruptedException {
        MDC.put("FILENAME", name);
        Instant start = Instant.now();
        logger.info("start process {}", name);
        IntStream.range(0, 1000000)
                .filter(i -> i%1000 == 0)
                .forEach(i -> {
                    try {
                        Thread.sleep(3l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("in process [{}] {}", i, name);
                });
        Instant end = Instant.now();
        logger.info("end process {} in {}ms", name, Duration.between(start, end).toMillis());
    }
}
