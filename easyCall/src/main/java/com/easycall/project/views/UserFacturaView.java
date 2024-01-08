
package com.easycall.project.views;

import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserService;
import com.easycall.project.service.Servicee;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "UserFacturaView")
@PageTitle("Factura de Usuario")
public class UserFacturaView extends VerticalLayout {

    private final UserService userService;
    private Grid<Servicee> userServicesGrid;
    private TextField userIdForServicesField;
    private Button loadUserServicesButton;
    private Span fiscalDataSpan;
    private Span userDataSpan;
    private Span totalCostSpan;

    @Autowired
    public UserFacturaView(UserService userService) {
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Consulta de Facturas");
        configureFiscalData();
        configureUserData();
        configureUserServicesGrid();
        configureForm();
        configureTotalCost();

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setWidthFull();
        headerLayout.add(fiscalDataSpan, userDataSpan);
        headerLayout.setFlexGrow(1, fiscalDataSpan, userDataSpan);
        fiscalDataSpan.getStyle().set("margin-right", "auto");

        add(header, headerLayout, userIdForServicesField, loadUserServicesButton, userServicesGrid, totalCostSpan);
    }

    private void configureFiscalData() {
        String fiscalData = "Escuela Superior de Ingeniería – Universidad de Cádiz\n" +
                "Campus Universitario de Puerto Real\n" +
                "Avda. Universidad de Cádiz, nº 10\n" +
                "CP 11519 – Puerto Real, Cádiz";
        fiscalDataSpan = new Span(fiscalData);
        fiscalDataSpan.getStyle().set("font-size", "0.9em");
    }

    private void configureUserData() {
        userDataSpan = new Span();
    }

    private void configureUserServicesGrid() {
        userServicesGrid = new Grid<>(Servicee.class);
        userServicesGrid.setColumns("nombre", "precio", "descripcion");
        userServicesGrid.setSizeFull();
    }

    private void configureForm() {
        userIdForServicesField = new TextField("ID del Usuario para Ver Servicios");
        loadUserServicesButton = new Button("Cargar Servicios", event -> loadUserServices());
    }

    private void configureTotalCost() {
        totalCostSpan = new Span();
    }

    private void loadUserServices() {
        try {
            Integer userId = Integer.parseInt(userIdForServicesField.getValue());
            List<Servicee> userServices = userService.getUserServices(userId);
            userServicesGrid.setItems(userServices);
            updateUserData(userId);
            updateTotalCost(userServices);
        } catch (NumberFormatException e) {
            Notification.show("ID de usuario inválido.");
        } catch (Exception e) {
            Notification.show("Error al cargar los servicios: " + e.getMessage());
        }
    }

    private void updateUserData(Integer userId) {
        User user = userService.findUserById(userId);
        if (user != null) {
            String userData = "Nombre: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Teléfono: " + user.getPhone();
            userDataSpan.setText(userData);
        } else {
            userDataSpan.setText("Datos del usuario no disponibles.");
        }
    }

    private void updateTotalCost(List<Servicee> services) {
        double totalCost = services.stream().mapToDouble(Servicee::getPrecio).sum();
        totalCostSpan.setText("Coste total: " + totalCost + " €");
    }
}
