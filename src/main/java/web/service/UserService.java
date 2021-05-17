package web.service;

//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import web.model.User;

import java.util.List;

@Service
//public interface UserService extends UserDetailsService {
public interface UserService {
    List<User> getUsers();

    void addUser(User user);

    void delUser(Long id);

    void editUser(User user);

    User getById(Long id);

//    User getByUsername(String username);
}
