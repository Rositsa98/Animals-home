package fmi.course.hcmi.animalshome.notifications;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationsServiceClientImpl implements NotificationsServiceClient {


    @Value("${notificationsservice.address}")
    private String serviceAddress;

    @Override
    public List<String> getNotifications(String jwt) {
        String url = ensureTrailingSlash(serviceAddress) + "notifications/get";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> jwtEntity = CreateHttpEntityWithJwt(jwt);

        ResponseEntity<String[]> response =
                restTemplate.exchange(url, HttpMethod.GET, jwtEntity, String[].class);

        return Arrays.stream(response.getBody()).collect(Collectors.toList());
    }

    private HttpEntity<String> CreateHttpEntityWithJwt(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt);

        return new HttpEntity<>(headers);
    }

    private String ensureTrailingSlash(String url) {
        if(url == null || url.endsWith("/")) {
            return url;
        }

        return url + "/";
    }
}
