package gaderning.example.savula.service;

import gaderning.example.savula.model.Product;
import gaderning.example.savula.model.User;
import gaderning.example.savula.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Create or Update User with Products
    public User saveUser(User user) {
        if (user.getProducts() != null) {
            user.getProducts().forEach(product -> product.setUser(user));
        }
        return userRepository.save(user);
    }

    // ✅ Get All Users with Pagination and Sorting
    public Page<User> getAllUsers(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }

    // ✅ Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // ✅ Delete User by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ✅ Update User and Products
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setAge(userDetails.getAge());
            
            // Update Products if provided
            if (userDetails.getProducts() != null) {
                for (Product product : userDetails.getProducts()) {
                    product.setUser(user);
                }
                user.getProducts().clear();
                user.getProducts().addAll(userDetails.getProducts());
            }

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // ✅ Find Users by Name using JPQL
    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
    }

    // ✅ Find Users by Age using JPQL
    public List<User> findLesserAge(int age) {
        return userRepository.findLesserAge(age);
    }
}
