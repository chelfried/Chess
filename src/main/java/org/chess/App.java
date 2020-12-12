package org.chess;

import org.chess.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class,args);
    }

}

