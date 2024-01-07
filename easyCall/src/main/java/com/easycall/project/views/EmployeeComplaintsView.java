package com.easycall.project.views;

import com.easycall.project.complaints.Complaint;
import com.easycall.project.complaints.ComplaintService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Employee Complaints")
public class EmployeeComplaintsView extends VerticalLayout {

    private final ComplaintService complaintService;
    private Grid<Complaint> grid;
    private TextField idResponseField;
    private TextField idStatusField;
    private Button submitButton;
    private Button openButton;
    private Button closeButton;

    public EmployeeComplaintsView(ComplaintService complaintService) {
        this.complaintService = complaintService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Solicitudes de los Clientes");
        add(header);

        configureGrid();
        configureResponseForm();

        add(grid, createFormLayout());

        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(Complaint.class);
        grid.setColumns("idreclamacion", "issue", "text", "open", "respuesta");
        grid.setSizeFull();
    }

    private HorizontalLayout createFormLayout() {
        configureResponseForm();
        configureStatusChangeButtons();

        HorizontalLayout formLayout = new HorizontalLayout(idResponseField, submitButton, idStatusField, openButton, closeButton);
        formLayout.setAlignItems(Alignment.BASELINE);

        return formLayout;
    }

    private void configureResponseForm() {
        idResponseField = new TextField("ID de Queja");
        submitButton = new Button("Enviar Respuesta", event -> submitResponse());
    }

    private void configureStatusChangeButtons() {
        idStatusField = new TextField("ID para Estado");
        openButton = new Button("Abrir Queja", event -> toggleComplaintStatus(true));
        closeButton = new Button("Cerrar Queja", event -> toggleComplaintStatus(false));
    }

    private void submitResponse() {
        int complaintId = Integer.parseInt(idResponseField.getValue());
        complaintService.updateComplaintResponse(complaintId, "Tu respuesta aquí");
        updateList();
    }

    private void toggleComplaintStatus(boolean open) {
        int complaintId = Integer.parseInt(idStatusField.getValue());
        complaintService.toggleComplaintOpenStatus(complaintId, open);
        updateList();
    }

    private void updateList() {
        grid.setItems(complaintService.findAllComplaints());
    }
}
