

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
        import com.vaadin.flow.router.PageTitle;
        import com.vaadin.flow.router.Route;
        import com.vaadin.flow.server.VaadinSession;

        import java.util.List;

@Route("UserDataView")
@PageTitle("UserDataView")
public class UserDataView extends VerticalLayout {

    private final LlamadaService llamadaService;
    private Grid<Llamada> gridLlamadas;
    private TextField addNumberField;
    private Button addButton;

    public UserDataView(LlamadaService llamadaService) {
        this.llamadaService = llamadaService;
        addClassName("user-data-view");

        User currentUser = getCurrentUser();
        if (currentUser != null) {
            displayUserData(currentUser);
            addLlamadaSection(currentUser);
            initializeGrid(currentUser);
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
        add(new Span("Rol: " + user.getRol().toString()));
    }

    private void addLlamadaSection(User user) {
        addNumberField = new TextField("Número de teléfono");
        addNumberField.setPlaceholder("Ingresa un número");
        addNumberField.setPattern("[0-9]{3,15}");  // Permite solo números con longitud de 3 a 15 caracteres
        addNumberField.setErrorMessage("Número inválido");

        addButton = new Button("Añadir número", e -> addNumber(user));
        HorizontalLayout horizontalLayout = new HorizontalLayout(addNumberField, addButton);
        add(horizontalLayout);
    }

    private void addNumber(User user) {
        if (!addNumberField.isInvalid() && !addNumberField.isEmpty()) {
            try {
                llamadaService.addNumeroALlamada(user, addNumberField.getValue());
                updateGrid(user);
                Notification.show("Número añadido: " + addNumberField.getValue());
            } catch (Exception e) {
                Notification.show("Error al añadir número: " + e.getMessage());
            }
        } else {
            Notification.show("Número inválido o vacío");
        }
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
}
