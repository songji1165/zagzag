package com.jtrio.zagzag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ZagzagApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZagzagApplication.class, args);
    }

}
