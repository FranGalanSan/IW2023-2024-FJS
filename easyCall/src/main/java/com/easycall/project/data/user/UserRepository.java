package com.easycall.project.data.user;
import org.springframework.data.jpa.repository.JpaRepository;
import com.easycall.project.data.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
