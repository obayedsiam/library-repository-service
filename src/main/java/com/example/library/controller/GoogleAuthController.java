package com.example.library.controller;

import com.example.library.service.JwtService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.openid.connect.sdk.claims.ClaimsSet;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class GoogleAuthController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String googleTokenUri;



    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    private String facebookClientId;

    @Value("${spring.security.oauth2.client.registration.facebook.redirect-uri}")
    private String facebookRedirectUri;

    @Value("${spring.security.oauth2.client.registration.facebook.client-secret}")
    private String facebookClientSecret;

    @Value("${spring.security.oauth2.client.provider.facebook.token-uri}")
    private String facebookTokenUri;

    @Autowired
    JwtService jwtService;

    public static String getAccessToken(String responseBody) throws Exception {
        // Parse the JSON response body
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        // Extract and return the access token
        return jsonNode.get("id_token").asText();
    }

    @GetMapping("/google-sign-in")
//    public RedirectView googleSignIn() {
    public ResponseEntity googleSignIn(@RequestHeader(value = "device", required = true) String device) {
        String state = device;
        // Build the Google OAuth2 authorization URL
        String googleAuthUrl = String.format(
                "https://accounts.google.com/o/oauth2/v2/auth?" +
                        "client_id=%s&" +
                        "redirect_uri=%s&" +
                        "response_type=code&" +
                        "scope=openid email profile&" +
                        "state=%s",
                googleClientId,
                googleRedirectUri,
                state
        );

        // Redirect the user to Google Sign-In
//        return new RedirectView(googleAuthUrl);
        return ResponseEntity.ok(googleAuthUrl);
    }

    @GetMapping("/facebook-sign-in")
//    public RedirectView googleSignIn() {
    public ResponseEntity facebookSignIn(@RequestHeader(value = "device", required = true) String device) {
        String state = device;
        // Build the Google OAuth2 authorization URL
        String facebookAuthUrl = String.format(
                "https://www.facebook.com/v12.0/dialog/oauth?"+
//                "https://accounts.google.com/o/oauth2/v2/auth?" +
                        "client_id=%s&" +
                        "redirect_uri=%s&" +
                        "response_type=code&" +
                        "scope=openid email public_profile&" +
                        "state=%s",
                facebookClientId,
                facebookRedirectUri,
                state
        );

        // Redirect the user to Google Sign-In
//        return new RedirectView(googleAuthUrl);
        return ResponseEntity.ok(facebookAuthUrl);
    }

    @GetMapping("/google-callback")
    public String handleGoogleCallback(@RequestParam("code") String code, @RequestParam("state") String state) {

        System.out.println(state);

        try {
            // Build the token exchange request
            String tokenUrl = "https://oauth2.googleapis.com/token";
            String requestBody = String.format(
                    "code=%s&" +
                            "client_id=%s&" +
                            "client_secret=%s&" +
                            "redirect_uri=%s&" +
                            "grant_type=authorization_code",
                    code, googleClientId, googleClientSecret, googleRedirectUri
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tokenUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request to exchange the code for tokens
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Return the token response (ID token, access token, etc.)

            String accessToken = getAccessToken(response.body());

            JsonNode claims = getAllClaimsFromToken(accessToken);
            String email = claims.get("email").asText();
            String emailVerified = claims.get("email_verified").asText();

            System.out.println("Username : "+email);
            System.out.println("Verified : "+emailVerified);


            return response.body();

        } catch (Exception e) {
            return "Error during token exchange: " + e.getMessage();
        }
    }

    public static JsonNode getAllClaimsFromToken(String token) throws Exception {
        // Split the JWT token into parts
        String[] tokenParts = token.split("\\.");
        if (tokenParts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT token format");
        }

        // Decode the payload (Base64 URL encoded)
        String payload = new String(Base64.getUrlDecoder().decode(tokenParts[1]));

        // Parse the payload as JSON
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(payload);
    }


    @GetMapping("/facebook-callback")
    public String handleFacebookCallback(@RequestParam("code") String code, @RequestParam("state") String state) {

        System.out.println(state);

        try {
            // Build the token exchange request
//            String tokenUrl = "https://oauth2.googleapis.com/token";
            String tokenUrl = facebookTokenUri;
            String requestBody = String.format(
                    "code=%s&" +
                            "client_id=%s&" +
                            "client_secret=%s&" +
                            "redirect_uri=%s&" +
                            "grant_type=authorization_code",
                    code, facebookClientId, facebookClientSecret, facebookRedirectUri
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tokenUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request to exchange the code for tokens
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Return the token response (ID token, access token, etc.)

            String accessToken = getAccessToken(response.body());

            JsonNode claims = getAllClaimsFromToken(accessToken);
            String email = claims.get("email").asText();
//            String emailVerified = claims.get("email_verified").asText();

            System.out.println("Username : "+email);
//            System.out.println("Verified : "+emailVerified);


            return response.body();

        } catch (Exception e) {
            return "Error during token exchange: " + e.getMessage();
        }
    }

}

