package com.rodrigo.votingagenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VotingAgendaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingAgendaApplication.class, args);
    }

}
