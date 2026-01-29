package com.example.challengeLiteratura;

import com.example.challengeLiteratura.principal.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {
    @Autowired Main mainPrincipal;
	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraturaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
       mainPrincipal.ConsultarLibroApi();
    }

    @Bean
    public static RestClient restClient() {
        return RestClient.create();
    }
}
