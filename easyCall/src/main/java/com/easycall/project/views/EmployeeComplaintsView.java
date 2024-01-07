package com.easycall.project.views;

import com.easycall.project.complaints.Complaint;
import com.easycall.project.complaints.ComplaintService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Employee Complaints")
public class EmployeeComplaintsView extends VerticalLayout {

    private final ComplaintService complaintService;
    private Grid<Complaint> grid;

    public EmployeeComplaintsView(ComplaintService complaintService) {
        this.complaintService = complaintService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Quejas de los Empleados");
        add(header);

        configureGrid();
        add(grid);

        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(Complaint.class);
        grid.setColumns("idreclamacion", "issue", "text", "open", "respuesta");
        grid.setSizeFull();
    }

    private void updateList() {
        grid.setItems(complaintService.findAllComplaints());
    }
}
