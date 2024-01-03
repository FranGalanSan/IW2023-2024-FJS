package com.easycall.project.employee;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.easycall.project.employee.Employee;
import com.easycall.project.employee.EmployeeService;

@PageTitle("Employee Change Password")
@Route(value = "employee-change-password")
public class EmployeeChangePasswordView extends VerticalLayout {
    private final EmployeeService employeeService;

    private final H1 title;
    private final TextField username;
    private final PasswordField password;
    private final PasswordField newPassword;
    private final Button button;

    public EmployeeChangePasswordView(EmployeeService employeeService) {
        this.employeeService = employeeService;

        title = new H1("Change Your Password");

        username = new TextField("Enter your username");
        username.setId("Username");

        password = new PasswordField("Enter your current password");
        password.setId("Password");

        newPassword = new PasswordField("Enter your new password");
        newPassword.setId("NewPassword");

        button = new Button("Change Password");

        add(title, username, password, newPassword, button);

        button.addClickListener(e -> validateAndChangePassword());
    }

    private void validateAndChangePassword() {
        String usernameValue = username.getValue();
        String passwordValue = password.getValue();
        String newPasswordValue = newPassword.getValue();

        if (usernameValue.isEmpty() || passwordValue.isEmpty() || newPasswordValue.isEmpty()) {
            Notification.show("All fields are required", 3000, Notification.Position.MIDDLE);
        } else {
            // Utilizar orElse para obtener el Employee o null si no está presente
            Employee authenticatedEmployee = employeeService.authenticate(usernameValue, passwordValue).orElse(null);

            if (authenticatedEmployee != null) {
                // Autenticación exitosa
                boolean passwordChanged = employeeService.changePassword(authenticatedEmployee, newPasswordValue);

                if (passwordChanged) {
                    Notification.show("Password changed successfully", 3000, Notification.Position.MIDDLE);
                } else {
                    Notification.show("Error changing password", 3000, Notification.Position.MIDDLE);
                }
            } else {
                // Autenticación fallida
                Notification.show("Authentication failed", 3000, Notification.Position.MIDDLE);
            }
        }
    }
}
