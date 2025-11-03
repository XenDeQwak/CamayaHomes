package com.xen.camaya.controller.internal_controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xen.camaya.entity.PropertyData;
import com.xen.camaya.entity.PropertyImageData;
import com.xen.camaya.model.PropertyModel;
import com.xen.camaya.service.CrudService;
import com.xen.camaya.service.PropertyService;
import com.xen.camaya.repository.PropertyRepository;

@RestController
@RequestMapping("/internal/property")
public class PropertyController extends BaseController<PropertyModel, Integer> {

    private final PropertyService propertyService;
    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
    }

    @Override
    protected String getResourceName() {
        return "property";
    }

    @Override
    protected CrudService<PropertyModel, Integer> getService() {
        return propertyService;
    }

    @PostMapping(value = "/{id}/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImages(
            @PathVariable int id,
            @RequestParam("file") List<MultipartFile> files) {
        try {
            PropertyData property = propertyRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Property not found"));

            Path uploadPath = Paths.get("uploads/");
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            for (MultipartFile file : files) {
                String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Files.copy(file.getInputStream(), uploadPath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

                PropertyImageData image = new PropertyImageData();
                image.setUrl("/uploads/" + filename);
                image.setProperty(property);
                property.getImages().add(image);
            }

            propertyRepository.save(property);
            return ResponseEntity.ok(property);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("/reserved/{userId}")
    public ResponseEntity<?> getReservedProperties(@PathVariable int userId) {
        List<PropertyData> reservedProperties = propertyRepository.findAll()
            .stream()
            .filter(property -> property.getLinkedUsers()
                .stream()
                .anyMatch(user -> user.getId() == userId))
            .toList();

        return ResponseEntity.ok(reservedProperties);
    }



}

