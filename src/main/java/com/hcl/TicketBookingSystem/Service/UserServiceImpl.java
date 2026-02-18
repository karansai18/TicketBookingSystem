package com.hcl.TicketBookingSystem.Service;
import com.hcl.TicketBookingSystem.model.User;
import com.hcl.TicketBookingSystem.Repository.UserRepository;   
import java.util.List;
import com.hcl.TicketBookingSystem.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    // Constructor Injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Create User
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ✅ Get All Users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Get User By Id
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
    }

    // ✅ Update User
    @Override
    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());

        return userRepository.save(existingUser);
    }

    // ✅ Delete User
    @Override
    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + id));

        userRepository.delete(existingUser);
    }
}