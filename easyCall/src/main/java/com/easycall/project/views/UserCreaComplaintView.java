package com.easycall.project.views;

import com.easycall.project.complaints.Complaint;
import com.easycall.project.complaints.ComplaintService;
import com.easycall.project.data.user.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "UserCreaComplaint")
@PageTitle("Crear Queja o Reclamación")
public class UserCreaComplaintView extends VerticalLayout {

    private final ComplaintService complaintService;

    private TextField issueField;
    private TextArea complaintTextField;
    private Button submitButton;

    public UserCreaComplaintView(ComplaintService complaintService) {
        this.complaintService = complaintService;

        // Configuración del layout
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        H1 header = new H1("Crear Queja o Reclamación");
        issueField = new TextField("Asunto");
        complaintTextField = new TextArea("Descripción de la Queja");
        submitButton = new Button("Enviar Queja", event -> createComplaint());

        // Ajustar el tamaño de los campos de texto
        issueField.setWidth("100%");
        complaintTextField.setWidth("100%");
        complaintTextField.setHeight("200px");

        add(header, issueField, complaintTextField, submitButton);
    }

    private void createComplaint() {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            Notification.show("Usuario no autenticado.");
            return;
        }

        Complaint complaint = new Complaint();
        complaint.setIssue(issueField.getValue());
        complaint.setText(complaintTextField.getValue());
        complaint.setOpen(true);
        complaint.setUser(currentUser); // Asignar el usuario actual a la queja

        try {
            complaintService.createComplaint(complaint); // Asegúrate de tener este método en tu ComplaintService
            Notification.show("Queja enviada con éxito.");
        } catch (Exception e) {
            Notification.show("Error al enviar la queja: " + e.getMessage());
        }
    }

    private User getCurrentUser() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }
}
