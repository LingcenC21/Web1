package com.example.web1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final int CODE_EXPIRE_TIME = 3; // 验证码有效时间（分钟）

    public String generateAndSendCode(String sessionId) {
        String code = generateCode();
        redisTemplate.opsForValue().set(sessionId, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);
        // 模拟发送验证码，实际场景中应调用短信服务商的API发送短信
        System.out.println("验证码：" + code);
        return code;
    }

    public boolean verifyCode(String sessionId, String code) {
        String storedCode = redisTemplate.opsForValue().get(sessionId);
        return code.equals(storedCode);
    }

    private String generateCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}