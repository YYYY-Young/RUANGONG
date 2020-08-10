package com.diamond;
import com.diamond.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void contextLoads() {
        emailService.sendMsg();
    }

}
