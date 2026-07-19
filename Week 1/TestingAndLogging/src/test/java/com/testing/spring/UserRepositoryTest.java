package com.testing.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        User u1 = new User(1L, "David");
        User u2 = new User(2L, "Diana");
        userRepository.save(u1);
        userRepository.save(u2);

        List<User> found = userRepository.findByName("David");
        assertEquals(1, found.size());
        assertEquals("David", found.get(0).getName());

        List<User> none = userRepository.findByName("NonExistent");
        assertTrue(none.isEmpty());
    }
}
