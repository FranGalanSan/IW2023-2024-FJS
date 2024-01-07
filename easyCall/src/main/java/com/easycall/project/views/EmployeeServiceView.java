package com.easycall.project.views;


import com.easycall.project.service.Service;
import com.easycall.project.service.ServiceService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Employee Services")
public class EmployeeServiceView extends VerticalLayout {

    private final ServiceService serviceService;
    private Grid<Service> grid;


    public EmployeeServiceView(@Autowired ServiceService serviceService) {
        this.serviceService = serviceService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Servicios Disponibles");
        add(header);

        configureGrid();
        add(grid);

        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(Service.class);
        grid.setColumns("serviceId", "nombre", "precio", "descripcion");
        grid.setSizeFull();
    }

    private void updateList() {
        grid.setItems(serviceService.findAllServices());
    }
}
