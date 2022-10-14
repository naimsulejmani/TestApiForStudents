package com.keds_energy.testapi;

import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements BaseUserService {
    private final UserRepository repository;

    public UserEntityServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream().map(t -> new User( t.getId(), t.getName(), t.getEmail())).toList();
    }

    @Override
    public User getUserById(long id) {
        var existsUser = repository.findById( id);
        if (existsUser.isEmpty())
            throw new EntityNotFoundException(String.format("User with id {%d} not found", id));
        return new User((int) existsUser.get().getId(), existsUser.get().getName(), existsUser.get().getEmail());
    }

    @Override
    public void addUser(User user) {
        var uexist = repository.findFirstByEmail(user.getEmail());
        if(uexist.isPresent())
            throw new EntityExistsException("Exists");
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        repository.save(entity);
    }

    @Override
    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    @Override
    public User updateUser(long id, User user) {
        var entity = repository.findById( id);
        if (entity.isEmpty())
            throw new EntityNotFoundException("Nuk u gjet");
        entity.get().setName(user.getName());
        entity.get().setEmail(user.getEmail());
        repository.save(entity.get());
        user.setId((int) entity.get().getId());
        return user;
    }
}
