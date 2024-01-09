package com.easycall.project.data.user;
import com.easycall.project.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import com.easycall.project.data.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User getByUsername(String username);

    default void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        save(user);
    }
}
