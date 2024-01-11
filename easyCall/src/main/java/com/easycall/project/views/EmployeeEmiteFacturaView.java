package com.easycall.project.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.easycall.project.data.user.User;
import com.easycall.project.data.user.UserService;
import com.easycall.project.service.Servicee;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


@PageTitle("Emisión de Factura")
public class EmployeeEmiteFacturaView extends VerticalLayout {

    private final UserService userService;
    private Grid<Servicee> userServicesGrid;
    private Button loadUserServicesButton;
    private Button exportButton;
    private Span fiscalDataSpan;
    private Span userDataSpan;
    private Span totalCostSpan;
    private IntegerField userIdTextField;


    public EmployeeEmiteFacturaView(@Autowired UserService userService) {
        this.userService = userService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Emisión de Facturas");
        configureFiscalData();
        configureUserData();
        configureUserIdTextField();
        configureUserServicesGrid();
        configureForm();
        configureTotalCost();
        configureExportButton();

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setWidthFull();
        headerLayout.add(fiscalDataSpan, userDataSpan);
        headerLayout.setFlexGrow(1, fiscalDataSpan, userDataSpan);
        fiscalDataSpan.getStyle().set("margin-right", "auto");
        Button button = new Button("Volver a Home", event -> {
            UI.getCurrent().navigate(EmployeeMainView.class);
        });
        add(header, headerLayout,button, userIdTextField, loadUserServicesButton, userServicesGrid, totalCostSpan, exportButton);
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

    private void configureUserIdTextField() {
        userIdTextField = new IntegerField("ID de Usuario");
        userIdTextField.setClearButtonVisible(true);
        userIdTextField.setMin(0);
    }

    private void configureUserServicesGrid() {
        userServicesGrid = new Grid<>(Servicee.class);
        userServicesGrid.setColumns("nombre", "precio", "descripcion");
        userServicesGrid.setSizeFull();
    }

    private void configureForm() {
        loadUserServicesButton = new Button("Cargar Servicios", event -> loadUserServices());
    }

    private void configureTotalCost() {
        totalCostSpan = new Span();
    }

    private void configureExportButton() {
        exportButton = new Button("Exportar Factura", event -> exportFactura());
    }

    private void loadUserServices() {
        Integer userId = userIdTextField.getValue();
        if (userId == null) {
            Notification.show("ID de usuario no proporcionado.");
            return;
        }

        User user = userService.findUserById(userId);
        if (user == null) {
            Notification.show("Usuario no encontrado.");
            return;
        }

        List<Servicee> userServices = userService.getUserServices(user.getId());
        userServicesGrid.setItems(userServices);
        updateUserData(user);
        updateTotalCost(userServices);
    }

    private void updateUserData(User user) {
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

    private void exportFactura() {
        Integer userId = userIdTextField.getValue();
        if (userId == null) {
            Notification.show("ID de usuario no proporcionado.");
            return;
        }

        User user = userService.findUserById(userId);
        if (user == null) {
            Notification.show("Usuario no encontrado.");
            return;
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(baos, StandardCharsets.UTF_8));


            writer.write(fiscalDataSpan.getText());
            writer.newLine();


            writer.write("Nombre: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                    "Email: " + user.getEmail() + "\n" +
                    "Teléfono: " + user.getPhone());
            writer.newLine();


            List<Servicee> userServices = userService.getUserServices(user.getId());
            for (Servicee service : userServices) {
                writer.write(service.getNombre() + " - Precio: " + service.getPrecio() + "€ - Descripción: " + service.getDescripcion());
                writer.newLine();
            }

            double totalCost = userServices.stream().mapToDouble(Servicee::getPrecio).sum();
            writer.write("Coste total: " + totalCost + " €");
            writer.newLine();

            writer.flush();
            writer.close();


            StreamResource sr = new StreamResource("Factura_" + user.getId() + ".txt", () -> new ByteArrayInputStream(baos.toByteArray()));
            sr.setContentType("text/plain");
            sr.setCacheTime(0);


            Anchor downloadLink = new Anchor(sr, "Descargar Factura");
            downloadLink.getElement().setAttribute("download", true);
            downloadLink.getStyle().set("margin-top", "10px");


            add(downloadLink);


            UI.getCurrent().getPage().executeJs("setTimeout(function() {$0.click();}, 0);", downloadLink.getElement());

            Notification.show("Factura preparada para la descarga.");
        } catch (IOException e) {
            Notification.show("Error al exportar la factura: " + e.getMessage());
        }
    }

}
