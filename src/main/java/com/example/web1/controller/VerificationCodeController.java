package com.example.web1.controller;

import com.example.web1.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/sendCode")
    public String sendCode(@RequestHeader("sessionId") String sessionId) {
        return verificationCodeService.generateAndSendCode(sessionId);
    }

    @PostMapping("/verifyCode")
    public boolean verifyCode(@RequestHeader("sessionId") String sessionId, @RequestParam String code) {
        return verificationCodeService.verifyCode(sessionId, code);
    }
}
