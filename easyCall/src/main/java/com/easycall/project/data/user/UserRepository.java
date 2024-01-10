package com.easycall.project.data.user;
import com.easycall.project.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import com.easycall.project.data.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User getByUsername(String username);

    boolean existsByEmailAndIdNot(String email, Integer id);
    boolean existsByPhoneAndIdNot(String phone, Integer id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
