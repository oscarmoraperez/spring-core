package org.oka.springcore.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.oka.springcore.dao.UserDAO;
import org.oka.springcore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUserById(final long userId) {
        return userDAO.getById(userId).orElseThrow(() -> new RuntimeException("User not found (byId: " + userId + ")"));
    }

    public User getUserByEmail(final String email) {
        return userDAO.getByEmail(email).orElseThrow(() -> new RuntimeException("User not found (byEmail: " + email + ")"));
    }

    public List<User> getUsersByName(final String name, final int pageSize, final int pageNum) {
        return userDAO.getByName(name, pageSize, pageNum);
    }

    public User createUser(final User user) {
        return userDAO.create(user);
    }

    public User updateUser(final User user) {
        return userDAO.update(user);
    }

    public boolean deleteUser(final long userId) {
        return userDAO.delete(userId);
    }
}
