package com.keds_energy.testapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements BaseUserService{
    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1, "Naim", "a@a.com"));
        users.add(new User(2, "Filan", "filan@a.com"));
        users.add(new User(3, "Fistek", "fistek@a.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(long id) {
        Optional<User> user = users.stream().filter(t -> t.getId() == id).findFirst();
        if (user.isEmpty())
            throw new RuntimeException("User not found!");
        return user.get();
    }

    public void addUser(User user) {
        Optional<User> existUser =
                users.stream().filter(t -> t.getEmail().equalsIgnoreCase(user.getEmail())).findFirst();
        if (existUser.isPresent())
            throw new RuntimeException("Useri me email existon");
        users.add(user);
    }

    public void deleteUser(long id) {
        User user = getUserById(id);
        users.remove(user);
        //users.removeIf(t->t.getId()==id);
    }

    public User updateUser(long id, User user) {
        if (id != user.getId())
            throw new RuntimeException("Not same users!");
        User oldUser = getUserById(id);
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        return oldUser;
    }
}
