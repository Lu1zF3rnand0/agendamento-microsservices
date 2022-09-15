package br.com.luiz.agendamentoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class AgendamentoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendamentoServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

}
