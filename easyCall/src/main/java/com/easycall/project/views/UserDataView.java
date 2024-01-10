package com.easycall.project.views;

import com.easycall.project.data.user.User;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("UserDataView")
@PageTitle("UserDataView")
public class UserDataView extends VerticalLayout {

    public UserDataView() {
        addClassName("user-data-view");

        // Obtener usuario actual
        User currentUser = getCurrentUser();

        // Si el usuario actual existe, mostrar datos
        if (currentUser != null) {
            displayUserData(currentUser);
        } else {
            add(new Span("Usuario no encontrado o sesión no iniciada."));
        }
    }

    private User getCurrentUser() {
        // Retorna el objeto User directamente desde la sesión de Vaadin
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
        add(new Span("Rol: " + user.getRol().toString()));

        // Aquí puedes agregar más componentes según sea necesario
    }
}
