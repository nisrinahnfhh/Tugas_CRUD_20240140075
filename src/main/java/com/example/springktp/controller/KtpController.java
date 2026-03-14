package com.example.springktp.controller;

import com.example.springktp.dto.KtpDto;
import com.example.springktp.entity.Ktp;
import com.example.springktp.model.WebResponse;
import com.example.springktp.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
public class KtpController {

    @Autowired
    private KtpService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody KtpDto request) {
        try {
            Ktp response = service.create(request);
            return ResponseEntity.ok(new WebResponse<>(200, "Success", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new WebResponse<>(400, e.getMessage(), null));
        }
    }

    @GetMapping
    public WebResponse<List<Ktp>> getAll() {
        return new WebResponse<>(200, "Success", service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(new WebResponse<>(200, "Success", service.getById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new WebResponse<>(400, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody KtpDto request) {
        try {
            return ResponseEntity.ok(new WebResponse<>(200, "Success", service.update(id, request)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new WebResponse<>(400, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public WebResponse<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return new WebResponse<>(200, "Deleted Successfully", "OK");
    }
}