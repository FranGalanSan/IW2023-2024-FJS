package com.easycall.project.views;

import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserService;
import com.easycall.project.employee.Role;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("UserRegisterView")
public class UserRegisterView extends FormLayout {
    private final UserService userService;

    private TextField username = new TextField("Username");
    private PasswordField password = new PasswordField("Password");
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField email = new TextField("Email");
    private TextField address = new TextField("Address");
    private TextField phone = new TextField("Phone");
    private DatePicker dateOfBirth = new DatePicker("Date of Birth");
    private Button registerButton = new Button("Register");

    @Autowired
    public UserRegisterView(UserService userService) {
        this.userService = userService;

        add(username, password, firstName, lastName, email, address, phone, dateOfBirth, registerButton);
        setResponsiveSteps(new ResponsiveStep("25em", 1));

        registerButton.addClickListener(event -> registerUser());
    }

    private void registerUser() {
        if (!userService.existsByUsernameOrEmail(username.getValue(), email.getValue())) {
            User newUser = new User();
            newUser.setUsername(username.getValue());
            newUser.setPassword(password.getValue());
            newUser.setRol(Role.USER);
            newUser.setFirstName(firstName.getValue());
            newUser.setLastName(lastName.getValue());
            newUser.setEmail(email.getValue());
            newUser.setAddress(address.getValue());
            newUser.setPhone(phone.getValue());
            newUser.setDateOfBirth(dateOfBirth.getValue());

            try {
                userService.save(newUser);
                Notification.show("User registered successfully");
            } catch (Exception e) {
                Notification.show("Error registering user: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            }
        } else {
            Notification.show("Username or email already in use", 3000, Notification.Position.MIDDLE);
        }
    }
}
