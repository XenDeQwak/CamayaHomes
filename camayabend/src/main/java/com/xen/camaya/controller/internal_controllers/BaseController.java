package com.xen.camaya.controller.internal_controllers;

import com.xen.camaya.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T, ID> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected abstract String getResourceName();
    protected abstract CrudService<T, ID> getService();

    @GetMapping
    public ResponseEntity<?> list() {
        try {
            List<T> entities = getService().getAll();
            return ResponseEntity.ok(entities);
        } catch (Exception ex) {
            logger.error("Failed to list {}s: {}", getResourceName(), ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable ID id) {
        logger.info("Fetching {} with id {}", getResourceName(), id);
        try {
            T entity = getService().get(id);
            return ResponseEntity.ok(entity);
        } catch (Exception ex) {
            logger.error("Failed to fetch {}: {}", getResourceName(), ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody T entity) {
        logger.info("Creating {} >> {}", getResourceName(), entity);
        try {
            T created = getService().create(entity);
            return ResponseEntity.ok(created);
        } catch (Exception ex) {
            logger.error("Failed to create {}: {}", getResourceName(), ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    
    @PostMapping("/{userId}/assign/{adminId}")
    public ResponseEntity<?> assignAdmin(@PathVariable ID userId, @PathVariable ID adminId) {
        try {
            boolean success = getService().assign(userId, adminId);
            if (!success) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Assignment failed");
            return ResponseEntity.ok("Assignment success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody T entity) {
        logger.info("Updating {} >> {}", getResourceName(), entity);
        try {
            T updated = getService().update(entity);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            logger.error("Failed to update {}: {}", getResourceName(), ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ID id) {
        logger.info("Deleting {} with id {}", getResourceName(), id);
        try {
            getService().delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            logger.error("Failed to delete {}: {}", getResourceName(), ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
