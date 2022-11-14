package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {


    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;
    static {
        users.add(new User(++usersCount, "Luca", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Kiran", LocalDate.now().minusYears(20)));
        users.add(new User(++usersCount, "Jan", LocalDate.now().minusYears(10)));
    }

    public List<User> findAll(){
        return  users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User saveUser(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
