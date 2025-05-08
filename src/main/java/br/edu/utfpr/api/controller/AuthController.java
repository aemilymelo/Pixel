package br.edu.utfpr.api.controller;
import br.edu.utfpr.api.dto.AuthRequest;
import br.edu.utfpr.api.service.AuthService;
import lombok.RequiredArgsConstructor;


import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${aws.cognito.url}")
    private String cognitoUrl;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.clientSecret}")
    private String clientSecret;
    
    private final RestTemplate restTemplate = new RestTemplate();

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value="/login", consumes = {"application/json", "application/*+json"})
    public String login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

     @PostMapping("/login-cognito")
    public ResponseEntity<?> loginCognito(@RequestBody AuthRequest authRequest) {
        try {
            String secretHash = calculateSecretHash(authRequest.getUsername());

            Map<String, Object> authParams = new HashMap<>();
            authParams.put("USERNAME", authRequest.getUsername());
            authParams.put("PASSWORD", authRequest.getPassword());
            authParams.put("SECRET_HASH", secretHash);

            Map<String, Object> payload = new HashMap<>();
            payload.put("AuthFlow", "USER_PASSWORD_AUTH");
            payload.put("ClientId", clientId);
            payload.put("AuthParameters", authParams);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-amz-json-1.1");
            headers.set("X-Amz-Target", "AWSCognitoIdentityProviderService.InitiateAuth");

            // Serializa o payload como JSON
            String body = objectMapper.writeValueAsString(payload);

            HttpEntity<String> request = new HttpEntity<>(body, headers);

            System.out.println("URL: " + cognitoUrl);
            System.out.println("Payload: " + payload);
            System.out.println("Headers: " + headers);
            System.out.println("Entity: " + request);

            ResponseEntity<String> response = restTemplate.postForEntity(cognitoUrl, request, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }


    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        authService.register(request);
        return "Usu�rio registrado com sucesso";
    }

    private String calculateSecretHash(String input) throws Exception {
        String message = input + clientId;
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(clientSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKey);
        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
