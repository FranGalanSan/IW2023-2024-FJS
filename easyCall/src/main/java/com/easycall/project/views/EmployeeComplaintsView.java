package com.easycall.project.views;

import com.easycall.project.complaints.Complaint;
import com.easycall.project.complaints.ComplaintService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Employee Complaints")
public class EmployeeComplaintsView extends VerticalLayout {

    private final ComplaintService complaintService;
    private Grid<Complaint> grid;
    private TextField idField;
    private TextArea responseField;
    private Button submitButton;

    public EmployeeComplaintsView(ComplaintService complaintService) {
        this.complaintService = complaintService;

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H1 header = new H1("Quejas de los Empleados");
        add(header);

        configureGrid();
        configureResponseForm();

        add(grid, idField, responseField, submitButton);

        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(Complaint.class);
        grid.setColumns("idreclamacion", "issue", "text", "open", "respuesta");
        grid.setSizeFull();
    }

    private void configureResponseForm() {
        idField = new TextField("ID de Queja");
        responseField = new TextArea("Respuesta");
        submitButton = new Button("Enviar Respuesta", event -> submitResponse());
    }

    private void submitResponse() {
        int complaintId = Integer.parseInt(idField.getValue());
        String response = responseField.getValue();
        complaintService.updateComplaintResponse(complaintId, response);
        updateList();
    }

    private void updateList() {
        grid.setItems(complaintService.findAllComplaints());
    }
}
