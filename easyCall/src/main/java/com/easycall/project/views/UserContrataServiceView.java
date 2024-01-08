


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
        import com.vaadin.flow.component.textfield.TextField;
        import com.vaadin.flow.router.PageTitle;
        import com.vaadin.flow.router.Route;
        import org.springframework.beans.factory.annotation.Autowired;

        import java.util.ArrayList;
        import java.util.List;

@Route(value = "UserContrataServiceView")
@PageTitle("User Contrata Service")
public class UserContrataServiceView extends VerticalLayout {

    private final ServiceService serviceService;
    private final UserService userService;
    private final InvoiceService invoiceService;
    private Grid<Servicee> serviceGrid;
    private TextField userIdField;
    private Button submitButton;

    private Grid<Servicee> userServicesGrid;
    private TextField userIdForServicesField;
    private Button loadUserServicesButton;

    @Autowired
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

        add(serviceGrid, userIdField, submitButton, contractedServicesHeader, userIdForServicesField, loadUserServicesButton, userServicesGrid);
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
        userIdField = new TextField("ID del Usuario para Contratar Servicios");
        submitButton = new Button("Contratar Servicios", event -> createInvoice());
        userIdForServicesField = new TextField("ID del Usuario para Ver Servicios");
        loadUserServicesButton = new Button("Cargar Servicios", event -> loadUserServices());
    }

    private void createInvoice() {
        try {
            Integer userId = Integer.parseInt(userIdField.getValue());
            User user = userService.findUserById(userId);
            if (user == null) {
                Notification.show("Usuario no encontrado.");
                return;
            }

            List<Servicee> selectedServices = new ArrayList<>(serviceGrid.getSelectedItems());
            if (selectedServices.isEmpty()) {
                Notification.show("No se seleccionaron servicios.");
                return;
            }

            invoiceService.createOrUpdateInvoice(user, selectedServices);
            Notification.show("Factura actualizada/creada para el usuario ID: " + user.getId());
        } catch (NumberFormatException e) {
            Notification.show("ID de usuario inválido.");
        } catch (Exception e) {
            Notification.show("Error al crear la factura: " + e.getMessage());
        }
    }

    private void loadUserServices() {
        try {
            Integer userId = Integer.parseInt(userIdForServicesField.getValue());
            User user = userService.findUserById(userId);
            if (user == null) {
                Notification.show("Usuario no encontrado.");
                return;
            }

            List<Servicee> userServices = userService.getUserServices(userId);
            userServicesGrid.setItems(userServices);
        } catch (NumberFormatException e) {
            Notification.show("ID de usuario inválido.");
        } catch (Exception e) {
            Notification.show("Error al cargar los servicios: " + e.getMessage());
        }
    }
}
