package com.rodrigo.votingagenda.application.service.vote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigo.votingagenda.common.exception.custom.ExternalServiceException;
import com.rodrigo.votingagenda.common.exception.custom.InvalidVoteException;
import com.rodrigo.votingagenda.contract.CpfValidationResponse;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Service
@Builder
public class ValidateCpfService {

    public boolean canVote(String cpf) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://user-info.herokuapp.com/users/" + cpf))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new InvalidVoteException("Falha ao consultar CPF: " + response.body());
            }
            CpfValidationResponse responseData = new ObjectMapper().readValue(response.body(), CpfValidationResponse.class);
            return Objects.equals(responseData.status(), "ABLE_TO_VOTE");
        } catch (IOException | InterruptedException e) {
            throw new ExternalServiceException("Falha ao acessar serviço: " + e.getMessage());
        }
    }
}
