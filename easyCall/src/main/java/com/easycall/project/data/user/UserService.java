package com.easycall.project.data.user;

import com.easycall.project.invoice.Invoice;
import com.easycall.project.invoice.InvoiceRepository;
import com.easycall.project.service.Servicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.easycall.project.data.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Servicee> getUserServices(Integer userId) {
        List<Invoice> invoices = invoiceRepository.findByUser_Id(userId);
        List<Servicee> services = new ArrayList<>();

        for (Invoice invoice : invoices) {
            services.addAll(invoice.getServices());
        }

        return services.stream().distinct().collect(Collectors.toList());
    }
}
