package com.easycall.project.llamadas;

import org.springframework.data.jpa.repository.JpaRepository;
import com.easycall.project.data.user.User;
import com.easycall.project.llamadas.Llamada;

import java.time.LocalDateTime;
import java.util.List;

public interface LlamadaRepository extends JpaRepository<Llamada, Integer> {
    Llamada findFirstByUser(User user);
    List<Llamada> findAllByUser(User user);

    List<Llamada> findByUserAndFechaHoraBetween(User user, LocalDateTime start, LocalDateTime end);

}
