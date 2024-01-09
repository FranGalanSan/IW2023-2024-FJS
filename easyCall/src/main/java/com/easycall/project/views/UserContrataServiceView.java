package com.easycall.project.views;

import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserService;
import com.easycall.project.service.Servicee;
import com.easycall.project.invoice.InvoiceService;
import com.easycall.project.service.ServiceService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Route(value = "UserContrataServiceView")
@PageTitle("User Contrata Service")
public class UserContrataServiceView extends VerticalLayout {

    private final ServiceService serviceService;
    private final UserService userService;
    private final InvoiceService invoiceService;
    private Grid<Servicee> serviceGrid;
    private Button submitButton;
    private Button unsubscribeButton;

    private Grid<Servicee> userServicesGrid;
    private Button loadUserServicesButton;

    public UserContrataServiceView(ServiceService serviceService, UserService userService, InvoiceService invoiceService) {
        this.serviceService = serviceService;
        this.userService = userService;
        this.invoiceService = invoiceService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Seleccionar Servicios");
        add(header);

        configureServiceGrid();
        configureUserServicesGrid();
        configureForm();

        H1 contractedServicesHeader = new H1("Estos son los servicios que tienes contratados");
        contractedServicesHeader.getStyle().set("font-size", "1.5em");

        add(serviceGrid, submitButton, unsubscribeButton, contractedServicesHeader, loadUserServicesButton, userServicesGrid);
    }

    private void configureServiceGrid() {
        serviceGrid = new Grid<>(Servicee.class);
        serviceGrid.setColumns("nombre", "precio", "descripcion");
        serviceGrid.setSizeFull();
        serviceGrid.setItems(serviceService.findAllServices());
        serviceGrid.setSelectionMode(Grid.SelectionMode.MULTI);
    }

    private void configureUserServicesGrid() {
        userServicesGrid = new Grid<>(Servicee.class);
        userServicesGrid.setColumns("nombre", "precio", "descripcion");
        userServicesGrid.setSizeFull();
    }

    private void configureForm() {
        submitButton = new Button("Contratar Servicios", event -> createInvoice());
        unsubscribeButton = new Button("Darse de Baja de Servicios", event -> removeServices());
        loadUserServicesButton = new Button("Cargar Servicios", event -> loadUserServices());
    }

    private User getCurrentUser() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }

    private void createInvoice() {
        try {
            User user = getCurrentUser();
            if (user == null) {
                Notification.show("Usuario no autenticado.");
                return;
            }

            List<Servicee> selectedServices = new ArrayList<>(serviceGrid.getSelectedItems());
            if (selectedServices.isEmpty()) {
                Notification.show("No se seleccionaron servicios.");
                return;
            }

            invoiceService.createOrUpdateInvoice(user, selectedServices);
            Notification.show("Factura actualizada/creada para el usuario ID: " + user.getId());
        } catch (Exception e) {
            Notification.show("Error al crear la factura: " + e.getMessage());
        }
    }

    private void loadUserServices() {
        try {
            User user = getCurrentUser();
            if (user == null) {
                Notification.show("Usuario no autenticado.");
                return;
            }

            List<Servicee> userServices = userService.getUserServices(user.getId());
            userServicesGrid.setItems(userServices);
        } catch (Exception e) {
            Notification.show("Error al cargar los servicios: " + e.getMessage());
        }
    }


    private void removeServices() {
        try {
            User user = getCurrentUser();
            if (user == null) {
                Notification.show("Usuario no autenticado.");
                return;
            }

            Set<Servicee> selectedServices = userServicesGrid.getSelectedItems();
            if (selectedServices.isEmpty()) {
                Notification.show("No se seleccionaron servicios para dar de baja.");
                return;
            }

            invoiceService.removeServicesFromInvoice(user, new ArrayList<>(selectedServices));
            Notification.show("Servicios dados de baja correctamente.");
        } catch (Exception e) {
            Notification.show("Error al dar de baja los servicios: " + e.getMessage());
        }
    }

}