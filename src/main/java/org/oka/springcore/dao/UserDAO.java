package org.oka.springcore.dao;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.oka.springcore.db.UserDB;
import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Setter
@Component
public class UserDAO {

    private UserDB userDB;

    public Optional<User> getById(final long id) {
        return userDB.getUsers().stream().filter(u -> u.getId() == id).findFirst();
    }

    public Optional<User> getByEmail(final String email) {
        return userDB.getUsers().stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    public List<User> getByName(final String name, final int pageSize, final int pageNum) {
        int init = pageNum * pageSize;
        int end = pageNum * pageSize + pageSize;
        List<User> users = userDB.getUsers().stream().filter(u -> u.getName().equals(name)).collect(toList());

        init = init > users.size() ? 0 : init;
        end = Math.min(end, users.size());

        return users.subList(init, end);
    }

    public User create(final User user) {
        int id = userDB.getUsers().size();
        User userToSave = new UserImpl(id, user.getName(), user.getEmail());
        this.userDB.getUsers().add(userToSave);

        return userToSave;
    }

    public User update(final User user) {
        User userToUpdate = userDB.getUsers().stream().filter(u -> u.getId() == user.getId()).findFirst().orElseThrow();
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());

        return userToUpdate;
    }

    public boolean delete(final long userId) {
        Optional<User> userToDelete = userDB.getUsers().stream().filter(u -> u.getId() == userId).findFirst();
        if (userToDelete.isEmpty()) {
            return false;
        }
        return userDB.getUsers().remove(userToDelete.get());
    }
}
