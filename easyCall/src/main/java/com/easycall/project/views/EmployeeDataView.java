package com.easycall.project.views;

import com.easycall.project.employee.Employee;
import com.easycall.project.employee.EmployeeService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@Route("EmployeeDataView")
@PageTitle("Datos del Empleado")
public class EmployeeDataView extends VerticalLayout {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeDataView(EmployeeService employeeService) {
        this.employeeService = employeeService;
        Employee currentEmployee = VaadinSession.getCurrent().getAttribute(Employee.class);

        if (currentEmployee != null) {
            add(new Span("ID: " + currentEmployee.getId()));
            add(new Span("Nombre de Usuario: " + currentEmployee.getUsername()));

            PasswordField oldPasswordField = new PasswordField("Contraseña actual");
            PasswordField newPasswordField = new PasswordField("Nueva contraseña");
            Button changePasswordButton = new Button("Cambiar contraseña", event -> {
                if (employeeService.updateEmployeePassword(currentEmployee, oldPasswordField.getValue(), newPasswordField.getValue())) {
                    Notification.show("Contraseña actualizada con éxito.");
                } else {
                    Notification.show("Error: La contraseña actual es incorrecta.", 5000, Notification.Position.MIDDLE);
                }
                oldPasswordField.clear();
                newPasswordField.clear();
            });

            add(oldPasswordField, newPasswordField, changePasswordButton);
        } else {
            add(new Span("No hay datos del empleado disponibles."));
        }
    }
}
