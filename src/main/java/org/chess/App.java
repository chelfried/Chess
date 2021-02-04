package org.chess;

import org.chess.config.AppConfig;
import org.chess.controller.SSEController;
import org.chess.core.GameBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@EnableAutoConfiguration
public class App {

    public static final String url = url();

    public static String url(){
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            return in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AppConfig.class,args);
    }

}

