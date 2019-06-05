package external.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import static external.ApiConfig.*;

import external.dto.AuthRequestDto;
import external.dto.AuthResponseDto;
import external.dto.ErrorResponseDto;
import external.exception.LoginException;
import external.exception.RegistrationException;
import external.exception.StoreException;
import external.store.StoreSender;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class AuthSenderImpl implements AuthSender {
    private final ObjectMapper mapper;
    private final StoreSender store;
    private final RestTemplate client;

    public AuthSenderImpl(StoreSender store, RestTemplate client, ObjectMapper mapper) {
        this.store = store;
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public boolean login(String email, String password) throws LoginException {
        AuthRequestDto requestDto = new AuthRequestDto(email, password);
        try {
            try {
                AuthResponseDto responseDto = client.postForObject(BASE_URL + LOGIN_PATH, requestDto, AuthResponseDto.class);
                if (responseDto != null) {
                    store.saveToken(responseDto.getToken());
                    return true;
                }
                throw new LoginException("Something went wrong! token is null");
            } catch (RestClientResponseException e) {
                String error = e.getResponseBodyAsString();
                ErrorResponseDto errorResponseDto = mapper.readValue(error, ErrorResponseDto.class);
                throw new LoginException(errorResponseDto.getMessage());
            } catch (StoreException e) {
                throw new LoginException("Save token error!", e);
            }
        } catch (IOException ex) {
            throw new LoginException("Parsing error", ex);
        }
    }

    @Override
    public boolean registration(String email, String password) throws RegistrationException {
        AuthRequestDto requestDto = new AuthRequestDto(email, password);

        try {
            try {
                AuthResponseDto responseDto = client.postForObject(BASE_URL + REG_PATH, requestDto, AuthResponseDto.class);
                if (responseDto != null) {
                    store.saveToken(responseDto.getToken());
                    return true;
                }
                throw new RegistrationException("Something went wrong! token is null");
            } catch (RestClientResponseException e) {
                String error = e.getResponseBodyAsString();
                ErrorResponseDto errorResponseDto = mapper.readValue(error, ErrorResponseDto.class);
                throw new RegistrationException(errorResponseDto.getMessage());

            } catch (StoreException e) {
                throw new LoginException("Save token error!", e);
            }
        } catch (IOException ex) {
            throw new RegistrationException("Parsing error", ex);
        }
    }
}
