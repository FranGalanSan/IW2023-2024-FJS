package com.easycall.project.views;

import com.easycall.project.data.user.User;
import com.easycall.project.service.Servicee;
import com.easycall.project.invoice.InvoiceService;
import com.easycall.project.data.user.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@PageTitle("Dar de Baja Servicios de Empleado")
public class EmployeeDarBajaView extends VerticalLayout {

    private final InvoiceService invoiceService;
    private final UserService userService;
    private Grid<Servicee> userServicesGrid;
    private Grid<Servicee> currentServicesGrid;
    private Button unsubscribeButton;
    private Button loadUserServicesButton;
    private Button loadCurrentServicesButton;
    private TextField userServicesUserIdField;
    private TextField currentServicesUserIdField;

    public EmployeeDarBajaView(@Autowired InvoiceService invoiceService, @Autowired UserService userService) {
        this.invoiceService = invoiceService;
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Dar de Baja Servicios de Empleado");
        add(header);

        configureUserServicesControls();
        configureCurrentServicesControls();
        configureUserServicesGrid();
        configureCurrentServicesGrid();
        Button button = new Button("Volver a Home", event -> {
            UI.getCurrent().navigate(EmployeeMainView.class);
        });
        add(button,new HorizontalLayout(userServicesUserIdField, loadUserServicesButton), userServicesGrid, unsubscribeButton,
                new HorizontalLayout(currentServicesUserIdField, loadCurrentServicesButton), currentServicesGrid);
    }

    private void configureUserServicesControls() {
        userServicesUserIdField = new TextField("ID de Usuario para User Services");
        userServicesUserIdField.setPlaceholder("Introduce el ID del usuario");

        loadUserServicesButton = new Button("Cargar Servicios de Usuario", e -> loadUserServices());

        unsubscribeButton = new Button("Darse de Baja en User Services", e -> unsubscribeServices());
    }

    private void configureCurrentServicesControls() {
        currentServicesUserIdField = new TextField("ID de Usuario para Current Services");
        currentServicesUserIdField.setPlaceholder("Introduce el ID del usuario");

        loadCurrentServicesButton = new Button("Cargar Servicios Actuales", e -> loadCurrentServices());
    }

    private void configureUserServicesGrid() {
        userServicesGrid = new Grid<>(Servicee.class);
        userServicesGrid.setColumns("nombre", "precio", "descripcion");
        userServicesGrid.setSizeFull();
        userServicesGrid.setSelectionMode(Grid.SelectionMode.MULTI);
    }

    private void configureCurrentServicesGrid() {
        currentServicesGrid = new Grid<>(Servicee.class);
        currentServicesGrid.setColumns("nombre", "precio", "descripcion");
        currentServicesGrid.setSizeFull();
    }

    private void unsubscribeServices() {
        User user = getUserById(userServicesUserIdField.getValue());
        if (user == null) {
            Notification.show("Usuario no encontrado.");
            return;
        }

        Set<Servicee> selectedServices = userServicesGrid.getSelectedItems();
        if (selectedServices.isEmpty()) {
            Notification.show("No se seleccionaron servicios para dar de baja.");
            return;
        }

        try {
            invoiceService.removeServicesFromInvoice(user, new ArrayList<>(selectedServices));
            Notification.show("Servicios dados de baja correctamente.");
            loadUserServices();
        } catch (Exception e) {
            Notification.show("Error al dar de baja los servicios: " + e.getMessage());
        }
    }

    private void loadUserServices() {
        User user = getUserById(userServicesUserIdField.getValue());
        if (user != null) {
            List<Servicee> userServices = userService.getUserServices(user.getId());
            userServicesGrid.setItems(userServices);
        } else {
            userServicesGrid.setItems(Collections.emptyList());
        }
    }

    private void loadCurrentServices() {
        User user = getUserById(currentServicesUserIdField.getValue());
        if (user != null) {
            List<Servicee> currentServices = userService.getUserServices(user.getId());
            currentServicesGrid.setItems(currentServices);
        } else {
            currentServicesGrid.setItems(Collections.emptyList());
        }
    }

    private User getUserById(String userId) {
        if (userId == null || userId.isEmpty()) {
            Notification.show("ID de usuario no proporcionado.");
            return null;
        }
        try {
            return userService.findUserById(Integer.parseInt(userId));
        } catch (NumberFormatException e) {
            Notification.show("ID de usuario inválido.");
            return null;
        }
    }
}
