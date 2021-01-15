package fmi.course.hcmi.animalshome.notifications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fmi.course.hcmi.animalshome.util.JwtUtil;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationsServiceClientImpl implements NotificationsServiceClient {

    @Value("${notificationsservice.address}")
    private String serviceAddress;
    @Autowired
    private JwtUtil jwtUtil;
    private String jwt;

    @Override
    public List<String> getNotifications(String username) {
        ensureJwtValid();

        String url = ensureTrailingSlash(serviceAddress) + "notifications/get/" + username;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.jwt);

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String[]> response =
                restTemplate.exchange(url, HttpMethod.GET, jwtEntity, String[].class);

        return Arrays.stream(response.getBody()).collect(Collectors.toList());
    }

    private void ensureJwtValid() {
        if(!isJwtValid()) {
            synchronized (this) {
                if(!isJwtValid()) {
                    this.jwt = jwtUtil.generateTokenForNotificationsService();
                }
            }
        }
    }

    private boolean isJwtValid() {
        return this.jwt != null && !jwtUtil.isTokenExpired(this.jwt);
    }

    private String ensureTrailingSlash(String url) {
        if(url == null || url.endsWith("/")) {
            return url;
        }

        return url + "/";
    }

    @Override
    public void sendNotification(Notification notification) {
        ensureJwtValid();

        String url = ensureTrailingSlash(serviceAddress) + "notifications/add";
        String notificationJson = parseNotificationToJson(notification);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.jwt);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(notificationJson, headers);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject(url, entity, Void.class);
    }

    private String parseNotificationToJson(Notification notification) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
