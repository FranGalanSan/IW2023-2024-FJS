package com.easycall.project.views;

import com.easycall.project.data.user.User;
import com.easycall.project.llamadas.Llamada;
import com.easycall.project.llamadas.LlamadaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.UI;

import java.util.List;

@Route("UserCallsDesglose")
public class UserCallsDesglose extends VerticalLayout {

    private final LlamadaService llamadaService;
    private Grid<Llamada> gridLlamadas;
    private TextField addNumberField;
    private Button addButton;
    private Span llamadasEsteMes;

    public UserCallsDesglose(LlamadaService llamadaService) {
        this.llamadaService = llamadaService;
        User currentUser = getCurrentUser();

        Button backButton = new Button("Volver a Los Datos De Usuario", e ->
                UI.getCurrent().navigate("UserDatosView"));
        add(backButton);

        if (currentUser != null) {
            initializeGrid(currentUser);
            addLlamadaSection(currentUser);
            addLlamadasEsteMes(currentUser);
        } else {
            add(new Span("Usuario no encontrado o sesión no iniciada."));
        }
    }

    private User getCurrentUser() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }

    private void addLlamadaSection(User user) {
        addNumberField = new TextField("Número de teléfono");
        addNumberField.setPlaceholder("Ingresa un número");
        addNumberField.setPattern("[0-9]{3,15}");
        addNumberField.setErrorMessage("Número inválido");

        addButton = new Button("Añadir número", e -> addNumber(user));
        HorizontalLayout horizontalLayout = new HorizontalLayout(addNumberField, addButton);
        add(horizontalLayout);
    }

    private void initializeGrid(User user) {
        gridLlamadas = new Grid<>(Llamada.class, false);
        gridLlamadas.addColumn(llamada -> String.join(", ", llamada.getNumeros()))
                .setHeader("Números de Llamadas");
        add(gridLlamadas);
        updateGrid(user);
    }

    private void updateGrid(User user) {
        List<Llamada> llamadas = llamadaService.getLlamadasPorUsuario(user);
        gridLlamadas.setItems(llamadas);
    }

    private void addLlamadasEsteMes(User user) {
        llamadasEsteMes = new Span("Llamadas este mes: " + llamadaService.contarLlamadasMesActual(user));
        add(llamadasEsteMes);
    }

    private void addNumber(User user) {
        if (!addNumberField.isInvalid() && !addNumberField.isEmpty()) {
            try {
                llamadaService.addNumeroALlamada(user, addNumberField.getValue());
                updateGrid(user);
                updateLlamadasEsteMes(user); // Actualiza el contador de llamadas
                Notification.show("Número añadido: " + addNumberField.getValue());
            } catch (Exception e) {
                Notification.show("Error al añadir número: " + e.getMessage());
            }
        } else {
            Notification.show("Número inválido o vacío");
        }
    }

    private void updateLlamadasEsteMes(User user) {
        int totalLlamadas = llamadaService.contarLlamadasMesActual(user);
        llamadasEsteMes.setText("Llamadas este mes: " + totalLlamadas);
    }
}
