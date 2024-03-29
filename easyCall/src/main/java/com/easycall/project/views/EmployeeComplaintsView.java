package com.easycall.project.views;

import com.easycall.project.complaints.Complaint;
import com.easycall.project.complaints.ComplaintService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Employee Complaints")
public class EmployeeComplaintsView extends VerticalLayout {

    private final ComplaintService complaintService;
    private Grid<Complaint> grid;
    private TextField idResponseField;
    private TextField responseTextField;
    private TextField idStatusField;
    private Button submitButton;
    private Button openButton;
    private Button closeButton;

    public EmployeeComplaintsView(ComplaintService complaintService) {
        this.complaintService = complaintService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Quejas y Reclamaciones");
        Button button = new Button("Volver a Home", event -> {
            UI.getCurrent().navigate(EmployeeMainView.class);
        });
        add(header,button);

        configureGrid();
        configureResponseForm();

        add(grid, createFormLayout());

        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(Complaint.class);
        grid.addColumn(Complaint::getIdreclamacion).setHeader("ID Reclamación");
        grid.addColumn(Complaint::getIssue).setHeader("Asunto").setAutoWidth(true);
        grid.addColumn(Complaint::getText).setHeader("Texto").setAutoWidth(true);
        grid.addColumn(Complaint::isOpen).setHeader("Abierta");
        grid.addColumn(Complaint::getRespuesta).setHeader("Respuesta").setAutoWidth(true);
        grid.setSizeFull();
    }

    private HorizontalLayout createFormLayout() {
        configureResponseForm();
        configureStatusChangeButtons();

        HorizontalLayout formLayout = new HorizontalLayout(idResponseField, responseTextField, submitButton, idStatusField, openButton, closeButton);
        formLayout.setAlignItems(Alignment.BASELINE);

        return formLayout;
    }

    private void configureResponseForm() {
        idResponseField = new TextField("ID de Queja");
        responseTextField = new TextField("Respuesta");
        submitButton = new Button("Enviar Respuesta", event -> submitResponse());
    }

    private void configureStatusChangeButtons() {
        idStatusField = new TextField("ID para Estado");
        openButton = new Button("Abrir Queja", event -> toggleComplaintStatus(true));
        closeButton = new Button("Cerrar Queja", event -> toggleComplaintStatus(false));
    }

    private void submitResponse() {
        int complaintId = Integer.parseInt(idResponseField.getValue());
        String response = responseTextField.getValue();
        complaintService.updateComplaintResponse(complaintId, response);
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
