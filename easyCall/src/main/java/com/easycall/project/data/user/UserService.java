package com.easycall.project.data.user;

import com.easycall.project.invoice.Invoice;
import com.easycall.project.invoice.InvoiceRepository;
import com.easycall.project.service.Servicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
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

    public void save(User user) {
        userRepository.save(user);
    }
    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
    }

    public boolean updateUserData(User user, String firstName, String lastName, String address, String phone) {

        if (userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId()) ||
                userRepository.existsByPhoneAndIdNot(phone, user.getId())) {
            return false;
        }


        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhone(phone);
        userRepository.save(user);
        return true;
    }

}
