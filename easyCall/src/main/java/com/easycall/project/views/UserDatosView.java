package com.easycall.project.views;

import com.easycall.project.data.user.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.UI;
import com.easycall.project.usoDatos.UsoDatosService;

@Route("UserDatosView")
public class UserDatosView extends VerticalLayout {

    private final UsoDatosService usoDatosService;
    private TextField consumoDatosHoyField;
    private TextField consumoTotalMesField;

    public UserDatosView(UsoDatosService usoDatosService) {
        this.usoDatosService = usoDatosService;
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            displayUserData(currentUser);
            setupUI(currentUser);
        } else {
            add(new Span("Usuario no encontrado o sesión no iniciada."));
        }
    }

    private User getCurrentUser() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }

    private void displayUserData(User user) {
        // Componentes para mostrar los datos del usuario
        add(new Span("Nombre: " + user.getFirstName() + " " + user.getLastName()));
        add(new Span("Nombre de usuario: " + user.getUsername()));
        add(new Span("Correo electrónico: " + user.getEmail()));
        add(new Span("Dirección: " + user.getAddress()));
        add(new Span("Teléfono: " + user.getPhone()));
        add(new Span("Fecha de nacimiento: " + user.getDateOfBirth().toString()));
    }

    private void setupUI(User currentUser) {
        consumoDatosHoyField = new TextField("Consumo de Datos Hoy (MB)");
        consumoTotalMesField = new TextField("Consumo Total del Mes (MB)");

        actualizarConsumoDatos(currentUser);

        consumoDatosHoyField.setReadOnly(true);
        consumoTotalMesField.setReadOnly(true);

        // Botones para navegación
        Button callsBreakdownButton = new Button("Ver Desglose Llamadas");
        callsBreakdownButton.addClickListener(e ->
                UI.getCurrent().navigate("UserCallsDesglose"));

        Button messagesBreakdownButton = new Button("Ver Desglose De Mensajes Y Mensajear");
        messagesBreakdownButton.addClickListener(e ->
                UI.getCurrent().navigate("UserSmsView"));

        add(consumoDatosHoyField, consumoTotalMesField, callsBreakdownButton, messagesBreakdownButton);
    }

    private void actualizarConsumoDatos(User user) {
        int consumoHoy = usoDatosService.obtenerConsumoDatosHoy(user);
        consumoDatosHoyField.setValue(consumoHoy + " MB");

        int consumoMes = usoDatosService.obtenerConsumoDatosMes(user);
        consumoTotalMesField.setValue(consumoMes + " MB");
    }
}
