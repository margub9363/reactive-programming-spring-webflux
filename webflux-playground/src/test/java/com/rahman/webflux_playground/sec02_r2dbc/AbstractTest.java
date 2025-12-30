package com.rahman.webflux_playground.sec02_r2dbc;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"sec=sec02_r2dbc",
        "logging.level.org.springframework.r2dbc=DEBUG"})
public abstract class AbstractTest {
}
