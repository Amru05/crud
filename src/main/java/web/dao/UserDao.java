package web.dao;

//import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void addUser(User user);

    void delUser(Long id);

    void editUser(User user);

    User getById(Long id);

    User getByUsername(String username);
}
