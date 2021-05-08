package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void addUser(User user);

    void delUser(Long id);

    void editUser(Long id, User user);

    User getById(Long id);
}
