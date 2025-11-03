package com.xen.camaya.controller;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.xen.camaya.model.UserModel;
import com.xen.camaya.model.PropertyModel;
import com.xen.camaya.service.UserService;
import com.xen.camaya.service.PropertyService;

@RestController
@RequestMapping("/api/proxy")
public class BffController {

    private final UserService userService;
    private final PropertyService propertyService;

    public BffController(UserService userService, PropertyService propertyService) {
        this.userService = userService;
        this.propertyService = propertyService;
    }

    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/users")
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.create(user);
    }

    @PostMapping("/users/{userId}/assign/{adminId}")
    public ResponseEntity<?> assignAdmin(@PathVariable Integer userId, @PathVariable Integer adminId) {
        String internalUrl = "http://localhost:8080/internal/users/" + userId + "/assign/" + adminId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(internalUrl, null, String.class);
    }

    @PostMapping("/users/{propertyId}/linked/{userId}")
    public ResponseEntity<?> assignProperty(@PathVariable Integer propertyId, @PathVariable Integer userId) {
        String internalUrl = "http://localhost:8080/internal/users/" + propertyId + "/linked/" + userId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(internalUrl, null, String.class);
    }

    @GetMapping("/users/link/{adminId}")
    public ResponseEntity<?> getLinkedCustomers(@PathVariable Integer adminId) {
        String internalUrl = "http://localhost:8080/internal/users/link/" + adminId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(internalUrl, Object.class);
    }

    @GetMapping("/properties")
    public List<PropertyModel> getAllProperties() {
        return propertyService.getAll();
    }

    @PostMapping("/properties")
    public PropertyModel createProperty(@RequestBody PropertyModel property) {
        return propertyService.create(property);
    }

    @PostMapping("/properties/{id}/upload")
    public ResponseEntity<?> uploadImage(
            @PathVariable int id,
            @RequestParam("file") List<MultipartFile> files) {
        try {
            String internalUrl = "http://localhost:8080/internal/property/" + id + "/upload";
            RestTemplate restTemplate = new RestTemplate();

            LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            for (MultipartFile file : files) {
                body.add("file", new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                });
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                    new HttpEntity<>(body, headers);

            return restTemplate.postForEntity(internalUrl, requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload proxy failed: " + e.getMessage());
        }
    }

    @GetMapping("/properties/reserved/{userId}")
    public ResponseEntity<?> getReservedProperties(@PathVariable int userId) {
        String internalUrl = "http://localhost:8080/internal/property/reserved/" + userId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(internalUrl, Object.class);
    }





}
