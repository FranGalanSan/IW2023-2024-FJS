package com.easycall.project.sms;

import org.springframework.data.jpa.repository.JpaRepository;
import com.easycall.project.sms.SMS;
import com.easycall.project.data.user.User;

import java.util.List;

public interface SMSRepository extends JpaRepository<SMS, Long> {
    List<SMS> findByUser(User user);
    List<SMS> findByNumeroDestinatario(String numeroDestinatario);
}
