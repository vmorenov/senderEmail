package com.email.sendemail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.sendemail.dto.ApiResponse;
import com.email.sendemail.dto.EmailRequest;
import com.email.sendemail.service.EmailService;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/emails")
    public ResponseEntity<ApiResponse> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendSimpleMail(request);
        return ResponseEntity.ok(new ApiResponse(true, "Email enviado correctamente"));
    }
}
