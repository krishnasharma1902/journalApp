package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest   //Start the whole application context otherwise userRepository bean will not be created as we have not started the application.
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach   // OR AFTEREACH
    void setUpBeforeEach(){
        // This will run before each test
    }

    @BeforeAll    // OR AFTERALL
    static void setUpBeforeAll(){
        // Run before running all test cases
    }


    @Disabled
    @Test
    public void testUsernameNotNull(){
         assertNotNull(userRepository.findByUsername("Krishna"));
    }

    @Disabled
    @Transactional
    @Test
    public void testUserNotEmpty(){
        User user = userRepository.findByUsername("Krishna");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {      // or @Enumsource can also be used
            "Krishna",
            "Shiva",
            "Shyam"
    })
    public void testUserNotNull(String name){
        assertNotNull(userRepository.findByUsername(name), "Failed for : "+name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "5,5,10",
            "3,3,9"
    })
    public void testAddition(int a, int b, int sum){
        assertEquals(sum, a+b);

    }
}
