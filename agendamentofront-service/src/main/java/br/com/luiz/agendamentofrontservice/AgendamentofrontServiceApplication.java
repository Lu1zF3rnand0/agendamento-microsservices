package br.com.luiz.agendamentofrontservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendamentofrontServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendamentofrontServiceApplication.class, args);
    }

}
