package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Map<Integer, User> repository = new ConcurrentHashMap<>();
    {
        save(new User(1, "Admin1", "admin@gmail.com", "admin", Role.ADMIN));
        save(new User(2, "User1", "user1@mail.ru", "user", Role.USER));
        save(new User(3, "User2", "user2@mail.ru", "user", Role.USER));
        System.out.println(repository.values());}
//    private final List<User> userList = Arrays.asList(
//            new User(1, "Admin1", "admin@gmail.com", "admin", Role.ADMIN),
//            new User(2, "User1", "user1@mail.ru", "user", Role.USER),
//            new User(3, "User2", "user2@mail.ru", "user", Role.USER));
    private final AtomicInteger counter = new AtomicInteger(3);
    private User currentUser;


    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(counter.incrementAndGet(),user);
        } return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream().collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);

//        for(User user : userList){
//            if(user.getEmail().equals(email)){
//                currentUser=user;
//                System.out.println(currentUser.getId());
//                return user;
//            }
//        }
//        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
