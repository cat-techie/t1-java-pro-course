package ru.t1.pmorozov.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.t1.pmorozov.exceptions.IntegrationException;
import ru.t1.pmorozov.exceptions.PaymentException;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            throw new PaymentException("Got response 4xx from executor", HttpStatus.BAD_REQUEST);
        } else if (response.getStatusCode().is5xxServerError()) {
            throw new IntegrationException("Got response 5xx from executor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
