package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendMail(){
        emailService.sendEmail("krishnasharma1902@gmail.com",
                "Testing Java Mail Sender",
                "Hi, you are hired!s");
    }

}
