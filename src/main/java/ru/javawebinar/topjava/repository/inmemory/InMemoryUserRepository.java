package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final List<User> userList = Arrays.asList(new User(1, "Admin1", "admin@gmail.com", "admin", Role.ADMIN),
            new User(2, "User1", "user@mail.ru", "user", Role.USER));


    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return userList;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return null;
    }
}
