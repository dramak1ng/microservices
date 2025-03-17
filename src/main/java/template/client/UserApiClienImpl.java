package template.client;

import template.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class UserApiClienImpl implements UserApiClient {

    @Value("${api.base-url}")
    private  String baseUrl;
    private final RestTemplate restTemplate;
    private String sessionId;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public List<User> getAllUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(baseUrl, User[].class);
        sessionId = extrackSessionId(response);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public String createUser(User user) {
        HttpEntity<User> request = new HttpEntity<>(user, buildHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        return response.getBody();
    }

    @Override
    public String updateUser(Long id,User user) {
        HttpEntity<User> request = new HttpEntity<>( user, buildHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.PUT,
                request,
                String.class
        );
        return response.getBody();
    }

    @Override
    public String deleteUser(Long id) {
        HttpEntity<User> request = new HttpEntity<>(buildHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.DELETE,
                request,
                String.class
        );
        return response.getBody();
    }

    private String extrackSessionId(ResponseEntity<?> response) {
        String setCookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        if (setCookie != null && setCookie.contains("JSESSIONID=")) {
            sessionId = setCookie.split("JSESSIONID=")[1].split(";")[0];
        }
        return null;
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        if (sessionId != null) {
            headers.set("Cookie", "JSESSIONID=" + sessionId);
        }
        return headers;
    }


}
