package com.lumen.social;

import org.springframework.boot.SpringApplication;

public class TestLumenApplication {

    public static void main(String[] args) {
        SpringApplication.from(LumenApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
