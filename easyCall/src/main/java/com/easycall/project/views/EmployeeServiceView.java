package com.easycall.project.views;

import com.easycall.project.service.Servicee;
import com.easycall.project.service.ServiceService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Employee Services")
public class EmployeeServiceView extends VerticalLayout {

    private final ServiceService serviceService;
    private Grid<Servicee> grid;
    private TextField serviceIdField;
    private TextField nombreField;
    private TextField precioField;
    private TextField descripcionField;
    private Button updateButton;

    public EmployeeServiceView(@Autowired ServiceService serviceService) {
        this.serviceService = serviceService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Servicios Disponibles");
        add(header);

        configureGrid();
        add(grid, createFormLayout());

        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(Servicee.class);
        grid.setColumns("serviceId", "nombre", "precio", "descripcion");
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(event -> fillForm(event.getValue()));
    }

    private HorizontalLayout createFormLayout() {
        serviceIdField = new TextField("ID del Servicio");


        nombreField = new TextField("Nombre");
        precioField = new TextField("Precio");
        descripcionField = new TextField("Descripción");

        updateButton = new Button("Actualizar Servicio", event -> updateService());

        HorizontalLayout formLayout = new HorizontalLayout(serviceIdField, nombreField, precioField, descripcionField, updateButton);
        formLayout.setAlignItems(Alignment.BASELINE);

        return formLayout;
    }

    private void updateService() {
        Long serviceId = Long.parseLong(serviceIdField.getValue());
        String nombre = nombreField.getValue();
        int precio = Integer.parseInt(precioField.getValue());
        String descripcion = descripcionField.getValue();

        Servicee serviceToUpdate = new Servicee();
        serviceToUpdate.setServiceId(serviceId);
        serviceToUpdate.setNombre(nombre);
        serviceToUpdate.setPrecio(precio);
        serviceToUpdate.setDescripcion(descripcion);

        serviceService.updateService(serviceToUpdate);

        updateList();
        clearForm();
    }

    private void fillForm(Servicee service) {
        if (service != null) {
            serviceIdField.setValue(String.valueOf(service.getServiceId()));
            nombreField.setValue(service.getNombre());
            precioField.setValue(String.valueOf(service.getPrecio()));
            descripcionField.setValue(service.getDescripcion());
        }
    }

    private void clearForm() {
        serviceIdField.clear();
        nombreField.clear();
        precioField.clear();
        descripcionField.clear();
    }

    private void updateList() {
        grid.setItems(serviceService.findAllServices());
    }
}
