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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Route(value = "UserDarseDeBajaView")
@PageTitle("Darse de Baja de Servicios")
public class UserDarseDeBajaView extends VerticalLayout {


    private final InvoiceService invoiceService;
    private final UserService userService;
    private Grid<Servicee> userServicesGrid;
    private Grid<Servicee> currentServicesGrid; // Nuevo Grid para mostrar servicios actuales
    private Button unsubscribeButton;

    public UserDarseDeBajaView(InvoiceService invoiceService, UserService userService) {
        this.invoiceService = invoiceService;
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Darse de Baja de Servicios Contratados");
        add(header);

        configureUserServicesGrid();
        configureUnsubscribeButton();
        configureCurrentServicesGrid(); // Configurar el nuevo Grid
        Button buttonx = new Button("Volver a Home", event -> UI.getCurrent().navigate("UserMainView"));

        add( buttonx,userServicesGrid, unsubscribeButton, currentServicesGrid); // Añadir el nuevo Grid al layout
        loadUserServices();
    }

    private void configureUserServicesGrid() {
        userServicesGrid = new Grid<>(Servicee.class);
        userServicesGrid.setColumns("nombre", "precio", "descripcion");
        userServicesGrid.setSizeFull();
        userServicesGrid.setSelectionMode(Grid.SelectionMode.MULTI);
    }

    private void configureUnsubscribeButton() {
        unsubscribeButton = new Button("Darse de Baja", e -> unsubscribeServices());
        add(unsubscribeButton);
    }

    private void unsubscribeServices() {
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

        try {
            invoiceService.removeServicesFromInvoice(user, new ArrayList<>(selectedServices));
            Notification.show("Servicios dados de baja correctamente.");
            loadUserServices(); // Recargar la lista de servicios después de dar de baja
        } catch (Exception e) {
            Notification.show("Error al dar de baja los servicios: " + e.getMessage());
        }
    }

    private void loadUserServices() {
        User user = getCurrentUser();
        if (user != null) {
            List<Servicee> userServices = userService.getUserServices(user.getId());
            userServicesGrid.setItems(userServices);
            currentServicesGrid.setItems(userServices); // Actualizar también el nuevo Grid
        } else {
            userServicesGrid.setItems(Collections.emptyList());
            currentServicesGrid.setItems(Collections.emptyList());
        }
    }

    private User getCurrentUser() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }

    private void configureCurrentServicesGrid() {
        currentServicesGrid = new Grid<>(Servicee.class);
        currentServicesGrid.setColumns("nombre", "precio", "descripcion");
        currentServicesGrid.setSizeFull();
    }
}