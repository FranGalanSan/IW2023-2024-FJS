package com.easycall.project.views;

import com.easycall.project.employee.Employee;
import com.easycall.project.employee.EmployeeService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Datos del Empleado")
public class EmployeeDataView extends VerticalLayout {

    private final EmployeeService employeeService;

    public EmployeeDataView(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
        Employee currentEmployee = VaadinSession.getCurrent().getAttribute(Employee.class);

        if (currentEmployee != null) {
            H2 title = new H2("Bienvenido " + currentEmployee.getUsername() + ", su ID asignada es " + currentEmployee.getId());
            title.setWidthFull();
            title.getStyle().set("text-align", "center");
            add(title);

            HorizontalLayout layout = new HorizontalLayout();
            layout.setSizeFull();

            VerticalLayout passwordLayout = createPasswordChangeSection(currentEmployee, "lightblue");
            VerticalLayout serviceLayout = createSectionWithHeader("Gestionar Servicios Ofertados", "lightblue", () -> UI.getCurrent().navigate("EmployeeServiceView"));
            VerticalLayout complaintLayout = createSectionWithHeader("Gestionar Quejas De Los Clientes", "lightblue", () -> UI.getCurrent().navigate("EmployeeComplaintsView"));
            VerticalLayout invoicesLayout = createSectionWithHeader("Gestionar Recibos De Los Clientes", "lightblue", () -> UI.getCurrent().navigate("EmployeeInvoicesView"));

            layout.add(passwordLayout, serviceLayout, complaintLayout, invoicesLayout);
            add(layout);
        } else {
            add(new Span("No hay datos del empleado disponibles."));
        }
    }

    private VerticalLayout createSectionWithHeader(String titleText, String backgroundColor, Runnable onClickAction) {
        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        H2 header = new H2(titleText);
        header.getStyle().set("text-align", "center").set("font-weight", "bold");

        Button button = new Button("Acceder", event -> onClickAction.run());

        layout.add(header, button);
        layout.getStyle().set("background-color", backgroundColor);
        layout.setWidth("500px"); // Fijar ancho
        layout.setHeight("500px"); // Fijar altura
        return layout;
    }

    private VerticalLayout createPasswordChangeSection(Employee currentEmployee, String backgroundColor) {
        VerticalLayout passwordLayout = new VerticalLayout();
        passwordLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        H2 changePasswordTitle = new H2("¿Desea Usted Cambiar Su Contraseña?");
        changePasswordTitle.getStyle().set("text-align", "center").set("font-weight", "bold");

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

        passwordLayout.add(changePasswordTitle, oldPasswordField, newPasswordField, changePasswordButton);
        passwordLayout.getStyle().set("background-color", backgroundColor);
        passwordLayout.setWidth("500px"); // Fijar ancho
        passwordLayout.setHeight("500px"); // Fijar altura
        return passwordLayout;
    }
}
