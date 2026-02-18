package com.hcl.TicketBookingSystem.Service;
import com.hcl.TicketBookingSystem.model.User;
import java.util.List;
public interface UserServiceInterface {

     User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

}
