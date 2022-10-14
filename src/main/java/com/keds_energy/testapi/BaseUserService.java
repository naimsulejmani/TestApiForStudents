package com.keds_energy.testapi;

import java.util.List;

public interface BaseUserService {
    public List<User> getAllUsers();
    public User getUserById(long id);
    public void addUser(User user);
    public void deleteUser(long id);
    public User updateUser(long id, User user);
}
