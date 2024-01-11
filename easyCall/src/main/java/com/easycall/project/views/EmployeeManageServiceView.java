package com.easycall.project.views;

import com.easycall.project.service.Servicee;
import com.easycall.project.service.ServiceService;
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

@PageTitle("Manage Services")
public class EmployeeManageServiceView extends VerticalLayout {

    private final ServiceService serviceService;
    private Grid<Servicee> grid;
    private TextField nombreField;
    private TextField precioField;
    private TextField descripcionField;
    private TextField deleteServiceIdField;
    private Button createButton;
    private Button deleteButton;

    public EmployeeManageServiceView(@Autowired ServiceService serviceService) {
        this.serviceService = serviceService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Gestión de Servicios");
        add(header);
        Button button = new Button("Volver a Home", event -> {
            UI.getCurrent().navigate(EmployeeMainView.class);
        });
        configureGrid();
        add(button,grid, createFormLayout());

        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(Servicee.class);
        grid.setColumns("serviceId", "nombre", "precio", "descripcion");
        grid.setSizeFull();
    }

    private HorizontalLayout createFormLayout() {
        nombreField = new TextField("Nombre");
        precioField = new TextField("Precio");
        descripcionField = new TextField("Descripción");
        deleteServiceIdField = new TextField("ID del Servicio a Eliminar");

        createButton = new Button("Crear Servicio", event -> createService());
        deleteButton = new Button("Eliminar Servicio", event -> deleteService());

        HorizontalLayout formLayout = new HorizontalLayout(nombreField, precioField, descripcionField, createButton, deleteServiceIdField, deleteButton);
        formLayout.setAlignItems(Alignment.BASELINE);

        return formLayout;
    }

    private void createService() {
        try {
            Servicee newService = new Servicee();
            newService.setNombre(nombreField.getValue());
            newService.setPrecio(Integer.parseInt(precioField.getValue()));
            newService.setDescripcion(descripcionField.getValue());

            serviceService.saveService(newService);
            updateList();
            clearForm();
            Notification.show("Servicio creado con éxito.");
        } catch (NumberFormatException e) {
            Notification.show("Error: El precio debe ser un número.");
        } catch (Exception e) {
            Notification.show("Error al crear el servicio: " + e.getMessage());
        }
    }

    private void deleteService() {
        try {
            Long serviceId = Long.parseLong(deleteServiceIdField.getValue());
            serviceService.deleteService(serviceId);
            updateList();
            clearForm();
            Notification.show("Servicio eliminado con éxito.");
        } catch (NumberFormatException e) {
            Notification.show("Error: El ID del servicio debe ser un número.");
        } catch (Exception e) {
            Notification.show("Error al eliminar el servicio: " + e.getMessage());
        }
    }

    private void clearForm() {
        nombreField.clear();
        precioField.clear();
        descripcionField.clear();
        deleteServiceIdField.clear();
    }

    private void updateList() {
        grid.setItems(serviceService.findAllServices());
    }
}
