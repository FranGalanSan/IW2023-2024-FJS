package com.easycall.project.invoice;

import com.easycall.project.data.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByUserAndMesAndAnno(User user, int mes, int anno);
    List<Invoice> findByUser_Id(Integer userId);

}
