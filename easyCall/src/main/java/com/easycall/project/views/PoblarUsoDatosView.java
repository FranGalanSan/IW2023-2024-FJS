package com.easycall.project.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.easycall.project.data.user.User;
import com.easycall.project.usoDatos.UsoDatosService;

@Route("PoblarUsoDatosView")
public class PoblarUsoDatosView extends VerticalLayout {
    private final UsoDatosService usoDatosService;
    private Button añadirUsoDatosButton;

    public PoblarUsoDatosView(UsoDatosService usoDatosService) {
        this.usoDatosService = usoDatosService;
        setupUI();
    }

    private void setupUI() {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            añadirUsoDatosButton = new Button("Añadir Uso de Datos para " + currentUser.getUsername(),
                    e -> añadirUsoDatos(currentUser));
            add(añadirUsoDatosButton);
        } else {
            add(new Span("Usuario no encontrado o sesión no iniciada."));
        }
    }

    private User getCurrentUser() {
        return VaadinSession.getCurrent().getAttribute(User.class);
    }

    private void añadirUsoDatos(User user) {
        usoDatosService.añadirUsoDatosAUsuario(user.getId());
        Notification.show("Uso de datos añadido para " + user.getUsername());
    }
}
