package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.response.ResponseData;
import com.example.dto.resquest.EmailRequest;
import com.example.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseData<Void> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request);
        return new ResponseData<>(HttpStatus.OK.value(), "Email sent successfully");
    }
}
