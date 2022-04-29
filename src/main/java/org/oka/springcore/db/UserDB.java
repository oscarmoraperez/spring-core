package org.oka.springcore.db;

import org.oka.springcore.model.User;
import org.oka.springcore.model.UserImpl;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Serves as storage of User objects.
 */
public class UserDB {

    public List<User> usersDb = new ArrayList<>();

    /**
     * Inits/populate the storage of Users.
     *
     * @throws IOException in case there are errors with location of the file.
     */
    public void initBean() throws IOException {
        List<String> users = Files.readAllLines(ResourceUtils.getFile("classpath:users.txt").toPath());
        for (String user : users) {
            String[] split = user.split(";");
            usersDb.add(new UserImpl(Integer.parseInt(split[0]), split[1], split[2]));
        }
    }

    /**
     * Returns the complete list of User.
     *
     * @return list of User
     */
    public List<User> getUsers() {
        return Collections.unmodifiableList(this.usersDb);
    }
}
