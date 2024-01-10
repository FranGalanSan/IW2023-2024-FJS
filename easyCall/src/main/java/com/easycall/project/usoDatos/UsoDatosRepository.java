package com.easycall.project.usoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.easycall.project.usoDatos.UsoDatos;
import com.easycall.project.data.user.User;

public interface UsoDatosRepository extends JpaRepository<UsoDatos, Integer> {
    UsoDatos findByUser(User user);
}
